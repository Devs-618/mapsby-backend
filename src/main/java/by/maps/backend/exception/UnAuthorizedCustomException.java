package by.maps.backend.exception;

public class UnAuthorizedCustomException extends RuntimeException {
    public UnAuthorizedCustomException(String message) {
        super(message);
    }
}
