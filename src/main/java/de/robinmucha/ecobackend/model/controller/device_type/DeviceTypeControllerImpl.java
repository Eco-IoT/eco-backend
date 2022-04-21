package de.robinmucha.ecobackend.model.controller.device_type;

import de.robinmucha.ecobackend.model.assembler.DeviceTypeModelAssembler;
import de.robinmucha.ecobackend.model.entity.DeviceType;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.DeviceTypeRepository;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import de.robinmucha.ecobackend.security.utils.DeviceTypeAuthenticationHelper;
import de.robinmucha.ecobackend.security.utils.UserAuthenticationHelper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DeviceTypeControllerImpl implements DeviceTypeController {

    private final UserRepository userRepository;
    private final DeviceTypeRepository deviceTypeRepository;

    private final DeviceTypeModelAssembler assembler;

    public DeviceTypeControllerImpl(UserRepository userRepository, DeviceTypeRepository deviceTypeRepository, DeviceTypeModelAssembler assembler) {
        this.userRepository = userRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.assembler = assembler;
    }

    @Override
    @PostMapping(value = "/users/{requestedUserId}/device-types")
    public EntityModel<DeviceType> createDeviceType(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody DeviceType newDeviceType) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        DeviceTypeAuthenticationHelper.isUserOwnerOfDeviceTypeElseThrowDeviceTypeCreationForbiddenEx(newDeviceType, requestedUser);

        return assembler.toModel(deviceTypeRepository.save(newDeviceType));
    }

    @Override
    @GetMapping("/users/{requestedUserId}/device-types")
    public CollectionModel<EntityModel<DeviceType>> getDeviceTypes(Authentication loggedInUser, @PathVariable int requestedUserId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        List<EntityModel<DeviceType>> resultDeviceTypes = requestedUser.getDeviceTypes().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultDeviceTypes, linkTo(methodOn(DeviceTypeController.class).getDeviceTypes(loggedInUser, requestedUserId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/device-types/{requestedDeviceTypeId}")
    public EntityModel<DeviceType> getDeviceType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceTypeId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        DeviceType requestedDeviceType = deviceTypeRepository.findDeviceTypeByIdElseThrowDeviceTypeNotFoundEx(requestedDeviceTypeId);
        DeviceTypeAuthenticationHelper.isUserOwnerOfDeviceTypeElseThrowDeviceTypeAccessForbiddenEx(requestedUser, requestedDeviceType);

        return assembler.toModel(requestedDeviceType);
    }

    @Override
    @DeleteMapping("/users/{requestedUserId}/device-types/{deviceTypeToDeleteId}")
    public void deleteDevice(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int deviceTypeToDeleteId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        DeviceType deviceTypeToDelete = deviceTypeRepository.findDeviceTypeByIdElseThrowDeviceTypeNotFoundEx(deviceTypeToDeleteId);
        DeviceTypeAuthenticationHelper.isUserOwnerOfDeviceTypeElseThrowDeviceTypeAccessForbiddenEx(requestedUser, deviceTypeToDelete);

        deviceTypeRepository.delete(deviceTypeToDelete);
    }
}
