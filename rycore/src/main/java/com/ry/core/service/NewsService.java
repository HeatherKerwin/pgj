package com.ry.core.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.util.page.PageResults;

@Repository
public interface NewsService {
	List<News> getList(News news);
	public List<News> getList(Integer start, Integer end);
	public List<News> getList(Integer start, Integer end, Integer type);
	PageResults<News> getPageList(NewsForm nf,Integer currentPage,Integer pageSize);
	public List<News> getTopList(Integer type, Integer topflag);
	void updateAllNews(List<News> newsList);
	public Integer saveNews(News s);
	public void removeNews(Integer id);
	public void modifyNews(News s);
	public News getNewsById(Integer id);
	public PageResults<News> findPageModel(String hql, String hqlcount, int pageNo, int pageSize, Object[] values);
	
	/**
	 * 查询咨询（含多条件）
	 * @author WKX
	 * @param nf
	 * @since 2016年12月16日 下午3:25:21
	 */
	public List<News> getList(NewsForm nf);
}