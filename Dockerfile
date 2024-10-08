# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from Maven target directory to the container
COPY target/hackapp-0.0.1-SNAPSHOT.jar /app/hackapp.jar

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/hackapp.jar"]
