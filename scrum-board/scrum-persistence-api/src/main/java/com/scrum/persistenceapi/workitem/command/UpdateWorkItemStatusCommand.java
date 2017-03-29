package com.scrum.persistenceapi.workitem.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemConverter;

@Component
public class UpdateWorkItemStatusCommand implements Command<WorkitemModel, WorkitemModel> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Override
	@Transactional
	public WorkitemModel executeCommand(WorkitemModel model) {
		
		WorkitemEntity entity = workItemRepository.getOne(model.getWorkitemId());
		
		if (entity == null) {
			return null;
		}
		entity.setStatus(model.getStatus().name());
		entity.setUpdatedBy("SYSTEM");
//		entity.setUpdatedDate(LocalDateTime.now());
		
		WorkitemEntity savedEntity = workItemRepository.save(entity);
		return WorkitemConverter.convertToModel(savedEntity);
	}
	
}
