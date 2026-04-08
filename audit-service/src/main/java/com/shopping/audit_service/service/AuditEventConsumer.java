package com.shopping.audit_service.service;

import com.shopping.audit_service.entity.AuditLog;
import com.shopping.audit_service.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditEventConsumer {

    private final AuditRepository auditRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public AuditEventConsumer(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @KafkaListener(topics = "global-audit-topic",groupId = "global-audit-group")
    public void handleAuditEvent(AuditLog auditLog) {
        log.info("Received auditLog: {}", auditLog);
        try {
            log.debug("Received event from Kafka topic {}",auditLog);
            auditRepository.save(auditLog);
        }catch (Exception e){
            log.error("Error while saving event to Kafka topic {}",auditLog,e);
        }
    }


}
