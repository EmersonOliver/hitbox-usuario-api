FROM maven:3.9.8-eclipse-temurin-21-alpine as build
WORKDIR /app
COPY src ./src
COPY pom.xml ./
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]