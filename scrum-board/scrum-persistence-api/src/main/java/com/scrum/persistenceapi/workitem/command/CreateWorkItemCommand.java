package com.scrum.persistenceapi.workitem.command;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.constant.workitem.WorkitemStatus;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemConverter;

@Component
public class CreateWorkItemCommand implements Command<WorkitemModel, WorkitemModel> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Override
	@Transactional
	public WorkitemModel executeCommand(WorkitemModel model) {
		WorkitemEntity entity = WorkitemConverter.convertToEntity(model);
		entity.setStatus(WorkitemStatus.Todo.name());
		entity.setWorkitemId(UUID.randomUUID().toString());
		WorkitemEntity savedEntity = workItemRepository.save(entity);
		return WorkitemConverter.convertToModel(savedEntity);
	}
	
}
