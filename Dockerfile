FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app
COPY mvnw .
COPY mvnw.cmd .
RUN chmod +x mvnw
COPY pom.xml .
COPY src /app/src
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/meeting-calendar-0.0.1-SNAPSHOT.jar /app/meeting-calendar-0.0.1-SNAPSHOT.jar
EXPOSE 9000
CMD ["java", "-jar", "/app/meeting-calendar-0.0.1-SNAPSHOT.jar"]
