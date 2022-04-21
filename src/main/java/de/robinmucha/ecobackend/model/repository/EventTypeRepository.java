package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.EventType;
import de.robinmucha.ecobackend.model.exception.event_type.EventTypeNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {

    default EventType findEventTypeByIdElseThrowEventTypeNotFoundEx(int eventTypeId) {
        return findById(eventTypeId).orElseThrow(() -> new EventTypeNotFoundException(eventTypeId));
    }

}