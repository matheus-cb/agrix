# ===============================
# = BUILD STAGE
# ===============================
FROM eclipse-temurin:17-jdk-jammy as build-image

WORKDIR /to-build-app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN ./mvnw clean package

# ===============================
# = RUN STAGE
# ===============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built application from the build stage
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]