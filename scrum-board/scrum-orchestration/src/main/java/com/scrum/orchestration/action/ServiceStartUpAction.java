package com.scrum.orchestration.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.scrum.orchestration.event.BootActivityCompleted;

@Component
public class ServiceStartUpAction implements org.springframework.context.ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("service has started, since context was refreshed");
		onSuccess();
		
	}

	private void onSuccess() {
		// TODO Auto-generated method stub
		this.eventPublisher.publishEvent(new BootActivityCompleted(this, "first action"));
	}

}
