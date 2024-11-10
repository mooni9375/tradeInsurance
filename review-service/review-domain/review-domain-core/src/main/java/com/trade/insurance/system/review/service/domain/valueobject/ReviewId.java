package com.trade.insurance.system.review.service.domain.valueobject;

import com.trade.insurance.system.domain.entity.BaseEntity;
import com.trade.insurance.system.domain.valueobject.BaseId;

import java.util.UUID;

public class ReviewId extends BaseId<UUID> {
    public ReviewId(UUID value) {
        super(value);
    }
}
