#!/bin/sh

NAME=$1

docker-machine create --driver virtualbox $NAME

if [[ ! -f $HOME/.docker-$NAME.rc ]]; then
    echo "eval \"\$(docker-machine env $NAME)\"" > ~/.docker-$NAME.rc
    echo "export DOCKER_HOST_IP=\$(docker-machine ip $NAME)" >> ~/.docker-$NAME.rc
    echo "source \$HOME/.docker-ce-base.rc" >> ~/.docker-$NAME.rc
fi

provisionDockerMachine.sh $NAME
