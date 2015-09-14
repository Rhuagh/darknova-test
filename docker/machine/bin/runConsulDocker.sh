#!/bin/sh

source /etc/profile.d/docker-env.sh

$(docker run --rm progrium/consul cmd:run $PUBLIC_IP -d)
