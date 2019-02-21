package com.ry.core.service;

import com.ry.core.entity.Discussion;

public interface DiscussionService {
	
	public void saveModel(Discussion discussion);
	
	public void updateModel(Discussion discussion);
	
	public Discussion getById(Integer id);
	
	/**
	 * 根据手机号获取对象
	 * @author WKX
	 * @param phone
	 * @since 2016年10月16日 下午6:51:12
	 */
	public Discussion getByPhone(String phone);
	
	/**
	 * 批量生产座谈会入场券
	 * @author WKX
	 * @param prefix 访问前缀（请求路径）
	 * @param path 二维码存放路径（供外界访问）
	 * @param path 文件保存路径
	 * @since 2016年10月16日 下午5:37:59
	 */
	public Discussion saveAndCreateQrcode(Discussion discussion,String prefix,String savePath,String path);
}