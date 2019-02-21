package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DimensionDao;
import com.ry.core.entity.Dimension;
import com.ry.core.service.DimensionService;

@Service
public class DimensionServiceImpl implements DimensionService {

	@Resource
	DimensionDao dimensionDao; 
	
	@Override
	public Dimension getByOrgIdAndMonth(Integer orgId, String yearMonth) {
		List<Dimension> list =  dimensionDao.getByOrgIdAndMonth(orgId, yearMonth);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	@Override
	public void saveOrUpdate(Dimension d) {
		dimensionDao.saveOrUpdate(d);
	}

	public void saveDimension(Dimension dimension) {
		dimensionDao.saveDimension(dimension);
	}
	
	public Dimension findDimension(String month,Integer org_id){
		List<Dimension> dimension = dimensionDao.findDimension(month, org_id);
		return dimension.get(0);
	}
	
	public void updateDimension(Dimension dimension){
		dimensionDao.updateDimension(dimension);
	}
}
