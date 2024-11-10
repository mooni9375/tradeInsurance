package com.trade.insurance.system.domain;

import com.trade.insurance.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent>{

    void publish(T domainEvent);

}


