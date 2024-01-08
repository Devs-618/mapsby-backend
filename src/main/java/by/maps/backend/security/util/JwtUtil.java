package by.maps.backend.security.util;

import by.maps.backend.core.util.ErrorMessages;
import by.maps.backend.exception.UnAuthorizedCustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtUtil {
    private static final String WRONG_TOKEN_MESSAGE = ErrorMessages.WRONG_TOKEN_MESSAGE.label;

    public AccountDetails getAccountDetails() {
        Jwt token = getJwtToken().orElseThrow(() -> new UnAuthorizedCustomException(WRONG_TOKEN_MESSAGE));
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setSub(token.getClaim("sub"));
        accountDetails.setScope(token.getClaim("scope"));
        accountDetails.setEmail_verified(token.getClaim("email_verified"));
        accountDetails.setEmail(token.getClaim("email"));
        accountDetails.setName(token.getClaim("name"));
        accountDetails.setPreferred_username(token.getClaim("preferred_username"));
        accountDetails.setGiven_name(token.getClaim("given_name"));
        accountDetails.setFamily_name(token.getClaim("given_name"));
        return accountDetails;
    }

    private Optional<Jwt> getJwtToken() {
        Authentication currentAuthentication = getAuthentication();
        return currentAuthentication instanceof JwtAuthenticationToken ?
                Optional.of(((JwtAuthenticationToken) currentAuthentication).getToken()) : Optional.empty();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
