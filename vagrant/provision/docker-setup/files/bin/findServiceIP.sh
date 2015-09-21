#!/usr/bin/env bash

SERVER_IP=$1
SERVICE=$2

curl "http://$SERVER_IP:8500/v1/catalog/service/$SERVICE?pretty" | grep "\"Address" | cut -d':' -f2 | sed 's/[", ]//g' | head -1
