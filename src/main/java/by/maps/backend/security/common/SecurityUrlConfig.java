package by.maps.backend.security.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;

@Getter
@Setter
@ConfigurationProperties(prefix = "security.unauthorized")
public class SecurityUrlConfig {
    private ArrayList<String> urls;
}