FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom e baixa as dpedencias
COPY pom.xml .
# Baixa as dpendencias offline (opcional, mas bom pra cache)
RUN mvn dependency:go-offline -B
# Copia o source e builda
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
