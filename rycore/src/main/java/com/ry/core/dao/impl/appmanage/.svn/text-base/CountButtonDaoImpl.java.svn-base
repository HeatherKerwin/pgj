package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.CountButtonDao;
import com.ry.core.entity.da.CountButton;
import com.ry.core.entity.da.CountUv;
import com.ry.util.DateUtil;

@Repository
public class CountButtonDaoImpl extends BaseDao<CountButton, Integer> implements CountButtonDao {

	@Override
	public void countButton(int flag, Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM countButton WHERE flag = ? AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){//周
			sql = "SELECT count(*) amount, date, code, style FROM ( SELECT DISTINCT CODE, DATE_FORMAT( DATE_SUB( currentDate, INTERVAL WEEKDAY(currentDate) DAY ), '%Y-%m-%d' ) AS date, memberId, style FROM clickCount WHERE CODE IS NOT NULL AND memberId IS NOT NULL AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a GROUP BY date, CODE, style ORDER BY date, style, CODE";
		}else if(flag == 1){//月
			sql = "SELECT count(*) amount, date, code, style FROM ( SELECT DISTINCT CODE, DATE_FORMAT(currentDate, '%Y-%m') AS date, memberId, style FROM clickCount WHERE CODE IS NOT NULL AND memberId IS NOT NULL AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a GROUP BY date, CODE, style ORDER BY date, style, CODE";
		}else{//日
			sql = "SELECT count(*) amount, date, code, style FROM ( SELECT DISTINCT CODE, DATE_FORMAT(currentDate, '%Y-%m-%d') AS date, memberId, style FROM clickCount WHERE CODE IS NOT NULL AND memberId IS NOT NULL AND DATE_FORMAT(currentDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a GROUP BY date, CODE, style ORDER BY date, style, CODE";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				CountButton button = new CountButton();
				button.setAmount(Integer.valueOf(map.get("amount").toString()));
				try {
					String date = map.get("date").toString();
					if(flag == 1){//月获取的时间格式是'年-月'
						date += "-01";
					}
					button.setDate(DateUtil.parser(date,DateUtil.FORMART3)); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				button.setStyle(map.get("style").toString());
				button.setCode(map.get("code").toString());
				button.setFlag(flag);
				save(button);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getButton(Date beginDate, Date endDate,
			int flag, String style, String code) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from countButton where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? and style = ? and code = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag);
		paramList.add(style);
		paramList.add(code);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
