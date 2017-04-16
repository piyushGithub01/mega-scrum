package com.scrum.workitem.jdbc.dao;

import java.util.List;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;

public interface WorkitemAuditDao {

	public List<WorkitemAuditModel> getAllWorkitemAudit(String id);
	
	public void createWorkitemAudit(String workitemId, String comment);
	
}
