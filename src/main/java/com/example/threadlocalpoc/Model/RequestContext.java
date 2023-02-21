package com.example.threadlocalpoc.Model;


public class RequestContext {

    String apiKey;
    String correlationId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void clear() {
        this.apiKey = null;
        this.correlationId = null;
    }
}
