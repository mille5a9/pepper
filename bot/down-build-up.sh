#!/bin/sh

./gradlew :bot:buildFatJar
docker-compose down
docker-compose up -d --build