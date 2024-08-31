#!/bin/bash

# Check if a parameter is provided
if [ -z "$1" ]; then
    echo "Usage: $0 image_name"
    exit 1
fi

# Set the image name from the parameter
IMAGE_NAME=$1

# Build the Docker image
docker build -t localhost:5000/$IMAGE_NAME:latest .

# Push the Docker image to the repository
docker push localhost:5000/$IMAGE_NAME:latest
