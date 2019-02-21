package com.ry.core.dao;
import java.util.List;

import com.ry.core.entity.Variables;
import com.ry.util.page.PageResults;


public interface VariablesDao {
	public PageResults<Variables> getPageList(Integer currentPage,Integer pageSize);
	public void saveInfo(Variables v)throws Exception;
	public Variables get(Integer id);
	
	/**
	 * 根据编号获取系统参数配置
	 * @author WKX
	 * @param code
	 * @since 2016年3月28日 下午5:04:07
	 */
	public List<Variables> getByCode(String code);
	
	/**
	 * 通过pricetypeid获取Variables(这是预警机构里新增的内容)
	 * @author ZY
	 * @param id
	 * @since 2016年8月23日 上午11:10:24
	 */
	public Variables getpingtai(Integer id);
}