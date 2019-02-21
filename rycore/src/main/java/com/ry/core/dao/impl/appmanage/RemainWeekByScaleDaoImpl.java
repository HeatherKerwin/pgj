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
import com.ry.core.dao.appmanage.RemainWeekByScaleDao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.entity.da.RemainWeekByQudao;
import com.ry.core.entity.da.RemainWeekByScale;
import com.ry.util.DateUtil;

@Repository
public class RemainWeekByScaleDaoImpl extends BaseDao<RemainWeekByScale, Integer> implements RemainWeekByScaleDao {

	
private static final Logger logger = Logger.getLogger(RemainWeekByScaleDaoImpl.class);
	
	List<RemainWeekByScale> remains = null;
	RemainWeekByScale remain = null;
	 SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Resource
	private RemainWeekByQudaoDao remainWeekByQudaoDao;
	
	@Override
	public void countReaminWeekByScale(Date executeDate, Date endDate) {
		/*String initSql = "select * from remainWeekByScale";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, null);
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			sql = "SELECT count(*), c.weeks, c.weekDay, c.date FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks FROM appVisitLog avl, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' )) c GROUP BY c.weekDay, c.date ORDER BY c.date DESC";
			lists = getListBySQL(sql, new Object[]{executeDate, executeDate});
		}else{
			sql = "SELECT count(*), c.weeks, c.weekDay, c.date FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks FROM appVisitLog avl, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id ) c GROUP BY c.weekDay, c.date ORDER BY c.date DESC";
			lists = getListBySQL(sql, null);
		}*/
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		sql = "SELECT count(*), c.weeks, c.weekDay, c.date FROM ( SELECT DISTINCT avl.memberId, b.date, DATE_FORMAT( DATE_SUB( actionDate, INTERVAL WEEKDAY(actionDate) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actionDate, '%Y%u') AS weeks FROM appVisitLog avl, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE avl.type = '0' AND memberId = b.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) c GROUP BY c.weekDay, c.date ORDER BY c.date DESC";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		if(lists == null || lists.size() == 0){
			
		}else{
			
			int size = lists.size();
			remain = new RemainWeekByScale();
			//获取第一条信息
			int amount1 = Integer.valueOf(lists.get(0)[0].toString());//获取这个时间段的总数
			Map<String, Object> map = remainWeekByQudaoDao.getBetweenDays(lists, 0);
			
			int days = (Integer) map.get("days");
			Date date = (Date) map.get("date");
			String tempSql = "from remainWeekByScale where DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
			List<RemainWeekByScale> tempRemains = getListByHQL(tempSql, new Object[]{date});
			if(tempRemains == null || tempRemains.size() == 0){//如果数据库中没有这条记录
				
			}else{
				remain = tempRemains.get(0);
			}
			
			remain.setDate(date);
			//根据统计日期获取新增总数
			List<IncreasedUserByQudao> increase = increaseUserByQudaoDao.increaseWeeks(date, date, null);
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
				Date date1 = remainWeekByQudaoDao.dateFormat(strDate1);
				int days1 = remainWeekByQudaoDao.getBetweenDays(date, date1);
				amount1 = Integer.valueOf(lists.get(i)[0].toString());//获取这个时间段的总数
				if(days1 == 0){//如果后面一条记录的时间和前面一条相同，则修改这个时间的留存信
					int day =  (Integer) remainWeekByQudaoDao.getBetweenDays(lists, i).get("days");
					if(day > 0){//如果这条记录的访问时间大于注册时间,则设置数据入库
						setRemainData(remain, amount1, amount2, day);
					}
				}else{//保存留存信息,新建一个留存对象进行保存
					saveOrUpdate(remain);
					date = date1;//将date设置成当前需要保存的日期；
					remain = new RemainWeekByScale();
					Map<String, Object> map2 = remainWeekByQudaoDao.getBetweenDays(lists, i);
					int days2 = (Integer) map2.get("days");
					Date date2 = (Date) map2.get("date");
					String tempSql1 = "from remainWeekByScale where DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
					List<RemainWeekByScale> tempRemains1 = getListByHQL(tempSql1, new Object[]{date2});
					if(tempRemains1 == null || tempRemains1.size() == 0){//如果数据库中没有这条记录
						
					}else{
						remain = tempRemains1.get(0);
					}
					remain.setDate(date2);
					//根据统计日期获取新增总数
					List<IncreasedUserByQudao> increase2 = increaseUserByQudaoDao.increaseWeeks(date2, date2, null);
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
	public void getInstance(List<RemainWeekByScale> remains,Date date){
		for (RemainWeekByScale r : remains) {
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
		remain = new RemainWeekByScale();
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
	public void setRemainData(RemainWeekByScale remain,int amount1, int amount2, int days){
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

	@Override
	public List<Map<String, Object>> showWeekRemain(Date beginDate, Date endDate) {
		String sql = "select date,sum(yi) yi,sum(er) er,sum(san) san ,sum(si) si,sum(wu) wu,sum(liu) liu,sum(qi) qi,sum(ba) ba,sum(jiu) jiu,sum(shi) shi,sum(shiEr) shiEr,sum(shiSan) shiSan,sum(shiWu) shiWu,sum(shiBa) shiBa,sum(erShiEr) erShiEr,sum(erShiLiu) erShiLiu FROM remainWeekByScale GROUP BY date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') order by date";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(beginDate);
		paramList.add(endDate);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}
}
