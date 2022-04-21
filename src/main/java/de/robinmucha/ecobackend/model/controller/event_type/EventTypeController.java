package de.robinmucha.ecobackend.model.controller.event_type;

import de.robinmucha.ecobackend.model.entity.EventType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public interface EventTypeController {
    @PostMapping(value = "/users/{requestedUserId}/event-types")
    EntityModel<EventType> createEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @RequestBody EventType newEventType);

    @GetMapping("/users/{requestedUserId}/event-types")
    CollectionModel<EntityModel<EventType>> getEventTypes(Authentication loggedInUser, @PathVariable int requestedUserId);

    @GetMapping("/users/{requestedUserId}/event-types/{requestedEventTypeId}")
    EntityModel<EventType> getEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int requestedEventTypeId);

    @DeleteMapping("/users/{requestedUserId}/event-types/{eventTypeToDeleteId}")
    void deleteEventType(Authentication loggedInUser, @PathVariable int requestedUserId, @PathVariable int eventTypeToDeleteId);
}
