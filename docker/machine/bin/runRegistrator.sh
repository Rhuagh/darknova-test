#!/bin/sh

source /etc/profile.d/docker-env.sh
docker run -d --name=registrator --net=host --volume=/var/run/docker.sock:/tmp/docker.sock gliderlabs/registrator:latest --ip $PUBLIC_IP consul://$PUBLIC_IP:8500
