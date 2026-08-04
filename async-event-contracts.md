# Async Event Contracts and Versioning

This document defines event contract rules for message-broker integration.

## 1. Event envelope

All events use a standard envelope in `schemas/async/event-envelope.schema.json`.

Required fields:

- `eventId`: unique event UUID
- `eventType`: logical event name
- `eventVersion`: semantic version (`major.minor.patch`)
- `occurredAt`: RFC3339 timestamp
- `correlationId`: cross-service trace id
- `producer`: service name
- `payload`: event payload object

## 2. Versioning policy

1. Backward-compatible changes (add optional fields) => increment `minor`.
2. Internal-only fixes that do not change contract => increment `patch`.
3. Breaking changes (rename/remove/type change required fields) => increment `major` and publish a new `eventType` major stream.
4. Consumers must reject unsupported major versions and route to DLQ with explicit reason.

## 3. Canonical event types (v1)

- `order.created.v1` -> payload schema: `schemas/async/order-created-v1.schema.json`
- `shipment.requested.v1` -> payload schema: `schemas/async/shipment-requested-v1.schema.json`
- `shipment.completed.v1` -> payload schema: `schemas/async/shipment-completed-v1.schema.json`
- `shipment.failed.v1` -> payload schema: `schemas/async/shipment-failed-v1.schema.json`

## 4. Compatibility guidelines

- Do not repurpose existing fields with new meaning.
- Use additive strategy first (new optional field + default behavior).
- Mark deprecations in docs for at least one release cycle before removal.
- Keep old major consumer alive during migration window.
