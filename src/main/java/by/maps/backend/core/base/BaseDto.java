package by.maps.backend.core.base;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class BaseDto implements Serializable {
    private UUID id;
    private ZonedDateTime createdOn;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
