package com.scrum.datapersistence.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "WORKITEM")
public class WorkitemEntity {
	
    @Id
    @Column(nullable = false, name="WORKITEM_ID")
    private String workitemId;
    
    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(nullable = false, name="STATUS")
    private String status;
    
    @Column(name="WORKITEM_PAYLOAD")
    private byte[] workitemPayload;
    
    @Column(name="CREATED_BY")
    private String createdBy;
    
    @Column(name="CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="UPDATED_BY")
    private String updatedBy;
    
    @Column(name="UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    @Version
    @Column(name="VERSION")
    private int version;

    WorkitemEntity() {
    }

    public WorkitemEntity(String workitemId, String name, String description, String status,
    						byte[] workitemPayload, String createdBy, Date createdDate,
    						String updatedBy, Date updatedDate, int version) {
    	this.workitemId = workitemId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.workitemPayload = workitemPayload;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.version = version;
    }

	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getWorkitemPayload() {
		return workitemPayload;
	}

	public void setWorkitemPayload(byte[] workitemPayload) {
		this.workitemPayload = workitemPayload;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkItemEntity [workitemId=");
		builder.append(workitemId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append(", workitemPayload=");
		builder.append(Arrays.toString(workitemPayload));
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", updatedDate=");
		builder.append(updatedDate);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
    
}
