package by.maps.backend.core.util;

public enum ErrorMessages {
    WRONG_TOKEN_MESSAGE("Invalid token");
    public final String label;
    ErrorMessages(String label) {
        this.label = label;
    }
}
