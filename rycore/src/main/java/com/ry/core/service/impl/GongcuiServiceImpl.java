package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.GongcuiDao;
import com.ry.core.entity.Gongcui;
import com.ry.core.service.GongcuiService;

@Service
public class GongcuiServiceImpl implements GongcuiService {

	@Resource
	GongcuiDao gongcuiDao;
	
	@Override
	public void addAllGongcui(List<Gongcui> gongcuiList) {	
		if(gongcuiList!=null&&gongcuiList.size()!=0){
			for(Gongcui gongcui : gongcuiList){
				gongcuiDao.addGongcui(gongcui);
			}
		}
	}

	@Override
	public List<Gongcui> getList(Integer start, Integer end) {		
		return gongcuiDao.getList(start, end);
	}

	@Override
	public List<Gongcui> getList(String gongcuinumber) {
		
		return gongcuiDao.getList(gongcuinumber);
	}

}
