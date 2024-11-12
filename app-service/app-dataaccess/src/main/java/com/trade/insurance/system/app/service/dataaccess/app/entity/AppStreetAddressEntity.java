package com.trade.insurance.system.app.service.dataaccess.app.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

//@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_address")
@Entity
public class AppStreetAddressEntity {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "APP_ID")
    private AppEntity app;

    private String street;
    private String postalCode;
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppStreetAddressEntity that = (AppStreetAddressEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
