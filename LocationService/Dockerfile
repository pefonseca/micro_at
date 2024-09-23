FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/location-service-0.0.1-SNAPSHOT.jar /app/location-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/location-service.jar"]