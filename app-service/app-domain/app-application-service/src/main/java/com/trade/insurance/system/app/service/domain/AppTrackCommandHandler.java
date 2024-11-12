package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.dto.track.TrackAppQuery;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppResponse;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.exception.AppNotFoundException;
import com.trade.insurance.system.app.service.domain.mapper.AppDataMapper;
import com.trade.insurance.system.app.service.domain.ports.output.repository.AppRepository;
import com.trade.insurance.system.app.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class AppTrackCommandHandler {

    private final AppDataMapper appDataMapper;

    private final AppRepository appRepository;

    public AppTrackCommandHandler(AppDataMapper appDataMapper,
                                  AppRepository appRepository) {
        this.appDataMapper = appDataMapper;
        this.appRepository = appRepository;
    }

    @Transactional(readOnly = true)
    public TrackAppResponse trackApp(TrackAppQuery trackAppQuery) {
        Optional<App> appResult =
                appRepository.findByTrackingId(new TrackingId(trackAppQuery.getAppTrackingId()));

        if (appResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", trackAppQuery.getAppTrackingId());
            throw new AppNotFoundException("Could not find order with tracking id: " +
                    trackAppQuery.getAppTrackingId());
        }
        return appDataMapper.appToTrackAppResponse(appResult.get());
    }


}
