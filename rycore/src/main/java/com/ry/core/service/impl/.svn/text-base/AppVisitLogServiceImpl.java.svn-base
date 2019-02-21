package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AppVisitLogDao;
import com.ry.core.entity.da.AppVisitLog;
import com.ry.core.service.AppVisitLogService;

@Service
public class AppVisitLogServiceImpl implements AppVisitLogService{

	@Resource
	private AppVisitLogDao appVisitLogDao;
	
	@Override
	public void add(AppVisitLog visit) {
		appVisitLogDao.add(visit);
		
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getGroupByMemberIdAndActionDate(java.lang.String, java.lang.String)
	 */
	public List<Map<String,Object>> getGroupByMemberIdAndActionDate(String start,String end){
		return appVisitLogDao.getGroupByMemberIdAndActionDate(start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end){
		return appVisitLogDao.getByStartAndEnd(start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getByStartAndEndInMemberIds(java.util.Date, java.util.Date, java.util.List)
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIds(Date start,Date end,List<Integer> memberIds){
		return appVisitLogDao.getByStartAndEndInMemberIds(start, end,memberIds);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#createTempByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public void createTempByStartAndEnd(Date start,Date end){
		appVisitLogDao.createTempByStartAndEnd(start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getHasOrgInfoByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getHasOrgInfoByStartAndEnd(Date start,Date end){
		return appVisitLogDao.getHasOrgInfoByStartAndEnd(start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getByStartAndEnd(java.util.Date, java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end,Integer index,Integer size){
		return appVisitLogDao.getByStartAndEnd(start, end, index, size);
	}
	
	public List<Map<String, Object>> getByStartAndEndNotinMemberIds(Date start,Date end,Integer size,List<Integer>memberIds){
		return appVisitLogDao.getByStartAndEndNotinMemberIds(start, end, size, memberIds);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AppVisitLogService#getByStartAndEndInMemberIdsNoReple(java.util.Date, java.util.Date, java.util.List)
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIdsNoReple(Date start,Date end,List<Integer>memberIds){
		return appVisitLogDao.getByStartAndEndInMemberIdsNoReple(start, end, memberIds);
	}
}