package de.robinmucha.ecobackend.model.exception.device;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(int id) {
        super("Could not find device: " + id);
    }
}
