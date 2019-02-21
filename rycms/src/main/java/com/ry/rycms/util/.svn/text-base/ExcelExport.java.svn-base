package com.ry.rycms.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ry.core.entity.Gongcui;
import com.ry.core.entity.Lianhang;
import com.ry.util.DateUtil;

public class ExcelExport {
	public static List<Gongcui> readGongcuiExcel(String filepath) throws Exception{
		Logger logger=Logger.getLogger(ExcelExport.class);
		List<Gongcui> list = new ArrayList<Gongcui>();
		InputStream is = new FileInputStream(filepath);
        HSSFWorkbook hwk = new HSSFWorkbook(is);// 将is流实例到 一个excel流里
        HSSFSheet sh = hwk.getSheetAt(0);// 得到book第一个工作薄sheet
        int rows = sh.getLastRowNum()+1 - sh.getFirstRowNum();  // 总行数
        for(int i=0; i<rows; i++){
             HSSFRow row = sh.getRow(i);
             if(row==null){
            	 continue;
             }
             int cols = row.getLastCellNum()+1 - row.getFirstCellNum();  // 该行的总列数
             Gongcui gongcui = new Gongcui();
             for (int j = 0; j < cols; j++) {
                 Object col = row.getCell((short)j);
                 if(col!=null){
                	 if(!"".equals(col.toString())){
                		 if(i!=0){
	                		 if(j==0){
	                			 try{
	                				 if(col.toString().startsWith("0")){
	                					 gongcui.setGongcuinumber(col.toString());
	                				 }else{
			                			 BigDecimal bd = new BigDecimal(col.toString());
			                			 gongcui.setGongcuinumber(bd.toPlainString());
	                				 }
	                			 }catch(Exception e){
	                				 gongcui.setGongcuinumber(col.toString());
	                			 }
	                		 }
	                		 if(j==1){
	                			 gongcui.setGongcuimember(col.toString());
	                		 }
	                		 if(j==2){
	                			 gongcui.setFayuan(col.toString());
	                		 }
	                		 if(j==3){
	                			 String date = col.toString();
	                			 logger.info(date);
	                			Date  dateT = DateUtil.parser(date, "dd-MMM-yyyy") ;
	                			 gongcui.setGongcuidate(dateT);
	                		 }
                		 }
                	 }
                 }
             }
             if(gongcui.getGongcuimember()!=null){
             	list.add(gongcui);
             }
        }
		return list;
	}
	
	public static List<Lianhang> readLianhangExcel(String filepath,String yinhang) throws Exception{
		List<Lianhang> list = new ArrayList<Lianhang>();
			InputStream is = new FileInputStream(filepath);
	        HSSFWorkbook hwk = new HSSFWorkbook(is);// 将is流实例到 一个excel流里
	        HSSFSheet sh = hwk.getSheetAt(0);// 得到book第一个工作薄sheet
	        int rows = sh.getLastRowNum()+1 - sh.getFirstRowNum();  // 总行数
	        for(int i=0; i<rows; i++){
	             HSSFRow row = sh.getRow(i);
	             if(row==null){
	            	 continue;
	             }
	             int cols = row.getLastCellNum()+1 - row.getFirstCellNum();  // 该行的总列数
	             Lianhang lianhang = new Lianhang();
	             for (int j = 0; j < cols; j++) {
	                 Object col = row.getCell((short)j);
	                 if(col!=null){
	                	 if(!"".equals(col.toString())){
	                		 if(i!=0){
		                		 if(j==0){
		                			 lianhang.setProvice(col.toString());
		                		 }
		                		 if(j==1){
		                			 lianhang.setCity(col.toString());
		                		 }
		                		 if(j==2){
		                			 lianhang.setAddress(col.toString());
		                		 }
		                		 if(j==3){
		                			 try{
			                			 BigDecimal bd = new BigDecimal(col.toString());
			                			 lianhang.setPhone(bd.toPlainString());
		                			 }catch(Exception e){
		                				 lianhang.setPhone(col.toString());
		                			 }
		                		 }
		                		 if(j==4){
		                			 try{
			                			 BigDecimal bd = new BigDecimal(col.toString());
			                			 lianhang.setLianhanghao(bd.toPlainString());
		                			 }catch(Exception e){
		                				 lianhang.setLianhanghao(col.toString());
		                			 }
		                		 }
		                		 if(j==5){
		                			 lianhang.setYinhangdesc(col.toString());
		                		 }
		                		 
		                		lianhang.setYinhang(yinhang);
		                		 
	                		 }
	                	 }
	               
	                 }
	             if(lianhang.getLianhanghao()!=null){
	            	 list.add(lianhang);
	             }
	        }
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception{
//		List<Gongcui> list = readGongcuiExcel();
//		System.out.println(list.size());
//		for(Gongcui g : list){
//			System.out.println(g.getFayuan()+"--"+g.getGongcuidate());
//		}
//		List<Lianhang> lianhanglist = readLianhangExcel();
//		System.out.println(lianhanglist.size());
//		for(Lianhang lh : lianhanglist){
//			System.out.println(lh.getLianhanghao());
//		}
	}
}
