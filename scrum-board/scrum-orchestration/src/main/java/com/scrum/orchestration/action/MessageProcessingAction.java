package com.scrum.orchestration.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.scrum.orchestration.event.BootActivityCompleted;

@Component
public class MessageProcessingAction implements ApplicationListener<BootActivityCompleted> {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public void onApplicationEvent(BootActivityCompleted event) {
		// TODO Auto-generated method stub
		System.out.println("message processing has started, since BootActivityCompleted was published");
		System.out.println("message is : " + event.getMessage()); 
		onSuccess();
		
	}

	private void onSuccess() {
		// TODO Auto-generated method stub
		System.out.println("message processing has completed");
	}

}
