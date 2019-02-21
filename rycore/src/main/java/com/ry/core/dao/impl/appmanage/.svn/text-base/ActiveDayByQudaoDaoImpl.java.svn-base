package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.ActiveDayByQudaoDao;
import com.ry.core.entity.Admin;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.entity.da.ActiveDayByQudao;
import com.ry.util.DateUtil;

@Repository
public class ActiveDayByQudaoDaoImpl extends BaseDao<ActiveDayByQudao, Integer> implements ActiveDayByQudaoDao{

	private static final Logger logger = Logger.getLogger(ActiveDayByQudaoDaoImpl.class);
	
	
	@Override
	public void countDay(Date executeDate, Date endDate){
		String initSql = "select * from activeDayByQudao where DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*) AS amount, t.days, t.qudao FROM ( SELECT DISTINCT a.memberId, qudao, DATE_FORMAT(actionDate, '%Y%m%d') AS days FROM appVisitLog a, phonedetail pd WHERE a.type = '0' AND a.memberId IS NOT NULL AND pd.memberid = a.memberId AND DATE_FORMAT(actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) t GROUP BY t.qudao, t.days";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyyMMdd");
		for (Object[] obj : lists) {
			ActiveDayByQudao activeDay = new ActiveDayByQudao();
			activeDay.setAmount(Integer.valueOf(obj[0].toString()));
			if(obj[2] != null){
				activeDay.setQuDao(obj[2].toString());
			}
			
			String days = obj[1].toString();
			
			try {
				Date date = simpleDateFormate.parse(days);
				activeDay.setDate(date);
				save(activeDay);
			} catch (ParseException e) {
//		//		logger.info(e);
				e.printStackTrace();
			}
		}
		//如果当天没有统计数据，则插0
		/*String sql2 = "from activeDayByQudao order by date desc";
		List<ActiveDayByQudao> actives = getListByHQL(sql2, null);
		if(actives != null){
			Date smallDate = actives.get(0).getDate();
			Date bigDate = actives.get(actives.size()-1).getDate();
			try {
				int days = DateUtil.daysBetween(smallDate,bigDate);
				if(days == actives.size()){//如果相差天数与数据记录数相等，则每天都有数据
					return;
				}else{
					Calendar cal = Calendar.getInstance();
					cal.setTime(smallDate);
					Date tempDate = null;
					for(int i = 1; i < actives.size() - 1; i ++){
						cal.add(Calendar.DATE, 1);
						tempDate = cal.getTime();
						if(actives.get(i).getDate() == tempDate){//时分秒都为0
							continue;
						}else{
							ActiveDayByQudao active = new ActiveDayByQudao();
							active.setAmount(0);
							active.setDate(tempDate);
							save(active);
						}
					}
					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}*/
		System.out.println(lists);
	}

	
	@Override
	public List<ActiveDayByQudao> activeDays(Date beginDate, Date endDate, String qudao) {
		
		String hql = "from activeDayByQudao where 1=1 ";
		List paramList = new ArrayList();
		if(qudao != null && !"null".equals(qudao)){
			hql += " and qudao = ?";
			paramList.add(qudao);
		}
		hql += " GROUP BY date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') order by date";
		paramList.add(beginDate);
		paramList.add(endDate);
		//List<Object[]> lists = getListBySQL(hql, paramList.toArray());
		
		/*String hql = "from activeDayByQudao where date between ? and ?";
		List paramList = new ArrayList();
		paramList.add(beginDate);
		paramList.add(endDate);
		if(qudao != null && !"null".equals(qudao)){
			hql += " and qudao = ?";
			paramList.add(qudao);
		}*/
		List<ActiveDayByQudao> lists = getListByHQL(hql, paramList.toArray());
		return lists;
	}

	

}
