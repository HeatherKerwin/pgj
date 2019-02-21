package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountButtonService {

	/**
	 * 统计推广按钮数据入库
	 * @param flag 0周1月2日
	 * @date 2016年2月22日
	 * @author lvyanqin
	 */
	void countButton(int flag, Date executeDate, Date endDate);

	/**
	 * 获取特殊按钮数据
	 * @param endDate 
	 * @param beginDate 
	 * @param flag,0周1月2日
	 * @param style,PC|WAP
	 * @date 2016年2月22日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getButton(Date beginDate, Date endDate, int flag, String style,String code);

}
