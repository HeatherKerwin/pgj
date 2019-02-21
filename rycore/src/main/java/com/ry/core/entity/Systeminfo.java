package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name="systeminfo")
public class Systeminfo extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer memberId;//会员主键
	
	private Type type;//对应消息类型（包含查询查复、系统、六种订单）
	
	private String alert;//推送通知[标题或内容]
	
	private String content;//内容[其他消息时使用、订单消息值填写订单主键]
	
	private Integer discountrecordId;//贴现主键[订单]（新：APP2.1根据类型查不同的订单[企业订单主键、机构订单主键]）
	
	private ReadState readState = ReadState.UNREAD;//消息读取状态[UNREAD未读,READ已读]
	
	private Date createTime;//创建时间
	
	private String url;//系统消息跳转页面（SYSTEM）
	
	/**
	 * 用户主键
	 * @author WKX
	 * @since 2016年1月6日 下午2:33:58
	 */
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 消息状态
	 * @author WKX
	 * @since 2016年1月6日 下午2:19:35
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="type_")
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * 推送通知[标题或内容]
	 * @author WKX
	 * @since 2016年1月6日 下午1:16:12
	 */
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	
	/**
	 * 消息内容[订单不需要]
	 * @author WKX
	 * @since 2016年1月6日 下午2:18:56
	 */
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 贴现主键[订单]
	 * @author WKX
	 * @since 2016年1月6日 下午2:29:05
	 */
	@Column(name="discountrecord_id")
	public Integer getDiscountrecordId() {
		return discountrecordId;
	}

	public void setDiscountrecordId(Integer discountrecordId) {
		this.discountrecordId = discountrecordId;
	}

	/**
	 * 消息读取状态[UNREAD未读,READ已读]
	 * @author WKX
	 */
	@Enumerated(EnumType.STRING)
	public ReadState getReadState() {
		return readState;
	}
	
	public void setReadState(ReadState readState) {
		this.readState = readState;
	}
	
	/**
	 * 操作时间
	 * @author WKX
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 系统消息跳转页面（SYSTEM）
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 消息类型
	 * @author WKX
	 */
	public static enum Type{
		DISCOUNTRECORD("企业订单（银票）"),DISTRIBUTEORDER("机构订单（银票）"),DISCOUNTRECORDSP("企业订单（商票）"),DISTRIBUTEORDERSP("机构订单（商票）"),DISCOUNTRECORDPL("企业订单（批量）"),DISTRIBUTEORDERPL("机构订单（批量）"),DISPATCH("待报价订单"),INQUIRYREPLY("查询查复"),PREFERENTIALINFO("优惠消息"),SYSTEM("系统消息"),OTHER("其他"),
		DISCOUNTRECORDUNION("企业跨平台"),DISTRIBUTEORDERUNION("机构跨平台");
		public String name;
		private String value;
		Type(String name){
			this.name = name;
			this.value = this.name();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 消息是否读过[枚举]
	 * @author WKX
	 */
	public static enum ReadState{
		UNREAD("未读"),READ("已读");
		public String name;
		private String value;
		ReadState(String name){
			this.name = name;
			this.value = this.name();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
