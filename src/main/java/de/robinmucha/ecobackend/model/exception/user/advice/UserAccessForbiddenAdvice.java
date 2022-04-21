package de.robinmucha.ecobackend.model.exception.user.advice;

import de.robinmucha.ecobackend.model.exception.user.UserAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAccessForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(UserAccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String userAccessForbiddenHandler(UserAccessForbiddenException e) {
        return e.getMessage();
    }
}
