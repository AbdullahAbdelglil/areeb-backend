package com.eventbooking.web.errors;

public class ErrorResponse {
    private String message;
    private String errorKey;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String errorKey) {
        this.message = message;
        this.errorKey = errorKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }
}
