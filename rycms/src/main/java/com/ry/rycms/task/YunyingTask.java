package com.ry.rycms.task;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.ry.core.service.ClickCountService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;

import cn.jimmyshi.beanquery.BeanQuery;

/**
 * 定时发送邮件（洪宁：神马搜狗等渠道注册用户）运营数据统计
 * @author WKX
 */
public class YunyingTask {

	private static Logger logger = Logger.getLogger(YunyingTask.class);

	@Resource
	private ClickCountService clickCountService;
	
	public void execute() {
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(new Date());
		if(g.get(Calendar.WEEK_OF_YEAR)%2 == 0){
			logger.info("------------------运营数据统计（洪宁）start------------------");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date end = cal.getTime();
			
			cal.add(Calendar.WEEK_OF_YEAR, -2);
	        Date start = cal.getTime();
			
	        List<Map<String,Object>> list = clickCountService.getSrcCountByDate(start, end);
	        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			if(list!=null && list.size()>0){
				Map<String,Object> old = null;
				Map<String,Object> temp = null;
				for(Map<String,Object> map:list){
					if(result.size()>0){
						old = result.get(result.size()-1);
						temp = this.mergeMap(old,map);
						if(temp!=null)result.add(temp);
					}else{
						result.add(this.reName(map));
					}
				}
			}
			result = BeanQuery.select("keyword,ALL,SHENMA,SOUGOU,OTHER").orderBy("ALL").desc().executeFrom(result);//数据排序
			try {
				this.excel1(result, start, end);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("------------------运营数据统计（洪宁）异常------------------");
			}
			logger.info("------------------运营数据统计（洪宁）end------------------");
		}
	}
	
	/**
	 * 整合数据（相同关键字汇总到同一条里面）
	 * @author WKX
	 * @param old 最近整合的一条数据
	 * @param map 读取的一条数据
	 * @since 2017年3月28日 下午2:56:20
	 */
	private Map<String,Object> mergeMap(Map<String,Object> old,Map<String,Object> map){
		if(old!=null && map!=null && old.get("keyword")!=null && map.get("keyword")!=null){
			if((old.get("keyword").toString()).equals(map.get("keyword").toString()) && map.get("amount")!=null){
				int count = Integer.valueOf(map.get("amount").toString());
				Object style = null;
				if(map.get("temp")!=null){
					if("SHENMA".equals(map.get("temp").toString())){
						style = old.get("SHENMA");
						if(style!=null){
							old.put("SHENMA", Integer.valueOf(style.toString())+count);
						}else{
							old.put("SHENMA",count);
						}
					}else if("SOUGOU".equals(map.get("temp").toString())){
						style = old.get("SOUGOU");
						if(style!=null){
							old.put("SOUGOU", Integer.valueOf(style.toString())+count);
						}else{
							old.put("SOUGOU",count);
						}
					}
				}else{
					old.put("OTHER",count);
				}
				this.amount(old);
				return null;
			}
		}
		return this.reName(map);
	}
	
	/**
	 * 重命名关键字KEY（神马、搜狗、其他）
	 * @author WKX
	 * @param map 原始记录
	 * @since 2017年3月28日 下午3:48:53
	 */
	private Map<String,Object> reName(Map<String,Object> map){
		if(map!=null && map.get("amount")!=null){
			int count = Integer.valueOf(map.get("amount").toString());
			if(map.get("temp")!=null){
				if("SHENMA".equals(map.get("temp").toString())){
					map.put("SHENMA",count);
				}else if("SOUGOU".equals(map.get("temp").toString())){
					map.put("SOUGOU",count);
				}
			}else{
				map.put("OTHER",Integer.valueOf(map.get("amount").toString()));
			}
			this.amount(map);
		}
		return map;
	}
	
	/**
	 * 汇总数量
	 * @author WKX
	 * @param map 单条数据
	 * @since 2017年3月28日 下午4:03:41
	 */
	private Map<String,Object>amount(Map<String,Object> map){
		int amount = 0;
		if(map.get("SHENMA")!=null){
			amount += Integer.valueOf(map.get("SHENMA").toString());
		}
		if(map.get("SOUGOU")!=null){
			amount += Integer.valueOf(map.get("SOUGOU").toString());
		}
		if(map.get("OTHER")!=null){
			amount += Integer.valueOf(map.get("OTHER").toString());
		}
		map.put("ALL",amount);
		return map;
	}
	
	/**
	 * 发送邮件（洪宁需要的数据）
	 * @author WKX
	 * @param result 数据
	 * @param start 开始日期
	 * @param end 结束日期
	 * @throws Exception
	 * @since 2017年3月28日 下午5:16:05
	 */
	private void excel1(List<Map<String, Object>> result,Date start,Date end) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(new Date(), DateUtil.FORMART3)+"关键词统计");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 600*20);
        sheet.setColumnWidth(1, 300*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 300*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
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
        cell.setCellValue("数据时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        //--列头------------------
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(DateUtil.formart(start, "yyyy/MM/dd") + "至" + DateUtil.formart(end, "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
      //--列头------------------
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("搜索词");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("搜狗");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("神马");
        cell.setCellStyle(style1);
        
        cell = row.createCell(3);
        cell.setCellValue("其他");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        MAX:if(result!=null){
        	int count_sg = 0;
        	int count_sm = 0;
        	int count_ot = 0;
        	Object temp = null;
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("keyword")!=null?res.get("keyword").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                temp = res.get("SOUGOU");
                if(temp!=null){
                	count_sg += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                temp = res.get("SHENMA");
                if(temp!=null){
                	count_sm += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                
                c = row.createCell(3);
                temp = res.get("OTHER");
                if(temp!=null){
                	count_ot += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                if(i>=90 || (i+1)==result.size()){
                	row = sheet.createRow((int) i + 4);
                	res = result.get(i);
                    c = row.createCell(0);
                    c.setCellValue("总计");
                    c.setCellStyle(style2);
                    
                    c = row.createCell(1);
                    c.setCellValue(count_sg);
                    c.setCellStyle(style2);
                    
                    c = row.createCell(2);
                    c.setCellValue(count_sm);
                    c.setCellStyle(style2);
                    
                    c = row.createCell(3);
                    c.setCellValue(count_ot);
                    c.setCellStyle(style2);
                	break MAX;
                }
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
			String email = Constant.properties.getProperty("HONGNING");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(new Date(), DateUtil.FORMART3)+"关键词统计", "",path);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("失败！");
        }  
	}
}