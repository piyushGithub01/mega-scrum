//package com.scrum.integrationTest.workitem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.Assert;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.concurrent.FailureCallback;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.SuccessCallback;
//import org.springframework.web.client.AsyncRestTemplate;
//import org.springframework.web.context.request.async.DeferredResult;
//
//import com.scrum.common.constant.workitem.WorkitemStatus;
//import com.scrum.common.model.workitem.args.CreateWorkItemArgs;
//import com.scrum.common.model.workitem.args.UpdateWorkItemStatusArgs;
//import com.scrum.common.model.workitem.args.WorkitemModel;
//import com.scrum.workitem.WorkItemApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = WorkItemApplication.class)
//@WebIntegrationTest
//public class WorkItemControllerTest {
//
//	private static final AsyncRestTemplate AsyncRestTemplate = new AsyncRestTemplate();
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(WorkItemControllerTest.class);
//	
//	@Test
//	public void testCreateWorkItem(){
//
//		CreateWorkItemArgs newWorkItem = CreateWorkItemArgs.Builder.newBuilder()
//											.setName("3")
//											.setDescription("three")
//											.build();
//		String url = "http://localhost:1112/workitem/create";
//		HttpMethod method = HttpMethod.POST;
//		
//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("Content-Type", "application/json");
//
//        headers.setAll(map);
//
//        Map<String, String> req_payload = new HashMap<String, String>();
//        req_payload.put("name", newWorkItem.getName());
//        req_payload.put("description", newWorkItem.getDescription());
//
//        HttpEntity<?> requestEntity = new HttpEntity<>(req_payload, headers);
//        
//		DeferredResult<WorkitemModel> deferredResult2 = initiateAsyncCreateWorkItemReq(url, method, requestEntity);
//		deferredResult2.setResultHandler(this::onProcessed);
//		
//	}
//	
//	@Test
//	public void testUpdateWorkItem(){
//
//		UpdateWorkItemStatusArgs newWorkItem = UpdateWorkItemStatusArgs.Builder.newBuilder()
//											.setName("3")
//											.setStatus(WorkitemStatus.Done)
//											.build();
//		String url = "http://localhost:1112/workitem/update-status";
//		HttpMethod method = HttpMethod.PUT;
//		
//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("Content-Type", "application/json");
//
//        headers.setAll(map);
//
//        Map<String, String> req_payload = new HashMap<String, String>();
//        req_payload.put("name", newWorkItem.getName());
//        req_payload.put("status", newWorkItem.getStatus().name());
//
//        HttpEntity<?> requestEntity = new HttpEntity<>(req_payload, headers);
//        
//		DeferredResult<WorkitemModel> deferredResult2 = initiateAsyncCreateWorkItemReq(url, method, requestEntity);
//		deferredResult2.setResultHandler(this::onProcessed);
//		
//	}
//	
//	private DeferredResult<WorkitemModel> initiateAsyncCreateWorkItemReq(String url, HttpMethod method, HttpEntity<?> requestEntity) {
//		DeferredResult<WorkitemModel> deferredResult = new DeferredResult<WorkitemModel>(1000L);
//		deferredResult.onTimeout(() -> {
//    		deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
//    	});
//		
//	    ListenableFuture<ResponseEntity<WorkitemModel>> future = AsyncRestTemplate.exchange(url, method, requestEntity, 
//	    		WorkitemModel.class);
//	    future.addCallback(new SuccessCallback<ResponseEntity<WorkitemModel>>(){
//								@Override
//								public void onSuccess(ResponseEntity<WorkitemModel> result) {
//									LOGGER.info("in success of listnable future");
//									deferredResult.setResult(result.getBody());
//								}
//						    }, 
//	    				  new FailureCallback(){
//								@Override
//								public void onFailure(Throwable ex) {
//									LOGGER.error("in failure of listnable future : {}",ex);
//									deferredResult.setErrorResult(ex);
//								}
//							    });
//		return deferredResult;
//	}
//
//	private void onProcessed(Object result){
//		if(result instanceof Exception){
//			Assert.isTrue(!(result instanceof Exception), "Restful service call to workitem failed with exception");
//			LOGGER.error("deferred result has error : {}",result);
//		}else{
//			LOGGER.info("deferred result is : {}",result);
//			Assert.notNull(result, "Restful service call to workitem Failed");
//		}
//	}
//	
//}
//
//
