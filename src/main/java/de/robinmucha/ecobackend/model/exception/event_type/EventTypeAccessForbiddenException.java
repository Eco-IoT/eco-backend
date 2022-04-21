package de.robinmucha.ecobackend.model.exception.event_type;

public class EventTypeAccessForbiddenException extends RuntimeException {
    public EventTypeAccessForbiddenException(String authorizedUser, int idOfEventType) {
        super("User " + authorizedUser + " is not authorized to access event-type " + idOfEventType);
    }
}
