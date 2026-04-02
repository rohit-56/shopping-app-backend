package com.shopping.order_service.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import jakarta.annotation.PreDestroy;


@Configuration
public class RedisCleanupConfig {

    private final RedisConnectionFactory connectionFactory;

    private static final Logger log = LoggerFactory.getLogger(RedisCleanupConfig.class);

    @Autowired
    public RedisCleanupConfig(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PreDestroy
    public void flushRedisOnShutdown() {
        log.info("Clearing Redis data on application shutdown...");
        try (RedisConnection connection = connectionFactory.getConnection()) {
            // Use FLUSHDB to clear redis data
            connection.flushDb();
        } catch (Exception e) {
            System.err.println("Error clearing Redis data: " + e.getMessage());
        }
        log.info("Redis data cleared.");
    }

}
