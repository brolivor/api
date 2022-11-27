FROM openjdk:18-jdk-alpine3.14
WORKDIR /opt/docker_images
ARG JAR_FILE=target/api-*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]