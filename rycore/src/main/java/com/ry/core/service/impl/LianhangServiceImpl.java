package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.LianhangDao;
import com.ry.core.entity.Lianhang;
import com.ry.core.entity.News;
import com.ry.core.service.LianhangService;
import com.ry.util.page.PageResults;

@Service
public class LianhangServiceImpl implements LianhangService {

	@Resource
	LianhangDao lianhangDao;
	
	@Override
	public void addAllLianhang(List<Lianhang> lianhangList) {
		if(lianhangList!=null&&lianhangList.size()!=0){
			for(Lianhang lianhang : lianhangList){
				lianhangDao.addLianhang(lianhang);
			}
		}
	}

	@Override
	public List<Lianhang> getList(Lianhang lianhang) {		
		return lianhangDao.getList(lianhang);
	}

	public PageResults<Lianhang> getPageList(Lianhang lianhang, Integer currentPage, Integer pageSize) {
		PageResults<Lianhang> pageResults = lianhangDao.getPageList(lianhang, currentPage, pageSize);
		return pageResults;
	}
	
	@Override
	public PageResults<Lianhang> getListBylianhang(Lianhang lianhang,Integer pageIndex,Integer pageSize) {		
		return lianhangDao.getListBylianhang(lianhang,pageIndex,pageSize);
	}
}
