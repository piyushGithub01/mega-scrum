package com.scrum.orchestration.feignclient;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.scrum.common.model.workitem.args.WorkitemModel;

@FeignClient("scrum-workitem-service")
public interface WorkItemServiceFeignClient {
	
	@RequestMapping(value = "/workitem/get-all-workitems", method = RequestMethod.GET)
    public @ResponseBody DeferredResult<List<WorkitemModel>> getAllWorkItems();
	
	@RequestMapping(value = "/workitem/get-all-workitems-simple", method = RequestMethod.GET)
    public @ResponseBody List<WorkitemModel> getAllWorkItemsSync();

}
