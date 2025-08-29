# Use a Java 17 image (or whichever version your app uses)
FROM eclipse-temurin:21-jdk-alpine


# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Build the app
RUN ./mvnw package -DskipTests

# Expose port (same as Spring Boot)
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/jobs-0.0.1-SNAPSHOT.jar"]
