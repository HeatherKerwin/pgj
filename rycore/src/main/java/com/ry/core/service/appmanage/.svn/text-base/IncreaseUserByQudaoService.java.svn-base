package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.IncreasedUserByQudao;

public interface IncreaseUserByQudaoService {

	/**
	 * 根据用户表，统计每日的新增数据入库
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	void countIncreaseUserByQudao(Date executeDate, Date endDate);

	/**
	 * 先判断对应的中间表是否有对应日期的记录，如果没有则分析执行日期的数据，如果有，就不做处理，这是为了定任务加的一个方法
	 * 处理过为true
	 * @param executeDate
	 * @date 2016年2月29日
	 * @author lvyanqin
	 */
	boolean count1(Date executeDate, Date endDate);
	
	/**
	 * 获取日新增数据
	 * @param beginDate
	 * @param endDate
	 * @param qudao
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	List<IncreasedUserByQudao> increaseDay(Date beginDate, Date endDate,
			String qudao);

	/**
	 * 获取周新增数据
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	List<IncreasedUserByQudao> increaseWeeks(Date beginDate, Date endDate,
			String qudao);

	/**
	 * 获取月新增信息
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	List<IncreasedUserByQudao> increaseMonths(Date beginDate, Date endDate, String qudao);
	
	/**
	 * 修改最终活跃率集合
	 * 后台得到的数据，可能那个时间点事没有数据的，得将其设置为0
	 * 
	 * @param showDates,日期类型的集合
	 * @param actives
	 * @param num,开始时间和结束时间相隔的星期数或月数或天数
	 * @return
	 * @date 2016年1月6日
	 * @author lvyanqin
	 */
	public Map<Object, Object> getResultsIncrease(List<Date> showDates, List<IncreasedUserByQudao> actives, int num);


}
