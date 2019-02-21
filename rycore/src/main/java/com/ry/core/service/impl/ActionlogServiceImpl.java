package com.ry.core.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ActionlogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.form.CustomReportForm;
import com.ry.core.service.ActionlogService;
import com.ry.util.page.PageResults;

@Service
public class ActionlogServiceImpl extends BaseDao<Actionlog, Integer> implements ActionlogService {

	@Resource
	ActionlogDao actionlogDao;

	@Override
	public void addActionlog(Actionlog actionlog) {
//		if(!"180.166.201.178".equals(actionlog.getIpadr())){
			actionlogDao.addActionlog(actionlog);
//		}
	}

	@Override
	public Actionlog getActionlog() {
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ActionlogService#countActionlogByStartAndEndAndMemberId(java.util.Date, java.util.Date, java.lang.Integer)
	 */
	public BigInteger countActionlogByStartAndEndAndMemberId(Date start,Date end,Integer memberId){
		return actionlogDao.countActionlogByStartAndEndAndMemberId(start, end, memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActionlogService#countMemberLogin(java.util.List)
	 */
	public List<Map<String, Object>> countMemberLogin(List<Object> paramList, Date startTime, Date endTime) {
		return actionlogDao.countMemberLogin(paramList, startTime, endTime);
	}
	
	public List<Actionlog> getListByIdAndTime(List<Object> paramList, Date startTime, Date endTime){
		return actionlogDao.getListByIdAndTime(paramList, startTime, endTime);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ActionlogService#getByCodeAndMemberIdAndDayAndFrom(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getByCodeAndMemberIdAndDayAndFrom(String code,Integer memberId,String day,String from){
		return actionlogDao.getByCodeAndMemberIdAndDayAndFrom(code, memberId, day, from);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActionlogService#orgloginreport(com.ry.core.form.CustomReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> orgloginreport(CustomReportForm from) {
		return actionlogDao.orgloginreport(from);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ActionlogService#bnsloginreport(com.ry.core.form.CustomReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> bnsloginreport(CustomReportForm from) {
		return actionlogDao.bnsloginreport(from);
	}
}