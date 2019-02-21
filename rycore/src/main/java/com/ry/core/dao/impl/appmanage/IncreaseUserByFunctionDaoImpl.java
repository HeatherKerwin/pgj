package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.IncreaseUserByFunctionDao;
import com.ry.core.entity.da.IncreasedUserByFunction;
import com.ry.util.DateUtil;

@Repository
public class IncreaseUserByFunctionDaoImpl extends BaseDao<IncreasedUserByFunction, Integer> implements IncreaseUserByFunctionDao{

	@Override
	public void countIncreaseUserByFlag(int flag, Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM increasedUserByFunction WHERE flag = ? AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){//周
			sql = "SELECT count(*) amount, t. CODE code, t.weekDay date FROM ( SELECT DISTINCT al.memberId, al. CODE, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS weekDay FROM actionlog al, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member WHERE qudao = 'APP' ) init WHERE al.memberId = init.id AND al. CODE IS NOT NULL AND al.source = 'APP' AND DATE_FORMAT(al.actiontime, '%Y-%u') = DATE_FORMAT(init.date, '%Y-%u') AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY t. CODE, t.weekDay ORDER BY t.weekDay";
		}else if(flag == 1){//月
			sql = "SELECT count(*) amount, t. CODE code, t.months date FROM ( SELECT DISTINCT al.memberId, al. CODE, DATE_FORMAT(al.actiontime, '%Y-%m') months FROM actionlog al, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member WHERE qudao = 'APP' ) init WHERE al.memberId = init.id AND al. CODE IS NOT NULL AND al.source = 'APP' AND DATE_FORMAT(al.actiontime, '%Y-%m') = DATE_FORMAT(init.date, '%Y-%m') AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY t. CODE, t.months ORDER BY t.months";
		}else{//默认为天
			sql = "SELECT count(*) amount, t. CODE code, t.date date FROM ( SELECT DISTINCT al.memberId, al. CODE, init.date FROM actionlog al, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member WHERE qudao = 'APP' ) init WHERE al.memberId = init.id AND al. CODE IS NOT NULL AND al.source = 'APP' AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') = init.date AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY t. CODE, t.date ORDER BY t.date";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				IncreasedUserByFunction increase = new IncreasedUserByFunction();
				increase.setAmount(Integer.valueOf(map.get("amount").toString()));
				increase.setCode(map.get("code").toString());
				try {
					String date = map.get("date").toString();
					if(flag == 1){//月获取的时间格式是'年-月'
						date += "-01";
					}
					increase.setDate(DateUtil.parser(date,DateUtil.FORMART3)); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				increase.setFlag(flag);
				save(increase);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findIncreaseUserByFunction(
			Date beginDate, Date endDate, Integer flag, String code) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from increasedUserByFunction where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? and code = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag);
		paramList.add(code);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
