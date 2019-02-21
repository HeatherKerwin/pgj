package com.ry.core.form;

import com.ry.core.entity.DistributeOrderPl;

/**
 * 页面传送查询参数（机构端商票订单）
 * @author KHC
 */
public class DistributeOrderPlForm extends DistributeOrderPl{

	private static final long serialVersionUID = 1L;

	DistributeOrderPl distributeOrderPl = new DistributeOrderPl();
	
	private Float allmoney;//企业贴现订单金额
	
	private Integer[] states;//多个状态（用户条件查询）
	
	private String start;//开始时间
	
	private String end;//结束时间
	
	private Double minMoney;//最小金额
	
	private Double maxMoney;//最大金额
	
	public DistributeOrderPl getDistributeOrderPl() {
		return distributeOrderPl;
	}

	public void setDistributeOrderPl(DistributeOrderPl distributeOrderPl) {
		this.distributeOrderPl = distributeOrderPl;
	}

	/**
	 * 企业批量贴现订单金额
	 * @author KHC
	 * @since 2016年8月19日 上午9:13:46
	 */
	public Float getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(Float allmoney) {
		this.allmoney = allmoney;
	}
	
	/**
	 * 多个状态（用户条件查询）
	 * @author ZY
	 */
	public Integer[] getStates() {
		return states;
	}

	public void setStates(Integer[] states) {
		this.states = states;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Double getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(Double minMoney) {
		this.minMoney = minMoney;
	}

	public Double getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}
}