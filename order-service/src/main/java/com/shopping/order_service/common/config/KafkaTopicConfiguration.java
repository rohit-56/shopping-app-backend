package com.shopping.order_service.common.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic auditEventTopic() {
        return TopicBuilder.name("global-audit-topic").partitions(3).replicas(1).build();
    }
}
