package com.trade.insurance.system.app.service.dataaccess.customer.adapter;

import com.trade.insurance.system.app.service.dataaccess.customer.entity.CustomerEntity;
import com.trade.insurance.system.app.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.trade.insurance.system.app.service.dataaccess.customer.repository.CustomerJpaRepository;
import com.trade.insurance.system.app.service.domain.entity.Customer;
import com.trade.insurance.system.app.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerDataAccessMapper::customerEntityToCustomer);
//              .map(customerEntity -> customerDataAccessMapper.customerEntityToCustomer(customerEntity));

    }
}
