package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.da.ActiveRatioByQudao;
import com.ry.core.entity.da.ActiveRatioByScale;

public interface ActiveRatioByScaleDao {

	/**
	 * 质量与规模，周活跃率数据入库
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	void countActiveRatioWeeks(Date executeDate, Date endDate);

	/**
	 * 质量与规模，月活跃率数据入库
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	void countActiveRatioMonths(Date executeDate, Date endDate);

	/**
	 * 质量与规模，得到周月活跃率数据
	 * @param beginDate
	 * @param endDate
	 * @param flag
	 * @param qudao
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	List<ActiveRatioByScale> activeRatioByScale(Date beginDate, Date endDate,
			String flag);
	
}
