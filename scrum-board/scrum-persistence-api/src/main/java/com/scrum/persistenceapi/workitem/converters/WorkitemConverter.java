package com.scrum.persistenceapi.workitem.converters;

import com.scrum.common.constant.workitem.WorkitemStatus;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.datapersistence.entity.WorkitemEntity;

public class WorkitemConverter {
	
	public static WorkitemModel convertToModel (WorkitemEntity entity) {
		return WorkitemModel.Builder.newBuilder()
					.setCreatedBy(entity.getCreatedBy())
					.setCreatedDate(entity.getCreatedDate())
					.setDescription(entity.getDescription())
					.setName(entity.getName())
					.setStatus(WorkitemStatus.valueOf(entity.getStatus()))
					.setUpdatedBy(entity.getUpdatedBy())
					.setUpdatedDate(entity.getUpdatedDate())
					.setVersion(entity.getVersion())
					.setWorkitemId(entity.getWorkitemId())
					.setWorkitemPayload(entity.getWorkitemPayload())
					.build();
	}
	
	public static WorkitemEntity convertToEntity (WorkitemModel model) {
		WorkitemEntity entity = new WorkitemEntity(
					model.getWorkitemId(),
					model.getName(),
					model.getDescription(),
					model.getStatus().name(),
					model.getWorkitemPayload(),
					model.getCreatedBy(),
					model.getCreatedDate(),
					model.getUpdatedBy(),
					model.getUpdatedDate(),
					model.getVersion()
				);
		return entity;
	}

}
