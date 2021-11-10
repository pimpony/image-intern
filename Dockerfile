## build stage 
FROM maven:3.6.1-jdk-11-slim AS build-stage 
COPY src /workspace/src
COPY pom.xml /workspace
WORKDIR /workspace
RUN mvn clean install

# production stage
FROM openjdk:11.0-slim
EXPOSE 5000
COPY --from=build-stage  /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]