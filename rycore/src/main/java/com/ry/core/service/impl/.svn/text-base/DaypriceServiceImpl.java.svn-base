package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DaypriceDao;
import com.ry.core.entity.Dayprice;
import com.ry.core.service.DaypriceService;

@Service
public class DaypriceServiceImpl implements DaypriceService {

	@Resource
	DaypriceDao daypriceDao;
	
	@Override
	public List<Dayprice> getDaypriceList(String day, Integer type4, String time) {
		
		return daypriceDao.getList(day, type4, time);
	}

	@Override
	public void updateAllDayprice(List<Dayprice> daypriceList) throws Exception {
		if(daypriceList!=null&&daypriceList.size()!=0){
			for(Dayprice dayprice : daypriceList){
				String day = dayprice.getDay();
				Integer type1 = dayprice.getType1();
				Integer type2 = dayprice.getType2();
				Integer type4 = dayprice.getType4();
				String time = dayprice.getTime();
				List baseEntityList = daypriceDao.getList(time, day, type1, type2, type4);				
				if(baseEntityList==null||baseEntityList.size()==0){
					daypriceDao.addDayprice(dayprice);
				}else{
					Object[] objs = (Object [])baseEntityList.get(0);
					Integer id = (Integer)objs[2];
					Dayprice dayprice2 = daypriceDao.getDayprice(id);
					dayprice2.setPrice(dayprice.getPrice());
					//Dayprice oldDayprice = daypriceList.get(i);
					//oldDayprice.setPrice(dayprice.getPrice());
					daypriceDao.updateDayprice(dayprice2);
				}
			}
		}		
	}

	@Override
	public List<Dayprice> findDaypriceList(String day, Integer type1, Integer type2,
			Integer type4, String time, Integer start, Integer end) {
		
		return daypriceDao.findDaypriceList(day, type1, type2, type4, time, start, end);
	}

	@Override
	public List<Dayprice> findDaypriceList(Integer type1, Integer type2,
			Integer type4, String time, Integer start, Integer end) {
		
		return daypriceDao.findDaypriceList(type1, type2, type4, time, start, end);
	}
	
	@Override
	public List<Dayprice> getList(String time, String day, Integer type1,
			Integer type2, Integer type4) {
		
		return daypriceDao.getList(time, day, type1, type2, type4);
	}

	@Override
	public List<Dayprice> findDaypriceList(String day1, String day2,
			String day3, Integer type1, Integer type2, Integer type4,
			String time, Integer start, Integer end) {

		return daypriceDao.findDaypriceList(day1, day2, day3, type1, type2, type4, time, start, end);
	}

	@Override
	public List<Dayprice> findDaypriceList(String day1, String day2,
			String day3, String day4, String day5, String day6, Integer type1,
			Integer type2, Integer type4, String time, Integer start,
			Integer end) {		
		
		return daypriceDao.findDaypriceList(day1, day2, day3, day4, day5, day6, type1, type2, type4, time, start, end);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DaypriceService#addAllDayprice(java.util.List)
	 */
	public void addAllDayprice(List<Dayprice> daypriceList) throws Exception {
		if (daypriceList != null && daypriceList.size() > 0) {
			for(Dayprice dayprice : daypriceList) {
				daypriceDao.addDayprice(dayprice);
			}
		}
	}
}
