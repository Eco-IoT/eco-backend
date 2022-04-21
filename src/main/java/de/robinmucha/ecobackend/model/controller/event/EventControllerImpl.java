package de.robinmucha.ecobackend.model.controller.event;

import de.robinmucha.ecobackend.model.assembler.EventModelAssembler;
import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.entity.Event;
import de.robinmucha.ecobackend.model.entity.EventType;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.DeviceRepository;
import de.robinmucha.ecobackend.model.repository.EventRepository;
import de.robinmucha.ecobackend.model.repository.EventTypeRepository;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import de.robinmucha.ecobackend.security.utils.DeviceAuthenticationHelper;
import de.robinmucha.ecobackend.security.utils.EventAuthenticationHelper;
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
public class EventControllerImpl implements EventController {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;

    private final EventModelAssembler assembler;

    public EventControllerImpl(UserRepository userRepository, DeviceRepository deviceRepository, EventTypeRepository eventTypeRepository, EventRepository eventRepository, EventModelAssembler assembler) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.eventRepository = eventRepository;
        this.assembler = assembler;
    }

    @Override
    @PostMapping(value = "/devices/{requestedDeviceId}/events")
    public EntityModel<Event> createEvent(Authentication loggedInDevice, @PathVariable int requestedDeviceId, @RequestBody Event newEvent) {
        Device requestedDevice = deviceRepository.findDeviceByIdElseThrowDeviceNotFoundEx(requestedDeviceId);
        DeviceAuthenticationHelper.isSameDeviceElseThrowDeviceAccessForbiddenEx(loggedInDevice.getName(), requestedDevice);

        EventAuthenticationHelper.isDeviceCreatorOfEventElseThrowEventCreationForbiddenEx(newEvent, requestedDevice);

        return assembler.toModel(eventRepository.save(newEvent));
    }

    @Override
    @GetMapping("/users/{requestedUserId}/events")
    public CollectionModel<EntityModel<Event>> getAllEvents(Authentication loggedInUser, @PathVariable int requestedUserId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        List<EntityModel<Event>> resultEvents = getAllEventsFromDevices(requestedUser.getDevices()).stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultEvents, linkTo(methodOn(EventController.class).getAllEvents(loggedInUser, requestedUserId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/devices/{requestedDeviceId}/events")
    public CollectionModel<EntityModel<Event>> getAllDeviceEvents(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        Device requestedDevice = deviceRepository.findDeviceByIdElseThrowDeviceNotFoundEx(requestedDeviceId);
        DeviceAuthenticationHelper.isUserOwnerOfDeviceElseThrowDeviceAccessForbiddenEx(requestedUser, requestedDevice);

        List<EntityModel<Event>> resultEvents = requestedDevice.getEvents().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultEvents, linkTo(methodOn(EventController.class).getAllDeviceEvents(loggedInUser, requestedUserId, requestedDeviceId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/event-types/{requestedEventTypeId}/events")
    public CollectionModel<EntityModel<Event>> getAllEventTypeEvents(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventTypeId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        EventType requestedEventType = eventTypeRepository.findEventTypeByIdElseThrowEventTypeNotFoundEx(requestedEventTypeId);
        EventTypeAuthenticationHelper.isUserOwnerOfEventTypeElseThrowEventTypeAccessForbiddenEx(requestedUser, requestedEventType);

        List<EntityModel<Event>> resultEvents = requestedEventType.getEvents().stream().map(assembler::toModel).toList();
        return CollectionModel.of(resultEvents, linkTo(methodOn(EventController.class).getAllEventTypeEvents(loggedInUser, requestedUserId, requestedEventTypeId)).withSelfRel());
    }

    @Override
    @GetMapping("/users/{requestedUserId}/events/{requestedEventId}")
    public EntityModel<Event> getEvent(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        Event requestedEvent = eventRepository.findEventByIdElseThrowEventNotFoundEx(requestedEventId);
        EventAuthenticationHelper.isUserOwnerOfEventElseThrowEventAccessForbiddenEx(requestedUser, requestedEvent);

        return assembler.toModel(requestedEvent);
    }

    @Override
    @DeleteMapping("/users/{requestedUserId}/events/{eventToDeleteId}")
    public void deleteEvent(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int eventToDeleteId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        Event eventToDelete = eventRepository.findEventByIdElseThrowEventNotFoundEx(eventToDeleteId);
        EventAuthenticationHelper.isUserOwnerOfEventElseThrowEventAccessForbiddenEx(requestedUser, eventToDelete);

        eventRepository.delete(eventToDelete);
    }

}
