# define base docker image

#FROM openjdk:17
#LABEL maintainer="javaguides.net"
#ADD doctormicroservices-0.0.1-SNAPSHOT.jar  doctormicroservices.jar 
#ENTRYPOINT ["java", "-jar", "doctormicroservices.jar"]

FROM openjdk:17
COPY patientmicroservices-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "patientmicroservices-0.0.1-SNAPSHOT.jar"]