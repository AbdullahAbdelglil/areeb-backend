package com.eventbooking.web.errors;

import java.util.List;

public class  ValidationErrorResponse {
    private int status;
    private List<String> errors;

    public ValidationErrorResponse(int status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
