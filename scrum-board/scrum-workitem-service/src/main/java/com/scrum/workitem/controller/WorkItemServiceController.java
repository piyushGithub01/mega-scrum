package com.scrum.workitem.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.scrum.common.model.workitem.args.CreateWorkItemArgs;
import com.scrum.common.model.workitem.args.DeleteWorkItemArgs;
import com.scrum.common.model.workitem.args.UpdateWorkItemStatusArgs;
import com.scrum.common.model.workitem.args.WorkItemModel;
import com.scrum.persistenceapi.workitem.command.CreateWorkItemCommand;
import com.scrum.persistenceapi.workitem.command.DeleteWorkItemCommand;
import com.scrum.persistenceapi.workitem.command.UpdateWorkItemStatusCommand;
import com.scrum.workitem.WorkItemServiceConfiguration;

@RestController
public class WorkItemServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkItemServiceController.class);
	
	@Autowired
	private CreateWorkItemCommand createWorkItemCommand;
	
	@Autowired
	private UpdateWorkItemStatusCommand updateWorkItemStatusCommand;
	
	@Autowired
	private DeleteWorkItemCommand deleteWorkItemCommand;
	
	@RequestMapping(value = "/workitem/create", method = RequestMethod.POST)
    public @ResponseBody DeferredResult<WorkItemModel> createWorkItem(@RequestBody CreateWorkItemArgs workItemModel) {
		LOGGER.debug("received request to create workitem with arguments : {}",workItemModel);
    	DeferredResult<WorkItemModel> deferredResult = new DeferredResult<WorkItemModel>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return createWorkItemCommand.executeCommand(workItemModel); }, 
    									WorkItemServiceConfiguration.WORKITEM_EXECUTOR)
        .whenCompleteAsync((result, throwable) -> {
        			if(null != throwable){
        				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
        			}else{
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/delete", method = RequestMethod.PUT)
    public @ResponseBody DeferredResult<Boolean> deleteWorkItem(@RequestBody DeleteWorkItemArgs workItemModel) {
		LOGGER.debug("received request to delete workitem with arguments : {}",workItemModel);
        DeferredResult<Boolean> deferredResult = new DeferredResult<Boolean>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return deleteWorkItemCommand.executeCommand(workItemModel); }, 
    									WorkItemServiceConfiguration.WORKITEM_EXECUTOR)
        .whenCompleteAsync((result, throwable) -> {
        			if(null != throwable){
        				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
        			}else{
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/update-status", method = RequestMethod.PUT)
    public @ResponseBody DeferredResult<WorkItemModel> updateWorkItemStatus(@RequestBody UpdateWorkItemStatusArgs workItemModel) {
		LOGGER.debug("received request to update workitem status with arguments : {}",workItemModel);
        DeferredResult<WorkItemModel> deferredResult = new DeferredResult<WorkItemModel>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return updateWorkItemStatusCommand.executeCommand(workItemModel); }, 
    									WorkItemServiceConfiguration.WORKITEM_EXECUTOR)
        .whenCompleteAsync((result, throwable) -> {
        			if(null != throwable){
        				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
        			}else{
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
}
