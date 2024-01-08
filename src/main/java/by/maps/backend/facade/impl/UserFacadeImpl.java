package by.maps.backend.facade.impl;

import by.maps.backend.api.dto.UserDto;
import by.maps.backend.domain.User;
import by.maps.backend.facade.UserFacade;
import by.maps.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    @Override
    public UserDto getUserDto() {
        UserDto dto = new UserDto();
        User user = userService.getUser();
        dto.setEmail(user.getEmail());
        dto.setGiven_name(user.getGiven_name());
        return dto;
    }
}
