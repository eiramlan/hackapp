# Start with an official Java runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your target directory to the working directory inside the container
COPY target/hackapp-0.0.1-SNAPSHOT.jar /app/hackapp.jar

# Expose the port the app runs on (adjust if needed)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/hackapp.jar"]
