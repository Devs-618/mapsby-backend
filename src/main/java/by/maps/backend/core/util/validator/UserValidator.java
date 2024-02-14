package by.maps.backend.core.util.validator;

import by.maps.backend.core.config.UserValidatorConfig;
import by.maps.backend.core.util.ErrorMessages;
import by.maps.backend.domain.User;
import by.maps.backend.exception.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//TODO:check annotation possibility
@Component
@RequiredArgsConstructor
public class UserValidator implements Validator<User>{

    private final UserValidatorConfig config;
    private static final String WRONG_FORMAT_MESSAGE = ErrorMessages.WRONG_FORMAT_MESSAGE.label;
    @Override
    public boolean validate(User user) {
        return false;
    }

    public boolean isValidEmail(String email) throws InvalidFormatException {
        if (email.matches(config.getEmailPattern())){
            return true;
        }
        else {
            throw new InvalidFormatException(String.format(WRONG_FORMAT_MESSAGE, email));
        }
    }
}
