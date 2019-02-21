package com.utiexian.utils.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * PDF编辑（通过读取模板改变内容）
 * 
 * @author WKX 注：使用Adobe Acrobat 制作PDF模板
 */
public class PdfUtil {

	/**
	 * 根据模板和参数编辑PDF
	 * @author WKX
	 * @param params 封装的数据（这边的key与pdf模板的域名保持一致）
	 * @throws Exception
	 */
	public static void fillFormDatas(Map<String, String> params) throws Exception {
		String template = "E://wts-org.pdf";//获取模板路径
		String outFile = "E://wts-org-0001.pdf";//生成的文件路径

		FileOutputStream fos = new FileOutputStream(outFile);//需要生成PDF
		PdfReader reader = new PdfReader(template);//模板
		PdfStamper stamper = new PdfStamper(reader, fos);//解析
		
		//3-获取到模板上预定义的参数域
		AcroFields form = stamper.getAcroFields();
		//获取模板中定义的变量
		Map<String, Item> acroFieldMap = form.getFields();
		
		BaseFont bfChi = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		for(Map.Entry<String, Item> entry : acroFieldMap.entrySet()) {//循环解析模板定义的表单域
			String fieldName = entry.getKey();//获得块名
			String fieldValue = params.get(fieldName);//通过名字，获取传入的参数值
			if (StringUtils.isNotBlank(fieldValue)) {
				form.addSubstitutionFont(bfChi);
				form.setField(fieldName, fieldValue);//为模板中的变量赋值(key与pdf模板定义的域名一致)
			}
		}
		//模板中的变量赋值之后不能编辑
		stamper.setFormFlattening(true);
		stamper.close();
		reader.close();//阅读器关闭
		stamper.close();//解析器关闭
	}
	
	/**
	 * PDF填充内容[生成文件]并保存
	 * @author WKX
	 * @param params 参数
	 * @param in 输入流
	 * @param outFile 导出文件路径及名称
	 */
	public static void fillFormDatasIn2File(Map<String, String> params,InputStream in,String outFile) throws Exception {
		FileOutputStream fos = new FileOutputStream(outFile);//需要生成PDF
		PdfReader reader = new PdfReader(in);//模板
		PdfStamper stamper = new PdfStamper(reader, fos);//解析

		AcroFields form = stamper.getAcroFields();//获取到模板上预定义的参数域
		Map<String, Item> acroFieldMap = form.getFields();//获取模板中定义的变量
		
		BaseFont bfChi = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//字体定义
		for(Map.Entry<String, Item> entry : acroFieldMap.entrySet()) {//循环解析模板定义的表单域
			String fieldName = entry.getKey();//获得块名
			String fieldValue = params.get(fieldName);//通过名字，获取传入的参数值
			if (StringUtils.isNotBlank(fieldValue)) {
				form.addSubstitutionFont(bfChi);
				form.setField(fieldName, fieldValue);//为模板中的变量赋值(key与pdf模板定义的域名一致)
			}
		}
		stamper.setFormFlattening(true);//模板中的变量赋值之后不能编辑
		stamper.close();
		reader.close();//阅读器关闭
		stamper.close();//解析器关闭
		fos.flush();
		fos.close();
	}
	
	/**
	 * PDF填充内容[返回字节]不保存该文件
	 * @author WKX
	 * @param params 参数
	 * @param in 输入流
	 */
	public static byte[] fillFormDatasIn2Byte(Map<String, String> params,InputStream in) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();//不生成文件
		PdfReader reader = new PdfReader(in);//模板
		PdfStamper stamper = new PdfStamper(reader, bos);//解析

		AcroFields form = stamper.getAcroFields();//获取到模板上预定义的参数域
		Map<String, Item> acroFieldMap = form.getFields();//获取模板中定义的变量
		
		BaseFont bfChi = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//字体定义
		for(Map.Entry<String, Item> entry : acroFieldMap.entrySet()) {//循环解析模板定义的表单域
			String fieldName = entry.getKey();//获得块名
			String fieldValue = params.get(fieldName);//通过名字，获取传入的参数值
			if (StringUtils.isNotBlank(fieldValue)) {
				form.addSubstitutionFont(bfChi);
				form.setField(fieldName, fieldValue);//为模板中的变量赋值(key与pdf模板定义的域名一致)
			}
		}
		stamper.setFormFlattening(true);//模板中的变量赋值之后不能编辑
		stamper.close();
		reader.close();//阅读器关闭
		stamper.close();//解析器关闭
		return bos.toByteArray();
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("fill_1", "张三丰的");//客户姓名
		params.put("fill_2", "男");
		params.put("fill_3", "1122222222");
		params.put("fill_4", "java软件工程1");
		params.put("fill_5", "发热的人电饭锅电饭锅的");
		params.put("fill_6", "大富大贵");
		params.put("fill_7", "东方格调");
		fillFormDatas(params);
	}
}