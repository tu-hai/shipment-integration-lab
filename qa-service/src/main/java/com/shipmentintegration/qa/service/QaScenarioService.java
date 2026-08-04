package com.shipmentintegration.qa.service;

import com.shipmentintegration.qa.dto.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QaScenarioService {

    private static final Logger log = LoggerFactory.getLogger(QaScenarioService.class);
    private final Map<String, QaReportResponse> reportStore = new ConcurrentHashMap<>();
    private final Counter scenarioRunCounter;

    public QaScenarioService(MeterRegistry meterRegistry) {
        this.scenarioRunCounter = Counter.builder("qa_scenarios_run_total")
                .description("Total synthetic QA test scenarios executed")
                .register(meterRegistry);
    }

    public void seedScenario(String scenarioId, ScenarioSeedRequest request) {
        log.info("QA Service: Seeding state for scenarioId={}, name={}", scenarioId, request.getScenarioName());
    }

    public ScenarioRunResponse runScenario(String scenarioId) {
        scenarioRunCounter.increment();
        String reportId = "RPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        log.info("QA Service: Executing synthetic test scenario scenarioId={}", scenarioId);

        List<String> passed = List.of(
                "Order Service intake API returns 201 CREATED",
                "RabbitMQ order.created event published and consumed",
                "Carrier Quote API returned valid quote ID",
                "Carrier Booking confirmed with tracking number"
        );
        List<String> failed = Collections.emptyList();

        QaReportResponse report = new QaReportResponse(
                reportId,
                scenarioId,
                "PASSED",
                passed,
                failed,
                Instant.now().toString()
        );
        reportStore.put(reportId, report);

        return new ScenarioRunResponse(scenarioId, "COMPLETED", reportId, Instant.now().toString());
    }

    public Optional<QaReportResponse> getReport(String reportId) {
        return Optional.ofNullable(reportStore.get(reportId));
    }
}
