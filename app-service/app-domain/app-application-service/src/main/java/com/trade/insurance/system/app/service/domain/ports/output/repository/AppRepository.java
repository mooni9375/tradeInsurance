package com.trade.insurance.system.app.service.domain.ports.output.repository;

import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.valueobject.TrackingId;
import com.trade.insurance.system.domain.valueobject.AppId;

import java.util.Optional;

public interface AppRepository {

    App save(App app);

    Optional<App> findByTrackingId(TrackingId trackingId);
}
