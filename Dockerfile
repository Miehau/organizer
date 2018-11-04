FROM openjdk:8-jdk-alpine
CMD ls
COPY target/organizer-0.0.1-SNAPSHOT.jar /usr/src/organizer-0.0.1.jar
CMD java -jar usr/src/organizer-0.0.1.jar
EXPOSE 8080:8080