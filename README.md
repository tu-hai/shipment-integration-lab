# shipment-integration-lab

Monorepo skeleton for the shipment integration domain.

## Layout

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
└── test-all.ps1
```

## Conventions

- Domain naming is centered on shipment integration.
- Each service is prepared as an independent module for future expansion.
- Deployment, test, and infrastructure assets are kept at the repository root for shared reuse.

## Quick start scaffold commands

- Build and run unit tests for all modules: `mvn clean verify`
- Run the same flow from PowerShell helper: `.\test-all.ps1`
- Environment variable template: copy `.env.example` into `.env`
- Local orchestration skeleton: `docker compose up -d`