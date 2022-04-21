package de.robinmucha.ecobackend.model.exception.user;

public class UserAccessForbiddenException extends RuntimeException {
    public UserAccessForbiddenException(String authorizedUser, int idOfDifferentUser) {
        super("User " + authorizedUser + " is not authorized to access user " + idOfDifferentUser);
    }
}
