/**
* @Title: StringUtil.java
* @Package com.ry.util
* @Description: TODO
* @author Ry-wk
* @date 2016年11月23日
* @version V1.0
*/
package com.ry.util;

/**
 * @ClassName: StringUtil
 * @Description: TODO
 * @author Ry-wk
 * @date 2016年11月23日
 *
 */
public class StringUtil {

	/**
	 * 
	* @Title: processRedirectUrl
	* @Description: 处理反向代理redirect URL
	* @param domain
	* @param @param url
	* @param @return    参数
	* @return String    返回类型
	* @throws
	* @author Ry-wk
	* @date 2016年11月23日
	 */
	public static String processRedirectUrl(String domain,String url){
		return "redirect:" + domain +url;
	}
	
	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args    参数
	 * @return void    返回类型
	 * @throws
	 * @author Ry-wk
	 * @date 2016年11月23日
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @param mobile
	 * @return
	 */
	public static String hideMobile(String mobile){
		if(mobile!=null && mobile.length()<=11){
	        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	    }
		return "";
	}
}