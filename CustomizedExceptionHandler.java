package com.in28minutes.springboot.machineservices.rest.exception;

import com.in28minutes.springboot.machineservices.service.ErrorrResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DeviceException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(DeviceException ex, WebRequest request) throws Exception {
        ErrorrResponse errorResponse = null;
        if (ex.getMessage().equals("Machine code invalid"))
            errorResponse = new ErrorrResponse("machine.code.invalid","ER001",
                    "The machine code is incorrect. Check the Machine code you provided and try again.");
        if (ex.getMessage().equals("Serial number invalid"))
            errorResponse = new ErrorrResponse("serial.number.invalid","ER003",
                    "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");

        if (ex.getMessage().equals("Machine code not found"))
            errorResponse = new ErrorrResponse("machine.code.not.found","ER002",
                    "The machine code does not match our records");
        if (ex.getMessage().equals("Serial number not found"))
            errorResponse = new ErrorrResponse("serial.number.not.found","ER004",
                    "The serial number does not match our records");

        return new ResponseEntity(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex, WebRequest request) {

        ErrorrResponse errorResponse = null;
        errorResponse = new ErrorrResponse("serial.number.invalid","ER003",
                "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");
        return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorrResponse errorResponse = null;
        errorResponse = new ErrorrResponse("serial.number.invalid","ER003",
                ex.getBindingResult().toString());
        return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
