package by.maps.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String id;
    @NotNull
    @Email(message = "Please provide a valid email address")
    private String email;
    private String given_name;
}
