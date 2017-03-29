package com.scrum.datapersistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "WORKITEM_AUDIT")
public class WorkitemAuditEntity {
	
    @Id
    @Column(nullable = false, name="WORKITEM_AUDIT_ID")
    private String workitemAuditId;
    
    @Column(nullable = false, name="WORKITEM_ID")
    private String workitemId;
    
    @Column(nullable = true, name="COMMENT")
    private String comment;
    
    @Column(nullable = true, name="CREATED_BY")
    private String createdBy;
    
    @Column(nullable = true, name="CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    WorkitemAuditEntity() {
    }

    public WorkitemAuditEntity(String workitemAuditId, String workitemId, String comment, 
    						String createdBy, Date createdDate) {
    	this.workitemAuditId = workitemAuditId;
    	this.workitemId = workitemId;
    	this.comment = comment;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

	public String getWorkitemAuditId() {
		return workitemAuditId;
	}

	public void setWorkitemAuditId(String workitemAuditId) {
		this.workitemAuditId = workitemAuditId;
	}

	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkitemAuditEntity [workitemAuditId=");
		builder.append(workitemAuditId);
		builder.append(", workitemId=");
		builder.append(workitemId);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append("]");
		return builder.toString();
	}

}
