package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.IncreaseUserByQudaoDao;
import com.ry.core.dao.appmanage.RemainMonthByQudaoDao;
import com.ry.core.dao.appmanage.RemainWeekByQudaoDao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.entity.da.RemainMonthByQudao;
import com.ry.util.DateUtil;

@Repository
public class RemainMonthByQudaoDaoImpl extends BaseDao<RemainMonthByQudao, Integer> implements RemainMonthByQudaoDao {

	private static final Logger logger = Logger.getLogger(RemainMonthByQudaoDaoImpl.class);
	
	List<RemainMonthByQudao> remains = null;
	RemainMonthByQudao remain = null;
	
	@Resource
	IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Resource
	RemainWeekByQudaoDao remainWeekDao;
	
	@Override
	public void countReaminMonthByQudao(Date executeDate, Date endDate) {
		/*String initSql = "select * from remainMonthByQudao";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, null);
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			sql = "SELECT count(*), c.months, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT(actionDate, '%Y-%m') AS months, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, FROM_UNIXTIME(regtime / 1000, '%Y-%m') date FROM member m, phonedetail pd WHERE m.id = pd.memberid AND pd.qudao IS NOT NULL ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT(avl.actionDate, '%Y-%m') = DATE_FORMAT(?, '%Y-%m')) c GROUP BY c.months, c.date, c.qudao ORDER BY c.date";
			lists = getListBySQL(sql, new Object[]{executeDate});
		}else{
			//date是注册用户的"年份-月份"
			sql = "SELECT count(*), c.months, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT(actionDate, '%Y-%m') AS months, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, FROM_UNIXTIME(regtime / 1000, '%Y-%m') date FROM member m, phonedetail pd WHERE m.id = pd.memberid AND pd.qudao IS NOT NULL ORDER BY date) a ) b WHERE avl.type ='0' and memberId = b.id ) c GROUP BY c.months, c.date,c.qudao ORDER BY c.date";
			lists = getListBySQL(sql, null);
		}*/
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		sql = "SELECT count(*), c.months, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT(actionDate, '%Y-%m') AS months, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, FROM_UNIXTIME(regtime / 1000, '%Y-%m') date FROM member m, phonedetail pd WHERE m.id = pd.memberid AND pd.qudao IS NOT NULL ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) c GROUP BY c.months, c.date, c.qudao ORDER BY c.date";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		countRemainMonthMethod(lists);
	}
	
	
	/**
	 * 月留存信息入库的公用方法
	 * @param sql
	 * @param flag,标记是否第一次入库统计,如果不是，则是修改以前信息，并插入一条数据,0代表第一次
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public void countRemainMonthMethod(List<Object[]> lists){
		
		if(lists == null || lists.size() == 0){
			
		}else{
			int size = lists.size();
				remain = new RemainMonthByQudao();
			
			//获取第一条信息
			int amount1 = Integer.valueOf(lists.get(0)[0].toString());//获取这个时间段的总数
			Map<String, Object> map = getBetweenMonths(lists, 0);
			int days = (Integer) map.get("days");
			Date date = (Date) map.get("date");
			
			
			remain.setQuDao(lists.get(0)[3].toString());
			
			String tempSql = "from remainMonthByQudao where qudao = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
			List<RemainMonthByQudao> tempRemains = getListByHQL(tempSql, new Object[]{remain.getQuDao(),date});
			if(tempRemains == null || tempRemains.size() == 0){//如果数据库中没有这条记录
				
			}else{
				remain = tempRemains.get(0);
			}
			
			remain.setDate(date);
			//根据统计日期获取新增总数
			List<IncreasedUserByQudao> increase = increaseUserByQudaoDao.increaseMonths(date, date, remain.getQuDao());
			int amount2 = 1;
			if(increase != null && increase.size() > 0){
				amount2 = increase.get(0).getAmount();
			}
			if(days > 0){//证明周一日期是在统计日期的下周以后
				setRemainData(remain, amount1, amount2, days);
				
			}else{//不入库
				
			}
			//后面入库信息依赖于前面信息，因为得到的数据是按日期排序
			int i = 1;
			for(; i < size; i++){
				String strDate1 = lists.get(i)[2].toString();
				strDate1 += "-01";
				Date date1 = remainWeekDao.dateFormat(strDate1);
				int days1 = remainWeekDao.getBetweenDays(date, date1);
				String qudao1 = lists.get(i)[3].toString();
				if(qudao1 == null){
					qudao1 = "";
				}
				amount1 = Integer.valueOf(lists.get(i)[0].toString());//获取这个时间段的总数
				if(days1 == 0  && remain.getQuDao().equalsIgnoreCase(qudao1)){//如果后面一条记录的时间和前面一条相同，则修改这个时间的留存信息
					int day =  (Integer) getBetweenMonths(lists, i).get("days");
					if(day > 0){
						setRemainData(remain, amount1, amount2, day);
					}
				}else{//保存留存信息,新建一个留存对象进行保存
					saveOrUpdate(remain);
					date = date1;//将date设置成当前需要保存的日期；
					
						remain = new RemainMonthByQudao();
					
					Map<String, Object> map2 = getBetweenMonths(lists, i);
					int days2 = (Integer) map2.get("days");
					Date date2 = (Date) map2.get("date");
					remain.setQuDao(lists.get(i)[3].toString());
					
					String tempSql1 = "from remainMonthByQudao where qudao = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
					List<RemainMonthByQudao> tempRemains1 = getListByHQL(tempSql1, new Object[]{remain.getQuDao(),date2});
					if(tempRemains1 == null || tempRemains1.size() == 0){//如果数据库中没有这条记录
						
					}else{
						remain = tempRemains1.get(0);
					}
					
					remain.setDate(date2);
					//根据统计日期获取新增总数
					List<IncreasedUserByQudao> increase2 = increaseUserByQudaoDao.increaseMonths(date2, date2, remain.getQuDao());
					int amount3 = 1;
					if(increase2 != null && increase2.size() > 0){
						amount3 = increase2.get(0).getAmount();
					}
					if(days2 > 0){//证明周一日期是在统计日期的下周以后
						setRemainData(remain, amount1, amount3, days2);
					}else{//不入库
					}
					amount2 = amount3;
				}
			}
			if(i == size){//如果保存到最后一条记录，则直接保存
				saveOrUpdate(remain);
			}
		}
			
		
	}
	
	/**
	 * 如果该表中已经存在这天的记录，这从表中获取这个对象，通过匹配时间
	 * @param remains
	 * @param date
	 * @date 2016年1月12日
	 * @author lvyanqin
	 * 如果表中没有这个时间的记录，则新建一个对象
	 */
	public void getInstance(List<RemainMonthByQudao> remains, Date date){
		for (RemainMonthByQudao r : remains) {
			try {
				if(DateUtil.daysBetween(r.getDate(), date) == 0){
					remain = r;
					return;
				}
			} catch (ParseException e) {
				logger.info("RemainMonthByQudaoDaoImpl-daysBetween日期转换>>>>>>",e);
				e.printStackTrace();
			}
		}
		remain = new RemainMonthByQudao();
	}

	/**
	 * 保存留存月的数据
	 * @param remain
	 * @param amount1
	 * @param amount2
	 * @param days
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public void setRemainData(RemainMonthByQudao remain,int amount1, int amount2, int days){
		int weekNum = days;
		float value = (float) (amount1*100.0/amount2);
		if(weekNum == 1){
			remain.setYi(value);
		}else if(weekNum == 2){
			remain.setEr(value);
		}else if(weekNum == 3){
			remain.setSan(value);
		}else if(weekNum == 4){
			remain.setSi(value);
		}else if(weekNum == 5){
			remain.setWu(value);
		}else if(weekNum == 6){
			remain.setLiu(value);
		}else if(weekNum == 7){
			remain.setQi(value);
		}else if(weekNum == 8){
			remain.setBa(value);
		}else if(weekNum == 9){
			remain.setJiu(value);
		}else{
			
		}
		
	}
	
	/**
	 * 求记录中的用户注册时间和行为访问记录的时间差
	 * @param lists
	 * @param index,列表下标
	 * @param simpleDateFormate，日期格式化
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public Map<String, Object> getBetweenMonths(List<Object[]> lists, int index){
		String strDate = lists.get(index)[2].toString();//获取统计日期
		strDate += "-01";
		String strMonth = lists.get(index)[1].toString();//获取记录那月
		strMonth += "-01";
		Date date = remainWeekDao.dateFormat(strDate);
		Date MonthDate = remainWeekDao.dateFormat(strMonth);
		//求相差的月数
		int months = 0;
		try {
			months = DateUtil.monthsBetween(date, MonthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("days", months);
		map.put("date", date);
		map.put("strDate", MonthDate);
		return map;
	}


	@Override
	public List<Map<String, Object>> showMonthRemain(String qudao,
			Date beginDate, Date endDate) {
		String sql = "select date,sum(yi) yi,sum(er) er,sum(san) san ,sum(si) si,sum(wu) wu,sum(liu) liu,sum(qi) qi,sum(ba) ba,sum(jiu) jiu FROM remainMonthByQudao where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if(!StringUtils.isEmpty(qudao)){
			sql += " and qudao = ?";
			paramList.add(qudao);
		}
		sql += " GROUP BY date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') order by date";
		paramList.add(beginDate);
		paramList.add(endDate);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}
}
