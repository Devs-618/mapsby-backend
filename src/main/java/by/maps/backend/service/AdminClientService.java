package by.maps.backend.service;

import by.maps.backend.exception.EntityNotFoundException;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public interface AdminClientService {
    UserRepresentation update(UserRepresentation ur);

    UserRepresentation getUserById(String id) throws EntityNotFoundException;
}
