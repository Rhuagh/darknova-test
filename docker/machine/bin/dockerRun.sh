#!/bin/sh

source /etc/profile.d/docker-env.sh
docker run --dns $BRIDGE_IP --dns 8.8.8.8 --dns-search service.consul $*
