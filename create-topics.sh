#!/bin/bash

docker run --rm -i --network kafka-net wurstmeister/kafka:latest kafka-topics.sh --delete --bootstrap-server broker-1:19092 --topic messages;
docker run --rm -i --network kafka-net wurstmeister/kafka:latest kafka-topics.sh --delete --bootstrap-server broker-1:19092 --topic enriched-messages;

docker run --rm -i --network kafka-net wurstmeister/kafka:latest kafka-topics.sh --create --bootstrap-server broker-1:19092 --partitions 9 --replication-factor 3 --topic messages;
docker run --rm -i --network kafka-net wurstmeister/kafka:latest kafka-topics.sh --create --bootstrap-server broker-1:19092 --partitions 9 --replication-factor 3 --topic enriched-messages;
