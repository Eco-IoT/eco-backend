package de.robinmucha.ecobackend.model.exception.user.advice;

import de.robinmucha.ecobackend.model.exception.user.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String usernameAlreadyExistsHandler(UsernameAlreadyExistsException e) {
        return e.getMessage();
    }
}
