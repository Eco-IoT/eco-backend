package de.robinmucha.ecobackend.security.utils;

import de.robinmucha.ecobackend.model.entity.DeviceType;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeAccessForbiddenException;
import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeCreationForbiddenException;

public class DeviceTypeAuthenticationHelper {

    private static boolean isUserOwnerOfDeviceType(User user, DeviceType deviceType) {
        return user.equals(deviceType.getOwner());
    }

    public static void isUserOwnerOfDeviceTypeElseThrowDeviceTypeAccessForbiddenEx(User requestedUser, DeviceType requestedDeviceType) {
        if (!isUserOwnerOfDeviceType(requestedUser, requestedDeviceType)) {
            throw new DeviceTypeAccessForbiddenException(requestedUser.getName(), requestedDeviceType.getId());
        }
    }

    public static void isUserOwnerOfDeviceTypeElseThrowDeviceTypeCreationForbiddenEx(DeviceType newDeviceType, User owner) {
        if (!isUserOwnerOfDeviceType(owner, newDeviceType)) {
            throw new DeviceTypeCreationForbiddenException(owner.getName(), newDeviceType.getId());
        }
    }

}
