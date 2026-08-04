package com.shipmentintegration.qa.dto;

import java.util.Map;

public class ScenarioSeedRequest {
    private String scenarioName;
    private Map<String, Object> parameters;

    public ScenarioSeedRequest() {}

    public ScenarioSeedRequest(String scenarioName, Map<String, Object> parameters) {
        this.scenarioName = scenarioName;
        this.parameters = parameters;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
