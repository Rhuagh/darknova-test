#!/bin/sh

NAME=$1
IP=$2

mkdir -p machines/$NAME/
cp Vagrantfile.template machines/$NAME/Vagrantfile
ln -s $PWD/provision/ machines/$NAME/
pushd machines/$NAME
  perl -pi -e "s/{{name}}/$NAME/g" Vagrantfile
  perl -pi -e "s/{{ip}}/$IP/g" Vagrantfile
  vagrant up
popd 
