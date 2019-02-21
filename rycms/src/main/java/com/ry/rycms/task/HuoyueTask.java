package com.ry.rycms.task;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ry.core.service.AppVisitLogService;
import com.ry.core.service.PriceService;
import com.ry.core.util.Constant;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;

/**
 * 定时发送邮件（获取上一个月新注册用户的活跃）
 * @author WKX
 */
public class HuoyueTask {

	private static Logger logger = Logger.getLogger(HuoyueTask.class);

	@Resource
	private AppVisitLogService appVisitLogService;
	
	@Resource
	private PriceService priceService;
	
	public void execute() {
		logger.info("------------------销售数据统计定时任务执行start------------------");
		Calendar cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        cal_1.set(Calendar.HOUR_OF_DAY, 0);
        cal_1.set(Calendar.MINUTE, 0);
        cal_1.set(Calendar.SECOND, 0);
        Date end = cal_1.getTime();
        
        cal_1.add(Calendar.MONTH, -1);
        Date start = cal_1.getTime();
		
		cal_1.add(Calendar.MONTH, -1);
		Date up = cal_1.getTime();
		Date down = start;
		
		appVisitLogService.createTempByStartAndEnd(up,end);//拷贝部分数据
		
		List<Integer> memberIds = new ArrayList<Integer>();
		List<Map<String, Object>> result_0 = appVisitLogService.getByStartAndEnd(up, down);
		List<Map<String, Object>> result_1 = null;//上上月
		if(result_0!=null && result_0.size()>0){
			for(Map<String, Object> map:result_0){
				if(map!=null && map.get("amount")!=null && Integer.valueOf(map.get("amount").toString())<2){
					if(map.get("memberId")!=null){
						memberIds.add(Integer.valueOf(map.get("memberId").toString()));
					}
				}
			}
			if(memberIds!=null && memberIds.size()>0)result_1 = appVisitLogService.getByStartAndEndInMemberIds(start, end,memberIds);
		}
		
        List<Map<String, Object>> result_2 = appVisitLogService.getByStartAndEnd(start, end);//上月
        List<Map<String, Object>> result_3 = appVisitLogService.getHasOrgInfoByStartAndEnd(start, end);//前两个月申请机构认证的用户（通过）的活跃数
        excel(start,result_2,up,result_1,result_3);
		logger.info("------------------销售数据统计定时任务执行end------------------");
		
		
		logger.info("------------------销售数据统计定时任务执行（机构报价天数）start------------------");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date end_ = cal.getTime();
		
		cal.add(Calendar.MONTH, -1);
        Date start_ = cal.getTime();
        List<Map<String, Object>> result = priceService.getPriceByOrgNoHezuo(start_, end_);
        excel3(result, start_);//绘制表格（发送邮件）
		
		logger.info("------------------销售数据统计定时任务执行（机构报价天数）end------------------");
	}
	
	/**
	 * 创建表格
	 * @param month 上月
	 * @param result 上月
	 * @param month1 上上月
	 * @param result1 上上月
	 * @param result2 前两个月申请机构认证的用户（通过）的活跃数
	 * @since 2017年3月28日 下午5:24:03
	 */
	private void excel(Date month,List<Map<String, Object>> result,Date month1,List<Map<String, Object>> result1,List<Map<String, Object>> result2){
		//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(month, "yyyy年MM月")+"注册名单");
        sheet.setColumnWidth(0, 500*20);
        sheet.setColumnWidth(1, 400*20);
        sheet.setColumnWidth(2, 400*20);
        HSSFSheet sheet1 = wb.createSheet(DateUtil.formart(month1, "yyyy年MM月")+"注册名单");
        sheet1.setColumnWidth(0, 500*20);
        sheet1.setColumnWidth(1, 400*20);
        sheet1.setColumnWidth(2, 400*20);
        
        HSSFSheet sheet2 = wb.createSheet("上个月认证通过用户名单");
        sheet2.setColumnWidth(0, 500*20);
        sheet2.setColumnWidth(1, 400*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("用户注册月份");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(month, "yyyy年MM月"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        //--列头------------------
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("注册用户");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("推荐人");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("活跃天数");
        cell.setCellStyle(style1);
        
        //================================================================================
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row1 = sheet1.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("统计时间");
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("");
        cell1.setCellStyle(style);
        
        row1 = sheet1.createRow(1);
        cell1 = row1.createCell(0);
        cell1.setCellValue("用户注册月份");
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue(DateUtil.formart(month1, "yyyy年MM月"));
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("");
        cell1.setCellStyle(style);
        
        //--列头------------------
        row1 = sheet1.createRow(2);
        cell1 = row1.createCell(0);
        cell1.setCellValue("上月活跃度小于2的注册用户");
        cell1.setCellStyle(style1);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue("推荐人");
        cell1.setCellStyle(style1);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("活跃天数");
        cell1.setCellStyle(style1);
        
      //================================================================================
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row2 = sheet2.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellValue("统计时间");
        cell2.setCellStyle(style);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell2.setCellStyle(style);
        
        
        row2 = sheet2.createRow(1);
        cell2 = row2.createCell(0);
        cell2.setCellValue("用户认证月份");
        cell2.setCellStyle(style);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue(DateUtil.formart(month, "yyyy年MM月"));
        cell2.setCellStyle(style);
        
        //--列头------------------
        row2 = sheet2.createRow(2);
        cell2 = row2.createCell(0);
        cell2.setCellValue("用户手机号");
        cell2.setCellStyle(style1);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue("活跃天数");
        cell2.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);
            }
        }
        HSSFCell c1 = null;
        if(result1!=null){
        	for(int i = 0; i < result1.size(); i++){
            	row1 = sheet1.createRow((int) i + 3);
            	res = result1.get(i);
                c1 = row1.createCell(0);
                c1.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c1.setCellStyle(style2);
                
                c1 = row1.createCell(1);
                c1.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c1.setCellStyle(style2);
                
                c1 = row1.createCell(2);
                c1.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c1.setCellStyle(style2);
            }
        }
        HSSFCell c2 = null;
        if(result2!=null){
        	for(int i = 0; i < result2.size(); i++){
            	row2 = sheet2.createRow((int) i + 3);
            	res = result2.get(i);
                c2 = row2.createCell(0);
                c2.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c2.setCellStyle(style2);
                
                c2 = row2.createCell(1);
                c2.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c2.setCellStyle(style2);
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("SALEEMAIL");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(month, "yyyy年MM月")+"销售数据报表", "",path);
        }catch (Exception e){  
            e.printStackTrace();  
        }  
	}
	
	private void excel3(List<Map<String, Object>> result,Date dataTime){
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(dataTime, "MM月")+"机构报价天数");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 300*20);
        sheet.setColumnWidth(1, 500*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 250*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFCellStyle style3 = wb.createCellStyle();
        
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        style3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style3.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style3.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        
        
        HSSFRow row = sheet.createRow(0);//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFCell cell = row.createCell(0);//第四步，创建单元格，并设置值表头 设置表头居中
        
        cell = row.createCell(0);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("数据月份");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(dataTime, "yyyy年MM月"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("用户手机号");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("公司名称");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("报价天数");
        cell.setCellStyle(style1);

        cell = row.createCell(3);
        cell.setCellValue("推荐码");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("company")!=null?res.get("company").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(3);
                c.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c.setCellStyle(style3);
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("SALEEMAIL");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(dataTime, DateUtil.FORMART4)+"机构报价天数", "",path);
        }catch (Exception e){
            e.printStackTrace();
        }  
	}
}