# Step 1: Build the JAR using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Create app directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# Step 2: Run the JAR
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/FullStackEventBookingSystem-0.0.1-SNAPSHOT.jar app.jar

# Define the PORT environment variable (for Render / Railway)
ENV PORT=8080

# Expose the port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
