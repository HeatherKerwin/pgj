/**
* @Title: MemberForm.java
* @Package com.ry.core.form
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.Member;

/**
 * @ClassName: MemberForm
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
public class MemberForm extends Member{
	
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 7084198427057134122L;
	private Date beginRegDate;
	private Date endRegDate;
	private String code;	
	private String url;
	
	public Date getBeginRegDate() {
		return beginRegDate;
	}
	public void setBeginRegDate(Date beginRegDate) {
		this.beginRegDate = beginRegDate;
	}
	public Date getEndRegDate() {
		return endRegDate;
	}
	public void setEndRegDate(Date endRegDate) {
		this.endRegDate = endRegDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
