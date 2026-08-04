# Engineering Standards

## Goal

Define implementation and collaboration standards for the shipment integration monorepo.

## Repository principles

- Keep naming consistent with the **shipment integration** domain.
- Keep services independent and deployable.
- Put shared assets at repository root (`k8s`, `infra`, `postman`, docs).
- Keep backward compatibility where possible; document intentional breaks.

## Branch and PR policy

- Base branch: `main`.
- Feature branches: `feature/<scope>` or `<owner>-<scope>`.
- One PR should focus on one coherent change set.
- PR must contain:
  - clear summary,
  - testing notes,
  - rollback notes for runtime-impacting changes.

## Code style

- Java 21, Spring Boot 3.3.x.
- Keep package naming under `com.shipmentintegration.<service>`.
- Favor constructor injection over field injection.
- Validate all external input at API boundary.
- Keep adapter contracts explicit (DTO + mapper).

## Test strategy

- Unit tests for domain logic and mapping.
- API tests for each service boundary.
- Contract tests for shipment-integration-service <-> carrier-mock-service.
- Smoke tests for compose and k8s profile.

## Configuration standards

- Runtime config in `src/main/resources/application.yml`.
- Test overrides in `src/test/resources/application-test.yml`.
- No secret in source code; use environment variables or secret stores.
- `.env.example` lists all required local variables.

## Observability minimum baseline

- Correlation ID on all HTTP requests.
- Structured logs (JSON preferred in non-local environments).
- Health endpoints and readiness checks for each service.
- Core integration metrics:
  - shipment_request_total
  - carrier_request_latency
  - shipment_retry_total
  - shipment_error_total

## Security baseline

- API gateway fronting internal services.
- JWT/OIDC validation at edge and sensitive internal endpoints.
- Principle of least privilege for broker, DB, and infra credentials.
- Audit logs for status-changing operations.
