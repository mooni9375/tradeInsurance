package com.trade.insurance.system.app.service.domain.exception;

import com.trade.insurance.system.domain.exception.DomainException;

public class AppNotFoundException extends DomainException {

    public AppNotFoundException(String message) {
        super(message);
    }

    public AppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
