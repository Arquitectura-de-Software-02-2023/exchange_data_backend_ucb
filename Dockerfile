# Stage 1: Build the Java application
FROM arm64v8/eclipse-temurin:17-jdk AS build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage 2: Create the final runtime image
FROM arm64v8/eclipse-temurin:17-jdk

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy files from the build stage to the final image
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


ENV PORT 8081
ENV DATABASE_USERNAME "postgres"
ENV DATABASE_PASSWORD "123456"
ENV DATABASE_URL "jdbc:postgresql://host.docker.internal:5432/currency_db"
ENV ISSUER_URI "http://host.docker.internal:8080/realms/prove"
ENV API_KEY "Tan1rLeMHLNa1kBHq37GvVukmLF7bFDY"
ENV RESOURCE_ID "backend_client"



ENTRYPOINT ["java", "-cp", "app:app/lib/*", "edu.ucb.bo.exchange_data_backend.ExchangeDataBackendApplication"]
