#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justinphan

#Add the application's jar to the container
COPY target/accounts-service-0.0.1-SNAPSHOT.jar accounts-service-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java","-jar","/accounts-service-0.0.1-SNAPSHOT.jar"]