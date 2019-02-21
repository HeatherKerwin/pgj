package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.NewsCrawlerDao;
import com.ry.core.entity.NewsCrawler;
import com.ry.core.form.NewsCrawlerForm;
import com.ry.util.page.PageResults;
@Repository
public class NewsCrawlerDaoImpl extends BaseDao<NewsCrawler, Integer> implements NewsCrawlerDao {
	
	@Override
	public NewsCrawler getById(Integer id) {
		return get(id);
	}
	
	@Override
	public void saveModel(NewsCrawler newscrawler) {
		save(newscrawler);
	}
	
	@Override
	public void updateModel(NewsCrawler newscrawler) {
		update(newscrawler);
	}

	@Override
	public PageResults<NewsCrawler> getPageList(NewsCrawlerForm ncf, Integer currentPage, Integer pageSize) {
		StringBuffer hql=new StringBuffer("from news_crawler where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(ncf!=null){
			if(ncf.getBeginDate()!=null){
				hql.append(" and create_time >= ?");
				paramList.add(ncf.getBeginDate());
			}
			if(ncf.getEndDate()!=null){
				hql.append(" and create_time <?");
				paramList.add(ncf.getEndDate());
			}
			if(!(ncf.getType()==null||ncf.getType().equals("-1"))){
				hql.append(" and type =?");
				paramList.add(ncf.getType());
			}
			if(ncf.getTitle()!=null){
				hql.append(" and title like?");
				paramList.add("%"+ncf.getTitle()+"%");
			}
			if(!(ncf.getState()==null||ncf.getState().equals("-1"))){
				hql.append(" and state =?");
				paramList.add(ncf.getState());
			}
		}
		hql.append(" order by create_time desc");
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}

	@Override
	public void deleteById(Integer id) {
		delete(id);
	}

	@Override
	public Long getByTitle(String title) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(*) FROM news_crawler WHERE 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(title!=null){
			hql.append(" and title=?");
			paramList.add(title);
		}
		return countByHql(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.NewsCrawlerDao#getCountByUrl(java.lang.String, java.lang.String)
	 */
	public Long getCountByUrl(String url, String date) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(*) FROM news_crawler WHERE urlSource = ? AND createTime = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(url);
		params.add(date);
		return countByHql(hql.toString(), params.toArray());
	}
}