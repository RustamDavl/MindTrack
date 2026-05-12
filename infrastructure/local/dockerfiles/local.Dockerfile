FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml
COPY mindtrack-core ./mindtrack-core
COPY mindtrack-application ./mindtrack-application
COPY mindtrack-test ./mindtrack-test

RUN mvn -pl mindtrack-application -am clean package -DskipTests

FROM eclipse-temurin:25-jre AS runtime

WORKDIR /app

COPY --from=builder /build/mindtrack-application/target/*-exec.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
