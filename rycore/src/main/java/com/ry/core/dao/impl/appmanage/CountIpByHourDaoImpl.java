package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountIpByHourDao;
import com.ry.core.entity.da.CountIpByHour;
import com.ry.core.entity.da.CountPvByHour;
import com.ry.util.DateUtil;

@Repository
public class CountIpByHourDaoImpl extends BaseDao<CountIpByHour, Integer> implements CountIpByHourDao {

	@Override
	public void countIpByHour(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM countIpByHour WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(date, '%H') BETWEEN DATE_FORMAT(?, '%H') AND DATE_FORMAT(?, '%H')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*) amount, date, hour, style FROM ( SELECT DISTINCT ip, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, DATE_FORMAT(currentDate, '%H') AS HOUR, style FROM clickCount WHERE ip IS NOT NULL AND ip <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(currentDate, '%H') BETWEEN DATE_FORMAT(?, '%H') AND DATE_FORMAT(?, '%H')) a GROUP BY date, HOUR, style ORDER BY date, HOUR, style";
		lists = getListMapBySQL(sql,  new Object[]{executeDate, endDate, executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountIpByHour ip = new CountIpByHour();
				ip.setAmount(Integer.valueOf(map.get("amount").toString()));
				ip.setHour(Integer.valueOf(map.get("hour").toString()));
				String date = map.get("date").toString();
				try {
					ip.setDate(DateUtil.parser(date,DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				ip.setStyle(map.get("style").toString());
				save(ip);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getIpByHour(Date beginDate, Date endDate,
			String style, int hours) {
		String sql = "select * from countIpByHour where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? and hour <= ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(style);
		paramList.add(hours);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}
	
	
}
