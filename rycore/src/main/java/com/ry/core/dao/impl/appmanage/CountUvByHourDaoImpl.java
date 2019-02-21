package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountUvByHourDao;
import com.ry.core.entity.da.CountIpByHour;
import com.ry.core.entity.da.CountUvByHour;
import com.ry.util.DateUtil;

@Repository
public class CountUvByHourDaoImpl extends BaseDao<CountUvByHour, Integer> implements CountUvByHourDao {

	
	@Override
	public void countUvByHour(Date executeDate, Date endDate) {
		String initSql = "select * from countUvByHour where DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d') and hour BETWEEN DATE_FORMAT(?, '%H') and DATE_FORMAT(?, '%H')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate,executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*) amount, date, hour, style FROM ( SELECT DISTINCT uuid, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, DATE_FORMAT(currentDate, '%H') AS HOUR, style FROM clickCount WHERE uuid IS NOT NULL AND uuid <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(currentDate, '%H') BETWEEN DATE_FORMAT(?, '%H') AND DATE_FORMAT(?, '%H')) a GROUP BY date, HOUR, style ORDER BY date, HOUR, style";
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate,executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountUvByHour uv = new CountUvByHour();
				uv.setAmount(Integer.valueOf(map.get("amount").toString()));
				uv.setHour(Integer.valueOf(map.get("hour").toString()));
				String date = map.get("date").toString();
				try {
					uv.setDate(DateUtil.parser(date,DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				uv.setStyle(map.get("style").toString());
				save(uv);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> getUvByHour(Date beginDate, Date endDate,
			String style, int hours) {
		String sql = "select * from countUvByHour where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? and hour <= ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(style);
		paramList.add(hours);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
