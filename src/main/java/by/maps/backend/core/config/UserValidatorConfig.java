package by.maps.backend.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "validator.user")
public class UserValidatorConfig {
    private String passwordPattern;
    private String emailPattern;
}
