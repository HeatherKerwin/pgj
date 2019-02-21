package com.ry.rycms.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.OrgExtendInfo;
import com.ry.core.entity.OrgInfo;
import com.ry.core.form.PriceForm;
import com.ry.core.service.OrgExtendInfoService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.PriceService;
import com.ry.util.DateUtil;

/**
 * 关于客户的归属类型的转化
 * @author MH
 */
public class CustomerReportTask {
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgExtendInfoService orgExtendInfoService;
	
	@Resource
	PriceService priceService;
	
	private static Logger logger = Logger.getLogger(CustomerReportTask.class);
	
	public void execute() {
		logger.info("--------------------进入归属类型的转化定时任务----------------------------");
		List<OrgExtendInfo> list1 = orgExtendInfoService.getOrgExtendInfoByInfo();
		PriceForm from = null ;
		if(list1 != null){
			for (OrgExtendInfo orgExtendInfo : list1) {
				if(orgExtendInfo.getAscriptionState() == 0){//是销售营销用户
					OrgInfo orgInfo = orgInfoService.getById(orgExtendInfo.getOrgInfoId());
					Date date1 = DateUtil.getStartDate(orgExtendInfo.getSalesTime());
					Date date2 = DateUtil.getStartDate(new Date());
					int ts = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
					if(ts == 150){//判断是否转化为平台用户
						Map<String, Object> map= new HashMap<String, Object>();
						from = new PriceForm();
						from.setStartDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(),90),DateUtil.FORMART3));
						from.setEndDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 120),DateUtil.FORMART3));
						from.setOrgId(orgInfo.getId());
						map  = priceService.getPriceSvmDay(from);
						if(map != null){
							Integer count = Integer.parseInt(map.get("count").toString());
							if(count>=6){}
							else{
									Map<String, Object> map1= new HashMap<String, Object>();
									from = new PriceForm();
									from.setStartDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 120),DateUtil.FORMART3));
									from.setEndDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 150),DateUtil.FORMART3));
									from.setOrgId(orgInfo.getId());
									map1  = priceService.getPriceSvmDay(from);
									if(map1 != null){
										Integer count1 = Integer.parseInt(map.get("count").toString());
										if(count1>=6){
											
										}else{
											orgExtendInfo.setConversionTime(new Date());
											orgExtendInfo.setAscriptionState(2);
											orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
										}
									}
								}
						}
					}else if(ts >= 180){//转化为平台用户
						orgExtendInfo.setPermitTime(new Date());
						orgExtendInfo.setAscriptionState(3);
						orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
					}
				}
				if(orgExtendInfo.getAscriptionState() == 2){//是转化营销用户
					Date date1 = DateUtil.getStartDate(orgExtendInfo.getConversionTime());
					Date date2 = DateUtil.getStartDate(new Date());
					int ts = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
					if(ts >= 30){//判断是否转化为平台用户
						orgExtendInfo.setPermitTime(new Date());
						orgExtendInfo.setAscriptionState(3);
					}
					orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
				}
			}
		}
		logger.info("--------------------定时任务结束----------------------------");
	}
}
