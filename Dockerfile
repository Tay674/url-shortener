# Phase 1: Das Projekt mit Maven bauen
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Phase 2: Das fertige Programm starten
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/urlshortener-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]