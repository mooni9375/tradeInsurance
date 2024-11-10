package com.trade.insurance.system.app.service.domain;

import com.trade.insurance.system.app.service.domain.dto.create.CreateAppCommand;
import com.trade.insurance.system.app.service.domain.dto.create.CreateAppResponse;
import com.trade.insurance.system.app.service.domain.event.AppCreatedEvent;
import com.trade.insurance.system.app.service.domain.mapper.AppDataMapper;
import com.trade.insurance.system.app.service.domain.ports.output.message.publisher.AppCreatedReviewRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Handler : 이벤트 발행
 * Helper  : 트랜잭션 처리
 *  ㄴ 일관성 유지를 위해 이벤트 발행을 Helper에서 처리하지 않고 Handler에서 처리
 *
 *  이벤트 생성과 발행의 역할 분리
 *   ㄴ 도메인 계층에서는 오직 이벤트 객체를 생성하여 반환하기만 하고
 *   ㄴ 실제 발행 시점과 방식은 애플리케이션 서비스가 결정.
 */
@Slf4j
@Component
public class AppCreateCommandHandler {

    private final AppCreateHelper appCreateHelper;

    private final AppDataMapper appDataMapper;

    private final AppCreatedReviewRequestMessagePublisher appCreatedReviewRequestMessagePublisher;

    public AppCreateCommandHandler(AppCreateHelper appCreateHelper,
                                   AppDataMapper appDataMapper,
                                   AppCreatedReviewRequestMessagePublisher appCreatedReviewRequestMessagePublisher) {
        this.appCreateHelper = appCreateHelper;
        this.appDataMapper = appDataMapper;
        this.appCreatedReviewRequestMessagePublisher = appCreatedReviewRequestMessagePublisher;
    }

    public CreateAppResponse createApp(CreateAppCommand createAppCommand) {

        // 트랜잭션 처리 by Helper
        AppCreatedEvent appCreatedEvent = appCreateHelper.persistApp(createAppCommand);
        log.info("App is created with id: {}", appCreatedEvent.getApp().getId().getValue());

        // 이벤트 발행 by Handler
        appCreatedReviewRequestMessagePublisher.publish(appCreatedEvent);

        return appDataMapper.appToCreateAppResponse(appCreatedEvent.getApp(), "App created successfully");
    }
}
