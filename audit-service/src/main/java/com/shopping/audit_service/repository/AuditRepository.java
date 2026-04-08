package com.shopping.audit_service.repository;

import com.shopping.audit_service.entity.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends MongoRepository<AuditLog,String> {
}
