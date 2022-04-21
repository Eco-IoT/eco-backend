package de.robinmucha.ecobackend.model.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("Could not find user: " + id);
    }
}
