FROM maven:3.9.9-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY resources ./resources
RUN mvn clean package -Pprod
RUN mv ./target/*.jar ./jira.jar
ENTRYPOINT ["java", "-jar", "/app/jira.jar", "-Dspring.profiles.active=prod"]