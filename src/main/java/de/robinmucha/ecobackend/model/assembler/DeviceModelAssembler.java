package de.robinmucha.ecobackend.model.assembler;

import de.robinmucha.ecobackend.model.controller.device.DeviceController;
import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.security.utils.AuthenticatedUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeviceModelAssembler implements RepresentationModelAssembler<Device, EntityModel<Device>> {
    @Override
    public EntityModel<Device> toModel(Device device) {
        return EntityModel.of(device,
                linkTo(methodOn(DeviceController.class)
                        .getDevice(AuthenticatedUser.getCurrentlyAuthenticatedUser(), device.getOwner().getId(), device.getId()))
                        .withSelfRel(),
                linkTo(methodOn(DeviceController.class)
                        .getDevices(AuthenticatedUser.getCurrentlyAuthenticatedUser(), device.getOwner().getId())).withRel("devices"));
    }
}
