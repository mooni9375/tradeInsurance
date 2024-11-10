package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.ports.output.message.publisher.AppCreatedReviewRequestMessagePublisher;
import com.trade.insurance.system.app.service.domain.ports.output.repository.AppRepository;
import com.trade.insurance.system.app.service.domain.ports.output.repository.CustomerRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages =  "com.trade.insurance.system")
public class AppTestConfiguration {

    @Bean
    public AppCreatedReviewRequestMessagePublisher appCreatedReviewRequestMessagePublisher() {
        return Mockito.mock(AppCreatedReviewRequestMessagePublisher.class);
    }


    @Bean
    public AppRepository appRepository() {
        return Mockito.mock(AppRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }


    // AppDomainService : mock 객체를 사용하지 않고, 실제 객체를 사용
    @Bean
    public AppDomainService appDomainService() {
        return new AppDomainServiceImpl();
    }
}
