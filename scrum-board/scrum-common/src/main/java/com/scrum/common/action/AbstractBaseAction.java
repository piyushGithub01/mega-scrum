package com.scrum.common.action;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public abstract class AbstractBaseAction<E extends ApplicationEvent, R> implements ApplicationListener<E> {

	@Override
	public void onApplicationEvent(E event) {
		performAction(event);
	}
	
	public abstract void performAction(E event);
	
	public abstract void onComplete(R result);

}
