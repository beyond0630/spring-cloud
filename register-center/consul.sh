#!/usr/bin/env bash
## 10.0.0.121
docker run --net=host \
  --name consul \
  --restart always \
  -v /data/consul/data:/consul/data \
  -v /data/consul/conf:/consul/config \
  -d docker.io/consul consul agent -server \
  -bind=10.0.0.121  -client=0.0.0.0 \
  -bootstrap-expect=3  -node=server:121 \
  -data-dir /consul/data \
  -config-dir /consul/config \
  -join 10.0.0.121 \
  -ui

## 10.0.0.122
docker run --net=host \
  --name consul \
  --restart always \
  -v /data/consul/data:/consul/data \
  -v /data/consul/conf:/consul/config \
  -d docker.io/consul consul agent -server \
  -bind=10.0.0.122  -client=0.0.0.0 \
  -bootstrap-expect=3  -node=server:122 \
  -data-dir /consul/data \
  -config-dir /consul/config \
  -join 10.0.0.121

## 10.0.0.123
docker run --net=host \
  --name consul \
  --restart always \
  -v /data/consul/data:/consul/data \
  -v /data/consul/conf:/consul/config \
  -d docker.io/consul consul agent -server \
  -bind=10.0.0.123  -client=0.0.0.0 \
  -bootstrap-expect=3  -node=server:123 \
  -data-dir /consul/data \
  -config-dir /consul/config \
  -join 10.0.0.121