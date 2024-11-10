package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.entity.Customer;
import com.trade.insurance.system.app.service.domain.event.AppCreatedEvent;
import com.trade.insurance.system.app.service.domain.exception.AppDomainException;
import com.trade.insurance.system.app.service.domain.mapper.AppDataMapper;
import com.trade.insurance.system.app.service.domain.ports.output.message.publisher.AppCreatedReviewRequestMessagePublisher;
import com.trade.insurance.system.app.service.domain.ports.output.repository.AppRepository;
import com.trade.insurance.system.app.service.domain.ports.output.repository.CustomerRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AppCreateHelper {

    private final AppDomainService appDomainService;
    private final AppRepository appRepository;
    private final CustomerRepository customerRepository;
    private final AppDataMapper appDataMapper;

    public AppCreateHelper(AppDomainService appDomainService,
                           AppRepository appRepository,
                           CustomerRepository customerRepository,
                           AppDataMapper appDataMapper,
                           AppCreatedReviewRequestMessagePublisher appCreatedReviewRequestMessagePublisher) {
        this.appDomainService = appDomainService;
        this.appRepository = appRepository;
        this.customerRepository = customerRepository;
        this.appDataMapper = appDataMapper;
    }

    @Transactional
    public AppCreatedEvent persistApp(CreateAppCommand createAppCommand) {

        // Check if the customer exists.
        checkCustomer(createAppCommand.getCustomerId());

        // Create app
        App app = appDataMapper.createAppCommandToApp(createAppCommand);

        // Validate and initiate app and get the AppCreatedEvent.
        AppCreatedEvent appCreatedEvent = appDomainService.validateAndInitiateApp(app);

        // Save the app
        saveApp(app);
        log.info("App is created with id: {}", appCreatedEvent.getApp().getId().getValue());

        // Return the AppCreatedEvent for Publishing the event.
        return appCreatedEvent;
    }

    private void checkCustomer( UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("could not find customer with id: {}", customerId);
            throw new AppDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private App saveApp(App app) {
        App appResult = appRepository.save(app);

        if (appResult == null) {
            log.error("could not save app!");
            throw new AppDomainException("could not save app!");
        }

        log.info("app is saved with id: {}", appResult.getId().getValue());
        return appResult;
    }
}
