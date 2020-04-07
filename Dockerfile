FROM adoptopenjdk/openjdk11:debian-slim
WORKDIR /opt
RUN mkdir /app
ADD build/libs/orhanizer-0.0.1-SNAPSHOT.jar /opt/app/organizer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/organizer.jar"]
