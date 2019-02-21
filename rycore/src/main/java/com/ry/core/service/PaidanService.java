package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;

public interface PaidanService {
	
	/**
	 * 获取配置参数（派单用）
	 * @author WKX
	 * @since 2016年5月25日 下午3:06:45
	 */
	public Map<String, Object> getConfigXML();
	
	/**
	 * 派单（系统、含定时）
	 * @author WKX
	 * @param id
	 * @param config
	 * @since 2016年5月25日 下午3:04:43
	 */
	public Map<String,Object> updatePaidan(Integer id,Map<String,Object> config);
	
	/**
	 * 支付订单（并保存支付流水）派单
	 * @author WKX
	 * @param discountrecord 订单
	 * @param state 支付状态
	 * @param des 支付备注（由插件生成）
	 * @since 2016年5月26日 上午10:08:30
	 */
	public void updateAndPayRecord(Discountrecord discountrecord,Integer state,String des);
	
	/**
	 * 重新派单（验票审核不通过、保存TASK、设置状态）
	 * @author GXW
	 * @param diso 机构端订单
	 * @param task 机构端订单记录
	 * @param dic  企业端订单
	 * @since 2016年5月31日
	 */
	public Map<String,Object> updatePaidanAndOrderState(DistributeOrder  diso,  DistributeOrderTask task , Discountrecord dic);
	
	/**
	 * 获取需要派单的订单（系统自动派单用）
	 * @author WKX
	 * @since 2016年6月7日 上午9:40:40
	 */
	public List<Map<String,Object>> getNeedPaidan();
	
	/**
	 * 根据创建时间获取超时订单（定时任务，待接单超时自动无效）
	 * @author WKX
	 * @param date
	 * @since 2016年6月7日 下午1:43:38
	 */
	public List<DistributeOrder> getOverrunByCreateTime(Date date);
}