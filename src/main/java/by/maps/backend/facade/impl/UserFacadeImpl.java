package by.maps.backend.facade.impl;

import by.maps.backend.api.dto.UserDto;
import by.maps.backend.domain.User;
import by.maps.backend.facade.UserFacade;
import by.maps.backend.service.AdminClientService;
import by.maps.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final AdminClientService adminClientService;

    @Override
    public UserDto getUserDto() {
        UserDto dto = new UserDto();
        User user = userService.getUser();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setGiven_name(user.getGiven_name());
        return dto;
    }


    @Override
    public UserDto updateUser(UserDto userDto) {
        UserRepresentation ur = adminClientService.getUserById(userDto.getId());
        ur.setEmail(userDto.getEmail());
        adminClientService.update(ur);
        return userDto;
    }
}
