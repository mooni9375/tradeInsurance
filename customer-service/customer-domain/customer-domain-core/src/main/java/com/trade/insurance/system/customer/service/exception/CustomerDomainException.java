package com.trade.insurance.system.customer.service.exception;

import com.trade.insurance.system.customer.service.entity.Customer;
import com.trade.insurance.system.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }

    public CustomerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
