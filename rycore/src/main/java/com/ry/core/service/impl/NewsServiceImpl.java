package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.NewsDao;
import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.core.service.NewsService;
import com.ry.util.page.PageResults;

@Service
public class NewsServiceImpl implements  NewsService{
	@Resource
	NewsDao  newsDao;

	@Override
	public List<News> getList(Integer start, Integer end) {
		List<News> list = newsDao.getList(start, end);
		return list;
	}

	@Override
	public PageResults<News> getPageList(NewsForm nf, Integer currentPage,
			Integer pageSize) {
		
		PageResults<News> pageResults = newsDao.getPageList(nf, currentPage, pageSize);
		List<News> newsList1 = pageResults.getResults();
		List<News> newsList2 = new ArrayList<News>();
		if(!(newsList1 == null || newsList1.size() == 0)){
			for(News baseEntity : newsList1){
				News news = (News)baseEntity;
				String title = news.getTitle();
				if (news.getPublishtime() != null) {
					news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
				}				
				news.setTitleShow(title.length()>30?title.substring(0,30)+"...":title);
				newsList2.add(news);
			}
		}
		pageResults.setResults(newsList2);
		return pageResults;
	}

	@Override
	public void updateAllNews(List<News> newsList) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer saveNews(News s) {
		return newsDao.saveNews(s);
	}
	@Override
	public void removeNews(Integer id) {
		newsDao.deleteNews(id);
	}

	@Override
	public void modifyNews(News s) {
		newsDao.updateNews(s);
		
	}
	@Override
	public News getNewsById(Integer id) {
		return newsDao.getById(id);
	}

	@Override
	public List<News> getList(News news) {
		return newsDao.getList(news);
	}

	@Override
	public List<News> getList(Integer start, Integer end, Integer type) {
		return newsDao.getList(start, end, type);
	}

	@Override
	public PageResults<News> findPageModel(String hql, String hqlcount,
			int pageNo, int pageSize, Object[] values) {
		//String hql = "from news where type like " + type +" and topflag = 1 order by id desc";
		return null;
	}

	@Override
	public List<News> getTopList(Integer type, Integer topflag) {

		return newsDao.getTopList(type, topflag);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.NewsService#getList(com.ry.core.form.NewsForm)
	 */
	public List<News> getList(NewsForm nf){
		return newsDao.getList(nf);
	}
}