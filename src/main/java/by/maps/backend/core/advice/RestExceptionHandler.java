package by.maps.backend.core.advice;

import by.maps.backend.api.dto.ErrorMessageDto;
import by.maps.backend.exception.EntityNotFoundException;
import by.maps.backend.exception.UnAuthorizedCustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({UnAuthorizedCustomException.class})
    protected ResponseEntity<ErrorMessageDto> unauthorizedHandler(Exception e) {
        log.debug(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMessageDto(e.getMessage()));
    }
    @ExceptionHandler({InvalidBearerTokenException.class})
    protected ResponseEntity<ErrorMessageDto> invalidBearerHandler(Exception e) {
        log.debug(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMessageDto(e.getMessage()));
    }
    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<ErrorMessageDto> notFoundHandler(Exception e) {
        log.debug(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDto(e.getMessage()));
    }
}
