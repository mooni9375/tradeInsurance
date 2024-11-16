package com.trade.insurance.system.app.service.dataaccess.review.entity;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntityId {

    private UUID reviewId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntityId that = (ReviewEntityId) o;
        return Objects.equals(reviewId, that.reviewId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reviewId);
    }
}
