package com.scrum.common.model.workitem.args;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.scrum.common.constant.workitemStatus.WorkItemStatus;

import junit.framework.Assert;

@JsonDeserialize(builder = CreateWorkItemArgs.Builder.class)
public class CreateWorkItemArgs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086963807813587123L;

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
	
	private CreateWorkItemArgs(Builder builder){
		this.name = builder.name;
		this.description = builder.description;
		this.status = WorkItemStatus.Todo;
	}
	

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("CreateWorkItemArgs [name=");
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
		
		public CreateWorkItemArgs build(){
			Assert.assertNotNull(name);
			return new CreateWorkItemArgs(this);
		}
		
	}
}
