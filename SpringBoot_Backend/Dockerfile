FROM openjdk:25-oracle
COPY ["./target/demo.jar", "/app/"]
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "backend-app.jar"]