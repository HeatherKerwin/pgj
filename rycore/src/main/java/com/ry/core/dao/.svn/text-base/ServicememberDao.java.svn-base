package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Servicemember;
import com.ry.util.page.PageResults;

public interface ServicememberDao {
	/**
	 * 
	* @Title: save
	* @Description: 保存
	* @param @param s    参数
	* @return void    返回类型
	* @throws
	 */
	public Integer saveServicemember(Servicemember s);
	
	/**
	 * 
	* @Title: getById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param id
	* @param @return    参数
	* @return Servicemember    返回类型
	* @throws
	 */
	public Servicemember getById(Integer id);
	/**
	 * 
	* @Title: updateServicemember
	* @Description: 更新
	* @param @param s
	* @param @return    参数
	* @return Integer    返回类型
	* @throws
	 */
	public void updateServicemember(Servicemember s);
	/**
	 * 
	* @Title: deleteServicemembe
	* @Description:  删除
	* @param @param id    参数
	* @return void    返回类型
	* @throws
	 */
	public void deleteServicemembe(Integer id);
	
	/**
	 * 
	* @Title: pageList
	* @Description:分页查询
	* @param @param sm
	* @param @param pageNo
	* @param @param pageSize
	* @param @return    参数
	* @return PageResults    返回类型
	* @throws
	 */
	PageResults<Servicemember> pageList(Servicemember sm,int pageNo, int pageSize);
	
	/**
	 * 
	* @Title: saleCountData
	* @Description:  销售业绩统计
	* @param @return    参数
	* @return List    返回类型
	* @throws
	 */
	List saleCountData(String nums);
	
	/**
	 * @author MH
	 * 		  统计 推荐人 昨天17:30 以后 到今天 17:30 之间一共推荐了多少人
	 * 		  每周周一统计上周推荐了多少人
	 * @param date1  昨天时间
	 * @param date2 今天时间
	 * @param weekendtime week 一周的结束时间
	 * @param weekbegtime 一周的开始时间
	 * @param year 本月1号时，按年查询的
	 * @param month 本月1号时，按月查询的
	 * @param num 推荐号
	 */
	public List<Map<String,Object>> recommendEmail(String date1,String date2,String num,String weekbegtime,String weekendtime,  int year, int month);
	
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
	
	/**
	 * @author MH
	 * @param weekendtime week 一周的结束时间
	 * @param weekbegtime 一周的开始时间
	 * @param year 本月1号时，按年查询的
	 * @param month 本月1号时，按月查询的
	 * @param num 销售的人员编号
 	 */
	public List<Map<String,Object>> YearWeekDayemailSaleDate(String weekendtime, String weekbegtime,int year ,int month,String num);
}
