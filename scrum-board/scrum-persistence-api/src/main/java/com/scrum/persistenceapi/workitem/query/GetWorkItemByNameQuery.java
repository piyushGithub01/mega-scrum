package com.scrum.persistenceapi.workitem.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.constant.workitemStatus.WorkItemStatus;
import com.scrum.common.model.workitem.args.WorkItemModel;
import com.scrum.common.query.Query;
import com.scrum.datapersistence.entity.WorkItemEntity;
import com.scrum.datapersistence.repository.WorkItemRepository;

@Component
public class GetWorkItemByNameQuery implements Query<String, WorkItemModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetWorkItemByNameQuery.class);
	
	@Autowired
	private WorkItemRepository workItemRepository;
	
	@Override
	@Transactional
	public WorkItemModel executeQuery(String name) {
		
		WorkItemEntity item =  workItemRepository.findOne(name);
		WorkItemModel model = WorkItemModel.Builder.newBuilder()
					.setName(item.getName())
					.setDescription(item.getDescription())
					.setStatus(WorkItemStatus.valueOf(item.getStatus()))
					.build();
		return model;
	}
	
}
