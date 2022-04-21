package de.robinmucha.ecobackend.model.exception.event_type.advice;

import de.robinmucha.ecobackend.model.exception.event_type.EventTypeAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventTypeAccessForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(EventTypeAccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String eventTypeAccessForbiddenHandler(EventTypeAccessForbiddenException e) {
        return e.getMessage();
    }
}
