FROM openjdk:21
COPY src /app/src
COPY pom.xml /app
COPY resources /resources
COPY target/jira-1.0.jar /app/target/jira-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/app/target/jira-1.0.jar"]