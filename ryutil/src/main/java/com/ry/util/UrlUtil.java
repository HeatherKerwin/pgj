package com.ry.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlUtil {
	
	/**
	 * 配置gb2312解析
	 * @author WKX
	 * @since 2016年9月20日 下午5:07:47
	 */
	private static List<String> other = null;
	private static synchronized List<String> getConfig(){
		if(other!=null){
			return other;
		}else{
			other = new ArrayList<String>();
			other.add("ie=gbk");//已经配置了编码的
			other.add("http://www.sogou.com/web");
			
			return other;
		}
	}
	
	/**
	 * 解析URL 获取请求参数
	 * @author WKX
	 * @param URL
	 * @since 2016年5月3日 上午11:16:31
	 */
	public static Map<String, String> URLRequest(String URL) {
		String enc = "utf-8";
		try {
			if(URL.indexOf("ie=gbk")>-1)enc = "gbk";
			for(String temp:getConfig()){
				if(URL.indexOf(temp)>-1)enc = "gb2312";
			}
			
			try {
				URL = java.net.URLDecoder.decode(URL,enc);
			} catch (java.lang.IllegalArgumentException e) {
				URL = java.net.URLDecoder.decode(URL.replaceAll("%", "%25"),enc);
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		//每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			//解析出键值
			if (arrSplitEqual.length > 1) {
				mapRequest.put(arrSplitEqual[0], removeFourChar(arrSplitEqual[1]));// 正确解析
			} else {
				if (arrSplitEqual[0] != "") {//只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * @author WKX
	 * @param strURL
	 * @since 2016年5月3日 上午11:18:01
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 2) {//如果url异常（含两个则取第二个）
				if (arrSplit[2] != null) {
					strAllParam = arrSplit[2];
				}
			}else if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}
	
	/**
	 * 是否是中文
	 * @author WKX
	 * @param c
	 * @since 2016年5月4日 上午9:50:06
	 */
	public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
	
	/**
	 * 是否是乱码
	 * @author WKX
	 * @param strName
	 * @since 2016年5月4日 上午9:50:16
	 */
	public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 * 替换掉四字节 中文
	 * @author WKX
	 * @param content
	 * @since 2016年5月3日 下午6:02:33
	 */
	public static String removeFourChar(String content) {
        char[] conbyte = content.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < conbyte.length; i++) {
        	char tt = conbyte[i];
        	if(!isMessyCode(String.valueOf(tt))){
        		sb.append(tt);
        	}
        }
        return sb.toString();
    }
	
	public static void main(String[] args) throws ParseException {
		String d = "江苏村镇银行的ࠄ😁兑汇票";
		System.err.println(removeFourChar(d));
		
//		System.err.println("苏".getBytes().length);
//		System.err.println("😁".getBytes().length);
//		System.err.println("ࠄ".getBytes().length);
		System.err.println(isMessyCode("ࠄ"));
		System.err.println(isMessyCode("😁"));
		System.err.println(isMessyCode("汇"));
		System.err.println("ࠄ".getBytes().length);
		System.err.println("汇".getBytes().length);
		
		String t = "2016-04-27";
		Date temp = DateUtil.parser(t, DateUtil.FORMART3);
		System.err.println(temp.getTime());
	}
}