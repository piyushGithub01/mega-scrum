package com.scrum.persistenceapi.workitemAudit.command;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.datapersistence.entity.WorkitemAuditEntity;
import com.scrum.datapersistence.repository.WorkitemAuditRepository;

@Component
public class CreateWorkitemAuditCommand implements Command<WorkitemAuditEntity, WorkitemAuditEntity> {

	@Autowired
	private WorkitemAuditRepository workItemAuditRepository;
	
	@Override
	@Transactional
	public WorkitemAuditEntity executeCommand(WorkitemAuditEntity entity) {
		entity.setWorkitemAuditId(UUID.randomUUID().toString());
		entity.setCreatedBy("SYSTEM");
		entity.setCreatedDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		WorkitemAuditEntity audit = workItemAuditRepository.save(entity);
		return audit;
	}
	
}
