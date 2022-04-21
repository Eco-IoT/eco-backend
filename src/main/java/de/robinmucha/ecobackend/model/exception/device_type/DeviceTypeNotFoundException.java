package de.robinmucha.ecobackend.model.exception.device_type;

public class DeviceTypeNotFoundException extends RuntimeException {
    public DeviceTypeNotFoundException(int id) {
        super("Could not find device-type: " + id);
    }
}
