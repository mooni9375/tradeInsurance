package com.trade.insurance.system.app.service.dataaccess.customer.mapper;

import com.trade.insurance.system.app.service.dataaccess.customer.entity.CustomerEntity;
import com.trade.insurance.system.app.service.domain.entity.Customer;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
