package com.trade.insurance.system.review.service.domain.event;

import com.trade.insurance.system.review.service.domain.entity.Review;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class ReviewRejectedEvent extends ReviewEvent {

    public ReviewRejectedEvent(Review review,
                               ZonedDateTime createdAt) {
        super(review, createdAt, Collections.emptyList());
    }
}