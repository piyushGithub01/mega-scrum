package com.scrum.persistenceapi.workitem.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.entity.WorkitemAuditEntity;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitemAudit.command.CreateWorkitemAuditCommand;

@Component
public class DeleteWorkitemCommand implements Command<WorkitemModel, Boolean> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Autowired
	private CreateWorkitemAuditCommand createWorkitemAuditCommand;
	
	@Override
	@Transactional
	public Boolean executeCommand(WorkitemModel model) {
		
		WorkitemEntity savedEntity = workItemRepository.findOne(model.getWorkitemId());
		if (savedEntity == null) 
			return Boolean.TRUE;
		
		//save audit
		WorkitemAuditEntity auditEntity = new WorkitemAuditEntity(savedEntity.getWorkitemId(),
						"Deleted workitem with name "+savedEntity.getName());
		createWorkitemAuditCommand.executeCommand(auditEntity);
		
		workItemRepository.delete(model.getWorkitemId());
		return Boolean.TRUE;
		
	}
	
}
