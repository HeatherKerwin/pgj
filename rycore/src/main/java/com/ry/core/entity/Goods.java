package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 积分商品表
 * @author ZWD
 */
@Entity(name = "goods")
public class Goods extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String no;//商品编号
	private String banner1;//商品图1
	private String banner2;//商品图2
	private String banner3;//商品图3
	private String goodsName;//商品名
	private Integer integral;//单价（积分）
	private Integer stock;//库存
	private Integer state;//状态0待上架1已上架2已受聘3删除
	private Integer sort;//序号
	private String goodsParam;//商品参数
	private String goodsExplain;//商品介绍
	private String explainBanner1;//介绍图片1
	private String explainBanner2;//介绍图片2
	private String explainBanner3;//介绍图片3
	private Integer hotGoods=1;//0是热门商品，1不是

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Column(length = 255)
	public String getBanner1() {
		return banner1;
	}

	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}

	@Column(length = 255)
	public String getBanner2() {
		return banner2;
	}

	public void setBanner2(String banner2) {
		this.banner2 = banner2;
	}

	@Column(length = 255)
	public String getBanner3() {
		return banner3;
	}

	public void setBanner3(String banner3) {
		this.banner3 = banner3;
	}

	@Column(name = "goods_name",length = 30)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Column(length = 10)
	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	@Column(length = 10)
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(length = 10)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(length = 10)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "goods_param", length = 500)
	public String getGoodsParam() {
		return goodsParam;
	}

	public void setGoodsParam(String goodsParam) {
		this.goodsParam = goodsParam;
	}

	@Column(name = "goods_explain", length = 500)
	public String getGoodsExplain() {
		return goodsExplain;
	}

	public void setGoodsExplain(String goodsExplain) {
		this.goodsExplain = goodsExplain;
	}

	@Column(name = "explain_banner1", length = 255)
	public String getExplainBanner1() {
		return explainBanner1;
	}

	public void setExplainBanner1(String explainBanner1) {
		this.explainBanner1 = explainBanner1;
	}

	@Column(name = "explain_banner2", length = 255)
	public String getExplainBanner2() {
		return explainBanner2;
	}

	public void setExplainBanner2(String explainBanner2) {
		this.explainBanner2 = explainBanner2;
	}

	@Column(name = "explain_banner3", length = 255)
	public String getExplainBanner3() {
		return explainBanner3;
	}

	public void setExplainBanner3(String explainBanner3) {
		this.explainBanner3 = explainBanner3;
	}

	@Column(name = "hot_goods", length = 1)
	public Integer getHotGoods() {
		return hotGoods;
	}

	public void setHotGoods(Integer hotGoods) {
		this.hotGoods = hotGoods;
	}
	
	
}
