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
└── infra/
```

## Conventions

- Domain naming is centered on shipment integration.
- Each service is prepared as an independent module for future expansion.
- Deployment, test, and infrastructure assets are kept at the repository root for shared reuse.