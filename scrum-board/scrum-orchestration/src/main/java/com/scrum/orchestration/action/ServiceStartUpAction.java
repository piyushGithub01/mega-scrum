package com.scrum.orchestration.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.scrum.common.action.AbstractBaseAction;
import com.scrum.common.routing.RoutingContext;
import com.scrum.orchestration.event.BootActivityCompleted;

@Component
public class ServiceStartUpAction extends AbstractBaseAction<ContextRefreshedEvent, String> {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Value("${scrum.orchestration.message}")
	private String message;
	
	@Override
	public void performAction(ContextRefreshedEvent event) {
		System.out.println("service has started, context was refreshed");
		onComplete(this.message);
		
	}

	@Override
	public void onComplete(String result) {
		RoutingContext routingContext = RoutingContext.Builder.newBuilder().setRoutingId().build();
		this.eventPublisher.publishEvent(new BootActivityCompleted(this, result, routingContext));
		
	}

}
