package com.trade.insurance.system.app.service.domain.ports.input.service;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import jakarta.validation.Valid;

public interface AppApplicationService {

    CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand);

}
