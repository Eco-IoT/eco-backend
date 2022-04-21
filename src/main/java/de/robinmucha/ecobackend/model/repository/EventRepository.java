package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.Event;
import de.robinmucha.ecobackend.model.exception.event.EventNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

    default Event findEventByIdElseThrowEventNotFoundEx(int eventId) {
        return findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
    }

}