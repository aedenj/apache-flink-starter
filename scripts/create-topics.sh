#!/bin/bash

docker exec -i kafka-tools kafka-topics.sh --delete --bootstrap-server broker-1:19092 --topic "source";
docker exec -i kafka-tools kafka-topics.sh --delete --bootstrap-server broker-1:19092 --topic destination;

docker exec -i kafka-tools kafka-topics.sh --create --bootstrap-server broker-1:19092 --partitions 9 --replication-factor 1 --topic "source";
docker exec -i kafka-tools kafka-topics.sh --create --bootstrap-server broker-1:19092 --partitions 9 --replication-factor 1 --topic "destination";
