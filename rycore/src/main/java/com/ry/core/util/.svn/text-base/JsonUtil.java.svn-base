package com.ry.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ry.core.entity.Accountrecord;

/**
 * @author GJJ
 * @date 2015年12月31日
 * json转化util类,支持list、map转换成json字符串
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	public static void main(String[] args) {
		Accountrecord a =new Accountrecord();
		a.setAccountdesc("ssssssssss");
		List<Accountrecord> l = new ArrayList<Accountrecord>();
		l.add(a);
	}
	
	
	/**
	 * @author GJJ
	 * @date 2016年1月9日
	 * @param map
	 * @return
	 * @return String
	 * @time 2016年1月9日
	 * @todo map转化为json字符串
	 */
	public static String map2json(Map<String, Object> map){
		String json = null;
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * @author GJJ
	 * @param list
	 * @return 数组型json字符串
	 */
	public static String listMap2json(List<Map<String,Object>> list){
		String json = null;
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * @author GJJ
	 * @param list
	 * @return 数组型json字符串
	 */
	public static String obj2json(Object obj){
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月11日
	 * @param json
	 * @param t
	 * @return T
	 * @time 2016年1月11日
	 * @todo json字符串转化对象
	 */
	public static <T> T json2model(String json,T t){
		try {
			t = (T) mapper.readValue(json.toString(), t.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
}
