package de.robinmucha.ecobackend.model.exception.event;

public class EventCreationForbiddenException extends RuntimeException {
    public EventCreationForbiddenException(String authorizedDevice, int idOfNewEvent) {
        super("Device " + authorizedDevice + " is not authorized to create a new event " + idOfNewEvent + " with not themselves as creator");
    }
}
