#!/bin/sh

NAME=$1

docker-machine ssh $NAME -- mkdir -p /opt/utils/
docker-machine scp -r $HOME/machine/bin $NAME:/opt/utils/
docker-machine scp -r $HOME/machine/profile.d $NAME:/opt/utils/
docker-machine ssh $NAME -- sudo ln -s /opt/utils/profile.d/docker-env.sh /etc/profile.d/
