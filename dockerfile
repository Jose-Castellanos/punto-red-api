# Use a JDK to build the app
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy your build tool files and source
COPY . .

# Build the Spring Boot app (assumes you use Gradle or Maven)
# Uncomment one of the following based on your build tool:

# For Gradle:
RUN ./gradlew clean build -x test


# Final image
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copy built jar from the builder image
COPY --from=builder /app/build/libs/*.jar app.jar

# Set port to 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]



