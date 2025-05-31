FROM alpine/java:21-jdk

COPY target/demo-spring-boot-0.0.2-dev.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]