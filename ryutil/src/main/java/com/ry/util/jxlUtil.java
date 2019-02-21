package com.ry.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class jxlUtil {

	public static void main(String[] args) {
		List<String[]> list = new ArrayList<String[]>();
		String[] array = new String[]{"id","name","pass","sex"};
		String[] array1 = new String[]{"id1","name1","pass1","sex1"};
		list.add(array);
		list.add(array1);
		jxlUtil.writeExcel(list,"D:/test.xlsx","student");
	}
	
	/**
	 * @param list
	 * @param filePath
	 * @param sheetName
	 * jxl写入excel文件工具类
	 */
	public static void writeExcel(List<String[]> list,String filePath,String sheetName){
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = book.createSheet(sheetName, 0);
			if(list!=null && !list.isEmpty()){
                for(int i=0; i<list.size(); i++){
                	String[] array = list.get(i);
                	for(int j = 0;j<array.length;j++){
						sheet.addCell(new Label(j, i, array[j]));
                	}
                    
                }
            }
			book.write();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
	}
	
	/**
	 * 根据key和title和内容生成表格[暂仅适用于APPCAN用户导出发送邮件]
	 * 注：含一定使用规则，title第一个参数为序号，其次为表格map对应的参数的名称
	 * @author WKX
	 * @param keys
	 * @param title
	 * @param list
	 * @param filePath
	 * @param sheetName
	 * @since 2016年1月20日 下午3:31:41
	 */
	public static void writeExcel(String[] keys,String[] title,List<Map<String,Object>>list,String filePath,String sheetName){
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = book.createSheet(sheetName, 0);
			
			WritableFont titleWf = new WritableFont(WritableFont.createFont("黑体"),// 字体  
                    12,//WritableFont.DEFAULT_POINT_SIZE,   // 字号  
                    WritableFont.NO_BOLD,                  // 粗体  
                    false,                                 // 斜体  
                    UnderlineStyle.NO_UNDERLINE,            // 下划线  
                    Colour.BLACK,                       // 字体颜色  
                    ScriptStyle.NORMAL_SCRIPT);
			WritableCellFormat wcf = new WritableCellFormat(titleWf);  
			wcf.setBackground(Colour.GRAY_25);// 设置单元格的背景颜色  
			wcf.setAlignment(Alignment.CENTRE); // 设置对齐方式  
			wcf.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 添加边框
			Label l = null;
			for(int j = 0;j<title.length;j++){
        		String col = title[j];
        		l = new Label(j, 0, col);
        		l.setCellFormat(wcf);
				sheet.addCell(l);
        	}
			if(list!=null && list.size()>0){
				WritableCellFormat wcf_ = new WritableCellFormat(titleWf);  
				wcf_.setBackground(Colour.GRAY_25);// 设置单元格的背景颜色  
				wcf_.setAlignment(Alignment.CENTRE); // 设置对齐方式  
				wcf_.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 添加边框
				wcf_.setBackground(Colour.WHITE);// 设置单元格的背景颜色  
				wcf_.setAlignment(Alignment.CENTRE); // 设置对齐方式  
                for(int i=0; i<list.size(); i++){
                	Map<String,Object> array = list.get(i);
                	l = new Label(0, i+1, i+1+"");
                	l.setCellFormat(wcf_);
                	sheet.addCell(l);
                	for(int j = 0;j<keys.length;j++){
                		String key = keys[j];
                		Object o = array.get(key);
                		l = new Label(j+1, i+1, o!=null?o.toString():"");
                		l.setCellFormat(wcf_);
						sheet.addCell(l);
                	}
                }
            }
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			sheet.setColumnView(8, 12);
			book.write();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
	}
}