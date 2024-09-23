FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/weather-service-0.0.1-SNAPSHOT.jar /app/weather-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/weather-service.jar"]