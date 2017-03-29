package com.scrum.persistenceapi.workitem.query;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.common.query.Query;
import com.scrum.datapersistence.entity.WorkitemEntity;
import com.scrum.datapersistence.repository.WorkitemRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemConverter;

@Component
public class GetAllWorkitemQuery implements Query<String, List<WorkitemModel>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetAllWorkitemQuery.class);
	
	@Autowired
	private WorkitemRepository workItemRepository;
	
	@Override
	@Transactional
	public List<WorkitemModel> executeQuery(String name) {
		
		List<WorkitemEntity> allEntity =  workItemRepository.findAll();
		List<WorkitemModel> allModel = new ArrayList<>();
		allEntity.stream().forEach((item) -> {
			LOGGER.info(item.toString());
			WorkitemModel model = WorkitemConverter.convertToModel(item);
			allModel.add(model);
		});
		return allModel;
	}
	
}
