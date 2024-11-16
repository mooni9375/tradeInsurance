package com.trade.insurance.system.app.service.dataaccess.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReviewEntityId.class)
@Table(name = "app_review_m_view", schema = "review")
@Entity
public class ReviewEntity {

    @Id
    private UUID reviewId;



}
