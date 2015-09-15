#!/bin/sh

NAME=$1
shift
JOIN_IP=
CLIENT=

while getopts "cj:" opt; do
    case "$opt" in
        c)
            CLIENT=yes
            ;;
        j)
            JOIN_IP=$OPTARG
            ;;
        *)
            ;;
    esac
done

source $HOME/.docker-$NAME.rc
if [ "" != "$JOIN_IP" ]; then
    if [ "" != "$CLIENT" ]; then
        docker-machine ssh $NAME -- /opt/utils/bin/runConsul.sh -c -j $JOIN_IP
    else
	docker-machine ssh $NAME -- /opt/utils/bin/runConsul.sh -j $JOIN_IP
    fi
else
    docker-machine ssh $NAME -- /opt/utils/bin/runConsul.sh
fi
docker-machine ssh $NAME /opt/utils/bin/runRegistrator.sh
