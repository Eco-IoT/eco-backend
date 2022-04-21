package de.robinmucha.ecobackend.model.assembler;

import de.robinmucha.ecobackend.model.controller.event.EventController;
import de.robinmucha.ecobackend.model.entity.Event;
import de.robinmucha.ecobackend.security.utils.AuthenticatedUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {
    @Override
    public EntityModel<Event> toModel(Event event) {
        return EntityModel.of(event,
                linkTo(methodOn(EventController.class)
                        .getEvent(AuthenticatedUser.getCurrentlyAuthenticatedUser(), event.getCreator().getOwner().getId(), event.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class)
                        .getAllEvents(AuthenticatedUser.getCurrentlyAuthenticatedUser(), event.getCreator().getOwner().getId())).withRel("events"));
    }
}
