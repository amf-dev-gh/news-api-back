FROM openjdk:21-jdk-slim 

ARG JAR_FILE=target/news-api-0.1.jar

COPY ${JAR_FILE} news-api.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "news-api.jar" ]