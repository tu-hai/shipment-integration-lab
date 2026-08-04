# Runbook

## 1. Local startup

1. Copy env:
   - `Copy-Item .env.example .env`
2. Build:
   - `mvn clean verify`
3. Start platform:
   - `docker compose up -d`
4. Check health:
   - `GET /actuator/health` on ports 8001-8004

## 2. Local shutdown

- `docker compose down`
- Optional volume cleanup:
  - `docker compose down -v`

## 3. Incident handling

### A) Shipment orchestration fails

- Check `shipment-integration-service` logs by `traceId`.
- Check carrier mock response behavior.
- Check RabbitMQ queue depth and dead-letter queue.
- Trigger retry endpoint if safe.

### B) Gateway 401/403 spikes

- Verify Keycloak availability and realm import.
- Verify Kong auth plugin settings.
- Verify token issuer/audience config.

### C) Queue backlog growth

- Validate consumer status and thread pool.
- Check broker connectivity and credentials.
- Scale consumer replicas if needed.

## 4. Operational checks

- Endpoint availability:
  - order-service: `:8001`
  - shipment-integration-service: `:8002`
  - carrier-mock-service: `:8003`
  - qa-service: `:8004`
- Infra availability:
  - RabbitMQ management: `:15672`
  - Keycloak: `:8080`
  - Kong proxy: `:8000`
  - Kong admin: `:8001`

## 5. Deployment checklist

1. Verify image tags and module versions.
2. Validate k8s manifests in `k8s/`.
3. Confirm secrets/config maps exist.
4. Run smoke tests against gateway routes.
5. Monitor metrics and logs for 30 minutes post rollout.
