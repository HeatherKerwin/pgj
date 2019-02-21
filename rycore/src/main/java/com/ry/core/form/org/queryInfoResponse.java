/**
 * 
 */
package com.ry.core.form.org;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.City;
import com.ry.core.entity.Member;
import com.ry.core.entity.Servicemember;
import com.ry.util.datatable.BaseResponseData;

/**
 * 名称: ListRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午2:03:19<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class queryInfoResponse extends BaseResponseData{
	
	private List<Member> menber;
	private Map<String,Object> orgInfo;
	private List<Map<String,Object>> orgCity;
	private List<City> cityList;
	private Map<String,Object> orgpic;//承诺函
	private List<Servicemember> servicemember;//销售人员集合
	
	
	public Map<String, Object> getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(Map<String, Object> orgInfo) {
		this.orgInfo = orgInfo;
	}
	public Map<String, Object> getOrgpic() {
		return orgpic;
	}
	public void setOrgpic(Map<String, Object> orgpic) {
		this.orgpic = orgpic;
	}
	/**
	 * @return the menber
	 */
	public List<Member> getMenber() {
		return menber;
	}
	/**
	 * @param menber the menber to set
	 */
	public void setMenber(List<Member> menber) {
		this.menber = menber;
	}
	
	/**
	 * @return the orgCity
	 */
	public List<Map<String, Object>> getOrgCity() {
		return orgCity;
	}
	/**
	 * @param orgCity the orgCity to set
	 */
	public void setOrgCity(List<Map<String, Object>> orgCity) {
		this.orgCity = orgCity;
	}
	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	
	public List<Servicemember> getServicemember() {
		return servicemember;
	}
	
	public void setServicemember(List<Servicemember> servicemember) {
		this.servicemember = servicemember;
	}

	
	
}
