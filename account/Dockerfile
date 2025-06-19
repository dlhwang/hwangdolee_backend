FROM openjdk:23-jdk
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} account.jar
ENTRYPOINT ["java", "-jar", "account.jar"]