package com.ry.core.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PhonedetailDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Phonedetail;
import com.ry.core.service.PhonedetailService;

@Service
public class PhonedetailServiceImpl extends BaseDao<Actionlog, Integer> implements
		PhonedetailService {

	@Resource
	PhonedetailDao phonedetailDao;

	@Override
	public void addPhonedetail(Phonedetail phonedetail) {
		
		phonedetailDao.addPhonedetail(phonedetail);
	}

	@Override
	public void updatePhonedetail(Phonedetail phonedetail) {
		
		phonedetailDao.updatePhonedetail(phonedetail);
	}

	@Override
	public List<Phonedetail> getPhonedetail(Phonedetail phonedetail) {
		
		return phonedetailDao.getPhonedetail(phonedetail);
	}
	
	@Override
	public Long countByQudaoandDay(String qdp,String createDate){
		return phonedetailDao.countByQudaoandDay(qdp, createDate);
	}
	
	@Override
	public Long countByQudaoandMonth(String qdp,String createDate){
		return phonedetailDao.countByQudaoandMonth(qdp, createDate);
	}
}
