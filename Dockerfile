FROM adoptopenjdk/openjdk12:latest
RUN mkdir /opt/app
ADD build/libs/orhanizer-0.0.1-SNAPSHOT.jar /opt/app/organizer.jar
ENTRYPOINT ["java", "-jar", "/opt/app/organizer.jar"]