package com.utiexian.website.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class Base64Utils {
 
	/**
	 * 本地文件转Base64
	 * @param filePath 本地文件路径
	 */
	public static String fileToBase64ByLocal(String filePath) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(filePath);//读取图片字节数组
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);//返回Base64编码过的字节数组字符串
	}
	
	/**
	 * Base64转存本地文件
	 * @param base64
	 * @param filePath 保存本地地址（含文件名）
	 */
	public static boolean base64ToFile(String base64,String filePath) {//对字节数组字符串进行Base64解码并生成图片
		if (StringUtils.isBlank(base64))return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 保存文件
	 * @param filePath 本地地址
	 * @param input 文件流
	 */
	public static boolean inputStreamToLocal(String filePath,InputStream input) {
		try {
			int index;
			byte[] bytes = new byte[1024];
			FileOutputStream downloadFile = new FileOutputStream(filePath);
			while ((index = input.read(bytes)) != -1) {
				downloadFile.write(bytes, 0, index);
				downloadFile.flush();
			}
			downloadFile.close();
			input.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}