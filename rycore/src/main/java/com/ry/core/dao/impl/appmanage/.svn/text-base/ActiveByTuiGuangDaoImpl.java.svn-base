package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveByTuiGuangDao;
import com.ry.core.entity.da.ActiveByTuiGuang;
import com.ry.util.DateUtil;

@Repository
public class ActiveByTuiGuangDaoImpl extends BaseDao<ActiveByTuiGuang, Integer> implements ActiveByTuiGuangDao {

	@Override
	public List<Map<String, Object>> getActive(Date beginDate, Date endDate,
			String flag, String style) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from activeByTuiGuang where DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? and style = ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag);
		paramList.add(style);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

	@Override
	public void countActive(int flag, Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM activeByTuiGuang WHERE flag = ? AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{flag, executeDate, endDate});
		String sql = "";
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		if(flag == 0){//周
			sql = "SELECT count(*) amount, a.weekDate date, a.qudao FROM (( SELECT DISTINCT DATE_FORMAT(actionDate, '%Y-%m-%d') AS date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDate, memberId, init.qudao FROM appVisitLog avl, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE avl.memberId = init.id AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT(al.actiontime, '%Y-%m-%d') AS date, DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) AS weekDate, memberId, init.qudao FROM actionlog al, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE al.memberId = init.id AND al.source = 'PC' AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) a GROUP BY a.weekDate, a.qudao";
		}else if(flag == 1){//月
			sql = "SELECT count(*) amount, a. MONTH date, a.qudao FROM (( SELECT DISTINCT DATE_FORMAT(actionDate, '%Y-%m-%d') AS date, DATE_FORMAT(actionDate, '%Y-%m') AS MONTH, memberId, init.qudao FROM appVisitLog avl, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE avl.memberId = init.id AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT(al.actiontime, '%Y-%m-%d') AS date, DATE_FORMAT(al.actiontime, '%Y-%m') AS MONTH, memberId, init.qudao FROM actionlog al, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE al.memberId = init.id AND al.source = 'PC' AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) a GROUP BY a. MONTH, a.qudao";
		}else{//默认为日
			sql = "SELECT count(*) amount, a.date, a.qudao FROM (( SELECT DISTINCT DATE_FORMAT(actionDate, '%Y-%m-%d') AS date, memberId, init.qudao FROM appVisitLog avl, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE avl.memberId = init.id AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT(al.actiontime, '%Y-%m-%d') AS date, memberId, init.qudao FROM actionlog al, ( SELECT id, qudao FROM member WHERE hezuo = 'baidu' ) init WHERE al.memberId = init.id AND al.source = 'PC' AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) a GROUP BY a.date, a.qudao";
		}
		lists = getListMapBySQL(sql, new Object[]{executeDate, endDate, executeDate, endDate});
		if(lists != null && lists.size() > 0){
			for (Map<String, Object> map : lists) {
				ActiveByTuiGuang active = new ActiveByTuiGuang();
				active.setAmount(Integer.valueOf(map.get("amount").toString()));
				active.setStyle(map.get("qudao").toString());
				try {
					String date = map.get("date").toString();
					if(flag == 1){//月获取的时间格式是'年-月'
						date += "-01";
					}
					active.setDate(DateUtil.parser(date,DateUtil.FORMART3)); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				active.setFlag(flag+"");
				save(active);
			}
		}
	}

}
