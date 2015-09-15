#!/bin/sh

NAME=$1
shift

source $HOME/.docker-$NAME.rc
docker-machine ssh $NAME -- /opt/utils/bin/startConsulStack.sh $*
