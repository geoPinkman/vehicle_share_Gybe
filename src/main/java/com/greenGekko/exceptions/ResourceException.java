package com.greenGekko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceException extends ResponseStatusException {
    public ResourceException() {
        super(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    public ResourceException(String reason) {
        super(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, reason);
    }
}
