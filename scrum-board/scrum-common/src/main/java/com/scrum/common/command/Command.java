package com.scrum.common.command;

public interface Command<U, T> {
	
	public abstract T executeCommand(U i);

}
