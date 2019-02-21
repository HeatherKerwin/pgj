package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.util.page.PageResults;

@Repository
public class DistributeOrderTaskDaoImpl extends BaseDao<DistributeOrderTask, Integer> implements DistributeOrderTaskDao {
	@Override
	public PageResults<DistributeOrderTask> getPageList(OrgOrderTaskRequest req) {	
		
		StringBuffer hql = new StringBuffer("from distribute_order_task where 1=1");
		StringBuffer hqlcount = new StringBuffer("select count(*) from distribute_order_task where 1=1 ");
		List<Object> params = new ArrayList<Object>();
	
		/*if(mem.getState() != null){
			hql.append(" and orderflag = ?");
			hqlcount.append(" and orderflag = ?");
			params.add(mem.getState());
		}*/
		hql.append(" order by ordertime desc");
		PageResults<DistributeOrderTask> pageResult = findPageByFetchedHql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
		
		return pageResult;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderTaskDao#saveModel(com.ry.core.entity.DistributeOrderTask)
	 */
	public void saveModel(DistributeOrderTask task) throws Exception {
		if(task!=null && task.getId()!=null)update(task);
		else save(task);
	}
	
	public DistributeOrderTask getById(Integer id) {
		return get(id);
	}
	
	public List<Map<String,Object>> Calcompltime(Integer dtboid){
		String sql = "SELECT r1.t jieshu,r2.t kaishi,r2.dtbo_id FROM ("
				+ " SELECT res.create_time t FROM ("
				+ "	SELECT * FROM ("
				+ "	SELECT * FROM distribute_order_task WHERE dtbo_id=? ORDER BY id DESC"
				+ "	)task GROUP BY task.state"
				+ " )res WHERE res.state=3)r1,"
				+ " (SELECT res.create_time t,res.dtbo_id FROM ("
				+ "	SELECT * FROM ("
				+ "	SELECT * FROM distribute_order_task WHERE dtbo_id=? ORDER BY id DESC"
				+ "	)task GROUP BY task.state"
				+ " )res WHERE res.state=1)r2";
		List<Object> paras = new ArrayList<Object>();
		paras.add(dtboid);
		paras.add(dtboid);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
}