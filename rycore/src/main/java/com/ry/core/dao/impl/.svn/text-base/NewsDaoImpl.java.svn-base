package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.NewsDao;
import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.util.page.PageResults;

@Repository
public class NewsDaoImpl extends BaseDao<News,Integer > implements NewsDao{
   
	@Override
	public PageResults<News> getPageList(NewsForm nf,Integer currentPage,Integer pageSize) {
		StringBuffer hql=new StringBuffer("from news where 1=1 and state<>2");
		List<Object> paramList = new ArrayList<Object>();
		if(nf!=null){
			if(nf.getBeginDate()!=null){
				hql.append(" and createTime >= ?");
				paramList.add(nf.getBeginDate());
			}
			if(nf.getEndDate()!=null){
				hql.append(" and createTime <=?");
				paramList.add(nf.getEndDate());
			}
			if(!(nf.getType()==null||nf.getType().equals(""))){
				hql.append(" and type =?");
				paramList.add(nf.getType());
			}
			if(nf.getTitle()!=null){
				hql.append(" and title like?");
				paramList.add("%"+nf.getTitle()+"%");
			}
			if(nf.getContent()!=null){
				hql.append(" and content like?");
				paramList.add("%"+nf.getContent()+"%");
			}
			if(!(nf.getState()==null||nf.getState().equals(""))){
				hql.append(" and state =?");
				paramList.add(nf.getState());
			}
		}
		if(nf.getTimeSort()!=null){
			hql.append(" order by createTime desc");
		}else{
			hql.append(" order by publishtime desc");
		}
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}

	@Override
	public void addNews(News news) {
		save(news);
		
	}
//	@Override
//	public void updateNews(News news) {
//		update(news);
//		
//	}
	@Override
	public void updateNews(News s) {
		 update(s);
	}

	@Override
	public void deleteNews(Integer id) {
		delete(id);
		
	}
	@Override
	public News getById(Integer id) {
		return get(id);
	}
	@Override
	public Integer saveNews(News s){
		return save(s);
	}

	@Override
	public List<News> getList(News news) {
		
		return getListByHQL("from news",null);
	}

	@Override
	public List<News> getList(Integer start, Integer end) {			
		Query query = getSession().createQuery("from news where state = 0 order by publishtime desc ");
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<News> newsList = (List<News>) query.list();
		
		return newsList;			
	}

	@Override
	public List<News> getList(Integer start, Integer end, Integer type) {			
		Query query = getSession().createQuery("from news where type like ? order by publishtime desc ");
		query.setParameter(0, type);
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<News> newsList = (List<News>) query.list();
		
		return newsList;			
	}

	@Override
	public PageResults<News> findPageModel(String hql, String hqlcount,
			int pageNo, int pageSize, Object[] values) {
		
		return findPageByFetchedHql(hql, hqlcount, pageNo, pageSize, values);
	}

	@Override
	public List<News> getTopList(Integer type, Integer topflag) {
		
		String hql = "from news where type like ? and topflag = ? order by id desc";
		
		return getListByHQL(hql, new Object[]{type, topflag});
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.NewsDao#getList(com.ry.core.form.NewsForm)
	 */
	public List<News> getList(NewsForm nf) {
		StringBuffer hql = new StringBuffer("from news where 1=1 and state<>2");
		List<Object> params = new ArrayList<Object>();
		if(nf!=null){
			if(nf.getBeginDate()!=null){
				hql.append(" and createTime >= ?");
				params.add(nf.getBeginDate());
			}
			if(nf.getEndDate()!=null){
				hql.append(" and createTime <=?");
				params.add(nf.getEndDate());
			}
			if(!(nf.getType()==null||nf.getType().equals(""))){
				hql.append(" and type =?");
				params.add(nf.getType());
			}
			if(nf.getTitle()!=null){
				hql.append(" and title like?");
				params.add("%"+nf.getTitle()+"%");
			}
			if(nf.getContent()!=null){
				hql.append(" and content like?");
				params.add("%"+nf.getContent()+"%");
			}
			if(!(nf.getState()==null||nf.getState().equals(""))){
				hql.append(" and state =?");
				params.add(nf.getState());
			}
			if(nf.getId()!=null){
				hql.append(" and id =?");
				params.add(nf.getId());
			}
		}
		if(nf.getTimeSort()!=null){
			hql.append(" order by createTime desc");
		}else{
			hql.append(" order by publishtime desc");
		}
		return queryByHql(hql.toString(), params.toArray());
	}
}