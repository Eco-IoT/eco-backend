package de.robinmucha.ecobackend.model.exception.event_type.advice;

import de.robinmucha.ecobackend.model.exception.event_type.EventTypeCreationForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventTypeCreationForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(EventTypeCreationForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String eventTypeCreationForbiddenHandler(EventTypeCreationForbiddenException e) {
        return e.getMessage();
    }
}
