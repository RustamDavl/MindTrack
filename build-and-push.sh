#!/bin/bash

set -e

# ===== CONFIG =====
REPO_NAME="mindly-app"   # можешь поменять под свой сервис
DOCKER_USERNAME="rustamdavl"
PLATFORMS="linux/amd64,linux/arm64"
BUILDER_NAME="mindly-multiarch-builder"

# ===== VERSION =====
VERSION=$1

if [ -z "$VERSION" ]; then
  VERSION="latest"
fi

IMAGE_NAME="$DOCKER_USERNAME/$REPO_NAME:$VERSION"

echo "🚀 Building multi-arch Docker image: $IMAGE_NAME"
echo "🧱 Platforms: $PLATFORMS"

# ===== BUILDX =====
if ! docker buildx inspect "$BUILDER_NAME" >/dev/null 2>&1; then
  docker buildx create --name "$BUILDER_NAME" --use
else
  docker buildx use "$BUILDER_NAME"
fi

docker buildx inspect --bootstrap >/dev/null

# ===== BUILD AND PUSH =====
echo "📤 Building and pushing image to Docker Hub: $IMAGE_NAME"
docker buildx build \
  --platform "$PLATFORMS" \
  -t "$IMAGE_NAME" \
  -f infrastructure/local/dockerfiles/local.Dockerfile \
  --push \
  .

echo "✅ Done!"
