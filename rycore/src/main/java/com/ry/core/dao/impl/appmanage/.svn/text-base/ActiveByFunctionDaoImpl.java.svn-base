package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveByFunctionDao;
import com.ry.core.entity.da.ActiveByFunction;
import com.ry.util.DateUtil;

@Repository
public class ActiveByFunctionDaoImpl extends BaseDao<ActiveByFunction, Integer> implements ActiveByFunctionDao {

	@Override
	public void countActiveByFunction(String flag, Date executeDate, Date endDate) {
		String initSql = "select * from activeByFunction where flag = ? and DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = null;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if(initLists != null && initLists.size() > 0){//如果数据库中存在这个时间段的数据，则先删这段时间的数据，然后再插入
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		
		if("0".equals(flag)){//周
			sql = "SELECT count(*) AS amount, DATE_FORMAT( DATE_SUB( actiondate, INTERVAL WEEKDAY(actiondate) DAY ), '%Y-%m-%d' ) AS date, t. CODE code FROM ( SELECT DISTINCT DATE_FORMAT(actiontime, '%Y-%m-%d') actiondate, CODE, memberId FROM actionlog WHERE memberId IS NOT NULL AND CODE IS NOT NULL AND source = 'APP' AND DATE_FORMAT(actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY date, t. CODE ORDER BY date";
		}else if("1".equals(flag)){//月
			sql = "SELECT count(*) AS amount, t. CODE code, DATE_FORMAT(t.actionDate, '%Y-%m') AS date FROM ( SELECT DISTINCT DATE_FORMAT(actiontime, '%Y-%m-%d') actionDate, CODE, memberId FROM actionlog WHERE memberId IS NOT NULL AND CODE IS NOT NULL AND source = 'APP' AND DATE_FORMAT(actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY date, t. CODE ORDER BY date";
		}else{//默认为日
			sql = "SELECT count(*) AS amount, t.days date, t. CODE code FROM ( SELECT DISTINCT al.memberId, al. CODE, DATE_FORMAT(al.actiontime, '%Y-%m-%d') AS days FROM actionlog al, phonedetail pd WHERE al.source = 'APP' AND al.memberId IS NOT NULL AND pd.memberid = al.memberId AND DATE_FORMAT(actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY t. CODE, t.days ORDER BY t.days";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate});
			
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				ActiveByFunction active = new ActiveByFunction();
				active.setAmount(Integer.valueOf(map.get("amount").toString()));
				active.setCode(map.get("code").toString());
				try {
					String date = map.get("date").toString();
					if("1".equals(flag)){//月获取的时间格式是'年-月'
						date += "-01";
					}
					active.setDate(DateUtil.parser(date,DateUtil.FORMART3)); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				active.setFlag(flag);
				save(active);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findActiveByFunction(Date beginDate,
			Date endDate, String flag, String code) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from activeByFunction where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? and code = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag);
		paramList.add(code);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
