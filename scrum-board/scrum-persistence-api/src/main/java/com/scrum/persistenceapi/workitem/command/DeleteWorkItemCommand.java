package com.scrum.persistenceapi.workitem.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.command.Command;
import com.scrum.common.model.workitem.args.DeleteWorkItemArgs;
import com.scrum.datapersistence.repository.WorkItemRepository;

@Component
public class DeleteWorkItemCommand implements Command<DeleteWorkItemArgs, Boolean> {

	@Autowired
	private WorkItemRepository workItemRepository;
	
	@Override
	@Transactional
	public Boolean executeCommand(DeleteWorkItemArgs model) {
		
		workItemRepository.delete(model.getName());
		return Boolean.TRUE;
		
	}
	
}
