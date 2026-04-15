package com.shopping.audit_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "audit_logs")
public class AuditLog {

    @Id
    public String id;
    public String traceId;
    public String serviceName;
    public String eventName;
    public Map<String, Object> details;
    public String performedBy;
    public LocalDateTime timestamp;

    public AuditLog() {
    }

    public AuditLog(String id, String traceId, String serviceName, String eventName, Map<String, Object> details, String performedBy, LocalDateTime timestamp) {
        this.id = id;
        this.traceId = traceId;
        this.serviceName = serviceName;
        this.eventName = eventName;
        this.details = details;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
