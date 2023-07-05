FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dspring.config.location=src/main/resources/application.yml

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/api-security-0.0.1-SNAPSHOT.jar api-security.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-security.jar"]