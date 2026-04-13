package com.shopping.order_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.order_service.external.dto.AuditEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditKafkaProducerService {

    private final KafkaTemplate<String, AuditEventDTO> kafkaTemplate;
    private final String topic="global-audit-topic";
    private final static Logger log = LoggerFactory.getLogger(AuditKafkaProducerService.class);

    public AuditKafkaProducerService(KafkaTemplate<String, AuditEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendAuditEvent(String action, Object payload, String userId) {

        AuditEventDTO auditEventDTO = new AuditEventDTO();

        auditEventDTO.setTraceId(UUID.randomUUID().toString());
        auditEventDTO.setServiceName("order_service");
        auditEventDTO.setAction(action);
        auditEventDTO.setPayload(payload);
        auditEventDTO.setPerformedBy(userId);
        auditEventDTO.setTimestamp(System.currentTimeMillis());

        log.info("Sending audit event to topic {}", topic);

        kafkaTemplate.send(topic, auditEventDTO);

    }


}
