@echo off
REM Check if a parameter is provided
if "%1"=="" (
    echo Usage: %0 image_name
    exit /b 1
)

REM Set the image name from the parameter
set IMAGE_NAME=%1

REM Build the Docker image
docker build -t localhost:5000/%IMAGE_NAME%:latest .

REM Push the Docker image to the repository
docker push localhost:5000/%IMAGE_NAME%:latest
