package de.robinmucha.ecobackend.model.exception.event.advice;

import de.robinmucha.ecobackend.model.exception.event.EventCreationForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventCreationForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(EventCreationForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String eventCreationForbiddenHandler(EventCreationForbiddenException e) {
        return e.getMessage();
    }
}
