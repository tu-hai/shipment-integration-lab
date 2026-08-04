# Shipment Integration Lab

Monorepo for the **shipment integration** domain, organized as a multi-service platform with shared test, infra, and deployment assets.

## Repository layout

```text
.
├── order-service/
├── shipment-integration-service/
├── carrier-mock-service/
├── qa-service/
├── postman/
├── k8s/
├── infra/
├── docker-compose.yml
├── .env.example
├── test-all.ps1
├── Standard.md
├── tech-stack.md
├── api-documentation.md
├── test-cases.md
├── practice-test.md
├── runbook.md
├── task4-gateway-idp.md
├── task6-message-broker.md
└── task7-observability.md
```

## Services

- **order-service**: order intake and order lifecycle API.
- **shipment-integration-service**: orchestration and integration with carrier adapters.
- **carrier-mock-service**: mock provider API for partner integration testing.
- **qa-service**: synthetic test hooks, validation endpoints, and quality gates.

Each service has:
- `src/main/...` for runtime code.
- `src/test/...` and `src/test/resources/application-test.yml` for tests.
- `pom.xml` for module-level dependency and build setup.

## Quick start

1. Copy environment template:
   - `Copy-Item .env.example .env`
2. Build all modules:
   - `mvn clean verify`
3. Run helper script (PowerShell):
   - `.\test-all.ps1`
4. Start local stack skeleton:
   - `docker compose up -d`

## Documentation index

- Engineering standards: [`Standard.md`](./Standard.md)
- Technology stack: [`tech-stack.md`](./tech-stack.md)
- API reference: [`api-documentation.md`](./api-documentation.md)
- Test plan and cases: [`test-cases.md`](./test-cases.md)
- Practice scenarios: [`practice-test.md`](./practice-test.md)
- Operations guide: [`runbook.md`](./runbook.md)
- Gateway and identity task guide: [`task4-gateway-idp.md`](./task4-gateway-idp.md)
- Message broker task guide: [`task6-message-broker.md`](./task6-message-broker.md)
- Observability task guide: [`task7-observability.md`](./task7-observability.md)