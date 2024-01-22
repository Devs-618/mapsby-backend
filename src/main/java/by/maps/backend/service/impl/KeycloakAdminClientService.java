package by.maps.backend.service.impl;

import by.maps.backend.core.util.ErrorMessages;
import by.maps.backend.exception.EntityNotFoundException;
import by.maps.backend.exception.UnAuthorizedCustomException;
import by.maps.backend.security.util.JwtUtil;
import by.maps.backend.service.AdminClientService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakAdminClientService implements AdminClientService {
    @Value("${keycloak.client.admin.realm}")
    private String REALM_NAME;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String ACCESS_DENIED_MESSAGE = ErrorMessages.ACCESS_DENIED_MESSAGE.label;
    private final Keycloak keycloak;
    private final JwtUtil util;

    @Override
    public UserRepresentation update(UserRepresentation ur) {
        if (!util.getAccountDetails().getSub().equals(ur.getId())) {
            throw new UnAuthorizedCustomException(ACCESS_DENIED_MESSAGE);
        }
        UserResource userResource = keycloak.realm(REALM_NAME).users().get(ur.getId());
        userResource.update(ur);
        return getUserById(ur.getId());
    }

    @Override
    public UserRepresentation getUserById(String id) {
        UserResource userResource = keycloak.realm(REALM_NAME).users().get(id);
        if (userResource == null) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, "id", id));
        }
        return userResource.toRepresentation();
    }
}
