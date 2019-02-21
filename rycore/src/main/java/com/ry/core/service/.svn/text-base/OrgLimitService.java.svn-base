package com.ry.core.service;

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
public interface OrgLimitService {

	/**
	 * 主键获取发票详情
	 * @param req
	 * @return
	 */
	List<Map<String,Object>> getOrgByObj(OrderRequest req);
	
	/**
	 * 根据机构ID获取该机构的额度(时间为当天)
	 * @author BKY
	 */
	public OrgLimit getByOrgIdAndDay(Integer id, String day);
	
	/**
	 * 修改今日过往报价的最新报价额度
	 * @author BKY
	 */
	public void saveModel(OrgLimit orgLimit);
}
