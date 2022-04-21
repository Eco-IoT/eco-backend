package de.robinmucha.ecobackend.security.utils;

import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.entity.Event;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.event.EventAccessForbiddenException;
import de.robinmucha.ecobackend.model.exception.event.EventCreationForbiddenException;

public class EventAuthenticationHelper {

    private static boolean isUserOwnerOfEvent(User user, Event event) {
        return user.equals(event.getCreator().getOwner());
    }

    public static void isUserOwnerOfEventElseThrowEventAccessForbiddenEx(User requestedUser, Event requestedEvent) {
        if (!isUserOwnerOfEvent(requestedUser, requestedEvent)) {
            throw new EventAccessForbiddenException(requestedUser.getName(), requestedEvent.getId());
        }
    }

    public static void isDeviceCreatorOfEventElseThrowEventCreationForbiddenEx(Event newEvent, Device owner) {
        if (!isDeviceCreatorOfEvent(owner, newEvent)) {
            throw new EventCreationForbiddenException(owner.getName(), newEvent.getId());
        }
    }

    private static boolean isDeviceCreatorOfEvent(Device device, Event event) {
        return device.equals(event.getCreator());
    }

}
