package com.scrum.common.model.workitem.args;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.scrum.common.constant.workitem.WorkitemStatus;

@JsonDeserialize(builder = WorkitemModel.Builder.class)
public class WorkitemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086963807813587456L;

	private final String workitemId;
	
	private final String name;
	
	private final String description;
	
	private final WorkitemStatus status;
	
	private final byte[] workitemPayload;
	
	private final String createdBy;
	
	private final Date createdDate;
	
	private final String updatedBy;
	
	private final Date updatedDate;
	
	private final int version;
	
	
	public String getWorkitemId() {
		return workitemId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public WorkitemStatus getStatus() {
		return status;
	}

	public byte[] getWorkitemPayload() {
		return workitemPayload;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public int getVersion() {
		return version;
	}

	private WorkitemModel(Builder builder){
		this.workitemId = builder.workitemId;
		this.name = builder.name;
		this.description = builder.description;
		this.status = builder.status;
		this.workitemPayload = builder.workitemPayload;
		this.createdBy = builder.createdBy;
		this.createdDate = builder.createdDate;
		this.updatedBy = builder.updatedBy;
		this.updatedDate = builder.updatedDate;
		this.version = builder.version;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("WorkitemModel [workitemId=");
		builder2.append(workitemId);
		builder2.append(", name=");
		builder2.append(name);
		builder2.append(", description=");
		builder2.append(description);
		builder2.append(", status=");
		builder2.append(status);
		builder2.append(", workitemPayload=");
		builder2.append(Arrays.toString(workitemPayload));
		builder2.append(", createdBy=");
		builder2.append(createdBy);
		builder2.append(", createdDate=");
		builder2.append(createdDate);
		builder2.append(", updatedBy=");
		builder2.append(updatedBy);
		builder2.append(", updatedDate=");
		builder2.append(updatedDate);
		builder2.append(", version=");
		builder2.append(version);
		builder2.append("]");
		return builder2.toString();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder{
		
		private String workitemId;
		
		private String name;
		
		private String description;
		
		private WorkitemStatus status;
		
		private byte[] workitemPayload;
		
		private String createdBy;
		
		private Date createdDate;
		
		private String updatedBy;
		
		private Date updatedDate;
		
		private int version;
		
		public Builder setWorkitemId(String workitemId) {
			this.workitemId = workitemId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setStatus(WorkitemStatus status) {
			this.status = status;
			return this;
		}

		public Builder setWorkitemPayload(byte[] workitemPayload) {
			this.workitemPayload = workitemPayload;
			return this;
		}

		public Builder setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder setCreatedDate(java.util.Date date) {
			this.createdDate = date;
			return this;
		}

		public Builder setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
			return this;
		}

		public Builder setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}

		public Builder setVersion(int version) {
			this.version = version;
			return this;
		}

		public static Builder newBuilder(){
			return new Builder();
		}
		
		public WorkitemModel build(){
			return new WorkitemModel(this);
		}
		
	}
}
