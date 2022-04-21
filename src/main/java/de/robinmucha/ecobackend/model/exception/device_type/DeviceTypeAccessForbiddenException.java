package de.robinmucha.ecobackend.model.exception.device_type;

public class DeviceTypeAccessForbiddenException extends RuntimeException {
    public DeviceTypeAccessForbiddenException(String authorizedUser, int idOfDeviceType) {
        super("User " + authorizedUser + " is not authorized to access device-type " + idOfDeviceType);
    }
}
