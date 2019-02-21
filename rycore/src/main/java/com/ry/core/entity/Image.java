package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 机构承诺函（审核主表）
 * @author WKX
 */
@Entity(name="image")
public class Image extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer fkId;//外键（可能是  查询查复...或者关联其他  需要发票的 表）
	private String fkType;//外键的关联类型（1：OrgImage机构认证承诺函其他、2：验票失败DistributeOrder、3：其他）

	private Date createTime;//创建时间（申请时间）
	private String imgPath;//图片路径
	

	/**
	 * 外键（可能是：  机构认证承诺函1、其他2）
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
	 * 外键的关联类型（ 机构认证承诺函1、其他2）
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

	/**
	 * 创建时间（申请时间）
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 图片路径
	 * @author WKX
	 */
	@Column(name="img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}