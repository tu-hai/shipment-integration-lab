# API Documentation (Skeleton)

## Base URLs (local default)

- Gateway: `http://localhost:8000`
- Order Service: `http://localhost:8001`
- Shipment Integration Service: `http://localhost:8002`
- Carrier Mock Service: `http://localhost:8003`
- QA Service: `http://localhost:8004`

## 1) Order Service

### POST `/api/v1/orders`

Create a new order request for shipment.

Request body (example):

```json
{
  "orderId": "ORD-10001",
  "customerId": "CUS-01",
  "destination": {
    "country": "VN",
    "city": "Hanoi"
  },
  "items": [
    { "sku": "SKU-1", "quantity": 2 }
  ]
}
```

Response `201`:

```json
{
  "orderId": "ORD-10001",
  "status": "CREATED"
}
```

### GET `/api/v1/orders/{orderId}`

Get current order state.

## 2) Shipment Integration Service

### POST `/api/v1/shipments`

Start shipment orchestration for an order.

Request:

```json
{
  "orderId": "ORD-10001",
  "carrierPreference": "FAST_SHIP"
}
```

Response `202`:

```json
{
  "shipmentId": "SHP-90001",
  "status": "PROCESSING"
}
```

### GET `/api/v1/shipments/{shipmentId}`

Get shipment orchestration status.

### POST `/api/v1/shipments/{shipmentId}/retry`

Retry carrier integration flow for failed states.

## 3) Carrier Mock Service

### POST `/api/v1/carrier/quotes`

Return mocked quote from carrier provider.

### POST `/api/v1/carrier/shipments`

Create mocked shipment booking.

### GET `/api/v1/carrier/shipments/{carrierShipmentId}`

Return mocked tracking/status payload.

## 4) QA Service

### POST `/api/v1/qa/scenarios/{scenarioId}/seed`

Seed data and state for scenario testing.

### POST `/api/v1/qa/scenarios/{scenarioId}/run`

Execute synthetic validation for a scenario.

### GET `/api/v1/qa/reports/{reportId}`

Get validation report.

## Common response model (recommended)

```json
{
  "timestamp": "2026-08-04T10:00:00Z",
  "traceId": "trace-abc",
  "data": {},
  "error": null
}
```

## Error model (recommended)

```json
{
  "timestamp": "2026-08-04T10:00:00Z",
  "traceId": "trace-abc",
  "error": {
    "code": "SHIPMENT_CARRIER_TIMEOUT",
    "message": "Carrier API timeout",
    "details": []
  }
}
```

## Health and management endpoints

Each service should expose:

- `GET /actuator/health`
- `GET /actuator/info`
- `GET /actuator/prometheus` (if metrics enabled)
