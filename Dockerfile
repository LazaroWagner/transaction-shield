# Estágio 1: Build (Compilação com JDK 21)
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Senior Decision: Copiar o wrapper primeiro otimiza o cache de camadas do Docker
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copia o código fonte e gera o artefato final
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Estágio 2: Runtime (Execução com JRE 21 leve)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Diretriz de CyberSeg: Criar um usuário sem privilégios (Non-root)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copia apenas o JAR compilado do estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Exposição da porta da aplicação
EXPOSE 8080

# Inicia a aplicação utilizando as otimizações do Java 21
ENTRYPOINT ["java", "-jar", "app.jar"]