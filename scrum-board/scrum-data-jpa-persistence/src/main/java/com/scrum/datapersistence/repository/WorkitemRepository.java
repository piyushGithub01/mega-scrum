package com.scrum.datapersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrum.datapersistence.entity.WorkitemEntity;

@Repository
public interface WorkitemRepository extends JpaRepository<WorkitemEntity, String> {

}
