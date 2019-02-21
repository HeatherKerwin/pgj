package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.Discountrecord;

public class DiscountrecordForm extends Discountrecord {

	private static final long serialVersionUID = -3484640427411299200L;
	
	//类型[1国股、2城商、3三农、4其他]
	private Integer type;
	
	//可能同时查看多个状态
	private Integer[] state;

	//开始时间[过滤下单时间]
	private Date start;
	
	//结束时间[过滤下单时间]
	private Date end;
	
	//评价标示（0已评价,1未评价）
	private Integer comment;
	
	private Integer depositState;//APP2.2 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给机构、4机构给我（含我））
	
	private Float maxallmoney;// 最大金额 
	
	private Float minallmoney;// 最小金额
	/**
	 * 类型[1国股、2城商、3三农、4其他]
	 * @author WKX
	 * @since 2016年1月11日 下午1:48:07
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 可能同时查看多个状态[已确认、待验票、待收款...]
	 * @author WKX
	 * @since 2016年1月23日 下午2:11:26
	 */
	public Integer[] getState() {
		return state;
	}

	public void setState(Integer[] state) {
		this.state = state;
	}

	/**
	 * 开始时间[过滤下单时间]
	 * @author WKX
	 * @since 2016年1月11日 下午1:48:30
	 */
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * 结束时间[过滤下单时间]
	 * @author WKX
	 * @since 2016年1月11日 下午1:48:38
	 */
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * 评价标示（0已评价,1未评价）
	 * @author WKX
	 * @since 2016年5月17日 上午10:19:05
	 */
	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	/**
	 * //APP2.2 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给机构、4机构给我（含我））
	 */
	public Integer getDepositState() {
		return depositState;
	}

	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}

	public Float getMaxallmoney() {
		return maxallmoney;
	}

	public void setMaxallmoney(Float maxallmoney) {
		this.maxallmoney = maxallmoney;
	}

	public Float getMinallmoney() {
		return minallmoney;
	}

	public void setMinallmoney(Float minallmoney) {
		this.minallmoney = minallmoney;
	}
	
	
}