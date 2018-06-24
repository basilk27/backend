FROM openjdk:8-jre-alpine
COPY ./target/reactivedemo-1.0.0-SNAPSHOT.jar /usr/src/spring-boot-reactivedemo/
WORKDIR /usr/src/spring-boot-reactivedemo
CMD ["java", "-jar", "reactivedemo-1.0.0-SNAPSHOT.jar"]