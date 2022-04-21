package de.robinmucha.ecobackend.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedUser {
    public static Authentication getCurrentlyAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
