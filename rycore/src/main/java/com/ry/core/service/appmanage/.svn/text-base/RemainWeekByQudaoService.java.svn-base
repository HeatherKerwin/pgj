package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.RemainWeekByQudao;

public interface RemainWeekByQudaoService {

	/**
	 * 统计周留存，如果数据库中已存在数据，则一天一天的更新然后加上今天的统计数据
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	void countReaminWeekByQudao(Date executeDate, Date endDate);

	/**
	 * 根据渠道和时间获取周留存信息
	 * @param qudao
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showWeekRemain(String qudao, Date beginDate,
			Date endDate);

	/**
	 * 计算得到留存上面一栏显示的数据,形如第1周,但得到的是一个Integer型集合
	 * @param flag,0代表周,1代表月
	 * @param otherDays
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	public List<Integer> calTopInt(int flag, String otherDays);
	
	/**
	 * 根据日期得到形如2016-1-11~2016-1-17的字符串集合,相隔days天
	 * @param showDates
	 * @param days,间隔的天数
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	public List<String> calLeftStrByDay(List<Date> showDates, int daysNum);
	
	/**
	 * 根据日期得到形如2016-1-11~2016-1-17的字符串集合,相隔month月
	 * @param showDates
	 * @param month
	 * @return
	 * @date 2016年2月14日
	 * @author lvyanqin
	 */
	public List<String> calLeftStrByMonth(List<Date> showDates, int month);

	/**
	 * 修改最终周留存信息
	 * 后台得到的数据，可能那个时间点事没有数据的，得将其设置为0
	 * @param showDates
	 * @param remains
	 * @return
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	public List<Map<String, Object>> getresultsRemains(List<Date> showDates, List<Map<String, Object>> remains);

}
