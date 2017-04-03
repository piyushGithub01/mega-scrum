package com.scrum.persistenceapi.workitem.command;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.constant.workitem.WorkitemStatus;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.entity.WorkitemAuditEntity;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemConverter;
import com.scrum.persistenceapi.workitemAudit.command.CreateWorkitemAuditCommand;

@Component
public class CreateWorkitemCommand implements Command<WorkitemModel, WorkitemModel> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Autowired
	private CreateWorkitemAuditCommand createWorkitemAuditCommand;
	
	@Override
	@Transactional
	public WorkitemModel executeCommand(WorkitemModel model) {
		WorkitemEntity entity = WorkitemConverter.convertToEntity(model);
		entity.setStatus(WorkitemStatus.Todo.name());
		entity.setWorkitemId(UUID.randomUUID().toString());
		entity.setCreatedBy("SYSTEM");
		entity.setCreatedDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		WorkitemEntity savedEntity = workItemRepository.save(entity);
		
		//save audit
		WorkitemAuditEntity auditEntity = new WorkitemAuditEntity(savedEntity.getWorkitemId(),
				"Created workitem with status ToDo");
		createWorkitemAuditCommand.executeCommand(auditEntity);
		
		
		return WorkitemConverter.convertToModel(savedEntity);
	}
	
}
