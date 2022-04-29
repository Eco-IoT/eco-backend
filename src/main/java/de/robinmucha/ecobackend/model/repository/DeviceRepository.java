package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.exception.device.DeviceNotFoundException;
import de.robinmucha.ecobackend.model.exception.device.DevicenameAlreadyExistsException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    default Device findDeviceByIdElseThrowDeviceNotFoundEx(int deviceId) {
        return findById(deviceId).orElseThrow(() -> new DeviceNotFoundException(deviceId));
    }

    default void isDevicenameUniqueElseThrowDevicenameAlreadyExistsEx(Device newDevice) {
        if (!isDevicenameUnique(newDevice.getName())) {
            throw new DevicenameAlreadyExistsException(newDevice.getName());
        }
    }

    private boolean isDevicenameUnique(String devicename) {
        for (Device device : findAll()) {
            if (device.getName().equals(devicename)) {
                return false;
            }
        }
        return true;
    }

}