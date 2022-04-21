package de.robinmucha.ecobackend.model.exception.device;

public class DeviceCreationForbiddenException extends RuntimeException {
    public DeviceCreationForbiddenException(String authorizedUser, int idOfNewDevice) {
        super("User " + authorizedUser + " is not authorized to create a new device " + idOfNewDevice + " with not themselves as owner");
    }
}
