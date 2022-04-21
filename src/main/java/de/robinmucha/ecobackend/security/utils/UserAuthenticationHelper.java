package de.robinmucha.ecobackend.security.utils;

import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.user.UserAccessForbiddenException;

public class UserAuthenticationHelper {

    public static void isSameUserElseThrowUserAccessForbiddenEx(String loggedInUserName, User requestedUser) {
        if (!isSameUser(loggedInUserName, requestedUser)) {
            throw new UserAccessForbiddenException(loggedInUserName, requestedUser.getId());
        }
    }

    private static boolean isSameUser(String loggedInUserName, User requestedUser) {
        return loggedInUserName.equals(requestedUser.getName());
    }

}
