# Use Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use lightweight JRE for runtime
FROM eclipse-temurin:21-jre-alpine

# Install PostgreSQL client for health checks
RUN apk add --no-cache postgresql-client

# Create application directory
WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Create non-root user for security
RUN addgroup -g 1001 spring && adduser -D -s /bin/sh -u 1001 -G spring spring
USER spring:spring

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD pg_isready -h db -p 5432 || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]