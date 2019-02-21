
package com.ry.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ry.util.page.PageResults;


/**
 * 描述：转码
 */
public class Utility {

	static List<String[]> keyList = new ArrayList<String[]>();
	
	/**
	 * 用友（获取他平台）用户拼接member
	 * （结构：手机号#企业主键#企业用户主键。例如：lglbvagwwsl#1#91）
	 */
	private static final String SIGN = "#";
	
	private static List<String[]> getKeyList(){
		synchronized(keyList){
			if (!keyList.isEmpty())return keyList;
			
			keyList.add(new String[]{"a","l","s","k","u","y","t","c","z","p"});
			keyList.add(new String[]{"m","n","h","g","e","w","q","u","i","o"});
			keyList.add(new String[]{"e","d","c","v","b","h","j","k","l","y"});
			keyList.add(new String[]{"v","b","h","u","k","z","s","e","r","f"});
			keyList.add(new String[]{"y","u","j","m","l","s","x","c","v","g"});
			keyList.add(new String[]{"q","w","e","d","c","m","k","l","z","a"});
			keyList.add(new String[]{"f","d","s","e","q","o","k","j","h","g"});
			keyList.add(new String[]{"v","c","x","s","w","p","k","m","n","b"});
			keyList.add(new String[]{"m","n","b","v","c","u","y","r","e","w"});
			keyList.add(new String[]{"k","f","j","h","g","p","t","a","s","d"});
			keyList.add(new String[]{"k","l","a","c","o","q","m","v","t","y"});
		}
		return keyList;
	}
	
	/**
	 * 加密
	 * @param mobile 手机号
	 */
	public static final String encodeMobile(String mobile) {
		return enMobile(mobile, "en");
	}
	
	/**
	 * 加密（附加）
	 * @param mobile 手机号（经办人）
	 * @param cid 公司主键（用友）
	 * @param uid 用户主键（用友）
	 */
	public static final String encodeMobile(String mobile,String cid,String uid) {
		if(StringUtils.isNotBlank(cid) && StringUtils.isNotBlank(uid)){
			String en = "";
			if(StringUtils.isNotBlank(mobile))en += enMobile(mobile, "en");
			return en + SIGN + cid + SIGN + uid;
		}else{
			return enMobile(mobile, "en");
		}
	}
	
	/**
	 * 解密
	 * @param mobile 加密后的手机号
	 */
	public static final String decodeMobile(String mobile) {
		if(StringUtils.isNotBlank(mobile) && mobile.length()>11){
			return decodeMobiles(mobile)[0];
		}
		return enMobile(mobile, "de");
	}
	
	/**
	 * 解密（附加）
	 * @param mobile 手机号（含企业主键、企业用户主键）用友
	 * @return 数据结构 []{手机号，企业主键，企业用户主键};
	 */
	public static final String[] decodeMobiles(String mobile) {
		if(StringUtils.isNotBlank(mobile) && mobile.length()>11){
			String[]result = mobile.split(SIGN);
			result[0] = enMobile(mobile.substring(0, 11), "de");
			return result;
		}else{
			return new String[]{enMobile(mobile, "de"),null,null};
		}
	}
	
	/**
	 * 手机号码编解码
	 * @param mobile
	 * @param type
	 */
	private static final String enMobile(String mobile,String type) {
		if (mobile==null){
			return mobile;
		}
		mobile = mobile.trim();
		if (mobile.length()!=11){
			return mobile;
		}
		StringBuffer sb = new StringBuffer();
		List<String[]> keyList = getKeyList();
		String regex = "[0-9]";
		for (int i=0;i<mobile.length();i++){
			String s = mobile.substring(i,i+1);
			if ("en".equals(type)){
				if (Pattern.matches(regex, s)){
					sb.append(keyList.get(i)[Integer.parseInt(s)]);
				}else{
					return mobile;
				}
			}else{
				int idx = -1;
				for (int j=0;j<keyList.get(i).length;j++){
					if (s.equals(keyList.get(i)[j])){
						idx =j;
					}
				}
				if (idx==-1){
					return mobile;
				}
				sb.append(idx);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 判断字符串是不是数字
	 * @param str
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 手机号转明文（根据获取的MAP中的电话号值）
	 * @author WKX
	 * @param temp
	 * @since 2016年8月17日 下午2:48:32
	 */
	public static Map<String,Object> decodeMobile(Map<String,Object> temp){
		if(temp!=null && temp.get("mobile")!=null){
			String mobile = temp.get("mobile").toString();
			temp.put("mobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("mobilenum")!=null){
			String mobile = temp.get("mobilenum").toString();
			temp.put("mobilenum", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("membermobile")!=null){
			String mobile = temp.get("membermobile").toString();
			temp.put("membermobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("memberMobile")!=null){
			String mobile = temp.get("memberMobile").toString();
			temp.put("memberMobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("mobilephone")!=null){
			String mobile = temp.get("mobilephone").toString();
			temp.put("mobilephone", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("memmobile")!=null){
			String mobile = temp.get("memmobile").toString();
			temp.put("memmobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("member_mobile")!=null){
			String mobile = temp.get("member_mobile").toString();
			temp.put("member_mobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("disc.membermobile")!=null){
			String mobile = temp.get("disc.membermobile").toString();
			temp.put("disc.membermobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("discsp.member_mobile")!=null){
			String mobile = temp.get("discsp.member_mobile").toString();
			temp.put("discsp.member_mobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("discpl.member_mobile")!=null){
			String mobile = temp.get("discpl.member_mobile").toString();
			temp.put("discpl.member_mobile", Utility.decodeMobile(mobile));
		}
		if(temp!=null && temp.get("dis.member_mobile")!=null){
			String mobile = temp.get("dis.member_mobile").toString();
			temp.put("dis.member_mobile", Utility.decodeMobile(mobile));
		}
		return temp;
	}
	
	/**
	 * 转码手机号[集合]
	 * @author WKX
	 * @param list
	 */
	public static List<Map<String,Object>> decodeMobile(List<Map<String,Object>> list){
		if(list!=null){
			for(Map<String,Object> map:list){
				map = decodeMobile(map);
			}
		}
		return list;
	}
	
	/**
	 * 转码手机号[分页]
	 * @author WKX
	 * @param page
	 * @since 2016年8月17日 下午5:10:26
	 */
	public static PageResults<Map<String,Object>> decodeMobile(PageResults<Map<String,Object>> page){
		if(page!=null){
			List<Map<String,Object>> list = page.getResults();
			if(list!=null){
				for(Map<String,Object> map:list){
					map = decodeMobile(map);
				}
			}
			page.setResults(list);
		}
		return page;
	}
	
	/**
	 * @Title: test
	 * @Description: 工具方法，用户批量加密、解密数据
	 * @param bcmcc 参数，true用于解密，false用于加密
	 * @return void 返回类型
	 */
	public static void test(boolean bcmcc){
		//String cmccList = "139,138,137,136,135,134,159,150,151,158,157,188,187,152,182,147";
		File file = new File("d:\\d.txt");
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;
		    while ((line = reader.readLine()) != null) {//显示行号
		    	String mobile = bcmcc?decodeMobile(line):encodeMobile(line);
		    	System.out.println(mobile);
		    }
		    reader.close();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if (reader != null) {
		        try {
		            reader.close();
		        } catch (Exception e1) {
		        }
		    }
		}
	}
	
	public static void main(String[] args) {
		System.out.println(decodeMobile("lgykymovwha"));
		System.out.println(encodeMobile("13818994981"));
		
		System.err.println(decodeMobiles("lgdhcaoxvgl#248a7caa-9ca0-4983-854f-b88930c209ca#132eb6f5-d0e7-49cc-a90e-2637faf0fc85")[0]);
		System.err.println(encodeMobile("13818994981","1","1"));
	}
}