package com.ry.ryapp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟缓存
 * [应用目录]
 * 1.存储APP首页获取的焦点图、广告、日历等[有效期一天]
 * @author WKX
 */
public class CacheUtil {
	
	//APP缓存数据
	private static Map<String,Object> index_params = null;
	
	/**
	 * 获取缓存中的值[APP首页：焦点图、广告图、日历图]
	 * @author WKX
	 * @since 2016年1月13日 上午11:39:23
	 */
	public static synchronized Map<String,Object> getIndexParams(String key){
		if(index_params!=null){
			if(index_params.get("date")!=null){
				Date tmp = (Date)index_params.get("date");
				String tag1 = DateUtil.formart(tmp,"yyyy-MM-dd");
				String tag2 = DateUtil.formart(new Date(),"yyyy-MM-dd");
				if(!tag1.equals(tag2)){//已过期
					return null;
				}else if(index_params.get(key)!=null){//未过期
					return index_params;
				}else{
					return null;
				}
			}else{//缓存中不存在过期标示
				return null;
			}
		}
		return index_params;
	}
	
	/**
	 * 缓存值
	 * @author WKX
	 * @param params 含有效期标示[日期yyyy-MM-dd]
	 * @since 2016年1月13日 上午11:38:42
	 */
	public static synchronized Map<String,Object> setIndexParams(Map<String,Object> params){
		if(params==null){
			index_params = new HashMap<String, Object>();
			//index_params.put("date", new Date());//设置时间
			return null;
		}
		if(index_params!=null){
			Map<String,Object> temp = index_params;
			index_params = params;
			if(params.get("date")==null)index_params.put("date", new Date());//设置时间
			
			if(index_params.get("banners")==null)index_params.put("banners", temp.get("banners"));//banner图（企业端）
			if(index_params.get("ads")==null)index_params.put("ads", temp.get("ads"));
			if(index_params.get("news")==null)index_params.put("news", temp.get("news"));
			if(index_params.get("sppl")==null)index_params.put("sppl", temp.get("sppl"));//商票批量贴现功能开关
			
			if(index_params.get("banners_org")==null)index_params.put("banners_org", temp.get("banners_org"));//banner图（机构端）
			if(index_params.get("index_ad")==null)index_params.put("index_ad", temp.get("index_ad"));//shibor图
			if(index_params.get("index_app_zx")==null)index_params.put("index_app_zx", temp.get("index_app_zx"));//机构端首页资讯（图片链接等）
		}else {
			if(params.get("date")==null)params.put("date", new Date());//设置时间
			index_params = new HashMap<String, Object>();
			index_params = params;
		}
		return index_params;
	}
}