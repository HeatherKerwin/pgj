package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 维度表（用来派单的维度）APP2.2
 * @author WKX
 */
@Entity(name="dimension")
public class Dimension extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orgId;//机构主键
	private String mouth;//年月（2016-05）
	private Float finishCount;//交易单数（评分）
	private Float refusedCount;//拒绝单数（评分）
	private Float finishTime;//总共完成交易时间（评分）
	private Float comments;//评价（评分）
	
	/**
	 * 机构主键
	 * @author WKX
	 * @since 2016年5月11日 上午11:23:53
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}
	
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 年月
	 * @author WKX
	 * @since 2016年5月11日 上午11:24:32
	 */
	public String getMouth() {
		return mouth;
	}
	
	public void setMouth(String mouth) {
		this.mouth = mouth;
	}
	
	/**
	 * 交易单数
	 * @author WKX
	 * @since 2016年5月11日 上午11:24:48
	 */
	@Column(name="finish_count")
	public Float getFinishCount() {
		return finishCount;
	}
	
	public void setFinishCount(Float finishCount) {
		this.finishCount = finishCount;
	}
	
	/**
	 * 交易单数
	 * @author WKX
	 * @since 2016年5月11日 上午11:24:48
	 */
	@Column(name="refused_count")
	public Float getRefusedCount() {
		return refusedCount;
	}
	
	public void setRefusedCount(Float refusedCount) {
		this.refusedCount = refusedCount;
	}
	
	/**
	 * 总共完成交易时间
	 * @author WKX
	 * @since 2016年5月11日 上午11:26:07
	 */
	@Column(name="finish_time")
	public Float getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Float finishTime) {
		this.finishTime = finishTime;
	}
	
	/**
	 * 评价（汇总评价的评分）
	 * @author WKX
	 * @since 2016年5月11日 上午11:26:59
	 */
	public Float getComments() {
		return comments;
	}
	
	public void setComments(Float comments) {
		this.comments = comments;
	}
}