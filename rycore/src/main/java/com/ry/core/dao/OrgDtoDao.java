/**
 * 
 */
package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.form.org.ListRequest;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface OrgDtoDao {
	
	PageResults<Map<String,Object>> getPageList(ListRequest list);

	/**
	 * 根据主键获取认证信息（已认证）
	 * @author WKX
	 * @param id
	 * @since 2016年3月9日 下午4:22:47
	 */
	public List<Map<String,Object>> getInfoById(Integer id);
	
	/**
	 * 分页查询企业认证信息列表
	 * @author GXW
	 * @param list 查询条件
	 * @since 2016年6月15日
	 */
	PageResults<Map<String,Object>> getMemberPageList(ListRequest list);
	
	/**
	 * 获取通过条件导出的表格信息
	 */
	public List<Map<String,Object>> getByObj(ListRequest list);
}