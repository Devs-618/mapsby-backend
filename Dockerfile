FROM openjdk:11-bullseye
WORKDIR /app
ENV KEYCLOAK_HOST=localhost:8083
ENV SERVER_PORT=8081
ENV KEYCLOAK_CLIENT_ID=maps-by-server-side
ENV KEYCLOAK_SECRET=5e5PEgFB7ffdz6dIEqjK4vBNbR0DytHq
ENV KEYCLOAK_SCOPE=openid
COPY src/main/resources/application-prod.yml /app/application-prod.yml
COPY target/mapsby-backend-1.0-SNAPSHOT.jar /app/mapsby-backend-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/mapsby-backend-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod"]
EXPOSE 8081
