package com.scrum.persistenceapi.workitem.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.constant.workitemStatus.WorkItemStatus;
import com.scrum.common.model.workitem.args.CreateWorkItemArgs;
import com.scrum.common.model.workitem.args.WorkItemModel;
import com.scrum.datapersistence.entity.WorkItemEntity;
import com.scrum.datapersistence.repository.WorkItemRepository;

@Component
public class CreateWorkItemCommand implements Command<CreateWorkItemArgs, WorkItemModel> {

	@Autowired
	private WorkItemRepository workItemRepository;
	
	@Override
	@Transactional
	public WorkItemModel executeCommand(CreateWorkItemArgs model) {
		WorkItemEntity entity = new WorkItemEntity(model.getName(),
													model.getDescription(),
													model.getStatus().name());
		WorkItemEntity savedEntity = workItemRepository.save(entity);
		return WorkItemModel.Builder.newBuilder()
				.setName(savedEntity.getName())
				.setDescription(savedEntity.getDescription())
				.setStatus(WorkItemStatus.valueOf(savedEntity.getStatus()))
				.build();
	}
	
}
