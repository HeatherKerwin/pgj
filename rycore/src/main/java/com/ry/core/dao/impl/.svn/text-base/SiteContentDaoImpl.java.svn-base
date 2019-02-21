package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.SiteContentDao;
import com.ry.core.entity.SiteContent;

@Repository
public class SiteContentDaoImpl extends BaseDao<SiteContent,Integer > implements SiteContentDao{
	
	@Override
	public SiteContent getById(Integer id){
		return get(id);
	}
	
	@Override
	public void saveModel(SiteContent siteContent) {
		save(siteContent);
	}

	@Override
	public void updateModel(SiteContent siteContent){
		 update(siteContent);
	}
	
	@Override
	public List<SiteContent> get(){
		List<SiteContent> list=(List<SiteContent>) getListByHQL(" from site_content where 1=1 order by publishtime desc" ,null);
		return list;
	}
}
