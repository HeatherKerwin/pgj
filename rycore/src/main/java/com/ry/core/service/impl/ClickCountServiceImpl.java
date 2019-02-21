package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ClickCountDao;
import com.ry.core.entity.ClickCount;
import com.ry.core.service.ClickCountService;

@Service
public class ClickCountServiceImpl implements ClickCountService {

	@Resource
	ClickCountDao clickCountDao;
	
	@Override
	public void saveClickCount(ClickCount clickCount) {
		
		clickCountDao.saveClickCount(clickCount) ;
	}

	@Override
	public int count(String style, String code) {
		return clickCountDao.count(style,code);
	}

	@Override
	public int countByIp(String style) {
		return clickCountDao.countByIp(style);
	}

	@Override
	public int countByUuid(String style) {
		return clickCountDao.countByUuid(style);
	}

	@Override
	public List<String> getAllStyles() {
		
		return clickCountDao.getAllStyle();
	}

	@Override
	public List<String> getAllCodes() {
		
		return clickCountDao.getAllCodes();
	}
	
	public List<ClickCount> getList(){
		return clickCountDao.getList();
	}

	@Override
	public void updateClickCount(ClickCount clickCount) {
		clickCountDao.updateClickCount(clickCount) ;
	}
	
	public List<Map<String,Object>> getCountByCurrentDate(Date start,Date end){
		return clickCountDao.getCountByCurrentDate(start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ClickCountService#getNoKeyword()
	 */
	public List<ClickCount> getNoKeyword(String url){
		return clickCountDao.getNoKeyword(url);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ClickCountService#getSrcCountByDate(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getSrcCountByDate(Date start,Date end){
		return clickCountDao.getSrcCountByDate(start, end);
	}
}