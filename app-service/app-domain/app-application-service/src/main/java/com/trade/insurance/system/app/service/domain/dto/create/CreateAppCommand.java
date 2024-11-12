package com.trade.insurance.system.app.service.domain.dto.create;

import com.trade.insurance.system.domain.valueobject.CountryCode;
import com.trade.insurance.system.app.service.domain.valueobject.StreetAddress;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAppCommand {

    @NotNull
    private final UUID customerId;

    @NotNull
    private final String exporterName;

    @NotNull
    private final StreetAddress exporterAddress;

    @NotNull
    private final CountryCode importerCountryCode;

    @NotNull
    private final String importerName;

    @NotNull
    private final StreetAddress importerAddress;

    @NotNull
    private final String exportProduct;

    @NotNull
    private final BigDecimal appAmount;


}
