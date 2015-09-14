#!/bin/sh

NAME=$1

echo "eval \"\$(docker-machine env $NAME)\"" > ~/.docker-$NAME.rc
echo "export DOCKER_HOST_IP=\$(docker-machine ip $NAME)" >> ~/.docker-$NAME.rc
echo "source \$HOME/.docker-ce-base.rc" >> ~/.docker-$NAME.rc
