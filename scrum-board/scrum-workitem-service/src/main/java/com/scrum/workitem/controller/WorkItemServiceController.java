package com.scrum.workitem.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.scrum.persistenceapi.workitem.query.GetAllWorkItemQuery;
import com.scrum.persistenceapi.workitem.query.GetWorkItemByNameQuery;
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
	
	@Autowired
	private GetAllWorkItemQuery getAllWorkItemQuery;
	
	@Autowired
	private GetWorkItemByNameQuery getWorkItemByNameQuery;
	
	@RequestMapping(value = "/workitem/create", method = RequestMethod.POST)
    public @ResponseBody DeferredResult<WorkItemModel> createWorkItem(@RequestBody CreateWorkItemArgs workItemModel) {
		LOGGER.info("received request to create workitem with arguments : {}",workItemModel);
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
        				LOGGER.info("successfully created workitem with arguments : {}",workItemModel);
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/delete", method = RequestMethod.PUT)
    public @ResponseBody DeferredResult<Boolean> deleteWorkItem(@RequestBody DeleteWorkItemArgs workItemModel) {
		LOGGER.info("received request to delete workitem with arguments : {}",workItemModel);
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
        				LOGGER.info("successfully deleted workitem with arguments : {}",workItemModel);
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/update-status", method = RequestMethod.PUT)
    public @ResponseBody DeferredResult<WorkItemModel> updateWorkItemStatus(@RequestBody UpdateWorkItemStatusArgs workItemModel) {
		LOGGER.info("received request to update workitem status with arguments : {}",workItemModel);
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
        				LOGGER.info("successfully updated status for workitem with arguments : {}",workItemModel);
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/get-all-workitems", method = RequestMethod.GET)
    public @ResponseBody DeferredResult<List<WorkItemModel>> getAllWorkItems() {
		LOGGER.info("received request to get all workitem ");
        DeferredResult<List<WorkItemModel>> deferredResult = new DeferredResult<List<WorkItemModel>>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return getAllWorkItemQuery.executeQuery(""); }, 
    									WorkItemServiceConfiguration.WORKITEM_EXECUTOR)
        .whenCompleteAsync((result, throwable) -> {
        			if(null != throwable){
        				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
        			}else{
        				LOGGER.info("successfully retrived all workitem ");
        				deferredResult.setResult(result);
        			}
        });
    	return deferredResult;
    }
	
	@RequestMapping(value = "/workitem/get-by-name/{name}", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<WorkItemModel> getWorkItemByName(@PathVariable String name) {
		LOGGER.info("received request to get workitem by name: {}", name);
		DeferredResult<WorkItemModel> deferredResult = new DeferredResult<WorkItemModel>();
		deferredResult.onTimeout(() -> {
			deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
		});
		CompletableFuture.supplyAsync(() -> { return getWorkItemByNameQuery.executeQuery(name); }, 
				WorkItemServiceConfiguration.WORKITEM_EXECUTOR)
		.whenCompleteAsync((result, throwable) -> {
			if(null != throwable){
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
			}else{
				LOGGER.info("successfully retrived workitem by name {} ", name);
				deferredResult.setResult(result);
			}
		});
		return deferredResult;
	}
	
}
