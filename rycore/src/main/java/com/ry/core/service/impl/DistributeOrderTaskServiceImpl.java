package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.core.service.DistributeOrderTaskService;
import com.ry.util.page.PageResults;

@Service
public class DistributeOrderTaskServiceImpl implements DistributeOrderTaskService {

	@Resource
	DistributeOrderTaskDao taskDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderTaskService#getPageList(com.ry.core.form.orgOrder.OrgOrderTaskRequest)
	 */
	@Override
	public PageResults<DistributeOrderTask> getPageList(OrgOrderTaskRequest req) {
		// TODO Auto-generated method stub
		return taskDao.getPageList(req);
	}
	
	@Override
	public List<Map<String,Object>> Calcompltime(Integer dtboid){
		return taskDao.Calcompltime(dtboid);
	}

	@Override
	public void saveModel(DistributeOrderTask distributeOrderTask) throws Exception{
		taskDao.saveModel(distributeOrderTask);
	}
}