package de.robinmucha.ecobackend.model.exception.event;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(int id) {
        super("Could not find event: " + id);
    }
}
