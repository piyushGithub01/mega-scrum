package com.scrum.persistenceapi.workitem.query;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.query.Query;
import com.scrum.datapersistence.entity.WorkItemEntity;
import com.scrum.datapersistence.repository.WorkItemRepository;

@Component
public class GetWorkItemQueryByName implements Query<String, List<WorkItemEntity>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetWorkItemQueryByName.class);
	
	@Autowired
	private WorkItemRepository workItemRepository;
	
	@Override
	@Transactional
	public List<WorkItemEntity> executeQuery(String name) {
		
		List<WorkItemEntity> all =  workItemRepository.findAll();
		all.stream().forEach((item) -> {
			LOGGER.info(item.toString());
		});
		return all;
	}
	
}
