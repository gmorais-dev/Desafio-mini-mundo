# Etapa de build com Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar Maven Wrapper e pom
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Baixar dependências
RUN ./mvnw dependency:go-offline -B

# Copiar código e recursos
COPY src ./src

# Build do JAR
RUN ./mvnw clean package -DskipTests

# Etapa final: JDK e JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/desafio-0.0.1-SNAPSHOT.jar app.jar

# Porta da aplicação
EXPOSE 8080

# Profile Docker
ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java","-jar","app.jar"]
