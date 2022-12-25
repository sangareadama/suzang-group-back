#
# Build stage
#
FROM maven:3.8.2-jdk-8 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-slim
COPY --from=build target/Suzang_Group_Back-0.0.1-SNAPSHOT.jar Suzang_Group_Back.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","Suzang_Group_Back.jar"] 
