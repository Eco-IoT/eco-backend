package de.robinmucha.ecobackend.model.exception.device_type.advice;

import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeviceTypeAccessForbiddenAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceTypeAccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String deviceTypeAccessForbiddenHandler(DeviceTypeAccessForbiddenException e) {
        return e.getMessage();
    }
}
