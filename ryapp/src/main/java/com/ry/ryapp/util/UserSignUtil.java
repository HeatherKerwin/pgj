package com.ry.ryapp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserSignUtil {
	
	//APP缓存数据
	private static Map<String,Object> index_params = null;
	
	/**
	 * 获取缓存中的值[是否签到]
	 * @author MH
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
	 * @author MH
	 * @param params 含有效期标示[日期yyyy-MM-dd]
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
			
			if(index_params.get("sign")==null)index_params.put("sign", temp.get("sign"));//banner图（企业端）
		}else {
			if(params.get("date")==null)params.put("date", new Date());//设置时间
			index_params = new HashMap<String, Object>();
			index_params = params;
		}
		return index_params;
	}
	
}
