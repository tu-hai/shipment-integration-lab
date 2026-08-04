# Test Cases

## Scope

This document defines baseline tests for shipment integration workflows across services.

## Functional test matrix

| ID | Scenario | Expected result |
|---|---|---|
| TC-01 | Create order with valid payload | `201 CREATED`, order status `CREATED` |
| TC-02 | Create order with missing destination | `400 BAD_REQUEST` with validation details |
| TC-03 | Trigger shipment orchestration for existing order | `202 ACCEPTED`, shipment status `PROCESSING` |
| TC-04 | Trigger orchestration for missing order | `404 NOT_FOUND` |
| TC-05 | Carrier quote returns normal response | shipment flow continues, status updates |
| TC-06 | Carrier quote timeout | retry policy applied, observable failure metric increased |
| TC-07 | Retry failed shipment manually | status moves from `FAILED` to `PROCESSING` |
| TC-08 | QA seed scenario endpoint | returns successful seed metadata |
| TC-09 | QA run scenario with happy path | report status `PASSED` |
| TC-10 | Gateway route for protected API without token | `401 UNAUTHORIZED` |

## Non-functional baseline

| ID | Category | Check |
|---|---|---|
| NFT-01 | Latency | p95 < 300ms for `/api/v1/orders` in local environment baseline |
| NFT-02 | Reliability | retry + dead-letter logic for carrier failures |
| NFT-03 | Security | JWT token required on protected routes |
| NFT-04 | Observability | logs include `traceId`, metrics exposed |

## Contract tests

- `shipment-integration-service` must handle mock carrier payload schema exactly.
- Any change in carrier response fields requires:
  1. contract test updates,
  2. backward compatibility notes in PR,
  3. test evidence attached.

## Regression checklist per PR

1. Run all module tests (`mvn clean verify`).
2. Validate compose startup (`docker compose up -d`).
3. Run Postman smoke collection in `postman/`.
4. Verify gateway route + authentication for changed endpoints.
