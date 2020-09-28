#!/bin/bash

docker-compose kill taskmanager;
docker-compose kill jobmanager;
docker-compose up -d jobmanager;
docker-compose up -d taskmanager;
