#!/bin/bash

CONTAINER_NAME='database'

docker build -t "$CONTAINER_NAME" .
docker run -p 0.0.0.0:6432:5432 -ti "$CONTAINER_NAME"