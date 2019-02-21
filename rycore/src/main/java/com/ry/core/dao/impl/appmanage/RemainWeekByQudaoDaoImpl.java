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
import com.ry.core.dao.appmanage.RemainWeekByQudaoDao;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.entity.da.RemainMonthByQudao;
import com.ry.core.entity.da.RemainWeekByFunction;
import com.ry.core.entity.da.RemainWeekByQudao;
import com.ry.util.DateUtil;

import freemarker.template.utility.StringUtil;

@Repository
public class RemainWeekByQudaoDaoImpl extends BaseDao<RemainWeekByQudao, Integer> implements RemainWeekByQudaoDao {

	private static final Logger logger = Logger.getLogger(RemainWeekByQudaoDaoImpl.class);
	
	List<RemainWeekByQudao> remains = null;
	RemainWeekByQudao remain = null;
	 SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Override
	public void countReaminWeekByQudao(Date executeDate, Date endDate) {
		/*String initSql = "select * from remainWeekByQudao";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, null);
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, phonedetail pd, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE m.id = pd.memberid AND pd.qudao IS NOT NULL AND init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' )) c GROUP BY c.weekDay, c.date, c.qudao ORDER BY c.date, qudao";
			lists = getListBySQL(sql, new Object[]{executeDate, executeDate});
		}else{
			sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, phonedetail pd, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE m.id = pd.memberid AND pd.qudao is not null AND init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' and memberId = b.id ) c GROUP BY c.weekDay, c.date,c.qudao ORDER BY c.date,qudao";
			lists = getListBySQL(sql, null);
		}*/
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c.qudao FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks, b.qudao FROM appVisitLog avl, ( SELECT a.id, a.date, a.qudao FROM ( SELECT m.id, pd.qudao, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, phonedetail pd, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE m.id = pd.memberid AND pd.qudao IS NOT NULL AND init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) c GROUP BY c.weekDay, c.date, c.qudao ORDER BY c.date, qudao";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		countRemainWeekMethod(lists);
	}
	
	@Override
	public List<Map<String, Object>> showWeekRemain(String qudao, Date beginDate,
			Date endDate) {
		/*List<RemainWeekByQudao> remains = null;
		if(!StringUtils.isEmpty(qudao)){//如果渠道不为空，直接将记录存在对象中
			String hql = "FROM remainWeekByQudao where 1=1 ";
			List<Object> paramList = new ArrayList<Object>();
			if(qudao != null && !"null".equals(qudao)){
				hql += " and qudao = ?";
				paramList.add(qudao);
			}
			hql += " GROUP BY date having date between ? and ? order by date";
			paramList.add(beginDate);
			paramList.add(endDate);
			remains = getListByHQL(hql, paramList.toArray());
		}else{//如果为空，以时间来进行分组，然后对所有数据进行求和
*/			
		String sql = "select date,sum(yi) yi,sum(er) er,sum(san) san ,sum(si) si,sum(wu) wu,sum(liu) liu,sum(qi) qi,sum(ba) ba,sum(jiu) jiu,sum(shi) shi,"
				+ "sum(shiEr) shiEr,sum(shiSan) shiSan,sum(shiWu) shiWu,sum(shiBa) shiBa,sum(erShiEr) erShiEr,sum(erShiLiu) erShiLiu FROM remainWeekByQudao where 1=1 ";
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
	
	
	/**
	 * 周留存信息入库的公用方法
	 * @param sql
	 * @param flag,标记是否第一次入库统计,如果不是，则是修改以前信息，并插入一条数据,0代表第一次
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public void countRemainWeekMethod(List<Object[]> lists){
		if(lists == null || lists.size() == 0){
			
		}else{
			
			int size = lists.size();
			remain = new RemainWeekByQudao();
			//获取第一条信息
			int amount1 = Integer.valueOf(lists.get(0)[0].toString());//获取这个时间段的总数
			Map<String, Object> map = getBetweenDays(lists, 0);
			
			int days = (Integer) map.get("days");
			Date date = (Date) map.get("date");
			String qudao = lists.get(0)[4].toString();
			
			String tempSql = "from remainWeekByQudao where qudao = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
			List<RemainWeekByQudao> tempRemains = getListByHQL(tempSql, new Object[]{qudao,date});
			if(tempRemains == null || tempRemains.size() == 0){//如果数据库中没有这条记录
				
			}else{
				remain = tempRemains.get(0);
			}
			
			remain.setQuDao(qudao);
			remain.setDate(date);
			if(qudao == null){
				qudao = "";
			}
			//根据统计日期获取新增总数
			List<IncreasedUserByQudao> increase = increaseUserByQudaoDao.increaseWeeks(date, date, qudao);
			int amount2 = 0;
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
				String strDate1 = lists.get(i)[3].toString();
				Date date1 = dateFormat(strDate1);
				int days1 = getBetweenDays(date, date1);
				String qudao1 = lists.get(i)[4].toString();
				if(qudao1 == null){
					qudao1 = "";
				}
				amount1 = Integer.valueOf(lists.get(i)[0].toString());//获取这个时间段的总数
				if(days1 == 0 && qudao.equalsIgnoreCase(qudao1)){//如果后面一条记录的时间和前面一条相同，则修改这个时间的留存信
					int day =  (Integer) getBetweenDays(lists, i).get("days");
					if(day > 0){//如果这条记录的访问时间大于注册时间,则设置数据入库
						setRemainData(remain, amount1, amount2, day);
					}
				}else{//保存留存信息,新建一个留存对象进行保存
					saveOrUpdate(remain);
					date = date1;//将date设置成当前需要保存的日期；
					qudao = qudao1;
					remain = new RemainWeekByQudao();
					Map<String, Object> map2 = getBetweenDays(lists, i);
					int days2 = (Integer) map2.get("days");
					Date date2 = (Date) map2.get("date");
					if(lists.get(i)[4] != null){
						remain.setQuDao(lists.get(i)[4].toString());
					}
					String tempSql1 = "from remainWeekByQudao where qudao = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
					List<RemainWeekByQudao> tempRemains1 = getListByHQL(tempSql1, new Object[]{remain.getQuDao(),date2});
					if(tempRemains1 == null || tempRemains1.size() == 0){//如果数据库中没有这条记录
						
					}else{
						remain = tempRemains1.get(0);
					}
					remain.setDate(date2);
					//根据统计日期获取新增总数
					List<IncreasedUserByQudao> increase2 = increaseUserByQudaoDao.increaseWeeks(date2, date2, remain.getQuDao());
					int amount3 = 0;
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
	 * 得到的remain为全局变量
	 */
	public void getInstance(List<RemainWeekByQudao> remains,Date date){
		for (RemainWeekByQudao r : remains) {
			try {
				if(DateUtil.daysBetween(r.getDate(), date) == 0){
					remain = r;
					return;
				}
			} catch (ParseException e) {
				logger.info("RemainWeekByQudaoDaoImpl-daysBetween日期转换>>>>>>",e);
				e.printStackTrace();
			}
		}
		remain = new RemainWeekByQudao();
	}
	
	
	/**
	 * 格式化日期
	 * @param strDate
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public  Date dateFormat(String strDate){
		Date date1 = null;
		try {
			date1 = simpleDateFormate.parse(strDate);
		} catch (ParseException e) {
			logger.info("RemainWeekByQudaoDaoImpl日期转换>>>>>>",e);
			e.printStackTrace();
		}
		return date1;
	}

	/**
	 * 求两个时间差
	 * @param date
	 * @param date1
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public  Integer getBetweenDays(Date date, Date date1){
		int days1 = 0;
		try {
			days1 = DateUtil.daysBetween(date, date1); 
		} catch (ParseException e) {
			logger.info("RemainWeekByQudaoDaoImpl-daysBetween日期转换>>>>>>",e);
			e.printStackTrace();
		}
		return days1;
	}
	
	/**
	 * 保存留存周的数据
	 * @param remain
	 * @param amount1
	 * @param amount2
	 * @param days
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public void setRemainData(RemainWeekByQudao remain,int amount1, int amount2, int days){
		//获取weekDate和date间隔的周数
		int weekNum = (int) Math.ceil(days/7.0);
		if(amount2 == 0){
			return;
		}
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
		}else if(weekNum == 10){
			remain.setShi(value);
		}else if(weekNum == 12){
			remain.setShiEr(value);
		}else if(weekNum == 13){
			remain.setShiSan(value);
		}else if(weekNum == 15){
			remain.setShiWu(value);
		}else if(weekNum == 18){
			remain.setShiBa(value);
		}else if(weekNum == 22){
			remain.setErShiEr(value);
		}else if(weekNum == 26){
			remain.setErShiLiu(value);
		}else{
			
		}
		
	}
	
	/**
	 * 求记录中的用户注册时间和行为访问记录的时间差
	 * @param lists
	 * @param index,列表下标
	 * @param simpleDateFormate，日期格式化
	 * @param remain，留存对象
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	@Override
	public  Map<String, Object> getBetweenDays(List<Object[]> lists, int index){
		String strDate = lists.get(index)[3].toString();//获取统计日期
		String strWeek = lists.get(index)[2].toString();//获取记录那一周的周一时间
		return getBetweenDaysMethod(strDate, strWeek);
	}
	
	/**
	 * 得到方法的中间方法
	 * @param simpleDateFormate
	 * @param strDate
	 * @param strWeek
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public  Map<String, Object> getBetweenDaysMethod(String strDate, String strWeek){
		Date date = dateFormat(strDate);
		Date weekDate = dateFormat(strWeek);
		
		//统计日期应该在周日期前，如果小，就不统计，因为计算的都是自然周
		int days = getBetweenDays(date, weekDate);//days的值小于0就不会入库
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("days", days);
		map.put("date", date);
		map.put("strDate", weekDate);
		return map;
	}



	

	
	
}
