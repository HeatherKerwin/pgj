package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PiaojujiangtangDao;
import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.util.page.PageResults;

@Repository
public class PiaojujiangtangDaoImpl extends BaseDao<Piaojujiangtang,Integer > implements PiaojujiangtangDao{
   
	@Override
	public PageResults<Piaojujiangtang> getPageList(PiaojujiangtangForm nf,Integer currentPage,Integer pageSize) {
		StringBuffer hql=new StringBuffer("from piaojujiangtang where 1=1 ");
		List paramList = new ArrayList();
		if(nf!=null){
			if(nf.getBeginDate()!=null){
				hql.append(" and publishTime >= ?");
				paramList.add(nf.getBeginDate());
			}
			if(nf.getEndDate()!=null){
				hql.append(" and publishTime <?");
				paramList.add(nf.getEndDate());
			}
			if (nf.getType()!=null) {
				hql.append(" and type like ?");
				paramList.add(nf.getType());
			}
			if (nf.getTitle()!=null && !"".equals(nf.getTitle().trim())) {
				hql.append(" and title like ?");
				paramList.add("%"+nf.getTitle()+"%");
			}
		}
		hql.append(" order by publishtime desc");
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}

	@Override
	public void addPiaojujiangtang(Piaojujiangtang piaojujiangtang) {
		save(piaojujiangtang);
		
	}
//	@Override
//	public void updateNews(News news) {
//		update(news);
//		
//	}
	@Override
	public void updatePiaojujiangtang(Piaojujiangtang s) {
		 update(s);
	}

	@Override
	public void deletePiaojujiangtang(Integer id) {
		delete(id);
		
	}
	@Override
	public Piaojujiangtang getById(Integer id) {
		return get(id);
	}
	@Override
	public Integer savePiaojujiangtang(Piaojujiangtang s){
		return save(s);
	}

	@Override
	public List<Piaojujiangtang> getList(Integer start, Integer end) {		
		List<Piaojujiangtang> pList = (List<Piaojujiangtang>) getSession().createQuery("from piaojujiangtang order by publishtime desc ").setFirstResult(start).setMaxResults(end).list();		
		return pList;
	}
	
}
