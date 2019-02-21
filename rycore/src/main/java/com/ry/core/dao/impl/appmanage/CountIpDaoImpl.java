package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountIpDao;
import com.ry.core.entity.da.CountIp;
import com.ry.core.entity.da.CountIpByHour;
import com.ry.util.DateUtil;

@Repository
public class CountIpDaoImpl extends BaseDao<CountIp, Integer> implements
		CountIpDao {

	@Override
	public void countIp(int flag, Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM countIp WHERE flag = ? AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){
			sql  = "SELECT count(*) amount, date, style FROM ( SELECT ip, style, DATE_FORMAT( DATE_SUB( date, INTERVAL WEEKDAY(date) DAY ), '%Y-%m-%d' ) AS date FROM ( SELECT DISTINCT ip, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, style FROM clickCount WHERE ip IS NOT NULL AND ip <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a ) b GROUP BY date, style ORDER BY date, style";
		}else if(flag == 2){
			sql = "SELECT count(DISTINCT ip) amount, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, style FROM clickCount WHERE ip IS NOT NULL AND ip <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY date, style";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountIp ip = new CountIp();
				ip.setAmount(Integer.valueOf(map.get("amount").toString()));
				String date = map.get("date").toString();
				try {
					ip.setDate(DateUtil.parser(date,DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				ip.setStyle(map.get("style").toString());
				ip.setFlag(flag+"");
				save(ip);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getIp(Date beginDate, Date endDate,
			String flag, String style) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from countIp where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? and flag = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(style);
		paramList.add(flag);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}
}
