# define base docker image


FROM openjdk:17
COPY appointmentmicroservice-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "appointmentmicroservice-0.0.1-SNAPSHOT.jar"]

