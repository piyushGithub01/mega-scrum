package com.scrum.orchestration.event;

import org.springframework.context.ApplicationEvent;

public class BootActivityCompleted extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;

	public BootActivityCompleted(Object source, String message) {
		super(source);
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
