package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Appimage;

public interface PicmanageService {
	List<Appimage> getPicList(String code);
	
	void deleteAll(String code);
	
	void addAll(List<Appimage> appimages);
	
	/**
	 * 根据编号获取图片[可能会同时获取多个]
	 * @author WKX
	 * @param code
	 * @since 2016年1月7日 下午3:36:40
	 */
	public List<Appimage> getInCode(String[] code);
	
	/**
	 * 根据编号获取图片[可能会同时获取多个]
	 * @author WKX
	 * @param code 类型
	 * @param sort 根据sort获取确切的日历
	 * @since 2016年8月30日 
	 */
	public List<Appimage> getNewInCode(String code,String sort);
	
	/**
	 * 根据参数选择，返回的图片集合
	 * @param code 参数，判断选择的是机构端，还是企业端
	 * @return 返回查询到的集合
	 */
	List<Appimage> getXzPiclist(String code);
}