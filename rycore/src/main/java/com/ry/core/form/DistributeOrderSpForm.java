package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.DistributeOrderSp;

/**
 * 页面传送查询参数（机构端商票订单）
 * @author KHC
 */
public class DistributeOrderSpForm extends DistributeOrderSp {

	private static final long serialVersionUID = 1L;
	
	DistributeOrderSp distributeOrderSp = new DistributeOrderSp();

	private Float allmoney;//企业贴现订单金额
	
	private Integer orderflag;//贴现订单状态
	
	private String company;//贴现订单企业名称
	
	private Integer[] states;//多个状态（用户条件查询）
	
	private String start;//起始时间
	
	private String end;//结束时间
	
	private Double minMoney;//最小金额
	
	private Double maxMoney;//最大金额
	
	private String bank;//承兑行企业
	
	public DistributeOrderSp getDistributeOrder() {
		return distributeOrderSp;
	}

	public void setDistributeOrder(DistributeOrderSp distributeOrderSp) {
		this.distributeOrderSp = distributeOrderSp;
	}

	public Integer getId() {
		return distributeOrderSp.getId();
	}

	public void setId(Integer id) {
		distributeOrderSp.setId(id);
	}

	public Integer getDcrdSpId() {
		return distributeOrderSp.getDcrdSpId();
	}

	public void setDcrdSpId(Integer dcrdSpId) {
		distributeOrderSp.setDcrdSpId(dcrdSpId);
	}

	public Integer getOrgId() {
		return distributeOrderSp.getOrgId();
	}

	public void setOrgId(Integer orgId) {
		distributeOrderSp.setOrgId(orgId);
	}

	public Integer getState() {
		return distributeOrderSp.getState();
	}

	public void setState(Integer state) {
		distributeOrderSp.setState(state);
	}

	public String getNo() {
		return distributeOrderSp.getNo();
	}

	public void setNo(String no) {
		distributeOrderSp.setNo(no);
	}

	public Date getCreateTime() {
		return distributeOrderSp.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		distributeOrderSp.setCreateTime(createTime);
	}

	/**
	 * 企业贴现订单金额
	 * @author KHC
	 * @since 2016年8月11日 上午9:13:46
	 */
	public Float getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(Float allmoney) {
		this.allmoney = allmoney;
	}

	/**
	 * 贴现订单状态
	 * @author KHC
	 * @since 2016年8月11日 上午9:14:15
	 */
	public Integer getOrderflag() {
		return orderflag;
	}

	public void setOrderflag(Integer orderflag) {
		this.orderflag = orderflag;
	}

	/**
	 * 贴现订单企业名称
	 * @author KHC
	 * @since 2016年8月11日 上午9:14:29
	 */
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 多个状态（用户条件查询）
	 * @author WKX
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
}