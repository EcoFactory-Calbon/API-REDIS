# ==========================
# Etapa 1: Build da aplicação
# ==========================
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o arquivo de configuração do Maven e o código-fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o .jar (sem rodar os testes)
RUN mvn -DskipTests clean package

# ==========================
# Etapa 2: Runtime
# ==========================
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia o .jar gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Configurações do Spring Boot
ENV SPRING_PROFILES_ACTIVE=qa
ENV PORT=8080

# Expõe a porta 8080 (Render detecta automaticamente)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
