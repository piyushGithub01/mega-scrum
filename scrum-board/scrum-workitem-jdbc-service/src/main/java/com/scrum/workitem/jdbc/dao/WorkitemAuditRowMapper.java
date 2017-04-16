package com.scrum.workitem.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;

public class WorkitemAuditRowMapper implements RowMapper<WorkitemAuditModel> {
	   
	@Override
	public WorkitemAuditModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		WorkitemAuditModel workitemAuditModel = WorkitemAuditModel.Builder.newBuilder()
				.setCreatedBy(rs.getString("CREATED_BY"))
				.setCreatedDate(rs.getDate("CREATED_DATE"))
				.setComment(rs.getString("COMMENTS"))
				.setWorkitemAuditId(rs.getString("WORKITEM_AUDIT_ID"))
				.setWorkitemId(rs.getString("WORKITEM_ID"))
				.build();
	      
	      return workitemAuditModel;
	}
}
