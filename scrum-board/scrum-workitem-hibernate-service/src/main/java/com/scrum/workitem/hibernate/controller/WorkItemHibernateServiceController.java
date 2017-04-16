package com.scrum.workitem.hibernate.controller;

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

import com.scrum.common.model.workitem.args.WorkitemAuditModel;
import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.persistenceapi.workitem.command.CreateWorkitemCommand;
import com.scrum.persistenceapi.workitem.command.DeleteWorkitemCommand;
import com.scrum.persistenceapi.workitem.command.UpdateWorkitemStatusCommand;
import com.scrum.persistenceapi.workitem.query.GetAllWorkitemQuery;
import com.scrum.persistenceapi.workitem.query.GetWorkitemByIdQuery;
import com.scrum.persistenceapi.workitemAudit.query.GetWorkitemAuditByIdQuery;
import com.scrum.workitem.hibernate.ScrumWorkItemHibernateServiceConfiguration;

@RestController
public class WorkItemHibernateServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkItemHibernateServiceController.class);
	
	@Autowired
	private CreateWorkitemCommand createWorkitemCommand;
	
	@Autowired
	private UpdateWorkitemStatusCommand updateWorkitemStatusCommand;
	
	@Autowired
	private DeleteWorkitemCommand deleteWorkitemCommand;
	
	@Autowired
	private GetAllWorkitemQuery getAllWorkitemQuery;
	
	@Autowired
	private GetWorkitemByIdQuery getWorkitemByNameQuery;
	
	@Autowired
	private GetWorkitemAuditByIdQuery getWorkitemAuditByIdQuery;
	
	/**
	 *   Workitem related rest api 
	 */
	@RequestMapping(value = "/workitem/create", method = RequestMethod.POST)
    public @ResponseBody DeferredResult<WorkitemModel> createWorkItem(@RequestBody WorkitemModel workItemModel) {
		LOGGER.info("received request to create workitem with arguments : {}",workItemModel);
    	DeferredResult<WorkitemModel> deferredResult = new DeferredResult<WorkitemModel>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return createWorkitemCommand.executeCommand(workItemModel); }, 
    									ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
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
    public @ResponseBody DeferredResult<Boolean> deleteWorkItem(@RequestBody WorkitemModel workItemModel) {
		LOGGER.info("received request to delete workitem with arguments : {}",workItemModel);
        DeferredResult<Boolean> deferredResult = new DeferredResult<Boolean>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return deleteWorkitemCommand.executeCommand(workItemModel); }, 
    									ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
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
    public @ResponseBody DeferredResult<WorkitemModel> updateWorkItemStatus(@RequestBody WorkitemModel workItemModel) {
		LOGGER.info("received request to update workitem status with arguments : {}",workItemModel);
        DeferredResult<WorkitemModel> deferredResult = new DeferredResult<WorkitemModel>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return updateWorkitemStatusCommand.executeCommand(workItemModel); }, 
    									ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
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
    public @ResponseBody DeferredResult<List<WorkitemModel>> getAllWorkItems() {
		LOGGER.info("received request to get all workitem ");
        DeferredResult<List<WorkitemModel>> deferredResult = new DeferredResult<List<WorkitemModel>>();
    	deferredResult.onTimeout(() -> {
    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
    	});
    	CompletableFuture.supplyAsync(() -> { return getAllWorkitemQuery.executeQuery(""); }, 
    									ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
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
	
	@RequestMapping(value = "/workitem/get-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<WorkitemModel> getWorkItemById(@PathVariable String id) {
		LOGGER.info("received request to get workitem by id: {}", id);
		DeferredResult<WorkitemModel> deferredResult = new DeferredResult<WorkitemModel>();
		deferredResult.onTimeout(() -> {
			deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
		});
		CompletableFuture.supplyAsync(() -> { return getWorkitemByNameQuery.executeQuery(id); }, 
				ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
		.whenCompleteAsync((result, throwable) -> {
			if(null != throwable){
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
			}else{
				LOGGER.info("successfully retrived workitem by id {} ", id);
				deferredResult.setResult(result);
			}
		});
		return deferredResult;
	}
	
	/**
	 *   Workitem Audit rest api
	 */
	@RequestMapping(value = "/workitem/get-audit-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<List<WorkitemAuditModel>> getWorkitemAuditById(@PathVariable String id) {
		LOGGER.info("received request to get workitem audit by id: {}", id);
		DeferredResult<List<WorkitemAuditModel>> deferredResult = new DeferredResult<List<WorkitemAuditModel>>();
		deferredResult.onTimeout(() -> {
			deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
		});
		CompletableFuture.supplyAsync(() -> { return getWorkitemAuditByIdQuery.executeQuery(id); }, 
				ScrumWorkItemHibernateServiceConfiguration.SCRUM_WORKITEM_EXECUTOR)
		.whenCompleteAsync((result, throwable) -> {
			if(null != throwable){
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
			}else{
				LOGGER.info("successfully retrived workitem by id {} ", id);
				deferredResult.setResult(result);
			}
		});
		return deferredResult;
	}
}
