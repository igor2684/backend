FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/backend-*-SNAPSHOT.jar /app/application.jar
COPY docker-application.yaml /app/config/application.yaml
VOLUME /app/config
ENTRYPOINT java -jar application.jar --spring.config.location=config/application.yaml