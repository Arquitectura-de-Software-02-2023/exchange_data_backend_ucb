FROM arm64v8/eclipse-temurin:17-jdk
COPY  target/*.jar app.jar
ENV DATABASE_URL="jdbc:postgresql://localhost:5432/currency_db"
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]