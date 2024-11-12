package com.trade.insurance.system.app.service.dataaccess.app.mapper;

import com.trade.insurance.system.app.service.dataaccess.app.entity.AppEntity;
import com.trade.insurance.system.app.service.dataaccess.app.entity.AppStreetAddressEntity;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.valueobject.StreetAddress;
import com.trade.insurance.system.app.service.domain.valueobject.TrackingId;
import com.trade.insurance.system.domain.valueobject.AppId;
import com.trade.insurance.system.domain.valueobject.CustomerId;
import com.trade.insurance.system.domain.valueobject.Money;

import java.util.ArrayList;
import java.util.Arrays;

import static com.trade.insurance.system.app.service.domain.entity.App.FAILURE_MESSAGE_DELIMITER;

public class AppDataAccessMapper {

    public AppEntity appToAppEntity(App app) {

        AppEntity appEntity = AppEntity.builder()
                .id(app.getId().getValue())
                .customerId(app.getCustomerId().getValue())
                .trackingId(app.getTrackingId().getValue())
                .exporterName(app.getExporterName())
                .exporterAddress(streetAddressToAppStreetAddressEntity(app.getExporterAddress()))
                .importerCountryCode(app.getImporterCountryCode())
                .importerName(app.getImporterName())
                .importerAddress(streetAddressToAppStreetAddressEntity(app.getImporterAddress()))
                .exportProduct(app.getExportProduct())
                .appAmount(app.getAppAmount().getAmount())
                .appStatus(app.getAppStatus())
                .failureMessages(app.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, app.getFailureMessages()) : "")
                .build();

        // 양방향 연관관계 설정
        appEntity.getExporterAddress().setApp(appEntity);

        return appEntity;
    }

    public App appEntityToApp(AppEntity appEntity) {
        return App.builder()
                .appId(new AppId(appEntity.getId()))
                .customerId(new CustomerId(appEntity.getCustomerId()))
                .exporterName(appEntity.getExporterName())
                .exporterAddress(appStreetAddressEntityToStreetAddress(appEntity.getExporterAddress()))
                .importerCountryCode(appEntity.getImporterCountryCode())
                .importerName(appEntity.getImporterName())
                .importerAddress(appStreetAddressEntityToStreetAddress(appEntity.getImporterAddress()))
                .exportProduct(appEntity.getExportProduct())
                .appAmount(new Money(appEntity.getAppAmount()))
                .appStatus(appEntity.getAppStatus())
                .trackingId(new TrackingId(appEntity.getTrackingId()))
                .failureMessages(appEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(appEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }


    private AppStreetAddressEntity streetAddressToAppStreetAddressEntity(StreetAddress streetAddress) {
        return AppStreetAddressEntity.builder()
                .id(streetAddress.getId())
                .street(streetAddress.getStreet())
                .postalCode(streetAddress.getPostalCode())
                .city(streetAddress.getCity())
                .build();
    }

    private StreetAddress appStreetAddressEntityToStreetAddress(AppStreetAddressEntity appStreetAddressEntity) {
        return new StreetAddress(appStreetAddressEntity.getId(),
                appStreetAddressEntity.getStreet(),
                appStreetAddressEntity.getPostalCode(),
                appStreetAddressEntity.getCity());
    }

}
