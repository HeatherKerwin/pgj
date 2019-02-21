package com.ry.core.service;
import com.ry.core.entity.Variables;
import com.ry.util.page.PageResults;


public interface VariablesService {
	public PageResults<Variables> getPageList(Integer currentPage,Integer pageSize);
	public void saveInfo(Variables v)throws Exception;
	public Variables get(Integer id);
	
	/**
	 * 根据编号获取系统参数配置
	 * @param code 编号
	 * @param default_ 默认值
	 * @author WKX
	 */
	public String getByCode(String code,String default_);
}