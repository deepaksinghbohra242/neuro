package com.neuromed.pharmarcy.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PharmacyAlreadyExistsException extends RuntimeException {

    public PharmacyAlreadyExistsException(String message) {
        super(message);
    }

}
