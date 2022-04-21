package de.robinmucha.ecobackend.model.exception.device_type;

public class DeviceTypeCreationForbiddenException extends RuntimeException {
    public DeviceTypeCreationForbiddenException(String authorizedUser, int idOfNewDeviceType) {
        super("User " + authorizedUser + " is not authorized to create a new device-type " + idOfNewDeviceType + " with not themselves as owner");
    }
}
