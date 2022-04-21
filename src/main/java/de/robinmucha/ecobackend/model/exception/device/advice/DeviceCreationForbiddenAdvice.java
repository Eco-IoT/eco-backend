package de.robinmucha.ecobackend.model.exception.device.advice;

import de.robinmucha.ecobackend.model.exception.device.DeviceCreationForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeviceCreationForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceCreationForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String deviceCreationForbiddenHandler(DeviceCreationForbiddenException e) {
        return e.getMessage();
    }
}
