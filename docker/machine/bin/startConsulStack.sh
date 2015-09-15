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

if [ "" != "$JOIN_IP" ]; then
    if [ "" != "$CLIENT" ]; then
        /opt/utils/bin/runConsul.sh -c -j $JOIN_IP
    else
	/opt/utils/bin/runConsul.sh -j $JOIN_IP
    fi
else
    /opt/utils/bin/runConsul.sh
fi
/opt/utils/bin/runRegistrator.sh
