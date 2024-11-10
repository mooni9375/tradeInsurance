package com.trade.insurance.system.review.service.domain.exception;

import com.trade.insurance.system.domain.exception.DomainException;

public class ReviewDomainException extends DomainException {

    public ReviewDomainException(String message) {
        super(message);
    }

    public ReviewDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
