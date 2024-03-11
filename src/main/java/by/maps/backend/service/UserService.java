package by.maps.backend.service;

import by.maps.backend.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser();
    User updateUser(User user);
}
