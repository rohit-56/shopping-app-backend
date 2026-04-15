package com.shopping.audit_service.service;

import com.shopping.audit_service.dto.AuditEventDTO;
import com.shopping.audit_service.entity.AuditLog;
import com.shopping.audit_service.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuditEventConsumer {

    private final AuditRepository auditRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public AuditEventConsumer(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @KafkaListener(topics = "global-audit-topic",groupId = "global-audit-group")
    public void handleAuditEvent(AuditEventDTO  event) {

        AuditLog auditLog = new AuditLog();
        auditLog.setId(event.getTraceId());
        auditLog.setEventName(event.getAction());
        auditLog.setTimestamp(event.getTimestamp());
        auditLog.setPerformedBy(event.getPerformedBy());
        auditLog.setServiceName(event.getServiceName());

        Map<String,Object> map = new HashMap<>();
        map.put(event.getTraceId(), event.getPayload());
        auditLog.setDetails(map);

        log.info("Received auditLog: {}", auditLog);
        try {
            log.debug("Received event from Kafka topic {}",auditLog);
            auditRepository.save(auditLog);
        }catch (Exception e){
            log.error("Error while saving event to Kafka topic {}",auditLog,e);
        }
    }


}
