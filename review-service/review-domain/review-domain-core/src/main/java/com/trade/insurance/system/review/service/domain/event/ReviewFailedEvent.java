package com.trade.insurance.system.review.service.domain.event;

import com.trade.insurance.system.review.service.domain.entity.Review;

import java.time.ZonedDateTime;
import java.util.List;

public class ReviewFailedEvent extends ReviewEvent {

    public ReviewFailedEvent(Review review,
                             ZonedDateTime createdAt,
                             List<String> failureMessages) {
        super(review, createdAt, failureMessages);
    }
}
