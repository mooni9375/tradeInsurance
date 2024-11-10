package com.trade.insurance.system.app.service.domain.valueobject;

import com.trade.insurance.system.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
