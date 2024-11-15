package com.trade.insurance.system.app.service.domain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class TrackAppQuery {

    @NotNull
    private final UUID appTrackingId;
}
