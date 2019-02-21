package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.da.IncreasedUserByQudao;

public interface IncreaseUserByQudaoDao {

	/**
	 * 根据用户表，统计每日的新增数据入库
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	void countIncreaseUserByQudao(Date executeDate, Date endDate);

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
	 * 先判断对应的中间表是否有对应日期的记录，如果没有则分析执行日期的数据，如果有，就不做处理，这是为了定任务加的一个方法
	 * 处理过为true
	 * @param executeDate
	 * @date 2016年2月29日
	 * @author lvyanqin
	 * @return 
	 */
	boolean count1(Date executeDate, Date endDate);

}
