package com.scrum.common.model.workitem.args;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.scrum.common.constant.workitemStatus.WorkItemStatus;

import junit.framework.Assert;

@JsonDeserialize(builder = UpdateWorkItemStatusArgs.Builder.class)
public class UpdateWorkItemStatusArgs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086963807813587584L;

	private final String name;
	
	private final WorkItemStatus status;

	public String getName() {
		return name;
	}
	
	public WorkItemStatus getStatus() {
		return status;
	}
	
	private UpdateWorkItemStatusArgs(Builder builder){
		this.name = builder.name;
		this.status = builder.status;
	}
	

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("UpdateWorkItemStatusArgs [name=");
		builder2.append(name);
		builder2.append(", status=");
		builder2.append(status);
		builder2.append("]");
		return builder2.toString();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder{
		private String name;
		private WorkItemStatus status;
		
		public static Builder newBuilder(){
			return new Builder();
		}
		
		public Builder setName(String name){
			this.name = name;
			return this;
		}
		
		public Builder setStatus(WorkItemStatus status){
			this.status = status;
			return this;
		}
		
		public UpdateWorkItemStatusArgs build(){
			Assert.assertNotNull(name);
			Assert.assertNotNull(status);
			return new UpdateWorkItemStatusArgs(this);
		}
		
	}
}
