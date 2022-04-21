package de.robinmucha.ecobackend.model.exception.event_type;

public class EventTypeNotFoundException extends RuntimeException {
    public EventTypeNotFoundException(int id) {
        super("Could not find event-type: " + id);
    }
}
