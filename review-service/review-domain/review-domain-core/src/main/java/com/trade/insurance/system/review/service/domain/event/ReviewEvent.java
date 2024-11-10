package com.trade.insurance.system.review.service.domain.event;

import com.trade.insurance.system.domain.event.DomainEvent;
import com.trade.insurance.system.review.service.domain.entity.Review;

import java.time.ZonedDateTime;
import java.util.List;

public class ReviewEvent implements DomainEvent<Review> {

    private final Review review;
    private ZonedDateTime createdAt;
    private final List<String> failureMessages;

    public ReviewEvent(Review review, ZonedDateTime createdAt, List<String> failureMessages) {
        this.review = review;
        this.createdAt = createdAt;
        this.failureMessages = failureMessages;
    }

    public Review getReview() {
        return review;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
