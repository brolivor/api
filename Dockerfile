FROM openjdk:18
ARG WAR_FILE=target/*.jar
COPY ${WAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]