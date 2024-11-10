package com.trade.insurance.system.app.service.domain.exception;

import com.trade.insurance.system.domain.exception.DomainException;

public class AppDomainException extends DomainException {

    public AppDomainException(String message) {
        super(message);
    }

    public AppDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}


