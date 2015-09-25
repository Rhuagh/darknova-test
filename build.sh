#!/bin/sh

MODULES=(config-module service-module http-module discovery-module monitor-module http-client)
SERVICES=(darknova-service-template)
for f in ${MODULES[@]}; do
    echo $f;
    pushd $f;
    ./gradlew install
    popd;
done
for f in ${SERVICES[@]}; do
    echo $f;
    pushd $f;
    ./gradlew install distDocker
    popd;
done
