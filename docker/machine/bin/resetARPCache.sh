#!/bin/sh

docker run --net=host --privileged --rm cap10morgan/conntrack -F
