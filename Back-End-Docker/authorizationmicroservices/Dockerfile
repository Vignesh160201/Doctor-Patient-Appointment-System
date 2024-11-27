# define base docker image

#FROM openjdk:17
#LABEL maintainer="javaguides.net"
#ADD doctormicroservices-0.0.1-SNAPSHOT.jar  doctormicroservices.jar 
#ENTRYPOINT ["java", "-jar", "doctormicroservices.jar"]

FROM openjdk:17
COPY authorizationmicroservice.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "authorizationmicroservice.jar"]