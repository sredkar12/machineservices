package com.in28minutes.springboot.machineservices.service;

public class ErrorrResponse {
    private String resourceKey;
    private String errorCode;
    private String message;

    public String getResourceKey() {
        return resourceKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorrResponse(String resourceKey, String errorCode, String message) {
        super();
        this.resourceKey = resourceKey;
        this.errorCode = errorCode;
        this.message = message;
    }
}
