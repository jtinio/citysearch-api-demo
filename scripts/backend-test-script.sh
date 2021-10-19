#!/bin/sh

cd ./backend
docker-compose -f ./Docker-dev/docker-compose.dev.yml up -d
./mvnw clean package
docker-compose -f ./Docker-dev/docker-compose.dev.yml down
