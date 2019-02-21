package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderTaskDao {
	
	/**
	 * 分页获机构订单操作记录列表
	 * @param req
	 * @since 2016年3月3日 下午4:24:35
	 */
	public PageResults<DistributeOrderTask> getPageList(OrgOrderTaskRequest req);
   
	/**
	 * 保存对象（更新）
	 * @param task
	 * @throws Exception
	 * @author WKX
	 */
	public void saveModel(DistributeOrderTask task) throws Exception;
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年3月17日 上午10:56:21
	 */
	public DistributeOrderTask getById(Integer id);
	
	/**
	 * 根据dtbo_id计算完成时间
	 * @author cx
	 * @since 2016年5月24日 
	 */
	public List<Map<String,Object>> Calcompltime(Integer dtboid);
}