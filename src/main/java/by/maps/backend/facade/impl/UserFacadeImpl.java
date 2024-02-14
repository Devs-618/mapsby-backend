package by.maps.backend.facade.impl;

import by.maps.backend.api.dto.UserDto;
import by.maps.backend.domain.User;
import by.maps.backend.facade.UserFacade;
import by.maps.backend.service.AdminClientService;
import by.maps.backend.service.UserService;
import lombok.RequiredArgsConstructor;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final AdminClientService<UserRepresentation> adminClientService;

    @Override
    @PreAuthorize("isAuthenticated()")
    public UserDto getUserDto() {
        UserDto dto = new UserDto();
        User user = userService.getUser();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setGiven_name(user.getGiven_name());
        return dto;
    }


    @Override
    @PreAuthorize("isAuthenticated()")
    public UserDto updateUser(UserDto userDto) {
        UserRepresentation ur = adminClientService.getUserById(userDto.getId());
        ur.setEmail(userDto.getEmail());
        adminClientService.update(ur);
        return userDto;
    }
}
