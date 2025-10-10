# Dockerfile (multi-stage)
# 1) build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests package -DskipTests

# 2) run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]

# Etapa de runtime com JDK 21 slim
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

# Ativa o profile QA
ENV SPRING_PROFILES_ACTIVE=qa

ENTRYPOINT ["java","-jar","app.jar"]

