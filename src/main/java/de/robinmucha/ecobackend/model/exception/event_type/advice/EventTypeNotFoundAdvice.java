package de.robinmucha.ecobackend.model.exception.event_type.advice;

import de.robinmucha.ecobackend.model.exception.event_type.EventTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EventTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String eventTypeNotFoundHandler(EventTypeNotFoundException e) {
        return e.getMessage();
    }
}
