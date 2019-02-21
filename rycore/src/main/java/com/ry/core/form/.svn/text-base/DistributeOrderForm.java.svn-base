package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.DistributeOrder;

/**
 * 页面传送查询参数（机构端订单）
 * @author WKX
 */
public class DistributeOrderForm extends DistributeOrder {

private static final long serialVersionUID = 1L;
	
	DistributeOrder distributeOrder = new DistributeOrder();

	//企业贴现订单金额
	private Float allmoney;
	
	//贴现订单状态
	private Integer orderflag;
	
	//贴现订单企业名称
	private String company;
	
	private Integer depositState;//@APP2.2 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给企业、4企业给我（含我））
	
	private Integer[] states;//多个状态（用户条件查询）
	
	private Float version;//版本标示（2.2以上才校验保证金）
	
	private String start;
	
	private String end;
	
	private Integer type;//承兑行类型
	
	private Double minMoney;//最低票据价格
	
	private Double maxMoney;//最低票据价格
	
	public DistributeOrder getDistributeOrder() {
		return distributeOrder;
	}

	public void setDistributeOrder(DistributeOrder distributeOrder) {
		this.distributeOrder = distributeOrder;
	}

	public Integer getId() {
		return distributeOrder.getId();
	}

	public void setId(Integer id) {
		distributeOrder.setId(id);
	}

	public Integer getDcrdId() {
		return distributeOrder.getDcrdId();
	}

	public void setDcrdId(Integer dcrdId) {
		distributeOrder.setDcrdId(dcrdId);
	}

	public Integer getOrgId() {
		return distributeOrder.getOrgId();
	}

	public void setOrgId(Integer orgId) {
		distributeOrder.setOrgId(orgId);
	}

	public Integer getState() {
		return distributeOrder.getState();
	}

	public void setState(Integer state) {
		distributeOrder.setState(state);
	}

	public String getNo() {
		return distributeOrder.getNo();
	}

	public void setNo(String no) {
		distributeOrder.setNo(no);
	}

	public Date getCreateTime() {
		return distributeOrder.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		distributeOrder.setCreateTime(createTime);
	}

	public String getImagePath() {
		return distributeOrder.getImagePath();
	}

	public void setImagePath(String imagePath) {
		distributeOrder.setImagePath(imagePath);
	}

	/**
	 * 企业贴现订单金额
	 * @author WKX
	 * @since 2016年3月3日 下午4:17:10
	 */
	public Float getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(Float allmoney) {
		this.allmoney = allmoney;
	}

	/**
	 * 贴现订单状态
	 * @author WKX
	 * @since 2016年3月3日 下午4:17:18
	 */
	public Integer getOrderflag() {
		return orderflag;
	}

	public void setOrderflag(Integer orderflag) {
		this.orderflag = orderflag;
	}

	/**
	 * 贴现订单企业名称
	 * @author WKX
	 * @since 2016年3月3日 下午4:17:26
	 */
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给企业、4企业给我（含我））
	 */
	public Integer getDepositState() {
		return depositState;
	}

	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}
	
	/**
	 * 多个状态（用户条件查询）
	 * @author WKX
	 * @since 2016年9月9日 下午4:07:58
	 */
	public Integer[] getStates() {
		return states;
	}

	public void setStates(Integer[] states) {
		this.states = states;
	}

	/**
	 * 版本标示（2.2以上才校验保证金）
	 * @author WKX
	 * @since 2016年6月22日 下午4:31:43
	 */
	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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