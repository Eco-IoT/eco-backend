package de.robinmucha.ecobackend.model.exception.device;

public class DeviceAccessForbiddenException extends RuntimeException {
    public DeviceAccessForbiddenException(String authorizedUser, int idOfDevice) {
        super("User " + authorizedUser + " is not authorized to access device " + idOfDevice);
    }
}
