/**
* @Title: ServicememberService.java
* @Package com.ry.core.service
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Servicemember;
import com.ry.util.page.PageResults;

/**
 * @ClassName: ServicememberService
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
public interface ServicememberService {
	/**
	 * 
	* @Title: saveServicemember
	* @Description: 插入数据
	* @param @param s
	* @param @return    参数
	* @return Integer    返回类型
	* @throws
	 */
	
	public Integer saveServicemember(Servicemember s);
	
	/**
	 * 
	* @Title: getServicememberById
	* @Description: 主键获取
	* @param @param id
	* @param @return    参数
	* @return Servicemember    返回类型
	* @throws
	 */
	public Servicemember getServicememberById(Integer id);
	/**
	 * 
	* @Title: removeServicemember
	* @Description: 根据主键删除
	* @param @param id    参数
	* @return void    返回类型
	* @throws
	 */
	public void removeServicemember(Integer id);
	/**
	 * 
	* @Title: modifyServicemember
	* @Description: 更新
	* @param @param s    参数
	* @return void    返回类型
	* @throws
	 */
	public void modifyServicemember(Servicemember s);
	/**
	 * 
	* @Title: getPageList
	* @Description: 分页查询获取Servicemember
	* @param @param sm
	* @param @param pageNo
	* @param @param pageSize
	* @param @return    参数
	* @return PageResults<Servicemember>    返回类型
	* @throws
	 */
	public PageResults<Servicemember> getPageList(Servicemember sm,int pageNo, int pageSize);
	
	/**
	 * 
	* @Title: emailSaleData
	* @Description: 发送销售统计数据邮件
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void emailSaleData();
	
	public Servicemember getServicemember(String servicenumber);
	
	/**
	 * 
	* @Title: saleLivenessData
	* @Description: 查询活跃度
	* @param @param type
	* @param @return    参数
	* @return List<Servicemember>    返回类型
	* @throws
	 */
	List saleLivenessData(String type);
	/**
	 * 
	* @Title: saleCustomCount
	* @Description: 销售客户数据统计
	* @param @param type
	* @param @return    参数
	* @return List    返回类型
	* @throws
	 */
	List saleCustomCount(String type);
	
	public List<Servicemember> getList(Servicemember sm);
}
