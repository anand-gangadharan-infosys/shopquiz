#!/bin/bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker build -t="ubuntu-docker-container" .
docker run -itd --rm ubuntu-docker-container


