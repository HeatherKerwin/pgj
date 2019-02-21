/**
* @Title: Base64Util.java
* @Package com.ry.util
* @Description: TODO
* @author Ry-wk
* @date 2016年4月14日
* @version V1.0
*/
package com.ry.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: Base64Util
 * @Description: TODO
 * @author Ry-wk
 * @date 2016年4月14日
 *
 */
public class Base64Util {
	
	/**         
     * BASE64加密   
     * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return new BASE64Encoder().encodeBuffer(key);               
    }       
       
    /**    
     * BASE64解密   
     * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String decryptBASE64(String key) throws Exception {               
        return new String(new BASE64Decoder().decodeBuffer(key));               
    } 

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args    参数
	 * @return void    返回类型
	 * @throws
	 * @author Ry-wk
	 * @date 2016年4月14日
	 */
	public static void main(String[] args) {
		try {
			String msg = "160414utiexian";
			String data = encryptBASE64(msg.getBytes());     
			System.out.println("加密后："+data.trim());     
			     
//        byte[] byteArray = Base64Util.decryptBASE64(data);     
			System.out.println("解密后："+decryptBASE64(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
	}

}
