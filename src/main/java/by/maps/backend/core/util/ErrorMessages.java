package by.maps.backend.core.util;

public enum ErrorMessages {
    ENTITY_NOT_FOUND_MESSAGE("Entity with %s : %s not exists"),
    WRONG_FORMAT_MESSAGE("Wrong format: %s"),
    SERVICE_UNAVAILABLE_MESSAGE("Service is not available: %s"),
    ACCESS_DENIED_MESSAGE("Access denied"),
    WRONG_TOKEN_MESSAGE("Invalid token");
    public final String label;
    ErrorMessages(String label) {
        this.label = label;
    }
}
