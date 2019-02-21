package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
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
import com.ry.core.dao.appmanage.RemainMonthByFunctionDao;
import com.ry.core.dao.appmanage.RemainMonthByQudaoDao;
import com.ry.core.dao.appmanage.RemainWeekByQudaoDao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.entity.da.RemainMonthByFunction;
import com.ry.core.entity.da.RemainMonthByQudao;
import com.ry.util.DateUtil;

@Repository
public class RemainMonthByFunctionDaoImpl extends BaseDao<RemainMonthByFunction, Integer> implements RemainMonthByFunctionDao {
	
	private static final Logger logger = Logger.getLogger(RemainMonthByFunctionDaoImpl.class);
	
	List<RemainMonthByFunction> remains = null;
	RemainMonthByFunction remain = null;
	
	@Resource
	private RemainWeekByQudaoDao remainWeekByQudaoDao;
	
	@Resource
	RemainMonthByQudaoDao remainMonthDao;
	
	@Resource
	RemainWeekByQudaoDao remainWeekDao;
	
	@Resource
	IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Resource
	private IncreaseUserByFunctionDao increaseUserByFunctionDao;
	
	@Resource
	private IncreaseUserByTuiGuangDao increaseUserByTuiGuangDao;
	
	@Override
	public void countRemainMonthByFunction(String field, Date executeDate, Date endDate) {
		/*String initSql = "select * from remainMonthByFunction where field = ? and DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d')AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{field, executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){
			
		}*/
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		//date是注册用户的"年份-月份"
		if("1".equals(field)){//功能留存入库
			sql = "SELECT count(*), c. MONTH, c.beginDate, c. CODE FROM ( SELECT DISTINCT al.memberId, b.beginDate, DATE_FORMAT(actiontime, '%Y-%m') AS monthDay, DATE_FORMAT(actiontime, '%Y-%m') AS MONTH, al.`code` FROM actionlog al, ( SELECT a.id, a.beginDate FROM ( SELECT m.id, DATE_FORMAT(init.date, '%Y-%m') AS beginDate FROM member m, ( SELECT id, FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d') date FROM member m ORDER BY date) init WHERE init.id = m.id ORDER BY beginDate ) a ) b WHERE al.source = 'APP' AND memberId = b.id AND al.`code` IS NOT NULL AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) c GROUP BY c. MONTH, c.beginDate, c. CODE ORDER BY c.beginDate";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		}else if("2".equals(field)){//推广PC留存入库，里面code默认为0
			sql = "SELECT count(*), c.date, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT(avl.actionDate, '%Y-%m') date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT(al.actiontime, '%Y-%m') date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'PC' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) c GROUP BY c.regist, c.date";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate, executeDate, endDate});
		}else if("3".equals(field)){//推广WAP留存入库,里面code默认为0
			sql = "SELECT count(*), c.date, c.regist, c. CODE, c.qudao FROM (( SELECT DISTINCT DATE_FORMAT(avl.actionDate, '%Y-%m') date, avl.memberId, a.regist, a.qudao, a. CODE FROM appVisitLog avl, ( SELECT id, DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE avl.memberId = a.id AND DATE_FORMAT(avl.actionDate, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')) UNION ( SELECT DISTINCT DATE_FORMAT(al.actiontime, '%Y-%m') date, al.memberId, a.regist, a.qudao, a. CODE FROM actionlog al, ( SELECT id, DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m' ) AS regist, qudao, aa. CODE FROM member, (SELECT '0' AS CODE FROM DUAL) aa WHERE qudao = 'WAP' AND hezuo = 'baidu' ORDER BY regist ) a WHERE al.memberId = a.id AND DATE_FORMAT(al.actiontime, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d'))) c GROUP BY c.regist, c.date";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate, executeDate, endDate});
		}
		if(lists == null || lists.size() == 0){
			return;
		}else{
			countRemainMonthMethod(lists, field);
		}
	}

	/**
	 * 功能模块，月留存信息入库的公用方法
	 * @param sql
	 * @param flag,标记是否第一次入库统计,如果不是，则是修改以前信息，并插入一条数据,0代表第一次
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	public void countRemainMonthMethod(List<Object[]> lists, String field){
		if(lists == null || lists.size() == 0){
			
		}else{
			int size = lists.size();
			
				remain = new RemainMonthByFunction();
			
			//获取第一条信息
			int amount1 = Integer.valueOf(lists.get(0)[0].toString());//获取这个时间段的总数
			Map<String, Object> map = remainMonthDao.getBetweenMonths(lists, 0);
			int days = (Integer) map.get("days");
			Date date = (Date) map.get("date");
			
			remain.setCode(lists.get(0)[3].toString());
			
			String tempSql = "from remainMonthByFunction where field = ? and code = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
			List<RemainMonthByFunction> tempRemains = getListByHQL(tempSql, new Object[]{field,remain.getCode(),date});
			if(tempRemains == null || tempRemains.size() == 0){//如果数据库中没有这条记录
				
			}else{
				remain = tempRemains.get(0);
			}
			
			remain.setDate(date);
			remain.setField(field);
			//根据统计日期获取新增总数
			//List<IncreasedUserByQudao> increase = increaseUserByQudaoDao.increaseMonths(date, date, null);
			List<Map<String,Object>>  increase = null;
			if("1".equals(field)){
				increase = increaseUserByFunctionDao.findIncreaseUserByFunction(date, date, 1, remain.getCode());
			}else{
				String style = "PC";
				if("3".equals(field)){
					style = "WAP";
				}
				increase = increaseUserByTuiGuangDao.findIncreaseUser(date, date, 1, style);
			}
			
			int amount2 = 1;
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
				String strDate1 = lists.get(i)[1].toString();
				strDate1 += "-01";
				Date date1 = remainWeekDao.dateFormat(strDate1);
				int days1 = remainWeekDao.getBetweenDays(date, date1);
				String code1 = lists.get(i)[3].toString();
				if(code1 == null){
					code1 = "";
				}
				amount1 = Integer.valueOf(lists.get(i)[0].toString());//获取这个时间段的总数
				if(days1 == 0 && remain.getCode().equalsIgnoreCase(code1)){//如果后面一条记录的时间和前面一条相同，则修改这个时间的留存信息
					int day =  (Integer) remainMonthDao.getBetweenMonths(lists, i).get("days");
					if(day > 0){
						setRemainData(remain, amount1, amount2, day);
					}
				}else{//保存留存信息,新建一个留存对象进行保存
					saveOrUpdate(remain);
					date = date1;//将date设置成当前需要保存的日期；
					
						remain = new RemainMonthByFunction();
					
					Map<String, Object> map2 = remainMonthDao.getBetweenMonths(lists, i);
					int days2 = (Integer) map2.get("days");
					Date date2 = (Date) map2.get("date");
					remain.setCode(lists.get(i)[3].toString());
					
					String tempSql1 = "from remainMonthByFunction where field = ? and code = ? and DATE_FORMAT(date,'%y-%m-%d') = DATE_FORMAT(?,'%y-%m-%d')";
					List<RemainMonthByFunction> tempRemains1 = getListByHQL(tempSql1, new Object[]{field,remain.getCode(),date2});
					if(tempRemains1 == null || tempRemains1.size() == 0){//如果数据库中没有这条记录
						
					}else{
						remain = tempRemains1.get(0);
					}
					
					remain.setDate(date2);
					remain.setField(field);
					//根据统计日期获取新增总数
					//List<IncreasedUserByQudao> increase2 = increaseUserByQudaoDao.increaseMonths(date2, date2, null);
					List<Map<String,Object>>  increase2 = null;
					if("1".equals(field)){
						increase2 = increaseUserByFunctionDao.findIncreaseUserByFunction(date2, date2, 1, remain.getCode());
					}else{
						String style = "PC";
						if("3".equals(field)){
							style = "WAP";
						}
						increase2 = increaseUserByTuiGuangDao.findIncreaseUser(date2, date2, 1, style);
					}
					
					int amount3 = 1;
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
	 * 保存留存月的数据
	 * @param remain
	 * @param amount1
	 * @param amount2
	 * @param days
	 * @return
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	public void setRemainData(RemainMonthByFunction remain,int amount1, int amount2, int days){
		
		//获取weekDate和date间隔的周数
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
	 * 如果该表中已经存在这天的记录，这从表中获取这个对象，通过匹配时间
	 * @param remains
	 * @param date
	 * @date 2016年1月12日
	 * @author lvyanqin
	 * 如果表中没有这个时间的记录，则新建一个对象
	 */
	public void getInstance(List<RemainMonthByFunction> remains, Date date){
		for (RemainMonthByFunction r : remains) {
			try {
				if(DateUtil.daysBetween(r.getDate(), date) == 0){
					remain = r;
					return;
				}
			} catch (ParseException e) {
				logger.info("RemainMonthByFunctionDaoImpl-daysBetween日期转换>>>>>>",e);
				e.printStackTrace();
			}
		}
		remain = new RemainMonthByFunction();
	}
	
	
	

	@Override
	public List<Map<String, Object>> showMonthRemain(String code,
			Date beginDate, Date endDate1,String field) {
		String sql = "select date,sum(yi) yi,sum(er) er,sum(san) san ,sum(si) si,sum(wu) wu,sum(liu) liu,sum(qi) qi,sum(ba) ba,sum(jiu) jiu FROM remainMonthByFunction where 1=1 ";
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
