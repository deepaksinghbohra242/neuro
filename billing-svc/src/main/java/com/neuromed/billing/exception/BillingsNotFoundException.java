package com.neuromed.billing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BillingsNotFoundException extends RuntimeException {

    public BillingsNotFoundException(Long id) {
        super(String.format("Billing record not found with ID: '%s'", id));
    }
}
