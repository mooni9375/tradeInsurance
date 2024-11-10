package com.trade.insurance.system.app.service.domain.entity;

import com.trade.insurance.system.domain.entity.AggregateRoot;
import com.trade.insurance.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

}
