package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 开具发票
 * @author WKX
 */
@Entity(name="invoice")
public class Invoice extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String titleType;//发票抬头类型：个人、企业
	private String title;//发票抬头
	private String invoiceType;//发票类型：普通发票
	private String content;//发票内容：服务费
	private String sendWay;//寄送方式（用户选择的寄送方式）
	private String name;//收件人
	private String phone;//联系方式
	private Integer expressWay;//快递方式（根据用户选择的方式，我方发货人员选择快递方式：平邮0、到付1）
	private String expressCompany;//快递公司：ems
	private String expressNo;//快递单号
	private String prov;//省
	private String city;//市
	private String dist;//区
	private String address;//详细地址
	private Integer fkId;//外键（可能是  查询查复...或者关联其他  需要发票的 表）
	private String fkType;//外键的关联类型（区分是 查询查复 还是  其他订单） 1 ：机构  2：查询查复
	private Date createTime;//创建时间
	private Integer state=1;//发送状态（已发送0、未发送1）
	
	/**
	 * 发票抬头类型：个人、企业
	 * @author WKX
	 * @since 2016年3月2日 下午7:27:54
	 */
	@Column(name="title_type",length=20)
	public String getTitleType() {
		return titleType;
	}
	
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	/**
	 * 发票抬头
	 * @author WKX
	 * @since 2016年3月2日 下午7:27:54
	 */
	@Column(length=100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 发票类型：普通发票
	 * @author WKX
	 * @since 2016年3月2日 下午7:30:16
	 */
	@Column(name="invoice_type",length=20)
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * 发票内容：服务费
	 * @author WKX
	 * @since 2016年3月2日 下午7:30:16
	 */
	@Column(length=100)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 寄送方式（用户选择的寄送方式）
	 * @author WKX
	 * @since 2016年3月2日 下午7:32:29
	 */
	@Column(name="send_way",length=50)
	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	/**
	 * 收件人
	 * @author WKX
	 * @since 2016年3月2日 下午7:33:12
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 联系方式
	 * @author WKX
	 * @since 2016年3月2日 下午7:33:26
	 */
	@Column(length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 快递方式（根据用户选择的方式，我方发货人员选择快递方式：平邮0、到付1）
	 * @author WKX
	 * @since 2016年3月2日 下午7:33:49
	 */
	@Column(name="express_way")
	public Integer getExpressWay() {
		return expressWay;
	}

	public void setExpressWay(Integer expressWay) {
		this.expressWay = expressWay;
	}

	/**
	 * 快递公司：ems
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(name="express_company",length=20)
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	/**
	 * 快递单号
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(name="express_no",length=20)
	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	/**
	 * 省
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(length=20)
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * 市
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(length=20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 区
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(length=20)
	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	/**
	 * 详细地址
	 * @author WKX
	 * @since 2016年3月2日 下午7:34:33
	 */
	@Column(length=20)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 外键（可能是  查询查复...或者关联其他  需要发票的 表）
	 * @author WKX
	 * @since 2016年3月2日 下午7:37:08
	 */
	@Column(name="fk_id")
	public Integer getFkId() {
		return fkId;
	}

	public void setFkId(Integer fkId) {
		this.fkId = fkId;
	}

	/**
	 * 外键的关联类型（区分是 查询查复 还是  其他订单）
	 * @author WKX
	 * @since 2016年3月2日 下午7:38:19
	 */
	@Column(name="fk_type",length=20)
	public String getFkType() {
		return fkType;
	}

	public void setFkType(String fkType) {
		this.fkType = fkType;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 发送状态（已发送0、未发送1）
	 * @author WKX
	 * @since 2016年3月2日 下午7:39:28
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}