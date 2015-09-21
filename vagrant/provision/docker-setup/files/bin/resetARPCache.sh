#!/usr/bin/env bash

docker run --net=host --privileged --rm cap10morgan/conntrack -F
