package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.DeviceType;
import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {

    default DeviceType findDeviceTypeByIdElseThrowDeviceTypeNotFoundEx(int deviceTypeId) {
        return findById(deviceTypeId).orElseThrow(() -> new DeviceTypeNotFoundException(deviceTypeId));
    }

}