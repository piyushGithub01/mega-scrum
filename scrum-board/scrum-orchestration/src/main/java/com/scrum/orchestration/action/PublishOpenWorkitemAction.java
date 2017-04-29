package com.scrum.orchestration.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrum.common.model.workitem.args.WorkitemModel;
import com.scrum.orchestration.feignclient.WorkItemServiceFeignClient;

@Component
public class PublishOpenWorkitemAction {
	
	@Autowired
	private WorkItemServiceFeignClient workItemServiceFeignClient;
	
	public boolean publishAllWorkitem(){
//		DeferredResult<List<WorkitemModel>> items = workItemServiceFeignClient.getAllWorkItems();
////		items.setResultHandler(this::onProcessed);
//		System.out.println(items.hasResult()?items.getResult():false);
//		return true;
		List<WorkitemModel> items = workItemServiceFeignClient.getAllWorkItemsSync();
		System.out.println(items);
		return true;
	}
	
//	private void onProcessed(Object result){
//		if(result instanceof Exception){
//			System.out.println("def res had issues"+result);
//		}else{
//			System.out.println("def result is available");
//		}
//	}

}
