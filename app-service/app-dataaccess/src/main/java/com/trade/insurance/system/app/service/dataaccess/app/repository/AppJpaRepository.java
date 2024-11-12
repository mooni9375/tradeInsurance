package com.trade.insurance.system.app.service.dataaccess.app.repository;

import com.trade.insurance.system.app.service.dataaccess.app.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppJpaRepository extends JpaRepository<AppEntity, UUID> {

    Optional<AppEntity> findByTrackingId(UUID trackingId);
}
