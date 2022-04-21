package de.robinmucha.ecobackend.model.exception.device.advice;

import de.robinmucha.ecobackend.model.exception.device.DeviceAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeviceAccessForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceAccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String deviceAccessForbiddenHandler(DeviceAccessForbiddenException e) {
        return e.getMessage();
    }
}
