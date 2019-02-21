package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Remarks;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

public interface RemarksService {

	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年8月13日 下午3:35:24
	 */
	public Remarks getById(Integer id);
	
	/**
	 * 新增
	 * @author KHC
	 * @param remarks
	 * @since 2016年8月13日 下午3:45:24
	 */
	public void saveModel(Remarks remarks);
	
	/**
	 * 根据主键获取备注列表
	 * @author KHC
	 * @param id
	 * @since 2016年8月13日 下午4:45:24
	 */
	public List<Map<String, Object>>  getInfoById(Integer id);
	
	/**
	 * 分页获取备注列表
	 * @author KHC
	 * @param id
	 * @since 2016年8月13日 下午5:45:24
	 */
	public PageResults<Map<String, Object>> getPageInfoById(BasePageRequestData bp,Integer fkid,Integer fkType);
	
	/**
	 * 根据外键和类型查询备注
	 * @author ZY
	 * @param fkId
	 * @param type
	 * @since 2017年1月10日 下午3:17:11
	 */
	public PageResults<Map<String, Object>>  listByTypeAndFkid(Integer currentPage,Integer pageSize,Integer fkId,Integer type);
	
	/**
	 * 根据订单ID获取备注列表
	 * @author KHC
	 * @param fkid
	 * @since 2017年1月5日 下午6:53:59
	 */
	public List<Map<String, Object>> getListByFkId(Integer fkid,Integer type);
	
	/**
	 * 根据外键获取订单备注列表
	 * @author KHC
	 * @param bp
	 * @param fkId
	 * @param type
	 * @since 2017年1月10日 下午4:44:45
	 */
	public PageResults<Map<String, Object>> getPageList(BasePageRequestData bp,Integer fkId,Integer type);
}
