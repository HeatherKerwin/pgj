package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveRatioByScaleDao;
import com.ry.core.entity.da.ActiveRatioByScale;

@Repository
public class ActiveRatioByScaleDaoImpl extends BaseDao<ActiveRatioByScale, Integer> implements ActiveRatioByScaleDao {


	@Override
	public void countActiveRatioWeeks(Date executeDate, Date endDate) {
		String initSql = "select * from activeRatioByScale where flag = 0 and DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
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
		
		sql1 = "SELECT sum(amount), DATE_FORMAT(date, '%Y-%m-%d') AS days, flag FROM activeByQudao WHERE flag = 0 AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days ORDER BY days";
		sql2 = "SELECT sum(amount), DATE_FORMAT( DATE_SUB( date, INTERVAL WEEKDAY(date) DAY ), '%Y-%m-%d' ) AS days FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days ORDER BY days";
		list1s = getListBySQL(sql1, new Object[]{executeDate, endDate});
		list2s = getListBySQL(sql2, new Object[]{executeDate, endDate});
		countMethod(list1s,list2s,"0");
	}
	
	@Override
	public void countActiveRatioMonths(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM activeRatioByScale WHERE flag = 1 AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql1 = "";
		String sql2 = "";
		List<Object[]> list1s = new ArrayList<Object[]>();
		List<Object[]> list2s = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			
		}
		sql1 = "SELECT sum(amount), DATE_FORMAT(date, '%Y-%m-%d') AS days, flag FROM activeByQudao WHERE flag = 1 AND DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY days ORDER BY days";
		sql2 = "SELECT sum(amount), DATE_FORMAT(date, '%Y-%m') AS months FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY months ORDER BY months";
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
			ActiveRatioByScale activeRatioByScale = new ActiveRatioByScale();
			String activeDayString = obj1[1].toString();
			try {
				Date activeDay = simpleDateFormate.parse(activeDayString);
				activeRatioByScale.setDate(activeDay);
				activeRatioByScale.setFlag(obj1[2].toString());
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
					if(activeDayString.equals(increaseDayString)){
						increaseSum = Integer.valueOf(obj2[0].toString());
						if(increaseSum == 0){
							increaseSum = 1;
						}
						activeRatioByScale.setValue(activeSum*1.0/increaseSum);
						save(activeRatioByScale);
						break;
					}else if(activeDayString.compareTo(increaseDayString) < 0){
						activeRatioByScale.setValue(activeSum*1.0/increaseSum);
						save(activeRatioByScale);
						break;
					}else{
						continue;
					}
				}
			}
		}
	}

	
	@Override
	public List<ActiveRatioByScale> activeRatioByScale(Date beginDate, Date endDate,
			String flag) {
		String sql = "select sum(value),date from activeRatioByScale ";
		List<Object> paramList = new ArrayList<Object>();
		
		sql += " GROUP BY flag,date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') and flag = ? order by date";
		
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(flag); 
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		List<ActiveRatioByScale> actives = new ArrayList<ActiveRatioByScale>();
		for (Object[] obj : lists) {
			Double amount = Double.valueOf(obj[0].toString());
			Date date ;
			String day = obj[1].toString();
			try {
				date = simpleDateFormate.parse(day);
				ActiveRatioByScale active = new ActiveRatioByScale();
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
