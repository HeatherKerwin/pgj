package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="hongbaoActivity")
public class HongbaoActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String name;//活动名称
	private Integer totalprice;//红包总金额
	private Integer totalnum;//红包个数
	private String header;//红包发起人	
	private Date startdate;//活动开始时间
	private Date enddate;//活动结束时间
	private Integer sharedays;//分享有效期限
	private Integer usedays;//使用有效期限
	private Integer price;//红包金额
	private Integer maxprice;// 
	private Integer type;//固定金额为0 随机金额为 1
	private Long limitprice;//额度
	private String lianjie;//红包链接
	private Date createtime;//创建时间
	private Date updatetime;//修改时间
	private Integer flag;//状态 0 已结束 1 进行中 2 未开始
	private Integer status;//0 未删除 1已删除
	
	private Integer validenum;//有效红包个数
	private Integer validetotalprice;//有效红包金额
	private Integer recivenum;//领取红包个数
	private Integer recivetotalprice;//领取红包金额
	private Double transrate;//红包转换率
	private Integer tagId;//@WKX 红包分类[字典表]
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="totalprice")
	public Integer getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}
	@Column(name="totalnum")
	public Integer getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
	@Column(name="header")
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	@Column(name="startdate")
	public Date getStartdate() {
		return startdate;
	}	
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@Column(name="enddate")
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}			
	
	@Column(name="sharedays")
	public Integer getSharedays() {
		return sharedays;
	}
	public void setSharedays(Integer sharedays) {
		this.sharedays = sharedays;
	}
	@Column(name="usedays")
	public Integer getUsedays() {
		return usedays;
	}
	public void setUsedays(Integer usedays) {
		this.usedays = usedays;
	}
	@Column(name="price")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="lianjie")
	public String getLianjie() {
		return lianjie;
	}
	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}
	@Column(name="createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name="updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	@Column(name="flag")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="limitprice")
	public Long getLimitprice() {
		return limitprice;
	}
	public void setLimitprice(Long limitprice) {
		this.limitprice = limitprice;
	}
	@Column(name="maxprice")
	public Integer getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(Integer maxprice) {
		this.maxprice = maxprice;
	}
	
	@Column(name="validenum")
	public Integer getValidenum() {
		return validenum;
	}
	public void setValidenum(Integer validenum) {
		this.validenum = validenum;
	}
	@Column(name="validetotalprice")
	public Integer getValidetotalprice() {
		return validetotalprice;
	}
	public void setValidetotalprice(Integer validetotalprice) {
		this.validetotalprice = validetotalprice;
	}
	@Column(name="recivenum")
	public Integer getRecivenum() {
		return recivenum;
	}
	public void setRecivenum(Integer recivenum) {
		this.recivenum = recivenum;
	}
	@Column(name="recivetotalprice")
	public Integer getRecivetotalprice() {
		return recivetotalprice;
	}
	public void setRecivetotalprice(Integer recivetotalprice) {
		this.recivetotalprice = recivetotalprice;
	}
	@Column(name="transrate")
	public Double getTransrate() {
		return transrate;
	}
	public void setTransrate(Double transrate) {
		this.transrate = transrate;
	}
	
	/**
	 * 红包活动类型[字典表]
	 * @author WKX
	 * @since 2016年1月15日 下午2:16:11
	 */
	public Integer getTagId() {
		return tagId;
	}
	
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
}