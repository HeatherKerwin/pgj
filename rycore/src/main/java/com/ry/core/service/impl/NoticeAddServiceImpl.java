package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.NoticeAddDao;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.service.NoticeAddService;

@Service
public class NoticeAddServiceImpl implements NoticeAddService {

	@Resource
	NoticeAddDao noticeAddDao;
	
	public NoticeAdd getById(Integer id){
		return noticeAddDao.getById(id);
	}
	
	public void saveModel(NoticeAdd noticeAdd){
		noticeAddDao.saveModel(noticeAdd);
	}
	
	public void updateModel(NoticeAdd noticeAdd){
		noticeAddDao.updateModel(noticeAdd);
	}
	
	public void deleteById(Integer id){
		noticeAddDao.deleteById(id);
	}
	
	public List<NoticeAdd> getByNoticeId(Integer noticeId){
		return noticeAddDao.getByNoticeId(noticeId);
	}
	
	public List<NoticeAdd> getByNowTime(Date nowTime){
    	return noticeAddDao.getByNowTime(nowTime);
	}
}