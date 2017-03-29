package com.scrum.persistenceapi.workitem.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.repository.WorkitemRepository;

@Component
public class DeleteWorkItemCommand implements Command<WorkitemModel, Boolean> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Override
	@Transactional
	public Boolean executeCommand(WorkitemModel model) {
		
		workItemRepository.delete(model.getWorkitemId());
		return Boolean.TRUE;
		
	}
	
}
