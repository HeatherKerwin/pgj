package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Appimage;

public interface PicmanageDao {

	List<Appimage> getList(String code);
	
	void deleteAll(String code);
	
	void saveAll(List<Appimage> appimages);
		
	/**
	 * 根据编号获取图片
	 * @author WKX
	 * @param code
	 * @since 2016年1月7日 下午3:32:58
	 */
	public List<Appimage> getInCode(String[] code);
	
	/**
	 * 根据编号获取图片
	 * @author WKX
	 * @param code
	 * @param sort
	 * @since 2016年1月7日 下午3:32:58
	 */
	public List<Appimage> getNewInCode(String code,String sort);
	
	/**
	 * 根据参数选择，返回的图片集合
	 * @param code 参数，判断选择的是机构端，还是企业端
	 * @return 返回查询到的集合
	 */
	List<Appimage> getXzPiclist(String code);
}