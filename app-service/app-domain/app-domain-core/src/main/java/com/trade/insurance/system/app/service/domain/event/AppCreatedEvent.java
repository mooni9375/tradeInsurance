package com.trade.insurance.system.app.service.domain.event;

import com.trade.insurance.system.app.service.domain.entity.App;

import java.time.ZonedDateTime;

public class AppCreatedEvent extends AppEvent {

        public AppCreatedEvent(App app, ZonedDateTime createdAt) {
            super(app, createdAt);
        }
}
