# Technology Stack

## Runtime platform

- **Language**: Java 21
- **Framework**: Spring Boot 3.3.x
- **Build**: Maven (multi-module)
- **API style**: REST + event-driven extensions

## Service catalog

1. `order-service`
   - Role: order command/query boundary
   - Typical interfaces: REST, async order events
2. `shipment-integration-service`
   - Role: orchestration and carrier integration
   - Typical interfaces: REST + RabbitMQ producers/consumers
3. `carrier-mock-service`
   - Role: partner API simulation
   - Typical interfaces: REST mock endpoints with configurable behaviors
4. `qa-service`
   - Role: quality checks, stubs, and synthetic validations
   - Typical interfaces: REST test hooks, data seeding endpoints

## Integration and infrastructure

- **Gateway**: Kong (`infra/kong`)
- **Identity provider**: Keycloak (`infra/keycloak`)
- **Message broker**: RabbitMQ (`infra/rabbitmq`)
- **Container orchestration assets**: Kubernetes manifests (`k8s`)
- **Local orchestration**: Docker Compose (`docker-compose.yml`)

## Quality and testing

- **Unit + integration tests**: JUnit 5, Spring Boot Test
- **API workspace**: Postman (`postman/`)
- **Batch validation helper**: `test-all.ps1`

## Observability baseline

- Metrics via Actuator + Prometheus-compatible scrape target
- Centralized logs with correlation IDs
- Tracing ready design (OpenTelemetry instrumentation path)

## Future-ready extensions

- Add persistence adapters (PostgreSQL, Redis) per service as needed
- Add message schema governance (Avro or JSON Schema)
- Add CI workflows for lint, test, package, and security scan
