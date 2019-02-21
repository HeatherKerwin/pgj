package com.ry.core.service.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OrgCityDao;
import com.ry.core.entity.OrgCity;
import com.ry.core.service.OrgCityService;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Service
public class OrgCityServiceImpl implements OrgCityService{
	
	@Resource
	OrgCityDao orgCityDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getById(java.lang.Integer)
	 */
	public OrgCity getById(Integer id){
		return orgCityDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getRegionByOrgId(Integer orgId){
		return orgCityDao.getRegionByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getInOrgIdAndCityName(java.util.List, java.lang.String)
	 */
	public List<Map<String,Object>> getInOrgIdAndCityName(List<Integer> orgIds,String cityName){
		return orgCityDao.getInOrgIdAndCityName(orgIds, cityName);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getInOrgIdAndCityId(java.util.List, java.lang.Integer)
	 */
	public List<Map<String,Object>> getInOrgIdAndCityId(List<Integer> orgIds,Integer cityId){
		return orgCityDao.getInOrgIdAndCityId(orgIds, cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#updateByOrgId(java.lang.Integer[], java.lang.Integer)
	 */
	public void updateByOrgId(Integer[] citys,Integer orgId) throws Exception{
		orgCityDao.deleteByOrgId(orgId);
		OrgCity city = null;
		if(citys!=null && citys.length>0){
			int flag = 0;
			for(Integer cityId:Arrays.asList(citys)){
				city = new OrgCity();
				city.setCityId(cityId);
				city.setOrgId(orgId);
				orgCityDao.saveModel(city);
				flag ++;
				if(flag>10)throw new Exception("选择的交易城市超过最大值");//防止数据有误跳出循环
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer id) throws Exception{
		orgCityDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#save(com.ry.core.entity.OrgCity)
	 */
	public void save(OrgCity orgCity){
		if(orgCity!=null && orgCity.getId()!=null){
			OrgCity temp = orgCityDao.getById(orgCity.getId());
			if(temp!=null){
				temp.setCityId(orgCity.getCityId());
				temp.setAddress(orgCity.getAddress());
				temp.setLatitude(orgCity.getLatitude());
				temp.setLongitude(orgCity.getLongitude());
				temp.setOther(orgCity.getOther());
				orgCityDao.updateModel(temp);
			}else{
				orgCityDao.saveModel(orgCity);
			}
		}else{
			orgCityDao.saveModel(orgCity);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getOrgCity(com.ry.core.entity.OrgCity)
	 */
	public OrgCity getOrgCity(OrgCity orgCity){
		return orgCityDao.getOrgCity(orgCity);
	}
	
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getByOrgId(java.lang.Integer)
	 */
	public List<OrgCity> getByOrgId(Integer orgId){
		return orgCityDao.getByOrgId(orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getCityByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getCityByOrgId(Integer orgId) {
		return orgCityDao.getCityByOrgId(orgId);
	}
	
	@Override
	public List<Map<String, Object>> getOrgCityList() {
		return orgCityDao.getOrgCityList();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getAndHasPriceByOrgId(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String,Object>> getAndHasPriceByOrgId(Integer orgId,String day){
		return orgCityDao.getAndHasPriceByOrgId(orgId, day);
	}
}