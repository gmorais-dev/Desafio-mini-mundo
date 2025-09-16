FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar pom.xml
COPY pom.xml .

# Copiar o Maven Wrapper
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Baixar dependências offline
RUN ./mvnw dependency:go-offline -B

# Copiar todo o projeto
COPY . .

# Build da aplicação
RUN ./mvnw package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/desafio-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
