# Use una imagen base que tenga Java instalado
FROM openjdk:17

# Establece el directorio de trabajo
WORKDIR /app

#copio 
COPY . /app
# Construye el archivo JAR de la aplicación
RUN ./mvnw clean install -DskipTests

# Ejecuta la aplicación Spring Boot cuando se inicia el contenedor
CMD ["java", "-jar", "/app/target/test-0.0.1-SNAPSHOT.jar"]

# CMD ["startup.sh"]