package com.scrum.workitem.hibernate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.scrum.common.constant.workitem.WorkitemStatus;
import com.scrum.common.model.workitem.args.WorkitemModel;

public class WorkitemRowMapper implements RowMapper<WorkitemModel> {
	   
	@Override
	public WorkitemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		WorkitemModel workitemModel = WorkitemModel.Builder.newBuilder()
				.setCreatedBy(rs.getString("CREATED_BY"))
				.setCreatedDate(rs.getDate("CREATED_DATE"))
				.setDescription(rs.getString("DESCRIPTION"))
				.setName(rs.getString("NAME"))
				.setStatus(WorkitemStatus.valueOf(rs.getString("STATUS")))
				.setUpdatedBy(rs.getString("UPDATED_BY"))
				.setUpdatedDate(rs.getDate("UPDATED_DATE"))
				.setVersion(rs.getInt("VERSION"))
				.setWorkitemId(rs.getString("WORKITEM_ID"))
				.setWorkitemPayload(rs.getBytes("WORKITEM_PAYLOAD"))
				.build();
	      
	      return workitemModel;
	}
}
