package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrgLimit;
import com.ry.core.form.orgOrder.OrderRequest;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface OrgLimitDao {

	/**
	 * 主键获取发票详情
	 * @param req
	 */
	List<Map<String,Object>> getOrgByObj(OrderRequest req);
	
	/**
	 * 根据机构ID获取该机构当天的报价额数据(时间为当天)
	 * @author BKY
	 */
	public List<OrgLimit> getByOrgIdAndDay(Integer id, String day);
	
	/**
	 * 修改今日过往报价的最新报价
	 * @author BKY
	 */
	public void saveModel(OrgLimit orgLimit);
}