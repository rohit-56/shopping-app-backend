package com.shopping.audit_service.dto;

import java.time.LocalDateTime;

public class AuditEventDTO {

    public String traceId;
    public String serviceName;
    public String action;
    public Object payload;
    public String performedBy;
    public LocalDateTime timestamp;

    public AuditEventDTO() {
    }

    public AuditEventDTO(String traceId, String serviceName, String action, Object payload, String performedBy, LocalDateTime timestamp) {
        this.traceId = traceId;
        this.serviceName = serviceName;
        this.action = action;
        this.payload = payload;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAction() {
        return action;
    }

    public Object getPayload() {
        return payload;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
