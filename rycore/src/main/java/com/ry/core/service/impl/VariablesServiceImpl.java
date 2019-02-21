package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.VariablesDao;
import com.ry.core.entity.Variables;
import com.ry.core.service.VariablesService;
import com.ry.util.page.PageResults;


@Service
public class VariablesServiceImpl implements VariablesService{

	@Resource
	VariablesDao variablesDao;
	
	@Override
	public PageResults<Variables> getPageList(Integer currentPage, Integer pageSize) {
		PageResults<Variables> pageResults = variablesDao.getPageList( currentPage, pageSize);
		return pageResults;
	}
	
	@Override
	public void saveInfo(Variables v) throws Exception {
		variablesDao.saveInfo(v);
	}
	
	@Override
	public Variables get(Integer id) {
		return variablesDao.get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.VariablesService#getByCode(java.lang.String, java.lang.String)
	 */
	public String getByCode(String code,String default_){
		List<Variables> list = variablesDao.getByCode(code);
		if(list!=null && list.size()>0){
			Variables temp = list.iterator().next();
			if(temp!=null && StringUtils.hasText(temp.getValue())){
				return temp.getValue();
			}else{
				return default_;
			}
		}else{
			return default_;
		}
	}
}