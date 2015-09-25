#!/bin/sh

NAME=$1
IP=$2
GROUP=$3

mkdir -p machines/$NAME/
cp Vagrantfile.template machines/$NAME/Vagrantfile
ln -s $PWD/provision/ machines/$NAME/
ln -s $PWD/images/ machines/$NAME/
pushd machines/$NAME
  perl -pi -e "s/{{name}}/$NAME/g" Vagrantfile
  perl -pi -e "s/{{ip}}/$IP/g" Vagrantfile
  perl -pi -e "s/{{group}}/$GROUP/g" Vagrantfile
  vagrant up --no-provision
  KEY_FILE=$(vagrant ssh-config | grep IdentityFile | cut -d' ' -f 4)
  docker-machine create --driver generic --generic-ip-address=$IP --generic-ssh-user=vagrant --generic-ssh-key=$KEY_FILE $NAME
  if [[ ! -f $HOME/.docker-$NAME.rc ]]; then
    echo "eval \"\$(docker-machine env $NAME)\"" > ~/.docker-$NAME.rc
    echo "export DOCKER_HOST_IP=\$(docker-machine ip $NAME)" >> ~/.docker-$NAME.rc
  fi
  source $HOME/.docker-$NAME.rc
  for f in `ls images/`; do
    echo "loading images/$f";
    docker load < images/$f;
  done
  vagrant provision
popd 
