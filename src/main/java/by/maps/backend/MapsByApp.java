package by.maps.backend;

import by.maps.backend.security.common.SecurityUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityUrlConfig.class})
public class MapsByApp {
    public static void main(String[] args) {
        SpringApplication.run(MapsByApp.class, args);
    }
}
