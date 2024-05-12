FROM openjdk:17-alpine
ENV PORT 8080
EXPOSE 8080
COPY ./target/*.jar myapp.jar
CMD ["java", "-jar", "myapp.jar"]