package com.scrum.persistenceapi.workitem.query;

import java.util.ArrayList;
import java.util.List;

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
public class GetAllWorkItemQuery implements Query<String, List<WorkItemModel>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetAllWorkItemQuery.class);
	
	@Autowired
	private WorkItemRepository workItemRepository;
	
	@Override
	@Transactional
	public List<WorkItemModel> executeQuery(String name) {
		
		List<WorkItemEntity> all =  workItemRepository.findAll();
		List<WorkItemModel> allModel = new ArrayList<>();
		all.stream().forEach((item) -> {
			LOGGER.info(item.toString());
			WorkItemModel model = WorkItemModel.Builder.newBuilder()
					.setName(item.getName())
					.setDescription(item.getDescription())
					.setStatus(WorkItemStatus.valueOf(item.getStatus()))
					.build();
			allModel.add(model);
		});
		return allModel;
	}
	
}
