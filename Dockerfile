FROM adoptopenjdk/openjdk12:latest
WORKDIR /opt
RUN mkdir /app
ADD build/libs/orhanizer-0.0.1-SNAPSHOT.jar /opt/app/organizer.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/opt/app/organizer.jar"]
