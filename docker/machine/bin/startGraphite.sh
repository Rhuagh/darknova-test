#!/bin/sh

docker run -d --name graphite -p 81:80 -p 2003:2003 -p 8125:8125/udp hopsoft/graphite-statsd
