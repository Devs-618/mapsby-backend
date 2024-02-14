package by.maps.backend.controller.impl;

import by.maps.backend.api.dto.UserDto;
import by.maps.backend.controller.UserController;
import by.maps.backend.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserFacade userFacade;
    @Override
    public ResponseEntity<UserDto> getAuthenticatedUser() {
        log.info("Call authorized user info");
        return ResponseEntity.ok(userFacade.getUserDto());
    }

    @Override
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        log.info(String.format("Call update user with id: %s", userDto.getId()));
        return ResponseEntity.ok(userFacade.updateUser(userDto));
    }
}
