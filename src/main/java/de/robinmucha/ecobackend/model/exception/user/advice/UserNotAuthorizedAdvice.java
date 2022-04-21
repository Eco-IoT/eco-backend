package de.robinmucha.ecobackend.model.exception.user.advice;

import de.robinmucha.ecobackend.model.exception.user.UserNotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotAuthorizedAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String userNotAuthorizedHandler(UserNotAuthorizedException e) {
        return e.getMessage();
    }
}
