#!/bin/sh

NAME=$1

echo "eval \"\$(docker-machine env $NAME)\"" > ~/.docker-$NAME.rc
$DOCKER_HOST_IP=$(docker-machine ip $NAME)
echo "export DOCKER_HOST_IP=\$(docker-machine ip $NAME)" >> ~/.docker-$NAME.rc
