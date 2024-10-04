# Use a base image with Java and Maven pre-installed
FROM jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory in the container
WORKDIR /pedistack-service/pedistack

# Copy the Maven project file
COPY pom.xml .

# Copy the entire project directory into the container
COPY . .

# Build the project with Maven
RUN mvn clean install -DskipTests=true

# Use AdoptOpenJDK as base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /pedistack-service/pedistack

# Copy the compiled JAR files from the build stage to the container
COPY --from=build /pedistack-service/pedistack/pedistack/target/*.jar pedistack.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run the application when the container starts
CMD ["java", "-jar", "pedistack.jar"]

