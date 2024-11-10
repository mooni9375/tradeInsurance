package com.trade.insurance.system.review.service.domain.exception;

import com.trade.insurance.system.domain.exception.DomainException;

public class ReviewNotFoundException extends DomainException {

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
