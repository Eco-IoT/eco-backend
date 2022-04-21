package de.robinmucha.ecobackend.model.exception.device_type.advice;

import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeCreationForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeviceTypeCreationForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceTypeCreationForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String deviceTypeCreationForbiddenHandler(DeviceTypeCreationForbiddenException e) {
        return e.getMessage();
    }
}
