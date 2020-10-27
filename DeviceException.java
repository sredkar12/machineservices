package com.in28minutes.springboot.machineservices.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeviceException extends RuntimeException {
    public DeviceException(String message) {
        super(message);
    }
}
