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
import com.ry.core.util.Constant;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;

import cn.jimmyshi.beanquery.BeanQuery;

/**
 * 定时发送邮件（余昊：活跃用户统计，上月150名活跃大于4，不足取上上月未入选的在上月活跃大于4）
 * @author WKX
 */
public class Huoyue2Task {

	private static Logger logger = Logger.getLogger(Huoyue2Task.class);

	@Resource
	private AppVisitLogService appVisitLogService;
	
	public void execute() {
		logger.info("------------------活跃用户统计（余昊）start------------------");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date end = cal.getTime();
		
		cal.add(Calendar.MONTH, -1);
        Date start = cal.getTime();
		
        List<Map<String, Object>> result_1 = appVisitLogService.getByStartAndEnd(start, end, 0, 150);
        List<Map<String, Object>> result_2 = new ArrayList<Map<String,Object>>();
        if(result_1!=null && result_1.size()<150){//上月数据已经够了
        	int size = 150;
        	if(result_1!=null && result_1.size()>0)size -= result_1.size();
        	cal.add(Calendar.MONTH, -1);
            Date start_ = cal.getTime();
            List<Map<String, Object>> result_temp = appVisitLogService.getByStartAndEnd(start_, start, 0, 150);
            if(result_temp!=null && result_temp.size()>0){
            	List<Integer> memberIds = new ArrayList<Integer>();
            	for(Map<String, Object> map:result_temp){
            		if(map.get("memberId")!=null){
						memberIds.add(Integer.valueOf(map.get("memberId").toString()));
					}
            	}
            	if(memberIds.size()>0){
            		result_temp = appVisitLogService.getByStartAndEndNotinMemberIds(start_, start, size, memberIds);
            		if(result_temp!=null && result_temp.size()>0){
            			List<Integer> memberIds_ = new ArrayList<Integer>();
                    	for(Map<String, Object> map:result_temp){
                    		if(map.get("memberId")!=null){
        						memberIds_.add(Integer.valueOf(map.get("memberId").toString()));
        					}
                    	}
                    	result_2 = appVisitLogService.getByStartAndEndInMemberIdsNoReple(start, end, memberIds_);
            		}
            	}
            }
        }
        result_1.addAll(result_2);
        try {
			excel2(result_1, start);//绘制表格（发送邮件）
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("------------------活跃用户统计（余昊）异常------------------");
		}
		logger.info("------------------活跃用户统计（余昊）end------------------");
	}
	
	/**
	 * 绘制表格（发送邮件）余昊用户活跃度
	 * @author WKX
	 * @param result 数据
	 * @param time 时间（统计的是上个月的数据）
	 * @throws Exception
	 * @since 2017年3月29日 下午4:27:32
	 */
	private void excel2(List<Map<String, Object>> result,Date time) throws Exception{
		result = BeanQuery.select("amount,memberId,mobile,regtime,username").orderBy("amount").desc().executeFrom(result);//数据排序
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(time, DateUtil.FORMART4)+"活跃用户名单");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 300*20);
        sheet.setColumnWidth(1, 300*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 300*20);
        
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
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
        
        
        HSSFRow row = sheet.createRow(0);//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFCell cell = row.createCell(0);//第四步，创建单元格，并设置值表头 设置表头居中
        
        cell = row.createCell(0);
        cell.setCellValue("用户姓名");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("手机号");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("注册日期");
        cell.setCellStyle(style1);
        
        cell = row.createCell(3);
        cell.setCellValue("活跃度");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){//amount,memberId,mobile,regtime,username
            	row = sheet.createRow((int) i + 1);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("username")!=null?res.get("username").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("regtime")!=null?res.get("regtime").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(3);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);
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
			String email = Constant.properties.getProperty("YUHAO");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(time, DateUtil.FORMART4)+"活跃用户名单", "",path);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("失败！");
        }  
	}
}