package com.ry.rycms.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.InquiryReply;
import com.ry.core.service.InquiryReplyService;

/**
 * 备份支付宝交易号
 * @author BKY
 */
public class InquiryReplyJyhTask {
	private static Logger logger = Logger.getLogger(InquiryReplyJyhTask.class);
	
	@Resource
	InquiryReplyService orderInvoiceService;
	
	public void execute() {
		logger.info("------------------定时任务:备份支付宝交易号------------------");
		try {
			Integer payState = 1;	//支付完成
			Date date = new Date();
			String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String createTime2 = day + " 00:00:00";
			Date createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime2);
			
			List<InquiryReply> list = orderInvoiceService.updateTransaction(payState, createTime, date);
			List<InquiryReply> inquiryReplyList = new ArrayList<InquiryReply>();
			for(InquiryReply inquiryReply : list) {
				inquiryReply.setJyh(inquiryReply.getNo());
				inquiryReplyList.add(inquiryReply);
			}
			orderInvoiceService.updateJyh(inquiryReplyList);
			
		} catch (Exception e) {
			logger.error("备份支付宝交易号异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}
