# Task 7 - Observability

## Objective

Define baseline observability for shipment integration services across logs, metrics, and traces.

## Logging

- Use structured logs with:
  - `timestamp`
  - `level`
  - `service`
  - `traceId`
  - `spanId`
  - `message`
- Propagate correlation headers through all service hops.

## Metrics

Minimum suggested metrics:

- `http_server_requests_seconds`
- `shipment_request_total`
- `shipment_success_total`
- `shipment_failure_total`
- `shipment_retry_total`
- `carrier_request_latency_seconds`
- `broker_consumer_lag`

## Tracing

- Instrument HTTP clients and controllers.
- Instrument broker producer/consumer spans.
- Link spans by correlation ID and message headers.

## Dashboards (recommended)

1. **API Health Dashboard**
   - request rate, error rate, latency p50/p95/p99
2. **Shipment Flow Dashboard**
   - created vs completed vs failed shipments
   - retry counts and failure reasons
3. **Broker Dashboard**
   - queue depth, consumer lag, dead-letter rate

## Alerts (baseline)

- Error rate > 5% for 5 minutes on shipment APIs.
- Consumer lag increasing continuously for 10 minutes.
- Dead-letter queue growth above threshold.
- Key upstream dependency unavailable.

## Deliverables

- Service-level observability configuration.
- Dashboard and alert definition templates.
- Runbook links for incident triage paths.
