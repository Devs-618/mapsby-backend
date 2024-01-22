package by.maps.backend.facade;

import by.maps.backend.api.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFacade {
    UserDto getUserDto();
    UserDto updateUser(UserDto userDto);
}
