# 무역보험 서비스 아키텍처 설명

이 프로젝트는 <b>무역보험 서비스의 청약 및 심사 프로세스를 메시지 기반의 비동기 아키텍처</b>로 설계한 것입니다. 

메시지 기반 설계를 통해 **서비스 간의 낮은 결합도**와 **확장 가능한 아키텍처**를 유지할 수 있도록 노력함으로써

무역보험 서비스의 각 단계가 보다 효율적으로 처리될 수 있도록 설계하였습니다.

각 서비스는 독립적으로 분리되어 있으며, Kafka를 통해 필요한 메시지를 주고받으며 통신합니다. 

(프로젝트는 지속적으로 수정 및 보완되고 있으며, 추후 학습이 완료되는 대로 SAGA 패턴, Outbox 패턴, CQRS 패턴 등이 적용될 예정입니다.)

---

## 메시지 흐름

![Message Flow](https://github.com/mooni9375/tradeInsurance/blob/master/Message%20Flow.png)

기본적으로 각 서비스는 **Input Port**와 **Output Port**를 통해 메시지 수신과 발신을 수행합니다. 

이를 통해 각 서비스 간의 결합도를 낮추고, 메시지 기반의 비동기 통신 구조를 유지할 수 있습니다.

1. **App Service**
   - 청약 요청이 들어오면 `AppApplicationService`가 이를 처리하고, <b>청약 생성 이벤트(AppCreatedEvent)</b>를 발행
   - 발행된 이벤트는 `ReviewRequestMessagePublisher`를 통해 Kafka로 전달되며, `review-request-topic`으로 전달

2. **Review Service**
   - `review-request-topic`에서 청약 생성 이벤트를 수신하여 심사 프로세스를 시작
   - 심사가 완료되면 <b>심사 승인 이벤트(ReviewApprovedEvent)</b>를 발행하고, 이 이벤트는 다시 Kafka를 통해 `review-response-topic`으로 전달

3. **App Service**
   - `review-response-topic`에서 심사 승인 이벤트를 수신하여 청약 프로세스를 마무리

---

## Kafka 토픽 구성

![Message Topic](https://github.com/mooni9375/tradeInsurance/blob/master/Message%20Topic.png)

Kafka 토픽을 활용한 이 메시징 시스템은 서비스 간의 비동기 통신을 가능하게 하여 확장성과 안정성을 높입니다. 

또한 각 서비스는 메시지 발행과 수신만으로 작업을 수행하므로, 다른 서비스의 상태에 종속되지 않고 독립적으로 운영될 수 있습니다.

1. **review-request-topic**
   - `App Service`에서 발행된 **AppCreatedEvent**를 수신하여 `Review Service`가 심사 프로세스를 시작할 수 있도록 하는 토픽

2. **review-response-topic**
   - `Review Service`에서 발행된 **ReviewApprovedEvent**를 수신하여 `App Service`가 청약 프로세스를 마무리할 수 있도록 하는 토픽

(토픽 구성 설계 하단부 빈 영역은 추후 통지 프로세스 도입과 함께 확장될 영역입니다.)

---


