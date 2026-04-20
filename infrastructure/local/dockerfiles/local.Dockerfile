FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml
COPY mindly-core ./mindly-core
COPY mindly-application ./mindly-application

RUN mvn -pl mindly-application -am clean package -DskipTests

FROM eclipse-temurin:25-jre AS runtime

WORKDIR /app

COPY --from=builder /build/mindly-application/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
