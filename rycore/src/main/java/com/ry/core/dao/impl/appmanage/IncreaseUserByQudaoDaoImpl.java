package com.ry.core.dao.impl.appmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.appmanage.IncreaseUserByQudaoDao;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.entity.da.ActiveDayByQudao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.util.DateUtil;

@Repository
public class IncreaseUserByQudaoDaoImpl extends BaseDao<IncreasedUserByQudao, Integer> implements IncreaseUserByQudaoDao {

	
	@Override
	public void countIncreaseUserByQudao(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists != null && initLists.size() > 0){//不是第一次插入数据，则分析今天或这周或这月的数据进行分析
			for (Map<String, Object> map : initLists) {
				delete(Integer.valueOf(map.get("id").toString()));
			}
		}
		sql = "SELECT count(*), FROM_UNIXTIME(regtime / 1000, '%Y%m%d') AS days, pd.qudao FROM member m, phonedetail pd WHERE m.qudao = 'APP'  AND pd.memberid = m.id AND DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y-%m-%d'), '%Y-%m-%d' ) BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d') GROUP BY pd.qudao, days";
		lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyyMMdd");
		for (Object[] obj : lists) {
			IncreasedUserByQudao increaseUser = new IncreasedUserByQudao();
			increaseUser.setAmount(Integer.valueOf(obj[0].toString()));
			String days = obj[1].toString();
			if(obj[2] == null){
				increaseUser.setQuDao("555");
			}else{
				increaseUser.setQuDao(obj[2].toString());
			}
			try {
				Date date = simpleDateFormate.parse(days);
				increaseUser.setDate(date);
				save(increaseUser);
			} catch (ParseException e) {
				//logger.info(e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<IncreasedUserByQudao> increaseDay(Date beginDate, Date endDate,
			String qudao) {
		
		String sql = "select sum(amount),date from increasedUserByQudao where 1=1 "; 
		List<Object> paramList = new ArrayList<Object>();
		
		if(qudao != null && !"null".equals(qudao)){
			sql += " and qudao = ? ";
			paramList.add(qudao);
		}
		sql += " GROUP BY date having DATE_FORMAT(date,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d') ";
		paramList.add(beginDate);
		paramList.add(endDate);
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		
		return getIncreaseUser(lists,2);
		
	}
	
	/**
	 * 将得到的集合类型转换成IncreasedUserByQudao对象集合
	 * @param lists
	 * @return
	 * @date 2016年1月6日
	 * @author lvyanqin
	 */
	public List<IncreasedUserByQudao> getIncreaseUser(List<Object[]> lists,int flag){
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd");
		List<IncreasedUserByQudao> increaseUsers = new ArrayList<IncreasedUserByQudao>();
		for (Object[] obj : lists) {
			Integer amount = Integer.valueOf(obj[0].toString());
			Date date ;
			String day = obj[1].toString();
			if(flag == 1){
				day += "-01";
			}
			try {
				date = simpleDateFormate.parse(day);
				IncreasedUserByQudao increase = new IncreasedUserByQudao();
				increase.setAmount(amount);
				increase.setDate(date);
				increaseUsers.add(increase);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return increaseUsers;
	}

	
	@Override
	public List<IncreasedUserByQudao> increaseWeeks(Date beginDate,
			Date endDate, String qudao) {
		String sql = "select sum(amount),DATE_FORMAT(DATE_SUB(date,INTERVAL WEEKDAY(date) DAY),'%Y-%m-%d') as days from increasedUserByQudao where 1=1 "; 
		List<Object> paramList = new ArrayList<Object>();
		
		if(qudao != null && !"null".equals(qudao)){
			sql += " and qudao = ?";
			paramList.add(qudao);
		}
		sql += " GROUP BY days having DATE_FORMAT(days,'%Y-%m-%d') BETWEEN DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d')";
		paramList.add(beginDate);
		paramList.add(endDate);
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		return getIncreaseUser(lists,0);
	}

	
	@Override
	public List<IncreasedUserByQudao> increaseMonths(Date beginDate, Date endDate,
			String qudao) {
		endDate = DateUtil.addDays(DateUtil.addMonth(endDate, 1),-1);
		String sql = "select sum(amount),DATE_FORMAT(date,'%Y-%m') month from increasedUserByQudao where 1=1 ";
		//String sql = "select sum(amount),date from increasedUserByQudao where 1=1 "; 
		List<Object> paramList = new ArrayList<Object>();
		
		if(qudao != null && !"null".equals(qudao)){
			sql += " and qudao = ? ";
			paramList.add(qudao);
		}
		sql += " and date BETWEEN ? and ? GROUP BY month;";
		//sql += " GROUP BY DATE_FORMAT(date,'%Y%m') having date BETWEEN ? and ?";
		paramList.add(beginDate);
		paramList.add(endDate);
		List<Object[]> lists = getListBySQL(sql, paramList.toArray());
		return getIncreaseUser(lists,1);
	}

	@Override
	public boolean count1(Date executeDate, Date endDate) {
		String initSql = "SELECT * FROM increasedUserByQudao WHERE DATE_FORMAT(date, '%Y-%m-%d') BETWEEN DATE_FORMAT(?, '%Y-%m-%d') AND DATE_FORMAT(?, '%Y-%m-%d')";
		List<Map<String, Object>> initLists = getListMapBySQL(initSql, new Object[]{executeDate, endDate});
		String sql = "";
		List<Object[]> lists = new ArrayList<Object[]>();
		if (initLists == null || initLists.size() == 0){//这段时间还没有数据
			sql = "SELECT count(*), FROM_UNIXTIME(regtime / 1000, '%Y%m%d') AS days, pd.qudao FROM member m, phonedetail pd WHERE m.qudao = 'APP' AND pd.qudao IS NOT NULL AND pd.memberid = m.id AND DATE_FORMAT( FROM_UNIXTIME(regtime / 1000, '%Y%m%d'), '%Y%m%d' ) BETWEEN DATE_FORMAT(?, '%Y%m%d') AND DATE_FORMAT(?, '%Y%m%d') GROUP BY pd.qudao, days";
			lists = getListBySQL(sql, new Object[]{executeDate, endDate});
		}else{//如果执行日期的数据处理过，那就不需要执行了
			return true;
		}
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyyMMdd");
		for (Object[] obj : lists) {
			IncreasedUserByQudao increaseUser = new IncreasedUserByQudao();
			increaseUser.setAmount(Integer.valueOf(obj[0].toString()));
			increaseUser.setQuDao(obj[2].toString());
			String days = obj[1].toString();
			try {
				Date date = simpleDateFormate.parse(days);
				increaseUser.setDate(date);
				save(increaseUser);
			} catch (ParseException e) {
				//logger.info(e);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	

}
