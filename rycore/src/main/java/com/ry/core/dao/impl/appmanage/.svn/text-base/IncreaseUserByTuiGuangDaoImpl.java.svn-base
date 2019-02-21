package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.IncreaseUserByTuiGuangDao;
import com.ry.core.entity.da.IncreasedUserByFunction;
import com.ry.core.entity.da.IncreasedUserByTuiGuang;
import com.ry.util.DateUtil;

@Repository
public class IncreaseUserByTuiGuangDaoImpl extends BaseDao<IncreasedUserByTuiGuang, Integer> implements IncreaseUserByTuiGuangDao {

	@Override
	public void countIncreaseUser(int flag, Date executeDate,Date endDate) {
		String initSql = "select * from increasedUserByTuiGuang where flag = ? and DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){//先删，再分析数据
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){//周
			sql = "SELECT count(*) amount, date, qudao AS style FROM ( SELECT id, qudao, DATE_FORMAT( DATE_SUB( a.regist, INTERVAL WEEKDAY(a.regist) DAY ), '%Y-%m-%d' ) AS date FROM ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') regist, qudao FROM member WHERE qudao IN ('PC', 'WAP') AND hezuo = 'baidu' AND DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m-%d' ) BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a ) b GROUP BY date, style";
		}else if(flag == 1){//月
			sql = "SELECT count(*) amount, regist AS date, qudao AS style FROM ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m') regist, qudao FROM member WHERE qudao IN ('PC', 'WAP') AND hezuo = 'baidu' AND DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m-%d' ) BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) a GROUP BY date, qudao";
		}else{//日
			sql = "SELECT count(*) amount, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') AS date, qudao AS style FROM member m WHERE m.qudao IN ('PC', 'WAP') AND hezuo = 'baidu' AND DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m-%d' ) BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY date, qudao";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				IncreasedUserByTuiGuang increase = new IncreasedUserByTuiGuang();
				increase.setAmount(Integer.valueOf(map.get("amount").toString()));
				try {
					String date = map.get("date").toString();
					if(flag == 1){//月获取的时间格式是'年-月'
						date += "-01";
					}
					increase.setDate(DateUtil.parser(date,DateUtil.FORMART3)); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				increase.setStyle(map.get("style").toString());
				increase.setFlag(flag);
				save(increase);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findIncreaseUser(Date beginDate,
			Date endDate, Integer flag, String style) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "SELECT * FROM increasedUserByTuiGuang WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') AND flag = ? AND style = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag);
		paramList.add(style);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
