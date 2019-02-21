package com.ry.ryapp.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author GJJ
 * @date 2015年12月31日
 * json转化util类,支持list、map转换成json字符串
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {
		System.out.println(mapper);
	}
	
	/**
	 * @author GJJ
	 * @param map
	 * @return map型json字符串 
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
}
