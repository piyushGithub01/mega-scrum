package com.scrum.workitem.hibernate.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scrum.common.model.workitem.args.WorkitemAuditModel;
import com.scrum.workitem.hibernate.converters.WorkitemAuditConverter;
import com.scrum.workitem.hibernate.entity.WorkitemAuditEntity;

@Repository
public class WorkitemAuditDaoImpl implements WorkitemAuditDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkitemAuditModel> getAllWorkitemAudit(String id) {
		
		String SQL = "select * from WORKITEM_AUDIT where WORKITEM_ID = " + id;
		
	    List<WorkitemAuditEntity> allEntity =  this.sessionFactory.getCurrentSession()
	    											.createQuery(SQL)
	    											.list();
		return WorkitemAuditConverter.convertToModel(allEntity);
	}

	@Override
	public void createWorkitemAudit(String workitemId, String comment) {

		WorkitemAuditEntity audit = new WorkitemAuditEntity();
	    audit.setWorkitemAuditId(UUID.randomUUID().toString());
	    audit.setCreatedBy("SYSTEM");
	    audit.setCreatedDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
	    audit.setComment(comment);
	    audit.setWorkitemId(workitemId);
		
		this.sessionFactory.getCurrentSession().save(audit);
	}

}
