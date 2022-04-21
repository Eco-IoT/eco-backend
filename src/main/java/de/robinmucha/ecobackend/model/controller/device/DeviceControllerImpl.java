package de.robinmucha.ecobackend.model.controller.device;

import de.robinmucha.ecobackend.model.assembler.DeviceModelAssembler;
import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.DeviceRepository;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import de.robinmucha.ecobackend.security.utils.DeviceAuthenticationHelper;
import de.robinmucha.ecobackend.security.utils.UserAuthenticationHelper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DeviceControllerImpl implements DeviceController {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    private final DeviceModelAssembler assembler;

    public DeviceControllerImpl(UserRepository userRepository, DeviceRepository deviceRepository, DeviceModelAssembler assembler) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.assembler = assembler;
    }

    @Override
    @PostMapping(value = "/users/{requestedUserId}/devices")
    public EntityModel<Device> createDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody Device newDevice) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        DeviceAuthenticationHelper.isUserOwnerOfDeviceElseThrowDeviceCreationForbiddenEx(newDevice, requestedUser);

        return assembler.toModel(deviceRepository.save(newDevice));
    }

    @Override
    @GetMapping("/users/{requestedUserId}/devices")
    public CollectionModel<EntityModel<Device>> getDevices(Authentication loggedInUser, @PathVariable int requestedUserId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        List<EntityModel<Device>> resultDevices = requestedUser.getDevices().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultDevices, linkTo(methodOn(DeviceController.class).getDevices(loggedInUser, requestedUserId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/devices/{requestedDeviceId}")
    public EntityModel<Device> getDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        Device requestedDevice = deviceRepository.findDeviceByIdElseThrowDeviceNotFoundEx(requestedDeviceId);
        DeviceAuthenticationHelper.isUserOwnerOfDeviceElseThrowDeviceAccessForbiddenEx(requestedUser, requestedDevice);

        return assembler.toModel(requestedDevice);
    }

    @Override
    @DeleteMapping("/users/{requestedUserId}/devices/{deviceToDeleteId}")
    public void deleteDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int deviceToDeleteId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        Device deviceToDelete = deviceRepository.findDeviceByIdElseThrowDeviceNotFoundEx(deviceToDeleteId);
        DeviceAuthenticationHelper.isUserOwnerOfDeviceElseThrowDeviceAccessForbiddenEx(requestedUser, deviceToDelete);

        deviceRepository.delete(deviceToDelete);
    }
}
