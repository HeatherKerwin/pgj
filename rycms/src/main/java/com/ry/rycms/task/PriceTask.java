package com.ry.rycms.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.NoticeAddService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.SendMessagesUtil;

/**
 * 定时发送邮件短信（检测是否已经报价，要根据节假日管理）
 * @author WKX
 */
public class PriceTask {

	private static Logger logger = Logger.getLogger(PriceTask.class);

	@Resource
	private TiexianNoticeService noticeService; 
	
	@Resource
	private NoticeAddService noticeAddService;
	
	@Resource
	private HistorypriceService historypriceService;
	
	public void execute() {
		logger.info("------------------工作日11:00点前报价检测 start------------------");
        try {
        	Date day = new Date();
        	Notice notice = noticeService.getFestivalByNowTime(day);
        	if(notice!=null){
        		return;
        	}
        	Calendar cal = Calendar.getInstance();  
        	cal.setTime(day);
	        int now = cal.get(Calendar.DAY_OF_WEEK);
	        if(now==Calendar.SUNDAY || now==Calendar.SATURDAY){//周六周日
	        	List<NoticeAdd> list_ = noticeAddService.getByNowTime(day);
	        	if(list_==null || list_.size()==0){//周末切不补班
	        		return;
	        	}
	        }
        	//正常上班（含假期补班）
	        List<Historyprice> price = historypriceService.findbyDayAndType(DateUtil.formart(day,DateUtil.FORMART3), null, null);
        	if(price==null || price.size()==0){//当天未报价
        		String msg = "【票据管家】您好，今日11：00之前仍没有报价，请了解情况，或通知相关人员查明原因。";
        		List<String> targetPerson = new ArrayList<String>();
    			String email = Constant.properties.getProperty("PRICEREPORT_EMAIL");//获取配置的邮箱地址
    			if(StringUtils.isNotBlank(email)){
    				String[] emails = email.split(",");
    				if(emails!=null && emails.length>0){
    					for(String e:emails){
    						targetPerson.add(e);
    					}
    				}
    			}
    			if(targetPerson.size()>0){//有配置的人员
    				EmailUtil.sendEmail(targetPerson,"工作日报价检测", msg,null);
    			}
    			
    			String phone = Constant.properties.getProperty("PRICEREPORT_PHONE");//获取配置的邮箱地址
    			if(StringUtils.isNotBlank(phone)){
    				String[] phones = phone.split(",");
    				if(phones!=null && phones.length>0){
    					for(String p:phones){
    						SendMessagesUtil.sendMessages(p, msg);
    					}
    				}
    			}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("------------------[异常]工作日11:00点前报价检测------------------");
		}
        logger.info("------------------工作日11:00点前报价检测 start------------------");
	}
}