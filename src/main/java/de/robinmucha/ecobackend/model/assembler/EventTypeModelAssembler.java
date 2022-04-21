package de.robinmucha.ecobackend.model.assembler;

import de.robinmucha.ecobackend.model.controller.event_type.EventTypeController;
import de.robinmucha.ecobackend.model.entity.EventType;
import de.robinmucha.ecobackend.security.utils.AuthenticatedUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventTypeModelAssembler implements RepresentationModelAssembler<EventType, EntityModel<EventType>> {
    @Override
    public EntityModel<EventType> toModel(EventType eventType) {
        return EntityModel.of(eventType,
                linkTo(methodOn(EventTypeController.class)
                        .getEventType(AuthenticatedUser.getCurrentlyAuthenticatedUser(), eventType.getOwner().getId(), eventType.getId())).withSelfRel(),
                linkTo(methodOn(EventTypeController.class)
                        .getEventTypes(AuthenticatedUser.getCurrentlyAuthenticatedUser(), eventType.getOwner().getId())).withRel("event-types"));
    }
}
