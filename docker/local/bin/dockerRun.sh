#!/bin/sh

NAME=$1
shift

source ~/.docker-$NAME.rc
docker-machine ssh $NAME -- /opt/utils/bin/dockerRun.sh $*
