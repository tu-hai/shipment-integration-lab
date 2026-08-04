# Shipment Integration Lab

Monorepo for the **Shipment Integration Platform**, designed and implemented to meet all 7 standard and advanced architecture criteria.

---

## 📋 7 Criteria Standard & Advanced Matrix

| # | Standard / Advanced Criterion | Status | Implementation Assets & Documentation |
|---|---|---|---|
| 1 | **Personal Business Domain** | ✅ Complete | Logistics Freight & Partner Shipment Integration (`shipment-integration-lab`). Domain models cover Order Intake, Shipment Dispatch, Carrier Quotation, Booking & Tracking. |
| 2 | **Workflow & API Integration** | ✅ Complete | Hybrid REST + Async Message Queue workflow. Full API specifications in [`api-documentation.md`](./api-documentation.md) and Postman workspace in [`postman/`](./postman/). |
| 3 | **Technical Stack (inc. Partner Mock)** | ✅ Complete | Java 21, Spring Boot 3.3.2, Spring AMQP, Kong, Keycloak. Implemented 4 microservices: `order-service` (8001), `shipment-integration-service` (8002), `carrier-mock-service` (8003 - Partner Mock), `qa-service` (8004). |
| 4 | **API Gateway & Identity Provider** | ✅ Complete | **Kong Gateway**: Declarative route config [`infra/kong/kong.yaml`](./infra/kong/kong.yaml) with Rate Limiting, Correlation ID, and CORS.<br>**Keycloak IDP**: Realm specification [`infra/keycloak/realm-shipment-integration.json`](./infra/keycloak/realm-shipment-integration.json) with OAuth2 OIDC clients, roles (`admin`, `customer`, `carrier`), and pre-configured test accounts. |
| 5 | **Deploy to Kubernetes Cluster** | ✅ Complete | K8s manifests for all microservices & infra stack in [`k8s/`](./k8s/) with Kustomize deployment manifest [`k8s/kustomization.yaml`](./k8s/kustomization.yaml). |
| 6 | **Message Broker Solution** | ✅ Complete | **RabbitMQ**: Topic Exchange (`shipment.integration.exchange`), Queues (`shipment.integration.order.created.q`, `shipment.integration.dlq`), bindings [`infra/rabbitmq/definitions.json`](./infra/rabbitmq/definitions.json), Spring AMQP producers/consumers, exponential backoff retries, and DLQ handling. |
| 7 | **Observability Solution** | ✅ Complete | **Prometheus + Grafana**: Actuator `/actuator/prometheus` export + Prometheus scrape config [`infra/prometheus/prometheus.yml`](./infra/prometheus/prometheus.yml) + Grafana dashboard [`infra/grafana/dashboards/shipment-dashboard.json`](./infra/grafana/dashboards/shipment-dashboard.json).<br>**EFK Stack**: JSON logging configuration + Fluent Bit collector [`infra/efk/fluent-bit.conf`](./infra/efk/fluent-bit.conf) + Elasticsearch & Kibana visualization. |

---

## 📂 Repository Structure

```text
.
├── order-service/                   # Order Intake REST API & AMQP Producer
├── shipment-integration-service/     # Core Orchestration, AMQP Consumer & Carrier Adapter
├── carrier-mock-service/            # Partner Mock Service (Quotes, Booking, Tracking)
├── qa-service/                      # Quality Gate & Synthetic Test Scenarios
├── infra/
│   ├── kong/                        # Kong API Gateway declarative config
│   ├── keycloak/                    # Keycloak realm & clients config
│   ├── rabbitmq/                    # RabbitMQ exchange & queue definitions
│   ├── prometheus/                  # Prometheus scraping configuration
│   ├── grafana/                     # Grafana dashboards & provisioning
│   └── efk/                         # Fluent Bit & EFK collector configuration
├── k8s/                             # Kubernetes manifests & Kustomize setup
├── postman/                         # Postman API Collection & Environment
├── docker-compose.yml               # Complete containerized stack launcher
├── Standard.md                      # Engineering standards
├── tech-stack.md                    # Technology stack overview
├── api-documentation.md             # Complete REST & Messaging API specification
├── task4-gateway-idp.md             # Kong & Keycloak task documentation
├── task6-message-broker.md          # RabbitMQ integration task documentation
└── task7-observability.md           # Prometheus, Grafana, EFK task documentation
```

---

## 🚀 Quick Start Guide

### 1. Build Java Microservices
```bash
mvn clean verify
```

### 2. Start Full Stack with Docker Compose
To launch all 4 microservices + Kong + Keycloak + RabbitMQ + Prometheus + Grafana + EFK stack:
```bash
docker compose up -d
```

### 3. Deploy to Kubernetes
```bash
kubectl apply -k k8s/
```

### 4. Service Endpoints Quick Reference
- **Kong API Gateway**: `http://localhost:8000`
- **Keycloak IDP**: `http://localhost:8080` (admin/admin)
- **RabbitMQ Management**: `http://localhost:15672` (guest/guest)
- **Prometheus UI**: `http://localhost:9090`
- **Grafana Dashboard**: `http://localhost:3000` (admin/admin)
- **Kibana Logs UI**: `http://localhost:5601`