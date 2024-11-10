package com.trade.insurance.system.app.service.domain.mapper;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import com.trade.insurance.system.domain.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class AppDataMapper {

    public App createAppCommandToApp(CreateAppCommand createAppCommand) {
        return App.builder()
                .customerId(new CustomerId(createAppCommand.getCustomerId()))
                .exporterName(createAppCommand.getExporterName())
                .exporterAddress(createAppCommand.getExporterAddress())
                .importerCountryCode(createAppCommand.getImporterCountryCode())
                .importerName(createAppCommand.getImporterName())
                .importerAddress(createAppCommand.getImporterAddress())
                .appAmount(new Money(createAppCommand.getAppAmount()))
                .build();
    }

    public CreateAppResponse appToCreateAppResponse(App app, String message) {
        return CreateAppResponse.builder()
                .appTrackingId(app.getTrackingId().getValue())
                .appStatus(app.getAppStatus())
                .message(message)
                .build();
    }
}
