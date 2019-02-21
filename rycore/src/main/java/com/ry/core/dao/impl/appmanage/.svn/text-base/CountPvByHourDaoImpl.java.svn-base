package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountPvByHourDao;
import com.ry.core.entity.da.CountPvByHour;
import com.ry.core.entity.da.IncreasedUserByFunction;
import com.ry.util.DateUtil;

@Repository
public class CountPvByHourDaoImpl extends BaseDao<CountPvByHour, Integer> implements CountPvByHourDao {

	
	@Override
	public void countPvByHour(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM countPvByHour WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND HOUR BETWEEN DATE_FORMAT(?, '%H') AND DATE_FORMAT(?, '%H')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate,executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*) AS amount, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, DATE_FORMAT(currentDate, '%H') AS hour, style FROM clickCount WHERE DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(currentDate, '%H') BETWEEN DATE_FORMAT(?, '%H') AND DATE_FORMAT(?, '%H') GROUP BY date, HOUR, style ORDER BY date, HOUR";
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate,executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountPvByHour pv = new CountPvByHour();
				pv.setAmount(Integer.valueOf(map.get("amount").toString()));
				pv.setHour(Integer.valueOf(map.get("hour").toString()));
				String date = map.get("date").toString();
				try {
					pv.setDate(DateUtil.parser(date,DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				pv.setStyle(map.get("style").toString());
				save(pv);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getPv(Date beginDate, Date endDate,
			String flag, String style, Integer hours) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(style);
		String sql = "";
		if("3".equals(flag)){
			sql = "select * from countPvByHour where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? and hour <= ?";
			paramList.add(hours);
		}else if("2".equals(flag)){
			sql = "select * from countPvByHour where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ?";
		}else if("0".equals(flag)){
			sql = "select sum(amount) amount,DATE(a.date) as date,a.style from (select amount,style,DATE_FORMAT( DATE_SUB( date, INTERVAL WEEKDAY(date) DAY ), '%Y-%m-%d' ) AS date from countPvByHour )a GROUP BY a.style,a.date having DATE_FORMAT(a.date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? ORDER BY a.date";
		}
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}
}
