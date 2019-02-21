package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.SiteContentDao;
import com.ry.core.entity.SiteContent;
import com.ry.core.service.SiteContentService;

@Service
public class SiteContentServiceImpl implements  SiteContentService{
	@Resource
	SiteContentDao  siteContentDao;

	@Override
	public SiteContent getById(Integer id){
		return siteContentDao.getById(id);
	}
	
	@Override
	public void saveModel(SiteContent siteContent) {
		siteContentDao.saveModel(siteContent);
	}

	@Override
	public void updateModel(SiteContent siteContent){
		siteContentDao.updateModel(siteContent);
	}
	
	@Override
	public SiteContent get(){
		List<SiteContent> list =siteContentDao.get();
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
}
