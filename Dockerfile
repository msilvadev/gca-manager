FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gca-manager-1.0.0.jar
COPY src/main/resources/logback-dev.xml /tmp/logback.xml
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dlogging.config=/tmp/logback.xml","/gca-manager-1.0.0.jar"]
