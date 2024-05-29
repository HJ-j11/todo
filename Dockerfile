FROM openjdk:17-jdk
ARG JAR_PATH=/build/libs
COPY ${JAR_PATH}/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]