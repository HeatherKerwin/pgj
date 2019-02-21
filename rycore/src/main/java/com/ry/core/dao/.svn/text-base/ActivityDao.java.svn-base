package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Activity;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.form.ActivityForm;
import com.ry.util.page.PageResults;

public interface ActivityDao {

	PageResults<Activity> getPageResults(ActivityForm activityForm);
	
	void addActivity(Activity activity);
	
	void updateActivity(Activity activity);
	
	void deleteActivity(Activity activity);
	
	Activity getActivity(Activity activity);
	
	/**
	 * 根据时间功能，判断
	 * @author MH
	 * @param action 功能
	 * @param day 时间
	 * @param memberId 用户Id
	 * @return
	 */
	public Actionlog getByDayAction(String action, String day,Integer memberId);
	
	/**
	 * @author MH
	 * @param title 记录名称
	 * @param day	时间
	 * @param memberId 用户Id
	 * @return
	 */
	public List<IntegraltradingDetail> getlistActivity(String title ,String day,Integer memberId); 
}
