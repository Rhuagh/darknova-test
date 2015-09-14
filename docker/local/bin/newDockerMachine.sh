#!/bin/sh

NAME=$1

docker-machine create --driver virtualbox $NAME

if [[ ! -f $HOME/.docker-$NAME.rc ]]; then
    newDockerMachineScript.sh $NAME
fi
provisionDockerMachine.sh $NAME
