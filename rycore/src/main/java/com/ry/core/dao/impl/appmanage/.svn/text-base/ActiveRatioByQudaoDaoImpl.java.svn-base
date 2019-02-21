package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveRatioByQudaoDao;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.entity.da.ActiveRatioByQudao;
import com.ry.core.entity.da.IncreasedUserByQudao;

@Repository
public class ActiveRatioByQudaoDaoImpl extends BaseDao<ActiveRatioByQudao, Integer> implements ActiveRatioByQudaoDao {

	
	@Override
	public void countActiveRatioWeeks(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM activeRatioByQudao WHERE flag = 0 AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql1 = "";
		String sql2 = "";
		List<Object[]> list1s = new ArrayList<Object[]>();
		List<Object[]> list2s = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql1 = "SELECT amount, DATE_FORMAT(date, '%Y-%m-%d') AS days, qudao, flag FROM activeByQudao WHERE flag = 0  AND qudao is not null AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') ORDER BY days, qudao";
		sql2 = "SELECT sum(amount), DATE_FORMAT( DATE_SUB( date, INTERVAL WEEKDAY(date) DAY ), '%Y-%m-%d' ) AS days, qudao FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days, qudao ORDER BY days, qudao";
		list1s = getListBySQL(sql1, new Object[]{executeDate, endDate});
		list2s = getListBySQL(sql2, new Object[]{executeDate, endDate});
		countMethod(list1s,list2s,"0");
	}
	
	@Override
	public void countActiveRatioMonths(Date executeDate, Date endDate) {
		String initSql = "select * from activeRatioByQudao where flag = 1 and DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql1 = "";
		String sql2 = "";
		List<Object[]> list1s = new ArrayList<Object[]>();
		List<Object[]> list2s = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql1 = "SELECT sum(amount), DATE_FORMAT(date, '%Y-%m-%d') AS days, qudao, flag FROM activeByQudao WHERE flag = 1 AND qudao is not null AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days, qudao ORDER BY days, qudao";
		sql2 = "SELECT sum(amount), DATE_FORMAT(date, '%Y-%m') AS months, qudao FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY months, qudao ORDER BY months, qudao";
		list1s = getListBySQL(sql1, new Object[]{executeDate, endDate});
		list2s = getListBySQL(sql2, new Object[]{executeDate, endDate});
		countMethod(list1s,list2s,"1");
	}
	
	/**
	 * 周月活跃率数据入库公用方法
	 * @param sql1
	 * @param sql2
	 * @param flag
	 * @date 2016年1月5日
	 * @author lvyanqin
	 */
	public void countMethod(List<Object[]> list1s, List<Object[]> list2s, String flag){
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] obj1 : list1s) {
			ActiveRatioByQudao activeRatioByQudao = new ActiveRatioByQudao();
			String activeDayString = obj1[1].toString();
			try {
				Date activeDay = simpleDateFormate.parse(activeDayString);
				activeRatioByQudao.setDate(activeDay);
				if(obj1[2] != null){
					activeRatioByQudao.setQuDao(obj1[2].toString());
				}
				activeRatioByQudao.setFlag(obj1[3].toString());
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
			int activeSum = Integer.valueOf(obj1[0].toString());
			for (Object[] obj2 : list2s){
				String increaseDayString = obj2[1].toString();
				if("1".equals(flag)){
					increaseDayString += "-01";
				}
				int increaseSum = 1;
				if(activeDayString != null){
					//如果时间相等并且渠道相同
					if(activeDayString.equals(increaseDayString) && activeRatioByQudao.getQuDao().equals(obj2[2].toString())){
						increaseSum = Integer.valueOf(obj2[0].toString());
						if(increaseSum == 0){
							increaseSum = 1;
						}
						activeRatioByQudao.setValue(activeSum*1.0/increaseSum);
						save(activeRatioByQudao);
						break;
					}else if(activeDayString.compareTo(increaseDayString) < 0){
						activeRatioByQudao.setValue(activeSum*1.0/increaseSum);
						save(activeRatioByQudao);
						break;
					}else{
						continue;
					}
				}
			}
		}
	}

	
	@Override
	public List<ActiveRatioByQudao> activeRatioByQudao(Date beginDate, Date endDate,
			String flag, String qudao) {
		
		
		String sql = "select sum(value),date from activeRatioByQudao where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if(qudao != null && !"null".equals(qudao)){
			sql += " and qudao = ?";
			paramList.add(qudao);
		}
		sql += " GROUP BY flag,date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? order by date";
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag); 
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		List<ActiveRatioByQudao> actives = new ArrayList<ActiveRatioByQudao>();
		for (Object[] obj : lists) {
			Double amount = Double.valueOf(obj[0].toString());
			Date date ;
			String day = obj[1].toString();
			try {
				date = simpleDateFormate.parse(day);
				ActiveRatioByQudao active = new ActiveRatioByQudao();
				active.setValue(amount);
				active.setDate(date);
				actives.add(active);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return actives;
		
	}

}
