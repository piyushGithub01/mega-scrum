package com.scrum.workitem.hibernate.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scrum.common.constant.workitem.WorkitemStatus;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.workitem.hibernate.converters.WorkitemConverter;
import com.scrum.workitem.hibernate.entity.WorkitemEntity;

@Repository
public class WorkitemDaoImpl implements WorkitemDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private WorkitemAuditDaoImpl workitemAuditDaoImpl;
	
	@Override
	@Transactional
	public boolean createWorkitem(WorkitemModel workIn) {
		String workitemId = UUID.randomUUID().toString();
		String comment = "Created workitem with status ToDo";
		String SQL = "insert into WORKITEM (WORKITEM_ID, NAME, DESCRIPTION, STATUS, WORKITEM_PAYLOAD, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, VERSION) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    jdbcTemplate.update( SQL, workitemId,
	    		workIn.getName(),
	    		workIn.getDescription(),
	    		workIn.getStatus()==null?WorkitemStatus.Todo.name():workIn.getStatus().name(),
	    		workIn.getWorkitemPayload(),
	    		"SYSTEM",
	    		Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
	    		"SYSTEM",
	    		Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
	    		1);
	    
	    workitemAuditDaoImpl.createWorkitemAudit(workitemId, comment);
	    return true;
	}

	@Override
	public WorkitemModel getWorkitem(String workitemId) {
	    WorkitemEntity entity =  sessionFactory.getCurrentSession()
	    		.get(WorkitemEntity.class, workitemId);
	    return WorkitemConverter.convertToModel(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkitemModel> getAllWorkitems() {
	    List<WorkitemEntity> entities = sessionFactory.getCurrentSession()
	    		.createCriteria(WorkitemEntity.class).list();
	    List<WorkitemModel> allModel = new ArrayList<>();
	    entities.stream().forEach((item) -> {
			WorkitemModel model = WorkitemConverter.convertToModel(item);
			allModel.add(model);
		});
		return allModel;
	}

	@Override
	@Transactional
	public boolean deleteWorkitem(WorkitemModel workIn) {

		String comment  = "Deleted workitem with name "+workIn.getName();
        
        sessionFactory.getCurrentSession()
        	.createQuery("delete from WORKITEM where WORKITEM_ID = "+workIn.getWorkitemId())
        	.executeUpdate();
        
        workitemAuditDaoImpl.createWorkitemAudit(workIn.getWorkitemId(), comment);
        
        return true;
	}

	@Override
	@Transactional
	public boolean updateWorkitem(WorkitemModel workIn) {
		String SQL = "update WORKITEM set NAME = ?, DESCRIPTION = ?, STATUS = ?, WORKITEM_PAYLOAD = ?, UPDATED_BY = ?, UPDATED_DATE = ?, VERSION = VERSION + 1  where WORKITEM_ID = ? and VERSION = ?";
        jdbcTemplate.update(SQL, workIn.getName(), 
        				workIn.getDescription(),
        				workIn.getStatus().name(),
        				workIn.getWorkitemPayload(),
        				"SYSTEM",
        				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
        				workIn.getWorkitemId(),
        				workIn.getVersion());
        
        String comment = "Changed workitem status to "+workIn.getStatus();
        workitemAuditDaoImpl.createWorkitemAudit(workIn.getWorkitemId(), comment);
        return true;
	}

}
