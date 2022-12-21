FROM openjdk:8
LABEL maintainer="javaguides.net"
ADD target/Suzang_Group_Back-0.0.1-SNAPSHOT.jar Suzang_Group_Back.jar
ENTRYPOINT ["java","-jar" ,"/Suzang_Group_Back.jar"]