package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.HongbaoActivityDao;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.form.HongbaoActivityForm;
import com.ry.util.page.PageResults;

@Repository
public class HongbaoActivityDaoImpl extends BaseDao<HongbaoActivity, Integer> implements HongbaoActivityDao{

	@Override
	public PageResults<HongbaoActivity> getPageResults(HongbaoActivityForm hongbaoActivityForm) {
		StringBuilder hql = new StringBuilder("from hongbaoActivity where 1=1 and status=0 ");		
		StringBuilder hqlcount = new StringBuilder(" select count(*) from hongbaoActivity where 1 = 1 and status=0 "); 		
		
		List<Object> paramList = new ArrayList<Object>();
		
		if(hongbaoActivityForm.getStartdate() !=null){
			hql.append(" and startdate >= ? ");
			hqlcount.append(" and startdate >= ? ");
			paramList.add(hongbaoActivityForm.getStartdate());
		}	
		if(hongbaoActivityForm.getEnddate() != null){
			hql.append(" and enddate < ?");
			hqlcount.append(" and enddate < ?");
			paramList.add(hongbaoActivityForm.getEnddate());
		}
		if(hongbaoActivityForm.getTagId() !=null){//红包活动类型
			hql.append(" and tagId = ? ");
			hqlcount.append(" and tagId = ? ");
			paramList.add(hongbaoActivityForm.getTagId());
		}
		if(hongbaoActivityForm.getName() !=null){//红包活动名称查询
			hql.append(" and name like ? ");
			hqlcount.append(" and name like ? ");
			paramList.add("%"+hongbaoActivityForm.getName()+"%");
		}
		hql.append(" order by createtime desc ");
		PageResults<HongbaoActivity> pageResults = findPageByFetchedHql(hql.toString(), hqlcount.toString(), hongbaoActivityForm.getPageNo(), hongbaoActivityForm.getPageSize(), paramList.toArray());		
		
		return pageResults;
	}

	@Override
	public void addActivity(HongbaoActivity activity) {
		
		save(activity);
	}

	@Override
	public void updateActivity(HongbaoActivity activity) {

		update(activity);
	}

	@Override
	public void deleteActivity(HongbaoActivity activity) {

		activity.setStatus(1);
		update(activity);
	}

	@Override
	public HongbaoActivity getActivity(HongbaoActivity activity) {
		String hql = "from hongbaoActivity where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (activity != null) {
			if (activity.getId() != null && activity.getId() > 0) {
				hql += " and id = ? ";
				paramList.add(activity.getId());
			}
			if (activity.getFlag() != null) {
				hql += " and flag = ? ";
				paramList.add(activity.getFlag());
			}
			if (activity.getStatus() != null) {
				hql += " and status = ? ";
				paramList.add(activity.getStatus());
			}
		}
		return getByHQL(hql, paramList.toArray());
	}
	
	@Override
	public List<HongbaoActivity> getActivitys(HongbaoActivity activity) {
		String hql = "from hongbaoActivity where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (activity != null) {
			if (activity.getId() != null && activity.getId() > 0) {
				hql += " and id = ? ";
				paramList.add(activity.getId());
			}
			if (activity.getFlag() != null) {
				hql += " and flag = ? ";
				paramList.add(activity.getFlag());
			}
			if (activity.getStatus() != null) {
				hql += " and status = ? ";
				paramList.add(activity.getStatus());
			}
			if (activity.getTagId() != null) {//查询指定分类的红包
				hql += " and tagId = ? ";
				paramList.add(activity.getTagId());
			}
		}
		return getListByHQL(hql, paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.HongbaoActivityDao#countByStartAndEndAndTagIdAndNotId(java.util.Date, java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	public Long countByStartAndEndAndTagIdAndNotId(Date start,Date end,Integer tagId,Integer id) {
		StringBuffer hql = new StringBuffer(" select count(*) from hongbaoActivity where 1 = 1"); 
		List<Object> params = new ArrayList<Object>();
		if(start !=null && end !=null){
			hql.append(" and ((startdate>=? and enddate>? and startdate<? and enddate>=?) or (startdate<=? and enddate>? and startdate<? and enddate<=?) or (startdate<=? and enddate>? and startdate<? and enddate>=?) or (startdate>=? and enddate>? and startdate<? and enddate<=?))");
			params.add(start);
			params.add(start);
			params.add(end);
			params.add(end);
			params.add(start);
			params.add(start);
			params.add(end);
			params.add(end);
			params.add(start);
			params.add(start);
			params.add(end);
			params.add(end);
			params.add(start);
			params.add(start);
			params.add(end);
			params.add(end);
		}
		if(tagId!=null){
			hql.append(" and tagId=?");
			params.add(tagId);
		}
		if(id!=null){
			hql.append(" and id<>?");
			params.add(id);
		}
		return countByHql(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.HongbaoActivityDao#getMapActivitys(com.ry.core.form.HongbaoActivityForm)
	 */
	public List<Map<String,Object>> getMapActivitys(HongbaoActivityForm activity) {
		String hql = "SELECT tag.name as tagName,hb.tagId,hb.id,hb.name,hb.startdate,hb.enddate FROM hongbaoActivity AS hb LEFT JOIN tag ON hb.tagId=tag.id where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (activity != null) {
			if (activity.getId() != null && activity.getId() > 0) {
				hql += " and hb.id = ? ";
				paramList.add(activity.getId());
			}
			if (activity.getFlag() != null) {
				hql += " and hb.flag = ? ";
				paramList.add(activity.getFlag());
			}
			if (activity.getStatus() != null) {
				hql += " and hb.status = ? ";
				paramList.add(activity.getStatus());
			}
			if (activity.getTagId() != null) {//查询指定分类的红包
				hql += " and hb.tagId = ? ";
				paramList.add(activity.getTagId());
			}
		}
		return getListMapBySQL(hql+" ORDER BY hb.tagId,hb.enddate DESC",paramList.toArray());
	}
}