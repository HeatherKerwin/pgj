package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountButtonDao {

	/**
	 * 统计推广按钮数据入库
	 * @param flag 0周1月2日
	 * @date 2016年2月22日
	 * @author lvyanqin
	 */
	void countButton(int flag, Date executeDate, Date endDate);

	/**
	 * 获取特殊按钮数据
	 * @param flag,0周1月2日
	 * @param style,PC|WAP
	 * @date 2016年2月22日
	 * @author lvyanqin
	 * @param endDate 
	 * @param beginDate 
	 */
	List<Map<String, Object>> getButton(Date beginDate, Date endDate, int flag, String style, String code);

}
