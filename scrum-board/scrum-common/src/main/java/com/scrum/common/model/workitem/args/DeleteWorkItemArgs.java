package com.scrum.common.model.workitem.args;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import junit.framework.Assert;

@JsonDeserialize(builder = DeleteWorkItemArgs.Builder.class)
public class DeleteWorkItemArgs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086963807813587678L;

	private final String name;
	
	public String getName() {
		return name;
	}
	
	private DeleteWorkItemArgs(Builder builder){
		this.name = builder.name;
	}
	

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("DeleteWorkItemArgs [name=");
		builder2.append(name);
		builder2.append("]");
		return builder2.toString();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder{
		private String name;
		
		public static Builder newBuilder(){
			return new Builder();
		}
		
		public Builder setName(String name){
			this.name = name;
			return this;
		}
		
		public DeleteWorkItemArgs build(){
			Assert.assertNotNull(name);
			return new DeleteWorkItemArgs(this);
		}
		
	}
}
