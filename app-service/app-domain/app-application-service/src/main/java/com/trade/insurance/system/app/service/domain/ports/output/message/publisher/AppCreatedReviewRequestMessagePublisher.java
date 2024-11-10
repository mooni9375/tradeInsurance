package com.trade.insurance.system.app.service.domain.ports.output.message.publisher;

import com.trade.insurance.system.app.service.domain.event.AppCreatedEvent;
import com.trade.insurance.system.domain.DomainEventPublisher;

public interface AppCreatedReviewRequestMessagePublisher extends DomainEventPublisher<AppCreatedEvent> {
}
