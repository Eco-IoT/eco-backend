package de.robinmucha.ecobackend.model.exception.event_type;

public class EventTypeCreationForbiddenException extends RuntimeException {
    public EventTypeCreationForbiddenException(String authorizedUser, int idOfNewEventType) {
        super("User " + authorizedUser + " is not authorized to create a new event-type " + idOfNewEventType + " with not themselves as owner");
    }
}
