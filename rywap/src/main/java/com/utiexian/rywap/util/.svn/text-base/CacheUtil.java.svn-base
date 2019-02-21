package com.utiexian.rywap.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ry.util.DateUtil;

/**
 * 模拟缓存（有效期一天）
 * [应用目录]
 * 1.存储APP首页获取的数据[有效期一天]总贴现金额、报价、指数、日历图
 * @author WKX
 */
public class CacheUtil {
	
	//APP缓存数据
	private static Map<String,Object> index_params = null;
	
	/**
	 * 获取缓存中的值
	 * @author WKX
	 */
	public static synchronized Object getIndexParams(String key){
		if(index_params!=null && StringUtils.isNotBlank(key)){
			if(index_params.get(key + "_DATE")!=null){
				Date tmp = (Date)index_params.get(key + "_DATE");
				String tag1 = DateUtil.formart(tmp,"yyyy-MM-dd");
				String tag2 = DateUtil.formart(new Date(),"yyyy-MM-dd");
				if(!tag1.equals(tag2)){//已过期
					return null;
				}else if(index_params.get(key)!=null){//未过期
					return index_params.get(key);
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
	 * 缓存值（含时间标识）
	 * @author WKX
	 * @param key 标识
	 * @param value 数值
	 */
	public static synchronized void setIndexParams(String key,Object value){
		if(StringUtils.isBlank(key) || value==null){
			return;
		}
		if(index_params==null){
			index_params = new HashMap<String, Object>();
		}
		index_params.put(key, value);
		index_params.put(key + "_DATE", new Date());
	}
}