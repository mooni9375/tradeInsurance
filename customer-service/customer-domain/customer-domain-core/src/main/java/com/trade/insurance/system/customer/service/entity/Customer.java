package com.trade.insurance.system.customer.service.entity;

import com.trade.insurance.system.domain.entity.AggregateRoot;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import com.trade.insurance.system.domain.valueobject.PhoneNumber;

public class Customer extends AggregateRoot<CustomerId> {
    private final String enterpriseName;
    private final PhoneNumber phoneNumber;

    public Customer(CustomerId customerId,
                    String enterpriseName,
                    PhoneNumber phoneNumber) {
        super.setId(customerId);
        this.enterpriseName = enterpriseName;
        this.phoneNumber = phoneNumber;
    }
}
