package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderTaskService {
	
	/**
	 * 分页获机构订单操作记录列表
	 * @param req
	 * @since 2016年3月3日 下午4:24:35
	 */
   PageResults<DistributeOrderTask> getPageList(OrgOrderTaskRequest req);
	
	/**
	 * 根据dtbo_id计算完成时间
	 * @author cx
	 * @since 2016年5月24日 
	 */
	public List<Map<String,Object>> Calcompltime(Integer dtboid);
	
	/**
	 * 保存派单纪录
	 * @author KHC
	 * @param distributeOrderTask
	 * @since 2016年9月1日 上午9:32:14
	 */
	public void saveModel(DistributeOrderTask distributeOrderTask) throws Exception;
}