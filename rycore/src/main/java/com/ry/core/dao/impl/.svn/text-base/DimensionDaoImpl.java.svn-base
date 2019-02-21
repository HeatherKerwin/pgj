package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DimensionDao;
import com.ry.core.entity.Dimension;

@Repository
public class DimensionDaoImpl extends BaseDao<Dimension, Integer> implements DimensionDao{
	
	@Override
	public List<Dimension> getByOrgIdAndMonth(Integer orgId, String yearMonth) {
		StringBuffer hql=new StringBuffer("from dimension where 1=1");
		List<Object> paramList = new ArrayList<Object>();
			if(orgId!=null){
				hql.append(" and orgId = ?");
				paramList.add(orgId);
			}
			if(StringUtils.hasText(yearMonth)){
				hql.append(" and mouth = ?");
				paramList.add(yearMonth);
			}
		return queryByHql(hql.toString(), paramList.toArray());
	}

	@Override
	public void saveOrUpdate(Dimension d) {
		if(d!=null && d.getId()!=null)update(d);
		else save(d);

	}
	
	/**
     * 新增数据
     * @author cx
     * @since 2016年5月12日 
     */
	public void saveDimension(Dimension dimension){
		save(dimension);
	}
	
	/**
     * 根据org_id和时间查找类
     * @author cx
     * @since 2016年5月12日 
     */
	public List<Dimension> findDimension(String month,Integer org_id){
		String hql = "from dimension as dim where dim.orgId = ? and dim.mouth= ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(org_id);
		paras.add(month);
		List<Dimension> dimension = queryByHql(hql,paras.toArray());
		return dimension;
	}
	
	/**
     * 更新数据
     * @author cx
     * @since 2016年5月12日 
     */
	public void updateDimension(Dimension dimension){
		update(dimension);
	}
}