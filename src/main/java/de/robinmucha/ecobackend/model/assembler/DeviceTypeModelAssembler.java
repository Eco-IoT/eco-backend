package de.robinmucha.ecobackend.model.assembler;

import de.robinmucha.ecobackend.model.controller.device_type.DeviceTypeController;
import de.robinmucha.ecobackend.model.entity.DeviceType;
import de.robinmucha.ecobackend.security.utils.AuthenticatedUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeviceTypeModelAssembler implements RepresentationModelAssembler<DeviceType, EntityModel<DeviceType>> {
    @Override
    public EntityModel<DeviceType> toModel(DeviceType deviceType) {
        return EntityModel.of(deviceType,
                linkTo(methodOn(DeviceTypeController.class)
                        .getDeviceType(AuthenticatedUser.getCurrentlyAuthenticatedUser(), deviceType.getOwner().getId(), deviceType.getId())).withSelfRel(),
                linkTo(methodOn(DeviceTypeController.class)
                        .getDeviceTypes(AuthenticatedUser.getCurrentlyAuthenticatedUser(), deviceType.getOwner().getId())).withRel("device-types"));
    }
}
