package de.robinmucha.ecobackend.model.exception.device;

public class DevicenameAlreadyExistsException extends RuntimeException {
    public DevicenameAlreadyExistsException(String devicename) {
        super("Device " + devicename + " already exists");
    }
}
