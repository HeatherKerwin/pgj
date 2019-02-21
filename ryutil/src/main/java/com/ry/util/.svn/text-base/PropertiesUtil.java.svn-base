/**
* @Title: PropertiesUtil.java
* @Package com.ry.util
* @Description: TODO
* @author Ry-wk
* @date 2015年10月30日
* @version V1.0
*/
package com.ry.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: PropertiesUtil
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月30日
 *
 */
public class PropertiesUtil {
	
	//路径
	public static String wwwroot;
	
	private static Properties config;
	
	/**
	 * @Title: loadProperties
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param path
	 * @param @return
	 * @param @throws Exception
	 * @param @throws IOException 参数
	 * @return Properties 返回类型
	 * @throws
	 */
	public static Properties loadProperties(String path) throws Exception, IOException{
		if(null == path)throw new Exception("配置文件路径读取错误!");
		File file = new File(path);
		if(!file.isFile() || !file.canRead()){
			throw new Exception(path+",文件不可读或者不是一个文件!");
		}
		FileInputStream inputFile = new FileInputStream(file);
		Properties p = new Properties();
		p.load(inputFile);
		inputFile.close();
		return p;
	}
	
	/**
	 * 加载config配置文件
	 * @author WKX
	 */
	public static Properties loadConfig(){
		if (config != null)return config;
		try {
			config = loadProperties(wwwroot + "WEB-INF"+File.separator+"classes"+File.separator+"config.properties");
			return config;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取config配置文件参数
	 * @param key
	 * @author WKX
	 */
	public static String getConfigPropertiesValue(String key) {
		return loadConfig().getProperty(key);
	}

	/**
	 * 获取config配置文件参数[为空则给默认值]
	 * @param key
	 * @param defaultValue
	 * @author WKX
	 */
	public static String getConfigPropertiesValue(String key, String defaultValue) {
		return loadConfig().getProperty(key, defaultValue);
	}
	
	/**
	 * 读取ryutil配置文件 的配置参数
	 * @author WKX
	 * @throws Exception
	 * @throws IOException
	 * @since 2016年6月24日 上午10:23:06
	 */
	public static String getRyUtilValue(String key,String defaultValue){
		Properties p = null;
		try {
			InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties");
			p = new Properties();
			p.load(in);
			in.close();
			if(p!=null){
				return p.getProperty(key, defaultValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	public static void main(String[] args) {
		
	}
}