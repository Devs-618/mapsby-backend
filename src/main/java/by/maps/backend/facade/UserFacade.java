package by.maps.backend.facade;

import by.maps.backend.api.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserFacade {
    UserDto getUserDto();
}
