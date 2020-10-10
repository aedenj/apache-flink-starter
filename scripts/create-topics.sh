#!/bin/bash

for var in "$@"
  do
    docker exec -i kafka-tools kafka-topics.sh --delete --bootstrap-server broker-1:19092 --topic "$var";
    docker exec -i kafka-tools kafka-topics.sh --create --bootstrap-server broker-1:19092 --partitions 1 --replication-factor 1 --topic "$var";
  done
