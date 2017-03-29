package com.scrum.datapersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrum.datapersistence.entity.WorkitemAuditEntity;

@Repository
public interface WorkitemAuditRepository extends JpaRepository<WorkitemAuditEntity, String> {

}
