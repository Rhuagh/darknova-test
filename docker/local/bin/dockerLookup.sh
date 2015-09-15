#!/bin/sh

NAME=$1
shift

dockerMachineVBox.sh run $NAME --rm aanand/docker-dnsutils $*
