# mapsby-backend

This is the server side application of National Geoportal of the Republic of Belarus.

# IntelliJ IDE

JetBrains provide a the IntelliJ IDE.

This IDE is recommended for excellent Maven integration, and very fast build times.

# Tools
 * Java 11
 * Spring framework (boot, mvc, logging, test, security)
 * Maven
 * Lombok
 * Keycloak
 * Swagger
 * JUnit 5
 * Log4J2

# Dependencies

All required dependencies are listed in the 'pom.xml' file.

## Setting up 
1. Get code from github with command `git clone https://github.com/Devs-618/mapsby-backend.git`
2. Open the root folder
3. *Execute Maven Goal*: `clean install`
4. Go to the `target` folder and check if exists *mapsby-backend-1.0-SNAPSHOT.jar*

## Security
1. Application security based on OAuth 2.0 protocol and JWT authentication with `keycloak` as authentication provider.
2. Before running the application setup `keycloak` and register the application as client.
3. File application-prod.yml contains a lot of security properties You can configure (for example you can specify keycloak settings, unauthorized urls, cors allowed-origins and etc.)
```html
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: ${KEYCLOAK_HOST}  # for example http://localhost:8083/realms/gn
      client:
        provider:
          keycloak:
            issuer-uri: ${KEYCLOAK_HOST}
            user-name-attribute: preferred_username
        registration:
          keycloak:
            client-id: ${KEYCLOAK_CLIENT_ID}   # client id from keycloak for example maps-by-server-side
            client-secret: ${KEYCLOAK_SECRET}  # client secret must look like Rt1SJajgFzEpIDwrxoCuBi008jc4Jomv
            scope: ${KEYCLOAK_SCOPE}   # protocol scope for example openid

 ```
   
## Setting up with Intelij Idea
1. Open project in IntelliJ, it will create an `.idea`.
2. Use *File* > *Project Structure* to confirm Java 11 is used.
3. Create *Edit Configuration* (if not exist Add new *Maven* configuration) or check build and run options (must be specified Java 11 SDK for 'mapsby-backend' module and 'by.maps.task.backend.MapsByApp' as running directory).
4. Use the *Maven* tools window to:
   * *Toggle "Skip Tests" Mode* (if You won't to test the application)
   * *Execute Maven Goal*: `clean install`
   * check the target directory (You should see there an archive named *mapsby-backend-1.0-SNAPSHOT.jar*).
     
## Running and testing
1. Run the application with command `java -jar mapsby-backend-1.0-SNAPSHOT.jar --spring.profiles.active=prod` in *command line*.
2. For testing all the endpoints possible see swagger user interface go to "<hostname>/swagger-ui/index.html#/".

❗️By default, You can see api docs in your browser as guest, but, for safety, You can close your docs, if You remove url patterns from 'security.unauthorized' config in application-prod.yml file.
 ```html
 security:
  auth_provider: keycloak  # can be keycloak or custom
  unauthorized:
    urls:
      - /swagger-ui/*      # remove or comment this pattern
      - /v3/api-docs/**    # remove or comment this pattern
 ```

## Dockerfile
1. You can create your custom image using the command  `docker build -t <image name>` .
2. If You want to change default docker properties, edit Dockerfile file located in the project folder 
3. Running the container for example `docker run --name <conatiner name> -p 8081:8081 <image name or id>`
Note: successful running the container depends on another infrastructure (working docker network, connection with db and etc.)

Hope You enjoy!
