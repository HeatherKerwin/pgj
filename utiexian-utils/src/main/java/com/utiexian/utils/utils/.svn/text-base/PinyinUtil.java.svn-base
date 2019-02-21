package com.utiexian.utils.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文转英文（取首字母）
 * @author WKX
 */
public class PinyinUtil {

    /**
     * 得到全拼
     * @param src 中文
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {//判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母
     * @param str 中文
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
    
    /**
     * 获取中文的首字母（第一个字不是中文返回#）
     * @author WKX
     * @param str 中文
     */
    public static String getPinYinFirst(String str) {
    	String result = "";
    	if(StringUtils.isBlank(str))return str;
    	
    	String f = str.substring(0,1);
    	if(f.matches("^[a-zA-Z]*")){
    		return f.toUpperCase();
    	}else if(f.matches("[\\u4e00-\\u9fa5]+")){
    		char word = str.charAt(0);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
            	result += pinyinArray[0].charAt(0);
            } else {
            	result += word;
            }
            return result.toUpperCase(); 
    	}else{
    		return "#";
    	}
    }

    /**
     * 将字符串转移为ASCII码
     * @param cnStr
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }
    
    /**
     * 根据Unicode编码完美的判断中文汉字和符号  
     * @author WKX
     * @param c
     */
    private static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {  
            return true;  
        }  
        return false;  
    }  
   
    /**
     * 完整的判断中文汉字和符号
     * @author WKX
     * @param strName
     */
    public static boolean isChinese(String strName) {  
        char[] ch = strName.toCharArray();  
        for(int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if(isChinese(c)) {  
                return true;  
            }  
        }  
        return false;  
    }  
   
    /**
     * 只能判断部分CJK字符（CJK统一汉字）
     * @author WKX
     * @param str
     */
    public static boolean isChineseByREG(String str) {
        if(str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }
   
    /**
     * 只能判断部分CJK字符（CJK统一汉字）
     * @author WKX
     * @param str
     */
    public static boolean isChineseByName(String str) {
        if(str == null) {
            return false;
        }
        //大小写不同：\\p 表示包含，\\P 表示不包含
        //\\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg ="\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }

    public static void main(String[] args) {
        String cnStr = "票据管家";
        System.out.println(getPingYin(cnStr));
        System.out.println(getPinYinFirst(cnStr));
    }
}