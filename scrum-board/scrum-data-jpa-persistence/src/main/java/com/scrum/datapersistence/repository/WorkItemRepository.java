package com.scrum.datapersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrum.datapersistence.entity.WorkItemEntity;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItemEntity, String> {

}
