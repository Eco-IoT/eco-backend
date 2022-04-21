package de.robinmucha.ecobackend.model.exception.event;

public class EventAccessForbiddenException extends RuntimeException {
    public EventAccessForbiddenException(String authorizedUser, int idOfEvent) {
        super("User " + authorizedUser + " is not authorized to access event " + idOfEvent);
    }
}
