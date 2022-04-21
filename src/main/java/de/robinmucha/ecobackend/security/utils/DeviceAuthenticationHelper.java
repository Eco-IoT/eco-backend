package de.robinmucha.ecobackend.security.utils;

import de.robinmucha.ecobackend.model.entity.Device;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.device.DeviceAccessForbiddenException;
import de.robinmucha.ecobackend.model.exception.device.DeviceCreationForbiddenException;

public class DeviceAuthenticationHelper {

    private static boolean isUserOwnerOfDevice(User user, Device device) {
        return user.equals(device.getOwner());
    }

    public static void isUserOwnerOfDeviceElseThrowDeviceAccessForbiddenEx(User requestedUser, Device requestedDevice) {
        if (!isUserOwnerOfDevice(requestedUser, requestedDevice)) {
            throw new DeviceAccessForbiddenException(requestedUser.getName(), requestedDevice.getId());
        }
    }

    public static void isUserOwnerOfDeviceElseThrowDeviceCreationForbiddenEx(Device newDevice, User owner) {
        if (!isUserOwnerOfDevice(owner, newDevice)) {
            throw new DeviceCreationForbiddenException(owner.getName(), newDevice.getId());
        }
    }

    public static void isSameDeviceElseThrowDeviceAccessForbiddenEx(String loggedInDeviceName, Device requestedDevice) {
        if (!isSameDevice(loggedInDeviceName, requestedDevice)) {
            throw new DeviceAccessForbiddenException(loggedInDeviceName, requestedDevice.getId());
        }
    }

    private static boolean isSameDevice(String loggedInDeviceName, Device requestedDevice) {
        return loggedInDeviceName.equals(requestedDevice.getName());
    }

}
