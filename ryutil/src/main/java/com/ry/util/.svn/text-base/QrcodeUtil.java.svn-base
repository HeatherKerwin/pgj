package com.ry.util;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;   
  
public class QrcodeUtil {   
    
	/**
	 * 生成二维码
	 * @author WKX
	 * @param text 二维码文本
	 * @param width 宽度
	 * @param height 高度
	 * @param srcImagePath 文件路径（含路径和文件名称）
	 * @param format 文件类型
	 * @since 2016年10月15日 下午4:57:57
	 */
	public static void encode(String text, int width, int height, String srcImagePath, String format) {
		if(StringUtils.isBlank(format))format = "png";
		try {
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			File outputFile = new File(srcImagePath);
			if(!outputFile.exists() && !outputFile.isDirectory())outputFile.mkdir();
			if(!outputFile.getParentFile().exists())outputFile.getParentFile().mkdirs();
			
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 说明：该测试（也是初始化生成座谈会的路径）
	 * 授权页：http://192.168.1.110:8080/rymobile/discussion/auth.htm?code=RUIYIN50965066
	 * 手机验证页：http://192.168.1.110:8080/rymobile/discussion/phone.htm
	 * @author WKX
	 * @param args
	 * @throws Exception
	 * @since 2016年10月12日 下午2:30:22
	 */
	public static void main(String []args)throws Exception{
    	encode("http://192.168.1.40:8888/rymobile/discussion/auth.htm?code=RUIYIN50965066", 320, 320, "e:/auth.png", null);
    	encode("http://192.168.1.40:8888/rymobile/discussion/phone.htm", 320, 320, "e:/phone.png", null);
    }
}