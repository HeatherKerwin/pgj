package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Activity;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.form.ActivityForm;
import com.ry.util.page.PageResults;

public interface ActivityService {

	PageResults<Activity> getPageResults(ActivityForm activityForm);
	
	public void addActivity(Activity activity);
	
	public void updateActivity(Activity activity);
	
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
	 * 使用功能添加积分
	 * @author MH
	 * @param memberId用户Id
	 */
	public void timingIntegral(Integer memberId,Integer integral,String title,String action);
	
	/**
	 * 定时任务，添加积分
	 * @author MH
	 * @param integral  分数
	 * @param title  名称
	 * @param action 功能
	 */
	public void actionIntegral(Integer integral,String title,String action);
	
	/**
	 * @author MH
	 * @param title 记录名称
	 * @param day	时间
	 * @param memberId 用户Id
	 * @return
	 */
	public List<IntegraltradingDetail> getlistActivity(String title ,String day,Integer memberId); 
}
