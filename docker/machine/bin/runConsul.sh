#!/bin/sh

JOIN_IP=
CLIENT=

while getopts "cj:" opt; do
    case "$opt" in
        c)
            CLIENT=yes
            ;;
        j)
            JOIN_IP=$OPTARG
            ;;
        *)
            ;;
    esac
done

source /etc/profile.d/docker-env.sh

PARAM=$PUBLIC_IP

if [ "" != "$JOIN_IP" ]; then
    PARAM=$PARAM::$JOIN_IP
    if [ "" != "$CLIENT" ]; then
        PARAM=$PARAM::client
    fi
fi

echo "$PARAM"

$(docker run --rm progrium/consul cmd:run $PARAM -d)
