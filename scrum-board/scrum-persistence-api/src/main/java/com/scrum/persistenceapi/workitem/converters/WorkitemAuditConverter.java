package com.scrum.persistenceapi.workitem.converters;

import java.util.ArrayList;
import java.util.List;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;
import com.scrum.datapersistence.entity.WorkitemAuditEntity;

public class WorkitemAuditConverter {
	
	public static List<WorkitemAuditModel> convertToModel (List<WorkitemAuditEntity> allEntity) {
		List<WorkitemAuditModel> allModel = new ArrayList<>();
		allEntity.forEach( entity -> {
			WorkitemAuditModel model = WorkitemAuditModel.Builder.newBuilder()
					.setComment(entity.getComment())
					.setCreatedBy(entity.getCreatedBy())
					.setCreatedDate(entity.getCreatedDate())
					.setWorkitemAuditId(entity.getWorkitemAuditId())
					.setWorkitemId(entity.getWorkitemId())
					.build();
			allModel.add(model);
		});
		return allModel;
	}
	
}
