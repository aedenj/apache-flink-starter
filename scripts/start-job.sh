#!/bin/bash

docker-compose -f docker/flink-job-cluster.yml down;
docker-compose -f docker/flink-job-cluster.yml up -d;
