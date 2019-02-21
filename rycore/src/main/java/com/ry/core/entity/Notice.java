package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 节假日（过去针对假期贴现的提示）现在询价报价等都相关
 * @author RY
 * @date 2016年1月4日
 */
@Entity(name="notice")
public class Notice extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	//节日名称
	private String name;

	//APPCAN项目里显示的标题内容
	private String content;
	
	//编号:[ERROR]额度受限;[OFFDAY]非工作日;[FESTIVAL]节日
	private String code;
	
	//开始日期
	private Date startDate;
	
	//结束日期
	private Date endDate;
	
	//弹框内容
	private String alert;
	
	//备注
	private String remark;
	
	//标示这是哪一年（针对节日：询价上面的调整天数）例如：2016
	private String yearToken;
	
	/**
	 * 节日名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 标示这是哪一年（针对节日：询价上面的调整天数）例如：2016
	 * @author WKX
	 * @since 2016年4月11日 下午1:18:28
	 */
	@Column(name="year_token")
	public String getYearToken() {
		return yearToken;
	}

	public void setYearToken(String yearToken) {
		this.yearToken = yearToken;
	}
}