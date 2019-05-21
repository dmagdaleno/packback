FROM openjdk:13-jdk-alpine
MAINTAINER BOOMERANG
VOLUME /tmp
COPY packback.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]