FROM openjdk:17-alpine

COPY /target/main-service-1.0-SNAPSHOT.jar /microservices/main-service.jar

ENTRYPOINT ["java", "-jar", "/microservices/main-service.jar"]