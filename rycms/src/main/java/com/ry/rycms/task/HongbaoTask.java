package com.ry.rycms.task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.service.HongbaoService;
import com.ry.util.DateUtil;

@Component
public class HongbaoTask {
	
	private static final Logger logger = Logger.getLogger(HongbaoTask.class);
	
	@Resource
	HongbaoService hongbaoService;
	
	public void setFlag() throws ParseException {
		
		logger.info("判断红包过期开始>>>>>>>>>>>>>");
		
		HongbaoActivity activity = new HongbaoActivity();
		activity.setStatus(0);
		activity.setFlag(1);
		
		List<HongbaoActivity> activities = hongbaoService.getActivitys(activity);
		
		if (activities != null && activities.size() > 0) {			
			Date nowDate = new Date();
			for (int i = 0; i < activities.size(); i++) {
				//判断红包活动是否过期
				if (DateUtil.daysBetween(nowDate, activities.get(i).getEnddate()) < 0 ) {
					HongbaoActivity activity2 = activities.get(i);
					activity2.setFlag(0);
					hongbaoService.updateActivity(activity2);					
				}
			}
						
			//Integer shareDays = activities.get(0).getSharedays();
			
			List<HongbaoDetail> hongbaoDetails = hongbaoService.getListFlag0();
			for (int i = 0; i < hongbaoDetails.size(); i++) {
				HongbaoActivity aform = new HongbaoActivity();
				aform.setId(hongbaoDetails.get(i).getHid());
				HongbaoActivity activity2 = hongbaoService.getActivity(aform);
				//判断 红包是否过期
				Date date1 = DateUtil.addDays(hongbaoDetails.get(i).getUpdatetime(), activity2.getSharedays());
				if (DateUtil.daysBetween(nowDate, date1)<0) {
					HongbaoDetail hongbaoDetail = hongbaoDetails.get(i);
					hongbaoDetail.setFlag(1);
					hongbaoService.updateHongbaoDetail(hongbaoDetail);
				}								
			}
						
		}
		logger.info("判断红包过期结束>>>>>>>>>>>>");
		logger.info("红包统计开始>>>>>>>>>>>");
		//先得到红包活动的id，然后根据红包id来统计红包数据
		List<Integer> hids = hongbaoService.getHids();
		if(hids != null && hids.size() > 0){
			for(int i = 0; i < hids.size(); i++){
				HongbaoActivity activity3  = hongbaoService.getActivityById(hids.get(i));
				HongbaoActivity activityTemp = hongbaoService.getCountByBean(hids.get(i));
				activity3.setValidenum(activityTemp.getValidenum());
				activity3.setValidetotalprice(activityTemp.getValidetotalprice());
				activity3.setRecivenum(activityTemp.getRecivenum());
				activity3.setRecivetotalprice(activityTemp.getRecivetotalprice());
				activity3.setTransrate(activityTemp.getTransrate());
				hongbaoService.updateActivity(activity3);
			}
		}
		logger.info("红包统计结束>>>>>>>>>>>");
	}

	
}
