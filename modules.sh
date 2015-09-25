#!/bin/sh

MODULES=(config-module service-module http-module discovery-module monitor-module http-client)
for f in ${MODULES[@]}; do
    echo $f;
    pushd $f;
    ./gradlew $*
    popd;
done
