package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.IncreaseUserByFunctionDao;
import com.ry.core.dao.appmanage.IncreaseUserByQudaoDao;
import com.ry.core.dao.appmanage.IncreaseUserByTuiGuangDao;
import com.ry.core.dao.appmanage.RemainWeekByFunctionDao;
import com.ry.core.dao.appmanage.RemainWeekByQudaoDao;
import com.ry.core.entity.da.IncreasedUserByFunction;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.entity.da.RemainWeekByFunction;
import com.ry.core.entity.da.RemainWeekByQudao;
import com.ry.util.DateUtil;

@Repository
public class RemainWeekByFunctionDaoImpl extends BaseDao<RemainWeekByFunction, Integer> implements RemainWeekByFunctionDao {

	private static final Logger logger = Logger.getLogger(RemainWeekByFunctionDaoImpl.class);
	
	@Resource
	private RemainWeekByQudaoDao remainWeekByQudaoDao;
	
	@Resource
	private IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Resource
	private IncreaseUserByFunctionDao increaseUserByFunctionDao;
	
	@Resource
	private IncreaseUserByTuiGuangDao increaseUserByTuiGuangDao;
	
	RemainWeekByFunction remain = null;
	List<RemainWeekByFunction> remains = null;
	
	@Override
	public void countRemainWeekByFunction(String field, Date executeDate, Date endDate) {
		/*String initSql = "select * from remainWeekByFunction where field = ?";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{field});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			if("1".equals(field)){//功能留存入库
				sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c. CODE FROM ( SELECT DISTINCT al.memberId, b.date, DATE_FORMAT( DATE_SUB( actiontime, INTERVAL WEEKDAY(actiontime) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actiontime, '%Y%u') AS weeks, al.`code` FROM actionlog al, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE al.source = 'APP' AND memberId = b.id AND al.`code` IS NOT NULL AND DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' )) c GROUP BY c.weekDay, c.date, c. CODE ORDER BY c.date";
				lists = getListBySQL(sql, new Object[]{executeDate, executeDate});
			}else if("2".equals(field)){//推广PC留存入库
				sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' )) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' ))) c GROUP BY c.regist, c.date";
				lists = getListBySQL(sql, new Object[]{executeDate, executeDate, executeDate, executeDate});
			}else if("3".equals(field)){//推广WAP留存入库
				sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' )) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) = DATE_FORMAT( DATE_SUB(?, INTERVAL WEEKDAY(?) DAY), '%Y-%m-%d' ))) c GROUP BY c.regist, c.date";
				lists = getListBySQL(sql, new Object[]{executeDate, executeDate, executeDate, executeDate});
			}
		}else{
			if("1".equals(field)){//功能留存入库
				sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c. CODE FROM ( SELECT DISTINCT al.memberId, b.date, DATE_FORMAT( DATE_SUB( actiontime, INTERVAL WEEKDAY(actiontime) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actiontime, '%Y%u') AS weeks, al.`code` FROM actionlog al, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE al.source = 'APP' AND memberId = b.id AND al.`code` IS NOT NULL ) c GROUP BY c.weekDay, c.date, c. CODE ORDER BY c.date";
			}else if("2".equals(field)){//推广PC留存入库
				sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id ) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id )) c GROUP BY c.regist, c.date";
			}else if("3".equals(field)){//推广WAP留存入库
				sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id ) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id )) c GROUP BY c.regist, c.date";
			}
			if(StringUtils.isEmpty(sql)){
				return;
			}else{
				lists = getListBySQL(sql, null);
			}
		}*/
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if("1".equals(field)){//功能留存入库
			sql = "SELECT count(*), c.weeks, c.weekDay, c.date, c. CODE FROM ( SELECT DISTINCT al.memberId, b.date, DATE_FORMAT( DATE_SUB( actiontime, INTERVAL WEEKDAY(actiontime) DAY ), '%Y-%m-%d' ) AS weekDay, DATE_FORMAT(actiontime, '%Y%u') AS weeks, al.`code` FROM actionlog al, ( SELECT a.id, a.date FROM ( SELECT m.id, DATE_FORMAT( DATE_SUB( init.date, INTERVAL WEEKDAY(init.date) DAY ), '%Y-%m-%d' ) AS date FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY date) a ) b WHERE al.source = 'APP' AND memberId = b.id AND al.`code` IS NOT NULL AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) c GROUP BY c.weekDay, c.date, c. CODE ORDER BY c.date";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		}else if("2".equals(field)){//推广PC留存入库
			sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) c GROUP BY c.regist, c.date";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate, executeDate, endDate});
		}else if("3".equals(field)){//推广WAP留存入库
			sql = "SELECT count(*), c.date date1, c.date date2, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT( DATE_SUB( avl.actionDate, INTERVAL WEEKDAY(avl.actionDate) DAY ), '%Y-%m-%d' ) date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT( DATE_SUB( al.actiontime, INTERVAL WEEKDAY(al.actiontime) DAY ), '%Y-%m-%d' ) date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( DATE_SUB( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), INTERVAL WEEKDAY( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d')) DAY ), '%Y-%m-%d' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) c GROUP BY c.regist, c.date";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate, executeDate, endDate});
		}
		if(lists == null || lists.size() == 0){
			return;
		}else{
			countRemainWeekMethod(lists, field);
		}
		
	}
	
	private void countRemainWeekMethod(List<Object[]> lists, String field){
		if(lists == null || lists.size() == 0){
		}else{
			int size = lists.size();
			remain = new RemainWeekByFunction();
			//获取第一条信息
			int amount1 = Integer.valueOf(lists.get(0)[0].toString());//获取这个时间段的总数
			Map<String, Object> map = remainWeekByQudaoDao.getBetweenDays(lists, 0);
			int days = (Integer) map.get("days");
			Date date = (Date) map.get("date");
			String code = lists.get(0)[4].toString();
			String tempSql = "from remainWeekByFunction where field = ? and code = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
			List<RemainWeekByFunction> tempRemains = getListByHQL(tempSql, new Object[]{field,code,date});
			if(tempRemains == null || tempRemains.size() == 0){//如果数据库中没有这条记录
			}else{
				remain = tempRemains.get(0);
			}
			remain.setCode(code);
			remain.setDate(date);
			remain.setField(field);
			if(code == null){
				code = "";
			}
			//根据统计日期获取新增总数
			List<Map<String, Object>> increase = null;
			if("1".equals(field)){
				increase = increaseUserByFunctionDao.findIncreaseUserByFunction(date, date, 0, code);
			}else{
				String style = "PC";
				if("3".equals(field)){
					style = "WAP";
				}
				increase = increaseUserByTuiGuangDao.findIncreaseUser(date, date, 0, style);
			}
			int amount2 = 0;
			if(increase != null && increase.size() > 0){
				amount2 = (Integer) increase.get(0).get("amount");
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
				int days1 = remainWeekByQudaoDao.getBetweenDays(date, date1);
				String code1 = lists.get(i)[4].toString();
				if(code1 == null){
					code1 = "";
				}
				amount1 = Integer.valueOf(lists.get(i)[0].toString());//获取这个时间段的总数
				if(days1 == 0 && code.equalsIgnoreCase(code1)){//如果后面一条记录的时间和前面一条相同，则修改这个时间的留存信
					int day =  (Integer)remainWeekByQudaoDao.getBetweenDays(lists, i).get("days");
					if(day > 0){//如果这条记录的访问时间大于注册时间,则设置数据入库
						setRemainData(remain, amount1, amount2, day);
					}
				}else{//保存留存信息,新建一个留存对象进行保存
					saveOrUpdate(remain);
					date = date1;//将date设置成当前需要保存的日期；
					code = code1;
					remain = new RemainWeekByFunction();
					Map<String, Object> map2 = remainWeekByQudaoDao.getBetweenDays(lists, i);
					int days2 = (Integer) map2.get("days");
					Date date2 = (Date) map2.get("date");
					if(lists.get(i)[4] != null){
						remain.setCode(lists.get(i)[4].toString());
					}
					String tempSql1 = "from remainWeekByFunction where field = ? and code = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
					List<RemainWeekByFunction> tempRemains1 = getListByHQL(tempSql1, new Object[]{field,remain.getCode(),date2});
					if(tempRemains1 == null || tempRemains1.size() == 0){//如果数据库中没有这条记录
					}else{
						remain = tempRemains1.get(0);
					}
					remain.setDate(date2);
					remain.setField(field);
					//根据统计日期获取新增总数
					List<Map<String, Object>> increase2 = null;
					if("1".equals(field)){
						increase2 = increaseUserByFunctionDao.findIncreaseUserByFunction(date2, date2, 0, code);
					}else{
						String style = "PC";
						if("3".equals(field)){
							style = "WAP";
						}
						increase2 = increaseUserByTuiGuangDao.findIncreaseUser(date2, date2, 0, style);
					}
					int amount3 = 0;
					if(increase2 != null && increase2.size() > 0){
						amount3 = (Integer) increase2.get(0).get("amount");
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
	public void getInstance(List<RemainWeekByFunction> remains,Date date){
		for (RemainWeekByFunction r : remains) {
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
		remain = new RemainWeekByFunction();
	}
	
	
	/**
	 * 格式化日期
	 * @param strDate
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public  Date dateFormat(String strDate){
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = simpleDateFormate.parse(strDate);
		} catch (ParseException e) {
			logger.info("RemainWeekByFunctionDaoImpl日期转换>>>>>>",e);
			e.printStackTrace();
		}
		return date1;
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
	public void setRemainData(RemainWeekByFunction remain,int amount1, int amount2, int days){
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
	public List<Map<String, Object>> showWeekRemain(String code,
			Date beginDate, Date endDate1, String field) {
		String sql = "select date,sum(yi) yi,sum(er) er,sum(san) san ,sum(si) si,sum(wu) wu,sum(liu) liu,sum(qi) qi,sum(ba) ba,sum(jiu) jiu,sum(shi) shi,"
				+ "sum(shiEr) shiEr,sum(shiSan) shiSan,sum(shiWu) shiWu,sum(shiBa) shiBa,sum(erShiEr) erShiEr,sum(erShiLiu) erShiLiu FROM remainWeekByFunction where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if(!StringUtils.isEmpty(code)){
			sql += " and code = ? ";
			paramList.add(code);
		}
		sql += " and field = ? ";
		paramList.add(field);
		sql += " GROUP BY date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') order by date";
		paramList.add(beginDate);
		paramList.add(endDate1);
		List<Map<String, Object>> lists = getListMapBySQL(sql, paramList.toArray());
		return lists;
	}

}
