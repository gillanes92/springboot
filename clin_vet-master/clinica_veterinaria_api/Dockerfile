FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/clinica_veterinaria_api-0.0.1.jar
COPY ${JAR_FILE} app_veterinaria_api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_veterinaria_api.jar", "--server.port=8081"]