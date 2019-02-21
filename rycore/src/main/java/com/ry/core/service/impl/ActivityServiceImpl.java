package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ActivityDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.CommentsDao;
import com.ry.core.dao.InquiryReplyDao;
import com.ry.core.dao.IntegraltradingDetailDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Activity;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Member;
import com.ry.core.form.ActivityForm;
import com.ry.core.service.ActivityService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Service
public class ActivityServiceImpl extends BaseDao<Activity, Integer> implements
		ActivityService {

	@Resource
	ActivityDao activityDao;
	
	@Resource
	IntegraltradingDetailDao integralDao;
	
	@Resource
	InquiryReplyDao  inquiryReplyDao;
	
	@Resource
	CommentsDao commentsDao;
	
	@Resource
	MemberDao memberDao;

	@Override
	public PageResults<Activity> getPageResults(ActivityForm activityForm) {
		
		return activityDao.getPageResults(activityForm);		
	}

	@Override
	public void addActivity(Activity activity) {
		activityDao.addActivity(activity);
	}

	@Override
	public void updateActivity(Activity activity) {
		activityDao.updateActivity(activity);
	}

	@Override
	public Activity getActivity(Activity activity) {		
		return activityDao.getActivity(activity);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActivityService#getByDayAction(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Actionlog getByDayAction(String action, String day, Integer memberId) {
		// TODO Auto-generated method stub
		return activityDao.getByDayAction(action, day, memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActivityService#timingIntegral(java.lang.Integer)
	 */
	@Override
	public void timingIntegral(Integer memberId,Integer integral,String title,String action) {
		IntegraltradingDetail integraltradingDetail = new IntegraltradingDetail();
		integraltradingDetail.setTradingTime(new Date());
		integraltradingDetail.setState(1);
		integraltradingDetail.setMemberId(memberId);
		List<IntegraltradingDetail> list = integralDao.getByMemberId(memberId);
		Integer integraltool = 0;
		if(list != null && list.size()>0){
			integraltool = list.get(0).getIntegralTotal();
		}
		integraltool = integraltool+integral;
		integraltradingDetail.setTitle(title);
		integraltradingDetail.setNum(integral);
		integraltradingDetail.setIntegralTotal(integraltool);
		integralDao.saveModel(integraltradingDetail);
	}
	
	public void actionIntegral(Integer integral,String title,String action){
		Actionlog actionlog = null;
		IntegraltradingDetail integraltradingDetail = null;
		List<Member> listmember = memberDao.getList(null);
		for (Member member : listmember) {
			integraltradingDetail = new IntegraltradingDetail();
			integraltradingDetail.setTradingTime(new Date());
			integraltradingDetail.setState(1);
			
			integraltradingDetail.setMemberId(member.getId());
			List<IntegraltradingDetail> list = integralDao.getByMemberId(member.getId());
			Integer integraltool = 0;
			if(list != null && list.size()>0){
				integraltool = list.get(0).getIntegralTotal();
			}
			actionlog = activityDao.getByDayAction(action, DateUtil.formart(new Date(), DateUtil.FORMART3), member.getId());
			if(actionlog!= null){//该功能今天已使用
				integraltool = integraltool+integral;
				integraltradingDetail.setTitle(title);
				integraltradingDetail.setNum(integral);
				integraltradingDetail.setIntegralTotal(integraltool);
				integralDao.saveModel(integraltradingDetail);
			}
		}
	}
	
	/**
	 * @author MH
	 * @param title 记录名称
	 * @param day	时间
	 * @param memberId 用户Id
	 * @return
	 */
	public List<IntegraltradingDetail> getlistActivity(String title ,String day,Integer memberId){
		return activityDao.getlistActivity(title, day, memberId);
	}
}
