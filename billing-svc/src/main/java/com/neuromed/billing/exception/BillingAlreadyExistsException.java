package com.neuromed.billing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BillingAlreadyExistsException extends RuntimeException {

    public BillingAlreadyExistsException(String message) {
        super(message);
    }
}
