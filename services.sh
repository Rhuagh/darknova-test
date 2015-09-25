#!/bin/sh

SERVICES=(darknova-service-template)
for f in ${SERVICES[@]}; do
    echo $f;
    pushd $f;
    ./gradlew $*
    popd;
done
