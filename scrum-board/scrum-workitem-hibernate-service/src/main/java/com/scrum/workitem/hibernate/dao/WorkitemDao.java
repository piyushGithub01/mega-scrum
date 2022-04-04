package com.scrum.workitem.hibernate.dao;

import java.util.List;

import com.scrum.common.model.workitem.args.WorkitemModel;

public interface WorkitemDao {

  public boolean createWorkitem(WorkitemModel workIn);
  
  public WorkitemModel getWorkitem(String id);
  
  public List<WorkitemModel> getAllWorkitems();
  
  public boolean deleteWorkitem(WorkitemModel workIn);
  
  public boolean updateWorkitem(WorkitemModel workIn);
  
}
