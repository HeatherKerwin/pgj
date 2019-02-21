package com.bbs.service.impl;

import com.bbs.model.CheckIn;
import com.bbs.model.PointLog.Fun;
import com.bbs.service.CheckInService;
import com.bbs.service.PointLogService;
import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class CheckInServiceImpl implements CheckInService {
	
	@Inject
	private PointLogService pointLogService;
	
	@Override
	public CheckIn getByUidAndDay(Long uid,String day){
		if(uid==null || StringKit.isBlank(day)){
			return null;
		}
		return AR.find("select * from t_checkin where uid =? AND day = ?",uid,day).first(CheckIn.class);
	}
	
	public boolean save(CheckIn checkIn) {
		try {
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_checkin(id, uid, day, create_time) values (?, ?, ?, ?)",
					checkIn.getId(), checkIn.getUid(), checkIn.getDay(), time).executeUpdate();
			
			pointLogService.save(checkIn.getUid(),checkIn.getUid(),Fun.QIANDAO,null);//签到（获取积分）
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}