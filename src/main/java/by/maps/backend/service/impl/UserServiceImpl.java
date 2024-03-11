package by.maps.backend.service.impl;

import by.maps.backend.core.util.ErrorMessages;
import by.maps.backend.domain.User;
import by.maps.backend.exception.ExternalServerException;
import by.maps.backend.security.util.AccountDetails;
import by.maps.backend.security.util.JwtUtil;
import by.maps.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtUtil util;
    private final KeycloakUserClientService keycloakService;
    private static final String SERVICE_UNAVAILABLE_MESSAGE = ErrorMessages.SERVICE_UNAVAILABLE_MESSAGE.label;

    @Override
    @PreAuthorize("isAuthenticated()")
    public User getUser() {
        AccountDetails ad = util.getAccountDetails();
        User user = new User();
        user.setId(ad.getSub());
        user.setEmail(ad.getEmail());
        user.setGiven_name(ad.getGiven_name());
        return user;
    }


    @Override
    @PreAuthorize("isAuthenticated()")
    public User updateUser(User user) {
        try {
            UserRepresentation userRepresentation = keycloakService.getUserById(user.getId());
            userRepresentation.setEmail(user.getEmail());
            keycloakService.update(userRepresentation);
        } catch (Exception e) {
            throw new ExternalServerException(String.format(SERVICE_UNAVAILABLE_MESSAGE, e.getMessage()));
        }
        return user;
    }

}
