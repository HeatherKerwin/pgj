package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.HongbaoActivity;
import com.ry.core.form.HongbaoActivityForm;
import com.ry.util.page.PageResults;

public interface HongbaoActivityDao {
	
	PageResults<HongbaoActivity> getPageResults(HongbaoActivityForm hongbaoActivityForm);
	
	void addActivity(HongbaoActivity activity);
	
	void updateActivity(HongbaoActivity activity);
	
	void deleteActivity(HongbaoActivity activity);
	
	HongbaoActivity getActivity(HongbaoActivity activity);
	
	public List<HongbaoActivity> getActivitys(HongbaoActivity activity);
	
	/**
	 * 根据开始结束时间查看此方位存在相同的红包活动个数[并且排除当前编辑的红包（可不传参）]
	 * @param start
	 * @param end
	 * @param tagId
	 * @param id 红包活动主键
	 * @author WKX
	 */
	public Long countByStartAndEndAndTagIdAndNotId(Date start,Date end,Integer tagId,Integer id);
	
	/**
	 * 获取红包列表-List<Map>
	 * @author WKX
	 * @param activity
	 * @since 2016年2月14日 下午3:48:41
	 */
	public List<Map<String,Object>> getMapActivitys(HongbaoActivityForm activity);
}