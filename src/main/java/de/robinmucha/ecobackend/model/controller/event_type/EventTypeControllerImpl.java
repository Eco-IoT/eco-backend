package de.robinmucha.ecobackend.model.controller.event_type;

import de.robinmucha.ecobackend.model.assembler.EventTypeModelAssembler;
import de.robinmucha.ecobackend.model.entity.EventType;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.EventTypeRepository;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import de.robinmucha.ecobackend.security.utils.EventTypeAuthenticationHelper;
import de.robinmucha.ecobackend.security.utils.UserAuthenticationHelper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EventTypeControllerImpl implements EventTypeController {

    private final UserRepository userRepository;
    private final EventTypeRepository eventTypeRepository;

    private final EventTypeModelAssembler assembler;

    public EventTypeControllerImpl(UserRepository userRepository, EventTypeRepository eventTypeRepository, EventTypeModelAssembler assembler) {
        this.userRepository = userRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.assembler = assembler;
    }

    @Override
    @PostMapping(value = "/users/{requestedUserId}/event-types")
    public EntityModel<EventType> createEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody EventType newEventType) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        EventTypeAuthenticationHelper.isUserOwnerOfEventTypeElseThrowEventTypeCreationForbiddenEx(newEventType, requestedUser);

        return assembler.toModel(eventTypeRepository.save(newEventType));
    }

    @Override
    @GetMapping("/users/{requestedUserId}/event-types")
    public CollectionModel<EntityModel<EventType>> getEventTypes(Authentication loggedInUser, @PathVariable int requestedUserId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        List<EntityModel<EventType>> resultEventTypes = requestedUser.getEventTypes().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultEventTypes, linkTo(methodOn(EventTypeController.class).getEventTypes(loggedInUser, requestedUserId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/event-types/{requestedEventTypeId}")
    public EntityModel<EventType> getEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventTypeId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        EventType requestedEventType = eventTypeRepository.findEventTypeByIdElseThrowEventTypeNotFoundEx(requestedEventTypeId);
        EventTypeAuthenticationHelper.isUserOwnerOfEventTypeElseThrowEventTypeAccessForbiddenEx(requestedUser, requestedEventType);

        return assembler.toModel(requestedEventType);
    }

    @Override
    @DeleteMapping("/users/{requestedUserId}/event-types/{eventTypeToDeleteId}")
    public void deleteEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int eventTypeToDeleteId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        EventType eventTypeToDelete = eventTypeRepository.findEventTypeByIdElseThrowEventTypeNotFoundEx(eventTypeToDeleteId);
        EventTypeAuthenticationHelper.isUserOwnerOfEventTypeElseThrowEventTypeAccessForbiddenEx(requestedUser, eventTypeToDelete);

        eventTypeRepository.delete(eventTypeToDelete);
    }
}
