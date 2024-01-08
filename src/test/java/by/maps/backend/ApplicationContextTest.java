package by.maps.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.yml")
class SpringBootBackendApplicationTests {

    @Test
    void contextLoads() {
        assertThat(SecurityContextHolder.getContext()).isNotNull();
    }

}