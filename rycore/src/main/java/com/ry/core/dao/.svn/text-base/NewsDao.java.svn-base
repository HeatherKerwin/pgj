package com.ry.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.util.page.PageResults;

@Repository
public interface NewsDao {

	PageResults<News> getPageList(NewsForm nf, Integer currentPage, Integer pageSize);

	List<News> getList(News news);

	List<News> getList(Integer start, Integer end);

	void addNews(News news);

	public void deleteNews(Integer id);

	public News getById(Integer id);

	public Integer saveNews(News s);

	public void updateNews(News s);

	public List<News> getList(Integer start, Integer end, Integer type);

	public List<News> getTopList(Integer type, Integer topflag);

	public PageResults<News> findPageModel(String hql, String hqlcount, int pageNo, int pageSize, Object[] values);

	/**
	 * 查询咨询（含多条件）
	 * @author WKX
	 * @param nf
	 * @since 2016年12月16日 下午3:22:32
	 */
	public List<News> getList(NewsForm nf);
}