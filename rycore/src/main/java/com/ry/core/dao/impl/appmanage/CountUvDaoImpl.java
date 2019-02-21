package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountUvDao;
import com.ry.core.entity.da.CountIp;
import com.ry.core.entity.da.CountUv;
import com.ry.util.DateUtil;

@Repository
public class CountUvDaoImpl extends BaseDao<CountUv, Integer> implements CountUvDao {

	@Override
	public void countUv(int flag, Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM countUv WHERE flag = ? AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){//周
			sql  = "SELECT count(*) amount, date, style FROM ( SELECT uuid, style, DATE_FORMAT( DATE_SUB( date, INTERVAL WEEKDAY(date) DAY ), '%Y-%m-%d' ) AS date FROM ( SELECT DISTINCT uuid, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, style FROM clickCount WHERE uuid IS NOT NULL AND uuid <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a ) b GROUP BY date, style ORDER BY date, style";
		}else if(flag == 2){
			sql = "SELECT count(DISTINCT uuid) amount, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, style FROM clickCount WHERE uuid IS NOT NULL AND uuid <> '' AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY date, style";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountUv uv = new CountUv();
				uv.setAmount(Integer.valueOf(map.get("amount").toString()));
				String date = map.get("date").toString();
				try {
					uv.setDate(DateUtil.parser(date,DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				uv.setStyle(map.get("style").toString());
				uv.setFlag(flag+"");
				save(uv);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> getUv(Date beginDate, Date endDate,
			String flag, String style) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from countUv where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and style = ? and flag = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(style);
		paramList.add(flag);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
