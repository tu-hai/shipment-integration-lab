package com.shipmentintegration.qa.dto;

import java.util.List;

public class QaReportResponse {
    private String reportId;
    private String scenarioId;
    private String result;
    private List<String> passedAssertions;
    private List<String> failedAssertions;
    private String createdAt;

    public QaReportResponse() {}

    public QaReportResponse(String reportId, String scenarioId, String result, List<String> passedAssertions, List<String> failedAssertions, String createdAt) {
        this.reportId = reportId;
        this.scenarioId = scenarioId;
        this.result = result;
        this.passedAssertions = passedAssertions;
        this.failedAssertions = failedAssertions;
        this.createdAt = createdAt;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getPassedAssertions() {
        return passedAssertions;
    }

    public void setPassedAssertions(List<String> passedAssertions) {
        this.passedAssertions = passedAssertions;
    }

    public List<String> getFailedAssertions() {
        return failedAssertions;
    }

    public void setFailedAssertions(List<String> failedAssertions) {
        this.failedAssertions = failedAssertions;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
