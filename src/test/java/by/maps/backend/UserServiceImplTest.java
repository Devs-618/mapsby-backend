package by.maps.backend;

import by.maps.backend.domain.User;
import by.maps.backend.exception.UnAuthorizedCustomException;
import by.maps.backend.security.util.AccountDetails;
import by.maps.backend.security.util.JwtUtil;
import by.maps.backend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@SpringBootTest
@Profile(value = "test")
@RequiredArgsConstructor
@DisplayName("Testing of user_service")
@TestPropertySource("classpath:application-test.yml")
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("get user when valid token")
    void getUserWhenValidToken() {
        User expectedUser = new User();
        expectedUser.setGiven_name("name");
        expectedUser.setEmail("email");
        AccountDetails ad = new AccountDetails();
        ad.setGiven_name("name");
        ad.setEmail("email");
        when(jwtUtil.getAccountDetails()).thenReturn(ad);
        User actualUser = userService.getUser();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("get user when invalid token")
    void getUserWhenInValidToken() {
        when(jwtUtil.getAccountDetails()).thenThrow(new UnAuthorizedCustomException("Invalid token"));
        Exception exception = assertThrows(UnAuthorizedCustomException.class, () ->
                userService.getUser());
        String expectedMessage = "Invalid token";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}