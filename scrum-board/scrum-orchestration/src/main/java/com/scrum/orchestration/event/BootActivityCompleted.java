package com.scrum.orchestration.event;

import org.springframework.context.ApplicationEvent;

import com.scrum.common.routing.RoutingContext;

public class BootActivityCompleted extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	private final RoutingContext routingContext;

	public BootActivityCompleted(Object source, String message, RoutingContext routingContext) {
		super(source);
		this.message = message;
		this.routingContext = routingContext;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public RoutingContext getRoutingContext() {
		return this.routingContext;
	}

}
