package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.VariablesDao;
import com.ry.core.entity.Variables;
import com.ry.util.page.PageResults;

@Repository
public class VariablesDaoImpl extends BaseDao<Variables,Integer> implements VariablesDao{

	@Override
	public PageResults<Variables> getPageList(Integer currentPage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("from variables");
		String countHql = "select count(*) "+ hql.toString();
		return findPageByFetchedHql(hql.toString(),countHql,currentPage,pageSize,null);
	}

	@Override
	public void saveInfo(Variables v) throws Exception {
		if(v!=null && v.getId()!=null)update(v);
		else save(v);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.VariablesDao#getByCode(java.lang.String)
	 */
	public List<Variables> getByCode(String code) {
		List<Variables> lists =  getListByHQL("from variables where code = ?", new Object[]{code});
		return lists;
	}
	
	@Override
	public Variables getpingtai(Integer id){
		StringBuffer hql = new StringBuffer(" from variables where ");
		if( id == 1){
			hql.append(" code = 'PLATFORM_1' ");
		}else if( id == 2){
			hql.append(" code = 'PLATFORM_2' ");
		}else if( id == 3){
			hql.append(" code = 'PLATFORM_3' ");
		}else if( id == 4){
			hql.append(" code = 'PLATFORM_4' ");
		}else if( id == 5){
			hql.append(" code = 'PLATFORM_5' ");
		}else if( id == 6){
			hql.append(" code = 'PLATFORM_6' ");
		}else if( id == 7){
			hql.append(" code = 'PLATFORM_7' ");
		}else if( id == 8){
			hql.append(" code = 'PLATFORM_8' ");
		}else if( id == 9){
			hql.append(" code = 'PLATFORM_9' ");
		}else if( id == 10){
			hql.append(" code = 'PLATFORM_10' ");
		}else if( id == 11){
			hql.append(" code = 'PLATFORM_11' ");
		}else if( id == 12){
			hql.append(" code = 'PLATFORM_12' ");
		}else if( id == 13){
			hql.append(" code = 'PLATFORM_13' ");
		}else if( id == 14){
			hql.append(" code = 'PLATFORM_14' ");
		}else if( id == 15){
			hql.append(" code = 'PLATFORM_15' ");
		}else if( id == 16){
			hql.append(" code = 'PLATFORM_16' ");
		}else if( id == 17){
			hql.append(" code = 'PLATFORM_17' ");
		}else if( id == 18){
			hql.append(" code = 'PLATFORM_18' ");
		}else if( id == 19){
			hql.append(" code = 'PLATFORM_19' ");
		}else if( id == 20){
			hql.append(" code = 'PLATFORM_20' ");
		}else if( id == 21){
			hql.append(" code = 'PLATFORM_21' ");
		}
		return getByHQL(hql.toString(), null);
	}
}