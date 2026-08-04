package com.shipmentintegration.qa.controller;

import com.shipmentintegration.qa.dto.*;
import com.shipmentintegration.qa.service.QaScenarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qa")
public class QaController {

    private final QaScenarioService qaScenarioService;

    public QaController(QaScenarioService qaScenarioService) {
        this.qaScenarioService = qaScenarioService;
    }

    @PostMapping("/scenarios/{scenarioId}/seed")
    public ResponseEntity<Void> seedScenario(@PathVariable String scenarioId, @RequestBody ScenarioSeedRequest request) {
        qaScenarioService.seedScenario(scenarioId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/scenarios/{scenarioId}/run")
    public ResponseEntity<ScenarioRunResponse> runScenario(@PathVariable String scenarioId) {
        ScenarioRunResponse response = qaScenarioService.runScenario(scenarioId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reports/{reportId}")
    public ResponseEntity<QaReportResponse> getReport(@PathVariable String reportId) {
        return qaScenarioService.getReport(reportId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
