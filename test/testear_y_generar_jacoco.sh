#!/bin/sh

# Ejecutar las pruebas
echo "Ejecutando pruebas con Maven..."
./mvnw test

# Verificar si el comando anterior fue exitoso
if [ $? -eq 0 ]; then
  echo "Las pruebas se ejecutaron correctamente. Generando reporte de Jacoco..."
  # Generar el reporte de Jacoco
  ./mvnw jacoco:report
else
  echo "Hubo un error al ejecutar las pruebas. No se generará el reporte de Jacoco."
  exit 1
fi
