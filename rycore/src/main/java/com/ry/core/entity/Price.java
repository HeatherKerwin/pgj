package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 提供的报价（具体到的报价和  机构）从表
 * @author WKX
 */
@Entity(name="price")
public class Price extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer priceTypeId;//提供的报价  类型（主表主键）
	private Integer orgId;//机构主键（对应机构）
	private Integer way;//报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
	private Date createTime;//报价日期
	private Integer cityId;//@APP2.2 城市主键
	
	private String guogu;//国股（以下都是  中文名称）
	private String chengshang;//@APP2.2版本以后此字段代表 小商
	private String dashang;//@APP2.2 大商(目前只有大票有大商)
	private String waizi;
	private String nongshang;
	private String nonghe;
	private String nongxin;
	private String cunzhen;

	private String guogu1;//国股（以下都是  中文名称）参数
	private String chengshang1;//@APP2.2版本以后此字段代表 小商
	private String dashang1;//@APP2.2 大商
	private String waizi1;
	private String nongshang1;
	private String nonghe1;
	private String nongxin1;
	private String cunzhen1;
	
	private String guogu2;//国股（以下都是  中文名称）每十万多少钱
	private String chengshang2;//@APP2.2版本以后此字段代表 小商
	private String dashang2;//@APP2.2 大商
	private String waizi2;
	private String nongshang2;
	private String nonghe2;
	private String nongxin2;
	private String cunzhen2;
	
	private String guogu3;//国股（以下都是  中文名称）每十万多少钱  足月票价
	private String chengshang3;//@APP2.2版本以后此字段代表 小商
	private String dashang3;//@APP2.2 大商
	private String waizi3;
	private String nongshang3;
	private String nonghe3;
	private String nongxin3;
	private String cunzhen3;
	
	/**
	 * 提供的报价  类型（主表主键）
	 * @author WKX
	 * @since 2016年3月3日 上午10:30:38
	 */
	@Column(name="price_type_id")
	public Integer getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Integer priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	/**
	 * 机构主键（对应机构）
	 * @author WKX
	 * @since 2016年3月3日 上午10:30:38
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
	 * @author WKX
	 * @since 2016年3月15日 下午3:04:17
	 */
	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}
	
	/**
	 * 报价日期
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * /@APP2.2 城市主键
	 * @author WKX
	 * @since 2016年6月3日 下午1:55:52
	 */
	@Column(name="city_id")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 国股
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getGuogu() {
		return guogu;
	}

	public void setGuogu(String guogu) {
		this.guogu = guogu;
	}

	/**
	 * 城商
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getChengshang() {
		return chengshang;
	}

	public void setChengshang(String chengshang) {
		this.chengshang = chengshang;
	}

	@Column(length=20)
	public String getDashang() {
		return dashang;
	}

	public void setDashang(String dashang) {
		this.dashang = dashang;
	}

	/**
	 * 外资
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getWaizi() {
		return waizi;
	}

	public void setWaizi(String waizi) {
		this.waizi = waizi;
	}

	/**
	 * 农商
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getNongshang() {
		return nongshang;
	}

	public void setNongshang(String nongshang) {
		this.nongshang = nongshang;
	}

	/**
	 * 农合
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getNonghe() {
		return nonghe;
	}

	public void setNonghe(String nonghe) {
		this.nonghe = nonghe;
	}

	/**
	 * 农信
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getNongxin() {
		return nongxin;
	}

	public void setNongxin(String nongxin) {
		this.nongxin = nongxin;
	}

	/**
	 * 村镇
	 * @author WKX
	 * @since 2016年3月3日 上午10:31:25
	 */
	@Column(length=20)
	public String getCunzhen() {
		return cunzhen;
	}

	public void setCunzhen(String cunzhen) {
		this.cunzhen = cunzhen;
	}

	public String getGuogu1() {
		return guogu1;
	}

	public void setGuogu1(String guogu1) {
		this.guogu1 = guogu1;
	}

	public String getChengshang1() {
		return chengshang1;
	}

	public void setChengshang1(String chengshang1) {
		this.chengshang1 = chengshang1;
	}

	public String getDashang1() {
		return dashang1;
	}

	public void setDashang1(String dashang1) {
		this.dashang1 = dashang1;
	}

	public String getWaizi1() {
		return waizi1;
	}

	public void setWaizi1(String waizi1) {
		this.waizi1 = waizi1;
	}

	public String getNongshang1() {
		return nongshang1;
	}

	public void setNongshang1(String nongshang1) {
		this.nongshang1 = nongshang1;
	}

	public String getNonghe1() {
		return nonghe1;
	}

	public void setNonghe1(String nonghe1) {
		this.nonghe1 = nonghe1;
	}

	public String getNongxin1() {
		return nongxin1;
	}

	public void setNongxin1(String nongxin1) {
		this.nongxin1 = nongxin1;
	}

	public String getCunzhen1() {
		return cunzhen1;
	}

	public void setCunzhen1(String cunzhen1) {
		this.cunzhen1 = cunzhen1;
	}

	public String getGuogu2() {
		return guogu2;
	}

	public void setGuogu2(String guogu2) {
		this.guogu2 = guogu2;
	}

	public String getChengshang2() {
		return chengshang2;
	}

	public void setChengshang2(String chengshang2) {
		this.chengshang2 = chengshang2;
	}

	public String getDashang2() {
		return dashang2;
	}

	public void setDashang2(String dashang2) {
		this.dashang2 = dashang2;
	}

	public String getWaizi2() {
		return waizi2;
	}

	public void setWaizi2(String waizi2) {
		this.waizi2 = waizi2;
	}

	public String getNongshang2() {
		return nongshang2;
	}

	public void setNongshang2(String nongshang2) {
		this.nongshang2 = nongshang2;
	}

	public String getNonghe2() {
		return nonghe2;
	}

	public void setNonghe2(String nonghe2) {
		this.nonghe2 = nonghe2;
	}

	public String getNongxin2() {
		return nongxin2;
	}

	public void setNongxin2(String nongxin2) {
		this.nongxin2 = nongxin2;
	}

	public String getCunzhen2() {
		return cunzhen2;
	}

	public void setCunzhen2(String cunzhen2) {
		this.cunzhen2 = cunzhen2;
	}

	public String getGuogu3() {
		return guogu3;
	}

	public void setGuogu3(String guogu3) {
		this.guogu3 = guogu3;
	}

	public String getChengshang3() {
		return chengshang3;
	}

	public void setChengshang3(String chengshang3) {
		this.chengshang3 = chengshang3;
	}

	public String getDashang3() {
		return dashang3;
	}

	public void setDashang3(String dashang3) {
		this.dashang3 = dashang3;
	}

	public String getWaizi3() {
		return waizi3;
	}

	public void setWaizi3(String waizi3) {
		this.waizi3 = waizi3;
	}

	public String getNongshang3() {
		return nongshang3;
	}

	public void setNongshang3(String nongshang3) {
		this.nongshang3 = nongshang3;
	}

	public String getNonghe3() {
		return nonghe3;
	}

	public void setNonghe3(String nonghe3) {
		this.nonghe3 = nonghe3;
	}

	public String getNongxin3() {
		return nongxin3;
	}

	public void setNongxin3(String nongxin3) {
		this.nongxin3 = nongxin3;
	}

	public String getCunzhen3() {
		return cunzhen3;
	}

	public void setCunzhen3(String cunzhen3) {
		this.cunzhen3 = cunzhen3;
	}
	
	
	
}