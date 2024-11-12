package com.trade.insurance.system.app.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class AppStreetAddress {

    @NotNull
    @Max(value = 50)
    private final String street;

    @NotNull
    @Max(value = 50)
    private final String city;

    @NotNull
    @Max(value = 50)
    private final String postalCode;
}
