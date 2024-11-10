package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.event.AppCreatedEvent;

public interface AppDomainService {

    AppCreatedEvent validateAndInitiateApp(App app);
}
