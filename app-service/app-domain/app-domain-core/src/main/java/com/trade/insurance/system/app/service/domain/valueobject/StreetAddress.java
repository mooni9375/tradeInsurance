package com.trade.insurance.system.app.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StreetAddress {

    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;


    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(street, that.street) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city);
    }
}
