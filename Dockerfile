FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

COPY --from=build /app/target/GPDS-0.0.1-SNAPSHOT.jar GPDS.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GPDS.jar"]