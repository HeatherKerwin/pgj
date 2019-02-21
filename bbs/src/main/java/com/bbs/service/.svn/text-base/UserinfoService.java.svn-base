package com.bbs.service;

import java.util.List;

import com.bbs.model.Userinfo;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface UserinfoService {
	
	Userinfo getUserinfo(Long uid);
	
	List<Userinfo> getUserinfoList(QueryParam queryParam);
	
	Page<Userinfo> getPageList(QueryParam queryParam);
	
	boolean save(Long uid);
	
	boolean update(Long uid, String nickName, String jobs, String webSite, String github, String weibo, String location, String signature, String instructions,String name,String company,String phone,String wechat,String qq);
	
	boolean delete(Long uid);

	/**
	 * 用户注册生成基本信息
	 * @author WKX
	 * @param uid
	 * @param phone
	 * @since 2016年11月11日 下午3:04:33
	 */
	public boolean save(Long uid,String phone);
	
	/**
	 * 更新用户资料
	 * @author WKX
	 * @param userinfo
	 * @since 2016年11月16日 下午5:21:43
	 */
	public boolean update(Userinfo userinfo);
}