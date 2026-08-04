# Task 4 - Gateway and Identity Provider

## Objective

Enable API gateway routing and identity enforcement for shipment integration services.

## Components

- Kong gateway config: `infra/kong/kong.yaml`
- Keycloak realm export: `infra/keycloak/realm-shipment-integration.json`
- Service routes:
  - `/api/v1/orders`
  - `/api/v1/shipments`
  - `/api/v1/carrier`
  - `/api/v1/qa`

## Implementation steps

1. Define Kong services + routes for each backend service.
2. Configure auth plugin for protected routes.
3. Import shipment realm into Keycloak.
4. Configure client, roles, and scopes.
5. Map JWT claims expected by gateway/policy.

## Route policy suggestion

- Public routes:
  - `GET /api/v1/shipments/{id}` (optional by business policy)
- Protected routes:
  - all write operations (`POST`, `PUT`, `PATCH`, `DELETE`)
- Internal-only routes:
  - QA scenario controls and admin endpoints

## Validation checklist

1. Request protected endpoint without token => `401`.
2. Request with invalid token => `401`.
3. Request with valid token but missing role => `403`.
4. Request with valid token + role => pass to upstream.
5. Correlation ID remains visible across gateway and upstream logs.

## Deliverables

- Updated Kong declarative config.
- Keycloak realm/client config.
- Postman auth flow examples.
- Notes in runbook for common auth incidents.
