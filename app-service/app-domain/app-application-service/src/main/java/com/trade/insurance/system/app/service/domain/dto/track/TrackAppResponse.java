package com.trade.insurance.system.app.service.domain.dto.track;

import com.trade.insurance.system.domain.valueobject.AppStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackAppResponse {

    @NotNull
    private final UUID appTrackingId;

    @NotNull
    private final AppStatus appStatus;

    private final List<String> failureMessages;
}
