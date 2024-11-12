package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.entity.Customer;
import com.trade.insurance.system.app.service.domain.exception.AppDomainException;
import com.trade.insurance.system.app.service.domain.mapper.AppDataMapper;
import com.trade.insurance.system.app.service.domain.ports.input.service.AppApplicationService;
import com.trade.insurance.system.app.service.domain.ports.output.repository.AppRepository;
import com.trade.insurance.system.app.service.domain.ports.output.repository.CustomerRepository;
import com.trade.insurance.system.app.service.domain.valueobject.StreetAddress;
import com.trade.insurance.system.domain.valueobject.AppId;
import com.trade.insurance.system.domain.valueobject.AppStatus;
import com.trade.insurance.system.domain.valueobject.CountryCode;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = AppTestConfiguration.class)
public class AppApplicationServiceTest {

    // Configuration 클래스에 정의된 Bean 주입
    // 단 다른 클래스들과 다르게, AppDomainService 객체는 Mocking 된 객체가 아닌 실제 객체를 사용

    @Autowired
    private AppApplicationService appApplicationService;

    @Autowired
    private AppDataMapper appDataMapper;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private CustomerRepository customerRepository;


    // DTO for Test
    private CreateAppCommand createAppCommand;
    private CreateAppCommand createAppCommandAppAmountIsUnder5000;
    private CreateAppCommand createAppCommandAppAmountIsNotUnitOf1000;

    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID APP_ID      = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");

    private final BigDecimal APP_AMOUNT = new BigDecimal("7000.00");

    @BeforeAll
    public void init() {
        createAppCommand = CreateAppCommand.builder()
                .customerId(CUSTOMER_ID)
                .exporterName("Exporter Tester")
                .exporterAddress(StreetAddress.builder()
                                            .city("SEOUL")
                                            .street("JONGRO")
                                            .postalCode("12345")
                                            .build())
                .importerCountryCode(CountryCode.UNITED_KINGDOM)
                .importerName("Importer Exeter")
                .importerAddress(StreetAddress.builder()
                                            .city("EXETER")
                                            .street("EXETER STREET")
                                            .postalCode("77777")
                                            .build())
                .exportProduct("Test Product")
                .appAmount(new BigDecimal("7000.00"))
                .build();

        createAppCommandAppAmountIsUnder5000 = CreateAppCommand.builder()
                .customerId(CUSTOMER_ID)
                .exporterName("Exporter Tester")
                .exporterAddress(StreetAddress.builder()
                        .city("SEOUL")
                        .street("JONGRO")
                        .postalCode("12345")
                        .build())
                .importerCountryCode(CountryCode.UNITED_KINGDOM)
                .importerName("Importer Exeter")
                .importerAddress(StreetAddress.builder()
                        .city("EXETER")
                        .street("EXETER STREET")
                        .postalCode("77777")
                        .build())
                .exportProduct("Test Product")
                .appAmount(new BigDecimal("4999.00"))
                .build();

        createAppCommandAppAmountIsNotUnitOf1000 = CreateAppCommand.builder()
                .customerId(CUSTOMER_ID)
                .exporterName("Exporter Tester")
                .exporterAddress(StreetAddress.builder()
                        .city("SEOUL")
                        .street("JONGRO")
                        .postalCode("12345")
                        .build())
                .importerCountryCode(CountryCode.UNITED_KINGDOM)
                .importerName("Importer Exeter")
                .importerAddress(StreetAddress.builder()
                        .city("EXETER")
                        .street("EXETER STREET")
                        .postalCode("77777")
                        .build())
                .exportProduct("Test Product")
                .appAmount(new BigDecimal("7500.00"))
                .build();


        // Customer 생성
        Customer customer = new Customer(new CustomerId(CUSTOMER_ID));

        // App 생성
        App app = appDataMapper.createAppCommandToApp(createAppCommand);
        app.setId(new AppId(APP_ID));


        // Mocking - customerRepository.findCustomer(CUSTOMER_ID) 호출 시, Optional.of(customer) 반환
        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        // Mocking - appRepository.save(app) 호출 시, app 반환
        when(appRepository.save(any(App.class))).thenReturn(app);
    }

    @Test
    public void testCreateApp() {
        CreateAppResponse createAppResponse = appApplicationService.createApp(createAppCommand);

        log.info("createAppResponse.getMessage() :: >> " + createAppResponse.getMessage());
        assertEquals(AppStatus.SUBMITTED, createAppResponse.getAppStatus());
        assertEquals("App created successfully", createAppResponse.getMessage());
    }

    @Test
    public void testAppAmountIsUnder5000() {
        AppDomainException appDomainException = assertThrows(AppDomainException.class,
                () -> appApplicationService.createApp(createAppCommandAppAmountIsUnder5000));

        assertEquals("App amount must be Greater than $ 5,000", appDomainException.getMessage());
    }

    @Test
    public void testAppAmountIsNotUnitOf1000() {
        AppDomainException appDomainException = assertThrows(AppDomainException.class,
                () -> appApplicationService.createApp(createAppCommandAppAmountIsNotUnitOf1000));

        assertEquals("App amount is not multiple of $ 1,000", appDomainException.getMessage());
    }


}
