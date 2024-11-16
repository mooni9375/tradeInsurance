package com.trade.insurance.system.app.service.dataaccess.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * 고객 존재 여부만 확인하기 위한 Entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_customer_m_view", schema = "customer")
@Entity
public class CustomerEntity {

    @Id
    private UUID id;
}
