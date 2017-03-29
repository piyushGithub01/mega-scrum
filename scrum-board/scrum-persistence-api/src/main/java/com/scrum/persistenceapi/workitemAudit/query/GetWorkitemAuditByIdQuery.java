package com.scrum.persistenceapi.workitemAudit.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;
import com.scrum.common.query.Query;
import com.scrum.datapersistence.entity.WorkitemAuditEntity;
import com.scrum.datapersistence.repository.WorkitemAuditRepository;
import com.scrum.persistenceapi.workitem.converters.WorkitemAuditConverter;

@Component
public class GetWorkitemAuditByIdQuery implements Query<String, List<WorkitemAuditModel>> {

	@Autowired
	private WorkitemAuditRepository workItemAuditRepository;
	
	@Override
	@Transactional
	public List<WorkitemAuditModel> executeQuery(String id) {
		
		List<WorkitemAuditEntity> allEntity =  workItemAuditRepository.findByWorkitemIdOrderByCreatedDateAsc(id);
		return WorkitemAuditConverter.convertToModel(allEntity);
	}
	
}
