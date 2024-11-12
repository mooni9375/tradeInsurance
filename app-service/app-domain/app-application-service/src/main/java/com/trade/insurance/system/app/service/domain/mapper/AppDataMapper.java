package com.trade.insurance.system.app.service.domain.mapper;

import com.trade.insurance.system.app.service.domain.dto.create.AppStreetAddress;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppResponse;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.valueobject.StreetAddress;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import com.trade.insurance.system.domain.valueobject.Money;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppDataMapper {

    public App createAppCommandToApp(CreateAppCommand createAppCommand) {
        return App.builder()
                .customerId(new CustomerId(createAppCommand.getCustomerId()))
                .exporterName(createAppCommand.getExporterName())
                .exporterAddress(appStreetAdderToStreetAddress(createAppCommand.getExporterAddress()))
                .importerCountryCode(createAppCommand.getImporterCountryCode())
                .importerName(createAppCommand.getImporterName())
                .importerAddress(appStreetAdderToStreetAddress(createAppCommand.getImporterAddress()))
                .appAmount(new Money(createAppCommand.getAppAmount()))
                .exportProduct(createAppCommand.getExportProduct())
                .build();
    }

    public CreateAppResponse appToCreateAppResponse(App app, String message) {
        return CreateAppResponse.builder()
                .appTrackingId(app.getTrackingId().getValue())
                .appStatus(app.getAppStatus())
                .message(message)
                .build();
    }

    public TrackAppResponse appToTrackAppResponse(App app) {
        return TrackAppResponse.builder()
                .appTrackingId(app.getTrackingId().getValue())
                .appStatus(app.getAppStatus())
                .failureMessages(app.getFailureMessages())
                .build();
    }

    private StreetAddress appStreetAdderToStreetAddress(AppStreetAddress appStreetAddress) {
        return new StreetAddress(UUID.randomUUID(),
                appStreetAddress.getStreet(),
                appStreetAddress.getCity(),
                appStreetAddress.getPostalCode());
    }
}
