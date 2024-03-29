#!/bin/sh

COMMAND=$1
shift
NAME=$1
shift
SCRIPT_PATH=/var/lib/docker/opt/utils/

[[ -f $HOME/.docker-$NAME.rc ]] && source $HOME/.docker-$NAME.rc

function provisionMachine() {
    MACHINE_PATH=${DOCKER_MACHINE_PATH:-$HOME/machine}
    docker-machine ssh $NAME -- mkdir -p /opt/utils/
    docker-machine ssh $NAME -- sudo mkdir -p $SCRIPT_PATH
    docker-machine scp -r $MACHINE_PATH/bin $NAME:/opt/utils/
    docker-machine scp -r $MACHINE_PATH/profile.d $NAME:/opt/utils/
    docker-machine ssh $NAME -- sudo cp -r /opt/utils/* $SCRIPT_PATH
}

function afterStart() {
    docker-machine ssh $NAME -- mkdir -p /opt/utils/
    docker-machine ssh $1 -- sudo ln -s $SCRIPT_PATH/profile.d/docker-env.sh /etc/profile.d/
    docker-machine ssh $1 -- sudo ln -s $SCRIPT_PATH/bin /opt/utils/bin
}

case "$COMMAND" in
    create)
        docker-machine create --driver virtualbox $NAME
        if [[ ! -f $HOME/.docker-$NAME.rc ]]; then
            echo "eval \"\$(docker-machine env $NAME)\"" > ~/.docker-$NAME.rc
            echo "export DOCKER_HOST_IP=\$(docker-machine ip $NAME)" >> ~/.docker-$NAME.rc
            echo "source \$HOME/.docker-ce-base.rc" >> ~/.docker-$NAME.rc
        fi
        source $HOME/.docker-$NAME.rc
        provisionMachine $NAME
        afterStart $NAME
        ;;
    provision)
        provisionMachine $NAME
        afterStart $NAME
        ;;
    restart)
        docker-machine stop $NAME
        docker-machine start $NAME
        source $HOME/.docker-$NAME.rc
        afterStart $NAME
        ;;
    start)
        docker-machine start $NAME
        source $HOME/.docker-$NAME.rc
        afterStart $NAME
        ;;
    stop)
        docker-machine stop $NAME
        ;;
    ssh)
        docker-machine ssh $NAME
        ;;
    rm)
        docker-machine rm $NAME
        ;;
    run)
        docker-machine ssh $NAME -- $SCRIPT_PATH/bin/dockerRun.sh $*
        ;;
    startConsul)
        docker-machine ssh $NAME -- $SCRIPT_PATH/bin/startConsulStack.sh $*
	;;
    *)
        echo "Unknown command: $COMMAND"
        ;;
esac

