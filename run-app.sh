#!/bin/bash

docker build -t -p todolist-rocketseat .
docker run --name TODO_LIST -dp 127.0.0.1:8080:8080 todolist-rocketseat:latest