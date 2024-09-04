#!/bin/bash

# Variables
SONAR_PROJECT_KEY="Grupo-1-1"
SONAR_HOST_URL="http://sonarjaveriana.dynaco.co/"
SONAR_TOKEN="sqp_e1d4b922152addc955c49db3130b7df50fceeeae"

# Ejecutar el comando Maven con SonarQube
./mvnw sonar:sonar \
  -Dsonar.projectKey=$SONAR_PROJECT_KEY \
  -Dsonar.host.url=$SONAR_HOST_URL \
  -Dsonar.token=$SONAR_TOKEN
