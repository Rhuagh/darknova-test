#!/bin/sh

NAME=$1
JOIN_IP=$2

source $HOME/.docker-$NAME.rc
docker-machine ssh $NAME -- /opt/utils/bin/runConsulDocker.sh -c -j $JOIN_IP
docker-machine ssh $NAME /opt/utils/bin/runRegistrator.sh
