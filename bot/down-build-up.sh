#!/bin/sh

./gradlew build
docker-compose down
docker-compose up -d --build