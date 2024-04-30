# Use Maven to build the application
FROM maven:3.6.3-jdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Package the application in the Java runtime image
FROM openjdk:17
COPY --from=build /app/target/contactddd-*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
