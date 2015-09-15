#!/bin/sh

MACHINE_PATH=${DOCKER_MACHINE_PATH:-$HOME/machine}
NAME=$1

docker-machine ssh $NAME -- mkdir -p /opt/utils/
docker-machine scp -r $MACHINE_PATH/bin $NAME:/opt/utils/
docker-machine scp -r $MACHINE_PATH/profile.d $NAME:/opt/utils/
docker-machine ssh $NAME -- sudo ln -s /opt/utils/profile.d/docker-env.sh /etc/profile.d/
