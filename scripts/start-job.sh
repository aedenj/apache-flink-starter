#!/bin/bash

docker-compose -f flink-job-cluster.yml down;
docker-compose -f flink-job-cluster.yml up -d;
