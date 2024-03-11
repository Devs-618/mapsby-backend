package by.maps.backend.client;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KeycloakClientBuilder {
    @Value("${keycloak.client.admin.server_url}")
    private String serverUrl;
    @Value("${keycloak.client.admin.realm}")
    private String realm = "gn";
    @Value("${keycloak.client.admin.client_id}")
    private String clientId = "maps-by-server-side";
    @Value("${keycloak.client.admin.username}")
    private String username = "admin";
    @Value("${keycloak.client.admin.password}")
    private String secretPassword = "admin";
    @Value("${keycloak.client.admin.client_secret}")
    private String clientSecret = "OS7INCB7caOXU92kRZezVb2lSRpa0Tta";

    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(secretPassword)
                .clientSecret(clientSecret)
                .build();
    }
}
