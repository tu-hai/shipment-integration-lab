package com.shipmentintegration.qa.dto;

public class ScenarioRunResponse {
    private String scenarioId;
    private String status;
    private String reportId;
    private String executionTime;

    public ScenarioRunResponse() {}

    public ScenarioRunResponse(String scenarioId, String status, String reportId, String executionTime) {
        this.scenarioId = scenarioId;
        this.status = status;
        this.reportId = reportId;
        this.executionTime = executionTime;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
