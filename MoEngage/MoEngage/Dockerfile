# Use a minimal JDK base image
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/MoEngage-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
