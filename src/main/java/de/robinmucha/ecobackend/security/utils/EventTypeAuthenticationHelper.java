package de.robinmucha.ecobackend.security.utils;

import de.robinmucha.ecobackend.model.entity.EventType;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.event_type.EventTypeAccessForbiddenException;
import de.robinmucha.ecobackend.model.exception.event_type.EventTypeCreationForbiddenException;

public class EventTypeAuthenticationHelper {

    public static void isUserOwnerOfEventTypeElseThrowEventTypeAccessForbiddenEx(User requestedUser, EventType requestedEventType) {
        if (!isUserOwnerOfEventType(requestedUser, requestedEventType)) {
            throw new EventTypeAccessForbiddenException(requestedUser.getName(), requestedEventType.getId());
        }
    }

    public static void isUserOwnerOfEventTypeElseThrowEventTypeCreationForbiddenEx(EventType newEventType, User owner) {
        if (!isUserOwnerOfEventType(owner, newEventType)) {
            throw new EventTypeCreationForbiddenException(owner.getName(), newEventType.getId());
        }
    }

    private static boolean isUserOwnerOfEventType(User user, EventType eventType) {
        return user.equals(eventType.getOwner());
    }

}
