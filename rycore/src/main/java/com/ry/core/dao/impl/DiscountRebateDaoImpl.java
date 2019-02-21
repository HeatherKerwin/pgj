package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountRebateDao;
import com.ry.core.entity.DiscountRebate;
import com.ry.core.form.DiscountRebateForm;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DiscountRebateDaoImpl extends BaseDao<DiscountRebate, Integer> implements DiscountRebateDao {
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountRebateDao#saveModel(com.ry.core.entity.DiscountRebate)
	 */
	public void saveModel(DiscountRebate discountRebate) {
		save(discountRebate);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountRebateDao#updateModel(com.ry.core.entity.DiscountRebate)
	 */
	public void updateModel(DiscountRebate discountRebate) {
		update(discountRebate);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountRebateDao#getById(java.lang.Integer)
	 */
	public DiscountRebate getById(Integer id){
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountRebateDao#findPageModel(java.lang.Integer, java.lang.Integer, com.ry.core.entity.DiscountRebate)
	 */
	public PageResults<Map<String,Object>> findPageModel(Integer pageIndex,Integer pageSize,DiscountRebate from) {
		StringBuffer sql = new StringBuffer("FROM discount_rebate dr LEFT JOIN discountrecord disc ON dr.dcrd_id=disc.id WHERE 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(from!=null){
			if(from.getState()!=null){
				sql.append(" AND dr.state=?");
				param.add(from.getState());
			}
			if(from.getMemberName()!=null && from.getMemberName()!= ""){
				sql.append(" AND dr.member_name=?");
				param.add(from.getMemberName());
			}
			if(from.getMemberMobile()!=null && from.getMemberMobile()!= ""){
				sql.append(" AND dr.member_mobile=?");
				param.add(from.getMemberMobile());
			}
			if(from.getCreateTime()!=null){
				sql.append(" AND dr.create_time <= ? and dr.create_time >= ?");
				param.add(DateUtil.addDays(from.getCreateTime(),1));
				param.add(from.getCreateTime());
			}
		}
		String count = "select count(*) "+ sql.toString();
		return findPageMapByFetchedSql("SELECT dr.*,disc.deposit "+sql.toString(),count,pageIndex,pageSize, param.toArray());
	}
	
	@Override
	public List<DiscountRebate> getByDcrdId(Integer dcrdId) {
		StringBuffer hql = new StringBuffer("FROM discount_rebate WHERE dcrdId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dcrdId);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
	public List<Map<String,Object>> getList(DiscountRebateForm form) {
		StringBuffer sql = new StringBuffer("SELECT dr.*,disc.deposit FROM discount_rebate dr LEFT JOIN discountrecord disc ON dr.dcrd_id=disc.id WHERE 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(form!=null){
			if(form.getMemberId()!=null){
				sql.append(" AND disc.memberid=?");
				param.add(form.getMemberId());
			}
			if(form.getState()!=null){
				sql.append(" AND dr.state=?");
				param.add(form.getState());
			}
			if(form.getMemberName()!=null){
				sql.append(" AND dr.memberName=?");
				param.add(form.getMemberName());
			}
			if(form.getMemberMobile()!=null){
				sql.append(" AND dr.memberMobile=?");
				param.add(form.getMemberMobile());
			}
			if(form.getCreateTime()!=null){
				sql.append(" AND dr.createTime between ? and ?");
				param.add(form.getStart());
				param.add(form.getEnd());
			}
		}
		return getListMapBySQL(sql.toString(), param.toArray());
	}
}