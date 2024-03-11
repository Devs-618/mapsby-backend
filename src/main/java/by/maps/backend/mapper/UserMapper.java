package by.maps.backend.mapper;

import by.maps.backend.api.dto.UserDto;
import by.maps.backend.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToUser(UserDto userDto);
    UserDto mapToUserDto(User user);
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
    List<UserDto> mapToListUserDto(List<User> users);
}
