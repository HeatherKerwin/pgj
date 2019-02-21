package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分收支明细表
 * @author ZWD
 */
@Entity(name="integral_trading_detail")
public class IntegraltradingDetail extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tradingTime;//收支时间
	private String title;//标题
	private Integer state;//0减 1加
	private Integer num;//积分数量
	private Integer memberId;//用户id
	private Integer integralTotal;//用户的积分总额度
	
	@Column(name="trading_time")
	public Date getTradingTime() {
		return tradingTime;
	}
	public void setTradingTime(Date tradingTime) {
		this.tradingTime = tradingTime;
	}
	
	@Column(length=20)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(length=20)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(length=20)
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Column(length=10)
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@Column(name = "integral_total",length=10)
	public Integer getIntegralTotal() {
		return integralTotal;
	}
	public void setIntegralTotal(Integer integralTotal) {
		this.integralTotal = integralTotal;
	}
	
}
