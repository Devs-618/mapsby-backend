package by.maps.backend.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String id;
    @NotNull
    @Email(message = "Please provide a valid email address")
    private String email;
    private String given_name;
}
