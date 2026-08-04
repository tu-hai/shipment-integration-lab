# Task 6 - Message Broker Integration

## Objective

Introduce asynchronous communication for shipment workflows using RabbitMQ.

## Scope

- Broker setup assets in `infra/rabbitmq/`
- Producer in order-service
- Consumer in shipment-integration-service
- Optional QA consumer hooks

## Messaging model

### Exchange

- `shipment.integration.exchange` (topic)

### Routing keys

- `order.created`
- `shipment.requested`
- `shipment.failed`
- `shipment.completed`

### Queues

- `shipment.integration.order.created.q`
- `shipment.integration.shipment.requested.q`
- `shipment.integration.dlq`

## Processing rules

- Use idempotency key (`orderId` or `shipmentId`) in message headers.
- Retry transient errors with bounded attempts.
- Route poison messages to dead-letter queue.
- Emit structured failure logs with trace and cause.

## Test checklist

1. Publish `order.created` event and verify consumer receives.
2. Simulate transient error and verify retry behavior.
3. Simulate persistent failure and verify DLQ routing.
4. Verify no duplicate side effects under redelivery.

## Deliverables

- Queue/exchange definitions.
- Producer/consumer skeleton in services.
- Integration tests for normal/retry/DLQ paths.
- Runbook notes for backlog and consumer failure handling.
