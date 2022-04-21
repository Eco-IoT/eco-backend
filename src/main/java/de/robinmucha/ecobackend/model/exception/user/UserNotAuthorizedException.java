package de.robinmucha.ecobackend.model.exception.user;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(int id) {
        super("Could not authorize user: " + id);
    }
}
