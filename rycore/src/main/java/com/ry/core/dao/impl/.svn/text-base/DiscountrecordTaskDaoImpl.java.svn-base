package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;

@Repository
public class DiscountrecordTaskDaoImpl extends BaseDao<DiscountrecordTask,Integer> implements DiscountrecordTaskDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordTaskDao#getByDiscountrecordId(java.lang.Integer)
	 */
	@Override
	public List<DiscountrecordTask> getByDiscountrecordId(Integer discountrecordId) {
		StringBuffer hql = new StringBuffer("from discountrecord_task where discountrecordId=? order by createTime asc");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(discountrecordId);
		return queryByHql(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordTaskDao#saveModel(com.ry.core.entity.DiscountrecordTask)
	 */
	@Override
	public void saveModel(DiscountrecordTask task) throws Exception {
		if(task!=null && task.getId()!=null)update(task);
		else save(task);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordTaskDao#getByDiscountrecordIdAndOperator(java.lang.Integer, com.ry.core.entity.DiscountrecordTask.Operator)
	 */
	public List<DiscountrecordTask> getByDiscountrecordIdAndOperator(Integer discountrecordId,Operator operator) {
		StringBuffer hql = new StringBuffer("from discountrecord_task where discountrecordId=? and operator=? order by createTime");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(discountrecordId);
		paramList.add(operator);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordTaskDao#getCurrentByDcrdId(java.lang.Integer)
	 */
	public List<DiscountrecordTask> getCurrentByDcrdId(Integer discountrecordId,Integer type) {
		StringBuffer hql = new StringBuffer(" FROM discountrecord_task WHERE id=(SELECT MAX(id) FROM discountrecord_task task WHERE task.discountrecordId=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(discountrecordId);
		if(type == null || type==0){//type，0：银票 ；1 ：商票 ；2：批量；
			hql.append(" and (task.type IS NULL or task.type=0) )");
		}else if(type != null ){
			hql.append(" and task.type = ? )");
			paramList.add(type);
		}
		return queryByHql(hql.toString(), paramList.toArray());
	}
}