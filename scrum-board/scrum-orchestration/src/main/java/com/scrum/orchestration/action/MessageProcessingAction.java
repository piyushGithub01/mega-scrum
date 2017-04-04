package com.scrum.orchestration.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.scrum.common.action.AbstractBaseAction;
import com.scrum.orchestration.event.BootActivityCompleted;

@Component
public class MessageProcessingAction extends AbstractBaseAction<BootActivityCompleted, String> {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public void performAction(BootActivityCompleted event) {
		System.out.println("message processing has started, BootActivityCompleted event was published");
		System.out.println("message is : " + event.getMessage());
		System.out.println("routing id is : " + event.getRoutingContext().getRoutingId());
		onComplete("");
	}

	@Override
	public void onComplete(String result) {
		System.out.println("message processing has completed");
	}

}
