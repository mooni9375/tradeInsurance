package com.trade.insurance.system.app.service.domain.event;

import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class AppEvent implements DomainEvent<App> {

    private final App app;
    private final ZonedDateTime createdAt;

    public AppEvent(App app, ZonedDateTime createdAt) {
        this.app = app;
        this.createdAt = createdAt;
    }

    public App getApp() {
        return app;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
