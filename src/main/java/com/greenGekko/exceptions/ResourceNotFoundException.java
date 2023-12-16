package com.greenGekko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

}
