FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/clinica_veterinaria_web-0.0.1.jar
COPY ${JAR_FILE} clinica_veterinaria_web.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "clinica_veterinaria_web.jar", "--server.port=8080"]