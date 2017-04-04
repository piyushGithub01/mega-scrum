package com.scrum.common.routing;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RoutingContext.Builder.class)
public class RoutingContext implements Serializable {
	
	private static final long serialVersionUID = 4460603848254706973L;
	
	private final String routingId;
	
	private RoutingContext(Builder builder){
		this.routingId = builder.routingId;
	}
	
	public String getRoutingId() {
		return routingId;
	}
	
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static class Builder {
		
		private String routingId;
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder setRoutingId() {
			routingId = UUID.randomUUID().toString();
			return this;
		}
		
		public RoutingContext build() {
			return new RoutingContext(this);
		}
	}

}
