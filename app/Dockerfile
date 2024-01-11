FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY run.sh .
RUN chmod +x run.sh

EXPOSE 8080

CMD ["./run.sh"]