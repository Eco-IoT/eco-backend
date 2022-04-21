package de.robinmucha.ecobackend.model.controller.event;

import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.entity.Event;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EventController {
    @PostMapping(value = "/devices/{requestedDeviceId}/events")
    EntityModel<Event> createEvent(Authentication loggedInDevice, @PathVariable int requestedDeviceId, @RequestBody Event newEvent);

    @GetMapping("/users/{requestedUserId}/events")
    CollectionModel<EntityModel<Event>> getAllEvents(Authentication loggedInUser, @PathVariable int requestedUserId);

    default List<Event> getAllEventsFromDevices(Set<Device> devices) {
        List<Event> events = new ArrayList<>();
        for (Device device : devices) {
            events.addAll(device.getEvents());
        }
        return events;
    }

    @GetMapping("/users/{requestedUserId}/devices/{requestedDeviceId}/events")
    CollectionModel<EntityModel<Event>> getAllDeviceEvents(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedDeviceId);

    @GetMapping("/users/{requestedUserId}/event-types/{requestedEventTypeId}/events")
    CollectionModel<EntityModel<Event>> getAllEventTypeEvents(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventTypeId);

    @GetMapping("/users/{requestedUserId}/events/{requestedEventId}")
    EntityModel<Event> getEvent(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventId);

    @DeleteMapping("/users/{requestedUserId}/events/{eventToDeleteId}")
    void deleteEvent(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int eventToDeleteId);
}
