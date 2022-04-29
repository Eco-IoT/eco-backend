package de.robinmucha.ecobackend.model.exception.device.advice;

import de.robinmucha.ecobackend.model.exception.device.DevicenameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DevicenameAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(DevicenameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String devicenameAlreadyExistsHandler(DevicenameAlreadyExistsException e) {
        return e.getMessage();
    }
}
