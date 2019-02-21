package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 所有的订单备注
 * @author KHC
 */
@Entity(name="remarks")
public class Remarks extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String content;//备注内容
	private String location;//所在城市
	private String paperDemand;//票据需求
	private String orgPrice;//机构报价
	private String paperInfo;//票据信息
	private Integer operatorId;//填写人id（ADMIN）
	
	private Integer fkId;//外键（可能是  Discountrecord、DistributeOrder）
	private Integer fkType;//外键的关联类型（【0】Discountrecord 、【1】DiscountrecordSp、【2】DiscountrecordPl）
	
	private Date createTime;//创建日期

	private Integer state;//查询查复订单备注状态: 0待处理  1处理中  2取消查询 3已发送银行 4查询完成  5待回访 6回访中 7待持续回访
    					  //贴现订单备注状态:0.待处理，1.处理中，2.无效订单，3.订单进行中，4.订单完成 5待回访 6回访中 7待持续回访
	private Integer type;//备注类型: 0查询查复 1贴现订单
	private Date updateTime;//修改时间
	
	@Column(length=1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	
	@Column(length=1000,name="location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(length=1000,name="paper_demand")
	public String getPaperDemand() {
		return paperDemand;
	}

	public void setPaperDemand(String paperDemand) {
		this.paperDemand = paperDemand;
	}

	@Column(length=1000,name="org_price")
	public String getOrgPrice() {
		return orgPrice;
	}

	public void setOrgPrice(String orgPrice) {
		this.orgPrice = orgPrice;
	}

	@Column(length=1000,name="paper_info")
	public String getPaperInfo() {
		return paperInfo;
	}

	public void setPaperInfo(String paperInfo) {
		this.paperInfo = paperInfo;
	}
	
	@Column(name="fk_id")
	public Integer getFkId() {
		return fkId;
	}

	public void setFkId(Integer fkId) {
		this.fkId = fkId;
	}

	@Column(name="fk_type",length=1)
	public Integer getFkType() {
		return fkType;
	}

	public void setFkType(Integer fkType) {
		this.fkType = fkType;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}