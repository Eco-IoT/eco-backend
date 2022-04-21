package de.robinmucha.ecobackend.model.exception.device_type.advice;

import de.robinmucha.ecobackend.model.exception.device_type.DeviceTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeviceTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String deviceTypeNotFoundHandler(DeviceTypeNotFoundException e) {
        return e.getMessage();
    }
}
