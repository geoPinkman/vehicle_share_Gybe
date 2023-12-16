package com.greenGekko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InputDataException extends ResponseStatusException {
    public InputDataException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public InputDataException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
