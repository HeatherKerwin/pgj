package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActivityDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Activity;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.form.ActivityForm;
import com.ry.util.page.PageResults;

@Repository
public class ActivityDaoImpl extends BaseDao<Activity, Integer> implements ActivityDao {

	@Override
	public PageResults<Activity> getPageResults(ActivityForm activityForm) {
		StringBuilder hql = new StringBuilder("from activity where 1=1 and flag=0 ");		
		StringBuilder hqlcount = new StringBuilder(" select count(*) from activity where 1=1 and flag=0 "); 		
		
		List paramList = new ArrayList();
		
		if(StringUtils.hasText(activityForm.getHeader())){
			hql.append(" and header = ?");
			hqlcount.append(" and header = ?");
			paramList.add(activityForm.getHeader());
		}
		
		if(activityForm.getStartdate() !=null){
			hql.append(" and startdate >= ? ");
			hqlcount.append(" and startdate >= ? ");
			paramList.add(activityForm.getStartdate());
		}	
		
		if(activityForm.getEnddate() != null){
			hql.append(" and enddate < ?");
			hqlcount.append(" and enddate < ?");
			paramList.add(activityForm.getEnddate());
		}		
		hql.append(" order by createtime desc ");
		PageResults<Activity> pageResults = findPageByFetchedHql(hql.toString(), hqlcount.toString(), activityForm.getPageNo(), activityForm.getPageSize(), paramList.toArray());		
		
		return pageResults;
	}
	
	

	@Override
	public void addActivity(Activity activity) {
		save(activity);
	}

	@Override
	public void updateActivity(Activity activity) {
		update(activity);
	}

	@Override
	public void deleteActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activity getActivity(Activity activity) {
		String hql = "from activity where 1=1 ";
		List paramList = new ArrayList();
		if (activity != null) {
			if (activity.getId() != null && activity.getId() > 0) {
				hql += " and id = ? ";
				paramList.add(activity.getId());
			}
		}
		
		return getByHQL(hql, paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActivityDao#getByDayAction(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Actionlog getByDayAction(String action, String day, Integer memberId) {
		StringBuilder hql = new StringBuilder("from actionlog where 1=1 ");		
		
		List<Object> paramList = new ArrayList<Object>();
		if(StringUtils.hasText(action)){
			hql.append(" and CODE = ?");
			paramList.add(action);
		}
		if(day !=null){
			hql.append(" and DATE_FORMAT(actiontime,'%Y-%m-%d') = ? ");
			paramList.add(day);
		}	
		if(memberId != null){
			hql.append(" and memberId = ?");
			paramList.add(memberId);
		}		
		List<Actionlog> list = queryByHql(hql.toString(), paramList.toArray());
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}



	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActivityDao#getlistActivity(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<IntegraltradingDetail> getlistActivity(String title, String day, Integer memberId) {
		StringBuilder hql = new StringBuilder("from integral_trading_detail where 1=1 ");		
		List<Object> paramList = new ArrayList<Object>();
		if(StringUtils.hasText(title)){
			hql.append(" and title = ?");
			paramList.add(title);
		}
		if(day !=null){
			hql.append(" and trading_time = ? ");
			paramList.add(day);
		}	
		if(memberId != null){
			hql.append(" and memberId = ?");
			paramList.add(memberId);
		}		
		return queryByHql(hql.toString(), paramList.toArray());
	}
}
