package com.trade.insurance.system.app.service.domain.ports.output.repository;

import com.trade.insurance.system.app.service.domain.entity.App;

import java.util.Optional;

public interface AppRepository {

    App save(App app);

    Optional<App> findByTrackingId(String trackingId);
}
