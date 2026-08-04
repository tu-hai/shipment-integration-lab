# API Governance: Idempotency, Error Catalog, Security Matrix

This document standardizes the integrated API behavior across all services.

## 1. Idempotency policy

### Header contract

- Header: `Idempotency-Key`
- Required for:
  - `POST /api/v1/orders`
  - `POST /api/v1/shipments`
  - `POST /api/v1/shipments/{shipmentId}/retry`
  - `POST /api/v1/carrier/shipments`
  - `POST /api/v1/qa/scenarios/{scenarioId}/seed`
  - `POST /api/v1/qa/scenarios/{scenarioId}/run`
- Key format: `^[a-zA-Z0-9\-_.]{16,128}$`
- Retention window: 24h

### Behavior

1. First request with a new key is processed normally.
2. Same key + same canonical payload returns the original response payload and status.
3. Same key + different payload returns `409 CONFLICT` with error code `IDEMPOTENCY_KEY_REUSED_WITH_DIFFERENT_PAYLOAD`.
4. If previous processing is in-flight, return `409 CONFLICT` with error code `IDEMPOTENCY_REQUEST_IN_PROGRESS`.

## 2. Error catalog

All error responses use `schemas/sync/error-response.schema.json`.

| Error code | HTTP status | Meaning |
|---|---:|---|
| `VALIDATION_FAILED` | 400 | Input failed schema/domain validation |
| `UNAUTHORIZED` | 401 | Missing/invalid token |
| `FORBIDDEN_SCOPE` | 403 | Token lacks required scope |
| `RESOURCE_NOT_FOUND` | 404 | Resource ID not found |
| `IDEMPOTENCY_KEY_REUSED_WITH_DIFFERENT_PAYLOAD` | 409 | Same key used with different payload |
| `IDEMPOTENCY_REQUEST_IN_PROGRESS` | 409 | Duplicate request while initial request is still processing |
| `SHIPMENT_STATE_CONFLICT` | 409 | Operation not allowed for current shipment state |
| `CARRIER_TIMEOUT` | 504 | Carrier dependency timed out |
| `INTEGRATION_DOWNSTREAM_ERROR` | 502 | Downstream integration returned invalid/error response |
| `INTERNAL_ERROR` | 500 | Unexpected internal failure |

## 3. Security matrix by endpoint

Authentication mode: `Bearer JWT` (Keycloak-issued token).  
Authorization model: OAuth2 scopes mapped by gateway/policy.

| Endpoint | Method | Scope required | Notes |
|---|---|---|---|
| `/api/v1/orders` | POST | `order.write` | Idempotency required |
| `/api/v1/orders/{orderId}` | GET | `order.read` |  |
| `/api/v1/shipments` | POST | `shipment.write` | Idempotency required |
| `/api/v1/shipments/{shipmentId}` | GET | `shipment.read` |  |
| `/api/v1/shipments/{shipmentId}/retry` | POST | `shipment.retry` | Idempotency required |
| `/api/v1/carrier/quotes` | POST | `carrier.quote` | For integration test role only |
| `/api/v1/carrier/shipments` | POST | `carrier.shipment.write` | Idempotency required |
| `/api/v1/carrier/shipments/{carrierShipmentId}` | GET | `carrier.shipment.read` |  |
| `/api/v1/qa/scenarios/{scenarioId}/seed` | POST | `qa.seed` | Idempotency required |
| `/api/v1/qa/scenarios/{scenarioId}/run` | POST | `qa.run` | Idempotency required |
| `/api/v1/qa/reports/{reportId}` | GET | `qa.read` |  |

## 4. Correlation and audit

- Optional request header: `X-Correlation-Id` (recommended).
- If absent, gateway or service generates one and returns it in response header.
- All service logs must include correlation ID and principal/client ID.
