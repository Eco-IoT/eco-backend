package de.robinmucha.ecobackend.model.controller.device_type;

import de.robinmucha.ecobackend.model.entity.DeviceType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public interface DeviceTypeController {
    @PostMapping(value = "/users/{requestedUserId}/device-types")
    EntityModel<DeviceType> createDeviceType(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody DeviceType newDeviceType);

    @GetMapping("/users/{requestedUserId}/device-types")
    CollectionModel<EntityModel<DeviceType>> getDeviceTypes(Authentication loggedInUser, @PathVariable int requestedUserId);

    @GetMapping("/users/{requestedUserId}/device-types/{requestedDeviceTypeId}")
    EntityModel<DeviceType> getDeviceType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceTypeId);

    @DeleteMapping("/users/{requestedUserId}/device-types/{deviceTypeToDeleteId}")
    void deleteDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int deviceTypeToDeleteId);
}
