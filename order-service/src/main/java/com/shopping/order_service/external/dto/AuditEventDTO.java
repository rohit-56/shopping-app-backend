package com.shopping.order_service.external.dto;


public class AuditEventDTO {

    public String traceId;
    public String serviceName;
    public String action;
    public Object payload;
    public String performedBy;
    public Long timestamp;

    public AuditEventDTO(String traceId, String serviceName, String action, Object payload, String performedBy, Long timestamp) {
        this.traceId = traceId;
        this.serviceName = serviceName;
        this.action = action;
        this.payload = payload;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }
    public AuditEventDTO() {}

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
