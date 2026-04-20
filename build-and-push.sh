#!/bin/bash

set -e

# ===== CONFIG =====
REPO_NAME="mindly-app"   # можешь поменять под свой сервис
DOCKER_USERNAME="rustamdavl"

# ===== VERSION =====
VERSION=$1

if [ -z "$VERSION" ]; then
  VERSION="latest"
fi

IMAGE_NAME="$DOCKER_USERNAME/$REPO_NAME:$VERSION"

echo "🚀 Building Docker image: $IMAGE_NAME"

# ===== BUILD =====
docker build -t $IMAGE_NAME -f infrastructure/local/dockerfiles/local.Dockerfile .

# ===== PUSH =====
echo "📤 Pushing image to Docker Hub: $IMAGE_NAME"
docker push $IMAGE_NAME

echo "✅ Done!"