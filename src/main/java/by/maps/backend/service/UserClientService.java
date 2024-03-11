package by.maps.backend.service;

import by.maps.backend.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserClientService<T> {
    T update(T t);

    T getUserById(String id) throws EntityNotFoundException;
}
