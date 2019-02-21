/**
 * 
 */
package com.ry.core.form.orgOrder;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.HongbaoDetail;
import com.ry.util.datatable.BaseResponseData;

/**
 * 名称: ListRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午2:03:19<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class OrderResponse extends BaseResponseData{
	
	private List<Map<String,Object>> map;
	private List<Map<String,Object>> record;
	private HongbaoDetail detail;
	private List<DiscountrecordTask> tasks;//订单状态变更
	
	/**
	 * @return the detail
	 */
	public HongbaoDetail getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(HongbaoDetail detail) {
		this.detail = detail;
	}
	/**
	 * @return the map
	 */
	public List<Map<String, Object>> getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(List<Map<String, Object>> map) {
		this.map = map;
	}
	/**
	 * @return the record
	 */
	public List<Map<String, Object>> getRecord() {
		return record;
	}
	/**
	 * @param record the record to set
	 */
	public void setRecord(List<Map<String, Object>> record) {
		this.record = record;
	}
	
	/**
	 * 订单变更状态
	 * @author WKX
	 * @since 2016年3月14日 下午2:52:33
	 */
	public List<DiscountrecordTask> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<DiscountrecordTask> tasks) {
		this.tasks = tasks;
	}
}