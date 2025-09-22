# Use OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage Docker caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give mvnw executable permission
RUN chmod +x mvnw

# Download dependencies offline to leverage caching
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Expose port (matches server.port in application.properties)
EXPOSE 9092

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "target/CICD-0.0.1-SNAPSHOT.jar"]
