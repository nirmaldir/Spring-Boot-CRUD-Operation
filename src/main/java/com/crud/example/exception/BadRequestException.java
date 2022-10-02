package com.crud.example.exception;

import java.util.List;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public BadRequestException(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }
}
