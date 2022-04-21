package de.robinmucha.ecobackend.model.controller.device;

import de.robinmucha.ecobackend.model.entity.Device;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public interface DeviceController {
    @PostMapping(value = "/users/{requestedUserId}/devices")
    EntityModel<Device> createDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody Device newDevice);

    @GetMapping("/users/{requestedUserId}/devices")
    CollectionModel<EntityModel<Device>> getDevices(Authentication loggedInUser, @PathVariable int requestedUserId);

    @GetMapping("/users/{requestedUserId}/devices/{requestedDeviceId}")
    EntityModel<Device> getDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceId);

    @DeleteMapping("/users/{requestedUserId}/devices/{deviceToDeleteId}")
    void deleteDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int deviceToDeleteId);
}
