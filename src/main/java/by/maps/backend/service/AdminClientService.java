package by.maps.backend.service;

import by.maps.backend.exception.EntityNotFoundException;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public interface AdminClientService<T> {
    T update(T t);

    T getUserById(String id) throws EntityNotFoundException;
}
