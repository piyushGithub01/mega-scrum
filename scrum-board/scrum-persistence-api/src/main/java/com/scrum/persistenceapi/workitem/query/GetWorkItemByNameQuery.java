package com.scrum.persistenceapi.workitem.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.common.query.Query;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemConverter;

@Component
public class GetWorkItemByNameQuery implements Query<String, WorkitemModel> {

	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Override
	@Transactional
	public WorkitemModel executeQuery(String id) {
		
		WorkitemEntity item =  workItemRepository.findOne(id);
		return WorkitemConverter.convertToModel(item);
	}
	
}
