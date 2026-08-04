# Practice Test Scenarios

## Objective

Hands-on scenarios to validate engineering readiness on the shipment integration domain.

## Scenario 1: New carrier onboarding

- Add carrier adapter `new-carrier-adapter`.
- Expose quote + booking mapping.
- Add fallback behavior when provider returns 5xx.
- Deliverables:
  - adapter code,
  - tests,
  - API documentation update.

## Scenario 2: Shipment status reconciliation

- Build a scheduled reconciliation flow to sync provider status.
- Handle stale and out-of-order updates.
- Deliverables:
  - reconciliation component,
  - idempotency handling,
  - metrics and logs.

## Scenario 3: Gateway protection

- Protect shipment write APIs behind JWT.
- Keep read-only status endpoint publicly readable (if required).
- Deliverables:
  - Kong route/policy update,
  - Keycloak client/role mapping,
  - smoke test evidence.

## Scenario 4: Broker-based async processing

- Publish shipment-created event from order flow.
- Consume event in shipment integration worker.
- Define retry + dead-letter strategy.
- Deliverables:
  - producer + consumer skeleton,
  - queue/exchange config,
  - failure case test.

## Evaluation criteria

- Correctness and domain consistency.
- Resilience and error handling quality.
- Test coverage relevance.
- Operational readiness (runbook + observability impact).
