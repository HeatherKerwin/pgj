package com.utiexian.utils.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.springframework.util.StringUtils;

/**
 * @ClassName: JacksonUtil
 * @Description: JSON数据处理工具类
 */
public class JacksonUtil {
	
	public static final ObjectMapper mapper = new ObjectMapper();
	
	public static JavaType getCollectionType(Class<?> collectionClass,Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
	}

	/**
	 * @Title: strReadObj
	 * @Description: 将一个json串按照指定的JavaType转化成对象
	 * @param @param str
	 * @param @param javaType
	 * @param @return    参数
	 * @return Object    返回类型
	 * @throws
	 */
	public static Object strReadObj(String str,JavaType javaType){
		if (StringUtils.hasText(str)) {
			ObjectMapper objmapper = new ObjectMapper();
			objmapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			return strReadObj(objmapper, str, javaType);
		}
		return null;
	}
	
	/**
	 * 将对象转换为json，只对时间格式化：yyyy-MM-dd HH:mm:ss
	 * @param object
	 */
	public static String objToJson(Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * <p class="detail"> 将一个json串按照指定的JavaType转化成对象 格式化日期 </p>
	 * @param str
	 * @param javaType
	 * @param dateFormatStr 日期格式，默认“yyyy-MM-dd HH:mm:ss”要与str中的日期格式匹配
	 * @return
	 */
	public static Object strReadObj(String str,JavaType javaType, String dateFormatStr){
		if (StringUtils.hasText(str)) {
			ObjectMapper objmapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.hasText(dateFormatStr)){
				dateFormat = new SimpleDateFormat(dateFormatStr);
			}
			objmapper.setDateFormat(dateFormat);
			//忽略json中但bean中没有的属性
			objmapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			return strReadObj(objmapper, str, javaType);
		}
		return null;
	}
	
	public static Object strReadObj( ObjectMapper objmapper, String str, JavaType javaType){
		if (StringUtils.hasText(str)) {
			try {
				return objmapper.readValue(str, javaType);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 将json转换 单个Object
	 * @param str
	 * @param dateFormatStr 日期格式，默认“yyyy-MM-dd HH:mm:ss”要与str中的日期格式匹配
	 * @return map
	 */
	public static Object strReadObj(String str, Class<?> elementClasses, String dateFormatStr){
		ObjectMapper objmapper = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.hasText(dateFormatStr)){
			df = new SimpleDateFormat(dateFormatStr);
		}
		objmapper.setDateFormat(df);
		if (StringUtils.hasText(str)) {
			try {
				//忽略json中但bean中没有的属性
				objmapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
				return objmapper.readValue(str, elementClasses);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 把一个Object对象输出成一个json串
	 * @param obj JacksonUtil.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	 * @return
	 */
	public static String objWriteStr(Object obj){
		ObjectMapper objMapper = new ObjectMapper();
		if(obj!=null){
			return objWriteStr(objMapper, obj);
		}
		return null;
	}
	
	/**
	 * @param obj
	 * @param inclusion : Inclusion.NON_NULL(去除为null的属性);Inclusion.NON_EMPTY(去除为null和空字符串的属性)
	 */
	public static String objWriteStr(Object obj, Inclusion inclusion) {
		ObjectMapper objmapper = new ObjectMapper();
		if(inclusion != null){
			objmapper.setSerializationInclusion(inclusion);
		}
		if(obj!=null){
			try {
				objmapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		    	objmapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
				return objmapper.writeValueAsString(obj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 日期转换
	 * @param obj
	 * @param str
	 */
	public static String objWriteDate(Object obj, String str) {
		ObjectMapper objmapper = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.hasText(str)){
			df = new SimpleDateFormat(str);
		}
		objmapper.setDateFormat(df);
		if(obj!=null){
			try {
				return objmapper.writeValueAsString(obj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String objWriteStr(ObjectMapper objmapper, Object obj){
		if(obj!=null){
			try {
				objmapper.setSerializationInclusion(Inclusion.NON_NULL);
				return objmapper.writeValueAsString(obj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 将map转换为一个json字符串, 并且判断是否去除value为null的key
	 * @param map
	 * @param isNotNUll 为true保留为value为null的key， false不保留
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String objWriteStr(Map map, boolean isNotNUll){
		ObjectMapper objmapper = new ObjectMapper();
		objmapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, isNotNUll);
		if(map!=null){
			try {
				return objmapper.writeValueAsString(map);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 把json字符串的  null  值  换成  空字符串
	 * @param content   json字符串
	 * @return
	 */
	public static String ckeckNull(String content){
		if (StringUtils.hasText(content)) {
			return content = content.replace("null", "''");
		}else{
			return null;
		}
	}
	
	/**
	 * 将对象转换为json并把所有null替换为空字符串''
	 * @param object
	 * @return
	 */
	public static String objToJsonAndCheckNull(Object object){
		return objToJson(object).replace("null", "\"\"");
	}
	
	/**
	 * 序列化对象，并处理日期，所有null和“”均不返回
	 * @param object
	 * @param dateFormat
	 * @return
	 */
	public static String obj2Str(Object object,String dateFormat){
		ObjectMapper objMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat(dateFormat);
		objMapper.setDateFormat(df);
		objMapper.setSerializationInclusion(Inclusion.NON_EMPTY);
		String result = null;
		try {
			result = objMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args    参数
	 * @return void    返回类型
	 * @throws
	 */
	public static void main(String[] args) {

	}
}