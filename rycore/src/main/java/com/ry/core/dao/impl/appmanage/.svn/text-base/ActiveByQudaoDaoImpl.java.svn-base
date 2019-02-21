package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveByQudaoDao;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.entity.da.ActiveDayByQudao;

@Repository
public class ActiveByQudaoDaoImpl extends BaseDao<ActiveByQudao, Integer> implements ActiveByQudaoDao {

	@Override
	public List<ActiveByQudao> activeByQudao(Date beginDate, Date endDate,String flag, String qudao) {
		String sql = "select sum(amount),date from activeByQudao where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if(qudao != null && !"null".equals(qudao)){
			sql += " and qudao = ? ";
			paramList.add(qudao);
		}
		sql += " GROUP BY flag,date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? order by date";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag); 
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		List<ActiveByQudao> actives = new ArrayList<ActiveByQudao>();
		for (Object[] obj : lists) {
			Integer amount = Integer.valueOf(obj[0].toString());
			Date date ;
			String day = obj[1].toString();
			try {
				date = simpleDateFormate.parse(day);
				ActiveByQudao active = new ActiveByQudao();
				active.setAmount(amount);
				active.setDate(date);
				actives.add(active);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return actives;
	}

	@Override
	public void countActive(Date executeDate, Date endDate) {
		String initSql = "select * from activeByQudao where flag = 0 and DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*), t.days, t.qudao FROM ( SELECT DISTINCT pd.qudao, a.memberId, DATE_FORMAT(actionDate, '%Y%m%d') AS cur, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y%m%d' ) AS days, DATE_FORMAT(actionDate, '%Y%u') AS weeks FROM appVisitLog a, phonedetail pd WHERE a.type = '0' AND a.memberId IS NOT NULL AND a.memberId = pd.memberid AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY cur, qudao, a.memberId ) t GROUP BY t.days, t.qudao";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		countActiveMethod(lists,"0");
	}

	@Override
	public void countActiveMonths(Date executeDate, Date endDate) {
		String initSql = "select * from activeByQudao where flag = 1 and DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		
		sql = "SELECT count(*), t.months, t.qudao FROM ( SELECT DISTINCT a.memberId, DATE_FORMAT(actionDate, '%Y%m') AS months, DATE_FORMAT(actionDate, '%Y%m%d') AS days, pd.qudao FROM appVisitLog a, phonedetail pd WHERE a.type = '0' AND a.memberId IS NOT NULL AND a.memberId = pd.memberid AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days, qudao, memberId ) t GROUP BY t.months, t.qudao";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		
		countActiveMethod(lists,"1");
	}
	
	/**
	 * 统计公用方法
	 * @param sql
	 * @param flag,0代表周，1代表月
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	public void countActiveMethod(List<Object[]> lists, String flag){
		
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyyMMdd");
		for (Object[] obj : lists) {
			ActiveByQudao active = new ActiveByQudao();
			active.setAmount(Integer.valueOf(obj[0].toString()));
			try {
				if(obj[2] != null){
					active.setQuDao(obj[2].toString());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String days;
			if("1".equals(flag)){
				days = obj[1].toString()+"1";
			}else{
				days = obj[1].toString();
			}
			try {
				Date date = simpleDateFormate.parse(days);
				active.setDate(date);
				active.setFlag(flag);
				save(active);
			} catch (ParseException e) {
				//logger.info(e);
				e.printStackTrace();
			}
		}
	}

	
	
	
	
}
