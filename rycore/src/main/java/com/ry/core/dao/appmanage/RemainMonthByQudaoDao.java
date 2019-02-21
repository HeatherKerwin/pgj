package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.RemainMonthByQudao;

public interface RemainMonthByQudaoDao {

	/**
	 * 统计月留存信息入库
	 * 
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public void countReaminMonthByQudao(Date executeDate, Date endDate);

	/**
	 * 获取月留存信息
	 * @param qudao
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	public List<Map<String, Object>> showMonthRemain(String qudao,
			Date beginDate, Date endDate);


	/**
	 * 月留存信息入库的公用方法
	 * @param sql
	 * @param flag,标记是否第一次入库统计,如果不是，则是修改以前信息，并插入一条数据,0代表第一次
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public void countRemainMonthMethod(List<Object[]> lists);
	
	/**
	 * 求记录中的用户注册时间和行为访问记录的时间差
	 * @param lists
	 * @param index,列表下标
	 * @param simpleDateFormate，日期格式化
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public Map<String, Object> getBetweenMonths(List<Object[]> lists, int index);
}
