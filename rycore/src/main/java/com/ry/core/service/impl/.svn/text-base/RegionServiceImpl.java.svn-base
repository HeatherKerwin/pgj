package com.ry.core.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.RegionDao;
import com.ry.core.entity.Region;
import com.ry.core.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService{
	
	@Resource
	RegionDao regionDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getById(java.lang.Integer)
	 */
	public Region getById(Integer id){
		return regionDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getByType(java.lang.String)
	 */
	public List<Region> getByType(String type){
		return regionDao.getByType(type);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getByNameAndType(java.lang.String, java.lang.String)
	 */
	public List<Region> getByNameAndType(String name,String type){
		return regionDao.getByNameAndType(name, type);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getByCode(java.lang.String)
	 */
	public Region getByCode(String code){
		return regionDao.getByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getByParent(java.lang.Integer)
	 */
	@Override
	public List<Region> getByParent(Integer parent_id) {
		return regionDao.getByParent(parent_id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RegionService#getByName(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getByName(Integer parent_id) {
		return regionDao.getByName(parent_id);
	}
}