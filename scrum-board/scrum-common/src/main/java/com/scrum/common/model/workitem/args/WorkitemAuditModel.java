package com.scrum.common.model.workitem.args;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = WorkitemAuditModel.Builder.class)
public class WorkitemAuditModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2902238466413444110L;

	private final String workitemAuditId;
	
	private final String workitemId;
	
	private final String comment;
	
	private final String createdBy;
	
	private final Date createdDate;

	
	public String getWorkitemAuditId() {
		return workitemAuditId;
	}

	public String getWorkitemId() {
		return workitemId;
	}

	public String getComment() {
		return comment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	private WorkitemAuditModel(Builder builder){
		this.workitemAuditId = builder.workitemAuditId;
		this.workitemId = builder.workitemId;
		this.comment = builder.comment;
		this.createdBy = builder.createdBy;
		this.createdDate = builder.createdDate;
	}


	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("WorkitemAuditModel [workitemAuditId=");
		builder2.append(workitemAuditId);
		builder2.append(", workitemId=");
		builder2.append(workitemId);
		builder2.append(", comment=");
		builder2.append(comment);
		builder2.append(", createdBy=");
		builder2.append(createdBy);
		builder2.append(", createdDate=");
		builder2.append(createdDate);
		builder2.append("]");
		return builder2.toString();
	}


	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder{
		
		private String workitemAuditId;
		
		private String workitemId;
		
		private String comment;
		
		private String createdBy;
		
		private Date createdDate;
		
		public Builder setWorkitemAuditId(String workitemAuditId) {
			this.workitemAuditId = workitemAuditId;
			return this;
		}

		public Builder setWorkitemId(String workitemId) {
			this.workitemId = workitemId;
			return this;
		}

		public Builder setComment(String comment) {
			this.comment = comment;
			return this;
		}

		public Builder setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public static Builder newBuilder(){
			return new Builder();
		}
		
		public WorkitemAuditModel build(){
			return new WorkitemAuditModel(this);
		}
		
	}
}
