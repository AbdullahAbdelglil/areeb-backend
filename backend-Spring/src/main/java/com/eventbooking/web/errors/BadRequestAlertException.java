package com.eventbooking.web.errors;


public class BadRequestAlertException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;
    private String errorKey;

    public BadRequestAlertException(String defaultMessage, String errorKey) {
        this.message = defaultMessage;
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
