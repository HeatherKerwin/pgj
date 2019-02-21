package com.bbs.service;

import com.bbs.model.CheckIn;

public interface CheckInService {
	
	public CheckIn getByUidAndDay(Long uid,String day);
	
	public boolean save(CheckIn checkIn);
}