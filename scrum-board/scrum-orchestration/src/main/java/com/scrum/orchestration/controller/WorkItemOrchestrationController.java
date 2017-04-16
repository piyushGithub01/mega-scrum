package com.scrum.orchestration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scrum.orchestration.action.PublishOpenWorkitemAction;

@RestController
public class WorkItemOrchestrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkItemOrchestrationController.class);
		
	@Autowired
	private PublishOpenWorkitemAction publishOpenWorkitemAction;
	
	@RequestMapping(value = "/orchestration/get-all-workitems", method = RequestMethod.GET)
    public @ResponseBody boolean getAllWorkItems() {
		LOGGER.info("orchestration received request to get all workitem ");
		publishOpenWorkitemAction.publishAllWorkitem();
    	return true;
    }
		
}
