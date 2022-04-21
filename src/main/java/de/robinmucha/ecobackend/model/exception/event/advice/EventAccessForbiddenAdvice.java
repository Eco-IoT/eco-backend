package de.robinmucha.ecobackend.model.exception.event.advice;

import de.robinmucha.ecobackend.model.exception.event.EventAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventAccessForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(EventAccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String eventAccessForbiddenHandler(EventAccessForbiddenException e) {
        return e.getMessage();
    }
}
