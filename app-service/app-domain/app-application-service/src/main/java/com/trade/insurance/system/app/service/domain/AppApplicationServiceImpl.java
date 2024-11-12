package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppQuery;
import com.trade.insurance.system.app.service.domain.dto.track.TrackAppResponse;
import com.trade.insurance.system.app.service.domain.ports.input.service.AppApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class AppApplicationServiceImpl implements AppApplicationService {

    private final AppCreateCommandHandler appCreateCommandHandler;
    private final AppTrackCommandHandler appTrackCommandHandler;

    public AppApplicationServiceImpl(AppCreateCommandHandler appCreateCommandHandler,
                                     AppTrackCommandHandler appTrackCommandHandler) {
        this.appCreateCommandHandler = appCreateCommandHandler;
        this.appTrackCommandHandler  = appTrackCommandHandler;
    }

    @Override
    public CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand) {
        return appCreateCommandHandler.createApp(createAppCommand);
    }

    @Override
    public TrackAppResponse trackApp(TrackAppQuery trackAppQuery) {
        return appTrackCommandHandler.trackApp(trackAppQuery);
    }
}
