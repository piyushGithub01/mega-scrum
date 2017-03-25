package com.scrum.common.model.workitem.args;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.scrum.common.constant.workitemStatus.WorkItemStatus;

@JsonDeserialize(builder = WorkItemModel.Builder.class)
public class WorkItemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086963807813587456L;

	private final String name;
	
	private final String description;
	
	private final WorkItemStatus status;

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public WorkItemStatus getStatus() {
		return status;
	}
	
	private WorkItemModel(Builder builder){
		this.name = builder.name;
		this.description = builder.description;
		this.status = builder.status;
	}
	

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("WorkItemModel [name=");
		builder2.append(name);
		builder2.append(", description=");
		builder2.append(description);
		builder2.append(", status=");
		builder2.append(status);
		builder2.append("]");
		return builder2.toString();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder{
		private String name;
		private String description;
		private WorkItemStatus status;
		
		public static Builder newBuilder(){
			return new Builder();
		}
		
		public Builder setName(String name){
			this.name = name;
			return this;
		}
		
		public Builder setDescription(String description){
			this.description = description;
			return this;
		}
		
		public Builder setStatus(WorkItemStatus status){
			this.status = status;
			return this;
		}
		
		public WorkItemModel build(){
			return new WorkItemModel(this);
		}
		
	}
}
