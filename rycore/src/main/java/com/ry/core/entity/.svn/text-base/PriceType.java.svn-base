package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 提供的报价类型（主表）
 * @author WKX
 */
@Entity(name="price_type")
public class PriceType extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer type1;//类型：大票0、小票1
	private Integer type2;//类型：纸票0、电票1
	private Integer type3;//类型：50万以下0、50-100万1、100万2...
	private Integer type4;//类型：小于等于90天0、91-178天1、大于等于179天2
	private Integer type5;//@APP2.2  期限：半年期0、一年期1
	
	private String title1;//票的类型（例如：大票、小票）
	private String title2;//票的类型（例如：纸票、电票）
	private String title3;//票的天数（例如：90天以内、91-178天，178天以上）
	private String title4;//票的大小（例如：50万以下，50-100万，100万以上）
	private String day;   //天数（用于当前类型计算 每10万对应的天数：如90天以内：93,91-178天：178,178天以上：187）
	private Integer sort; //排序
	private Integer state;//状态：启用0、禁用1
	
	/**
	 * 大票0、小票1
	 * @author WKX
	 * @since 2016年3月15日 下午2:52:14
	 */
	public Integer getType1() {
		return type1;
	}

	public void setType1(Integer type1) {
		this.type1 = type1;
	}

	/**
	 * 纸票0、电票1
	 * @author WKX
	 * @since 2016年3月15日 下午2:52:31
	 */
	public Integer getType2() {
		return type2;
	}

	public void setType2(Integer type2) {
		this.type2 = type2;
	}

	public Integer getType3() {
		return type3;
	}

	public void setType3(Integer type3) {
		this.type3 = type3;
	}

	public Integer getType4() {
		return type4;
	}

	public void setType4(Integer type4) {
		this.type4 = type4;
	}
	
	public Integer getType5() {
		return type5;
	}

	public void setType5(Integer type5) {
		this.type5 = type5;
	}

	/**
	 * 限制时间范围：90天以内的
	 * @author WKX
	 * @since 2016年3月3日 上午10:26:35
	 */
	@Column(length=50)
	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	/**
	 * 票的类型（例如：买断纸票）
	 * @author WKX
	 * @since 2016年3月3日 上午10:26:35
	 */
	@Column(length=50)
	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	
	/**
	 * 票的天数（例如：90天以内、91-178天，178天以上）
	 */
	@Column(length=60)
	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	/**
	 * 票的大小（例如：50万以下，50-100万，100万以上）
	 */
	@Column(length=60)
	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	/**
	 * 天数（当前类型计算 每10万对应的天数：如90天以内：93,91-178天：178,178天以上：187）
	 */
	@Column(length=20)
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * 排序
	 * @author WKX
	 * @since 2016年3月3日 上午10:27:19
	 */
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 状态：启用0、禁用1
	 * @author WKX
	 * @since 2016年3月3日 上午10:27:19
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}