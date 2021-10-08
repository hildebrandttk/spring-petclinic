FROM adoptopenjdk/openjdk11
COPY target/spring-petclinic-2.5.0-SNAPSHOT.jar /app.jar
EXPOSE 8080
#CMD ["-jar", "-Dspring.profiles.active=mysql", "/app.jar"]
CMD ["java", "-jar", "/app.jar"]
