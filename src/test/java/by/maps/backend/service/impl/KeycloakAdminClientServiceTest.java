package by.maps.backend.service.impl;

import by.maps.backend.core.config.UserValidatorConfig;
import by.maps.backend.core.util.ErrorMessages;
import by.maps.backend.core.util.validator.UserValidator;
import by.maps.backend.exception.EntityNotFoundException;
import by.maps.backend.exception.InvalidFormatException;
import by.maps.backend.exception.UnAuthorizedCustomException;
import by.maps.backend.security.util.AccountDetails;
import by.maps.backend.security.util.JwtUtil;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@RequiredArgsConstructor
@DisplayName("Testing of keycloak_admin_client_service")
@TestPropertySource("classpath:application-test.yml")
class KeycloakAdminClientServiceTest {

    private KeycloakAdminClientService adminClientService;
    private static final String KEYCLOAK_REALM = "master";
    private static final String KEYCLOAK_USERNAME = "admin";
    private static final String KEYCLOAK_PASSWORD = "admin";
    @Mock
    private JwtUtil jwtUtil;
    private Keycloak keycloak;
    private static String userId;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserValidatorConfig userValidatorConfig;

    @Container
    private static final KeycloakContainer keycloakContainer =
            new KeycloakContainer()
                    .withAdminUsername(KEYCLOAK_USERNAME)
                    .withAdminPassword(KEYCLOAK_PASSWORD);

    @DynamicPropertySource
    static void keycloakProperties(DynamicPropertyRegistry registry) {
        registry.add("keycloak.client.admin.realm", () -> KEYCLOAK_REALM);
        registry.add("keycloak.client.admin.url", () -> String.format("http://%s/auth",
                keycloakContainer.getHost()));
        registry.add("keycloak.client.admin.username", () -> KEYCLOAK_USERNAME);
        registry.add("keycloak.client.admin.password", () -> KEYCLOAK_PASSWORD);
    }

    @BeforeEach
    void setupAdminClient() {
        UserRepresentation oldUser = new UserRepresentation();
        oldUser.setUsername("user");
        oldUser.setEmail("Email");
        oldUser.setEnabled(true);
        if (keycloak == null) {
            keycloakContainer.start();
            String keycloakHost = keycloakContainer.getHost();
            int keycloakPort = keycloakContainer.getFirstMappedPort();
            String keycloakUrl = String.format("http://%s:%d", keycloakHost, keycloakPort);
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakUrl)
                    .realm(KEYCLOAK_REALM)
                    .username(KEYCLOAK_USERNAME)
                    .password(KEYCLOAK_PASSWORD)
                    .clientId("admin-cli")
                    .build();
        }
        adminClientService = new KeycloakAdminClientService(keycloak, jwtUtil, userValidator);
        adminClientService.setName(KEYCLOAK_REALM);
        RealmResource realmResource = keycloak.realm(KEYCLOAK_REALM);
        try (Response response = realmResource.users().create(oldUser)) {
            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        }
    }

    @AfterEach
    void delete() {
        RealmResource realmResource = keycloak.realm(KEYCLOAK_REALM);
        try (Response response = realmResource.users().delete(userId)) {

        }
    }


    @Test
    @DisplayName("Update user when exists")
    void updateWhenUserExists() throws InvalidFormatException {
        AccountDetails ad = new AccountDetails();
        UserRepresentation newUser = adminClientService.getUserById(userId);
        newUser.setEmail("nemail@mail.com");
        ad.setSub(userId);
        when(jwtUtil.getAccountDetails()).thenReturn(ad);
        UserRepresentation oldUser = adminClientService.update(newUser);
        Assertions.assertEquals(oldUser.getEmail(), newUser.getEmail());
    }

    @Test
    @DisplayName("Update user when not exists")
    void updateWhenUserNotExists() {
        AccountDetails ad = new AccountDetails();
        char first = userId.charAt(0) == 'a' ? 'c' : 'a';
        String wrongId = userId;
        wrongId = wrongId.replace(userId.charAt(0), first);
        ad.setSub(wrongId);
        when(jwtUtil.getAccountDetails()).thenReturn(ad);
        assertThatExceptionOfType(UnAuthorizedCustomException.class)
                .isThrownBy(() -> adminClientService.update(new UserRepresentation()))
                .withMessage(ErrorMessages.ACCESS_DENIED_MESSAGE.label);
    }

    @Test
    @DisplayName("Update user when invalid email")
    void updateUserWhenInvalidEmail() {
        AccountDetails ad = new AccountDetails();
        UserRepresentation newUser = adminClientService.getUserById(userId);
        newUser.setEmail("nemailmail.com");
        ad.setSub(userId);
        when(jwtUtil.getAccountDetails()).thenReturn(ad);
        assertThatExceptionOfType(InvalidFormatException.class)
                .isThrownBy(() -> adminClientService.update(newUser))
                .withMessage(String.format(ErrorMessages.WRONG_FORMAT_MESSAGE.label, newUser.getEmail()));
    }

    @Test
    @DisplayName("Get user when exists")
    void getUserByIdWhenExists() {
        UserRepresentation user = adminClientService.getUserById(userId);
        Assertions.assertNotNull(user);
    }

    @Test
    @DisplayName("Get user when not exists")
    void getUserById() {
        char first = userId.charAt(0) == 'a' ? 'c' : 'a';
        String wrongId = userId;
        wrongId = wrongId.replace(userId.charAt(0), first);
        String finalWrongId = wrongId;
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> adminClientService.getUserById(finalWrongId))
                .withMessage(String.format(ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label, "id", wrongId));
    }
}