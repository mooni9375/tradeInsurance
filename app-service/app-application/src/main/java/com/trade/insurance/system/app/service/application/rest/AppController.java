package com.trade.insurance.system.app.service.application.rest;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppQuery;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppResponse;
import com.trade.insurance.system.app.service.domain.ports.input.service.AppApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/app", produces = "application/vnd.api.v1+json")
public class AppController {

    private final AppApplicationService appApplicationService;

    public AppController(AppApplicationService appApplicationService) {
        this.appApplicationService = appApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateAppResponse> createApp(@RequestBody CreateAppCommand createAppCommand) {
        log.info("Creating app for customer: {}", createAppCommand.getCustomerId());

        CreateAppResponse createAppResponse = appApplicationService.createApp(createAppCommand);
        log.info("App created with tracking id: {}", createAppResponse.getAppTrackingId());

        return ResponseEntity.ok(createAppResponse);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackAppResponse> getAppByTrackingId(@PathVariable UUID trackingId) {
        TrackAppResponse trackAppResponse =
                appApplicationService.trackApp(TrackAppQuery.builder().appTrackingId(trackingId).build());
        log.info("Returning app with tracking id: {}", trackAppResponse.getAppTrackingId());

        return ResponseEntity.ok(trackAppResponse);
    }
}
