package com.trade.insurance.system.app.service.domain.ports.output.repository;

import com.trade.insurance.system.app.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
}
