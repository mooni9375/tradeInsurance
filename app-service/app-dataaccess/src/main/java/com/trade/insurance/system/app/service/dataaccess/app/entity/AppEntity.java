package com.trade.insurance.system.app.service.dataaccess.app.entity;
import com.trade.insurance.system.domain.valueobject.AppStatus;
import com.trade.insurance.system.domain.valueobject.CountryCode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app")
@Entity
public class AppEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private UUID trackingId;

    private String exporterName;
//    @Embedded
//    @AttributeOverrides({
//                    @AttributeOverride(name = "street", column = @Column(name = "exporter_street")),
//                    @AttributeOverride(name = "postalCode", column = @Column(name = "exporter_postal_code")),
//                    @AttributeOverride(name = "city", column = @Column(name = "exporter_city"))
//    })
    @OneToOne(mappedBy = "app", cascade = CascadeType.ALL)
    private AppStreetAddressEntity exporterAddress;

    private CountryCode importerCountryCode;
    private String importerName;
    @OneToOne(mappedBy = "app", cascade = CascadeType.ALL)
    private AppStreetAddressEntity importerAddress;

    private String exportProduct;
    //@Convert(converter = MoneyConverter.class) -> BigDecimal
    private BigDecimal appAmount;
    @Enumerated(EnumType.STRING)
    private AppStatus appStatus;

    private String failureMessages;

    protected AppEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppEntity appEntity = (AppEntity) o;
        return Objects.equals(id, appEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
