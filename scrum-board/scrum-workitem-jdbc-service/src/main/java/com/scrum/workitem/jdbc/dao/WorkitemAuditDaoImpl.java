package com.scrum.workitem.jdbc.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;

@Component
public class WorkitemAuditDaoImpl implements WorkitemAuditDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<WorkitemAuditModel> getAllWorkitemAudit(String id) {
		
		String SQL = "select * from WORKITEM_AUDIT where WORKITEM_ID = ?";
		
		List<WorkitemAuditModel> auditModel = jdbcTemplate.query(SQL, 
	         new Object[]{id}, new WorkitemAuditRowMapper());
	      
	    return auditModel;
	}

	@Override
	public void createWorkitemAudit(String workitemId, String comment) {
		String SQL = "insert into WORKITEM_AUDIT (WORKITEM_AUDIT_ID, WORKITEM_ID, COMMENTS, CREATED_BY, CREATED_DATE) values (?, ?, ?, ?, ?)";
	    jdbcTemplate.update( SQL, UUID.randomUUID().toString(),
	    		workitemId,
	    		comment,
	    		"SYSTEM",
	    		Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
	}

}
