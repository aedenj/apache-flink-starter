#!/bin/bash

docker run --rm -v "$PWD":/app -w /app --network kafka-net flink:latest flink run -d -m jobmanager:8081 ./build/libs/flink-stream-job-1.0-all.jar
