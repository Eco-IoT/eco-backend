package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.exception.device.DeviceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    default Device findDeviceByIdElseThrowDeviceNotFoundEx(int deviceId) {
        return findById(deviceId).orElseThrow(() -> new DeviceNotFoundException(deviceId));
    }

}