#!/usr/bin/env bash

IFACE=$1
PUBLIC_IP="$(ifconfig $IFACE | awk -F '\\s*:' '/inet addr/{print $2}' | cut -d' ' -f1)"

echo $PUBLIC_IP
