package com.ry.core.dao;
import com.ry.core.entity.NewsCrawler;
import com.ry.core.form.NewsCrawlerForm;
import com.ry.util.page.PageResults;


public interface NewsCrawlerDao {
	
	/**
	 * 通过id得到NewsCrawler对象
	 * @author ZY
	 * @param id
	 * @since 2016年7月28日 下午3:45:49
	 */
	public NewsCrawler getById(Integer id);
	
	/**
	 * 保存NewsCrawler对象
	 * @author ZY
	 * @param newscrawler
	 * @since 2016年7月28日 下午3:45:53
	 */
	public void saveModel(NewsCrawler newscrawler);
	
	/**
	 * 更新NewsCrawler对象
	 * @author ZY
	 * @param newscrawler
	 * @since 2016年7月28日 下午3:46:01
	 */
	public void updateModel (NewsCrawler newscrawler);
	
	/**
	 * 获取爬虫资讯列表
	 * @author KHC
	 * @param ncf
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年8月9日 下午6:13:43
	 */
	public PageResults<NewsCrawler> getPageList(NewsCrawlerForm ncf,Integer currentPage,Integer pageSize);
	
	/**
	 *  根据id删除
	 * @author KHC
	 * @param id
	 * @since 2016年8月9日 下午6:30:23
	 */
	public void deleteById(Integer id);
	
	/**
	 * 根据标题查询
	 * @author KHC
	 * @param title
	 * @since 2016年8月24日 上午10:54:35
	 */
	public Long getByTitle(String title);
	
	/**
	 * 获取当天新闻网站的数量
	 * @author KHC
	 * @param url
	 * @since 2017年2月9日 下午1:49:14
	 */
	public Long getCountByUrl(String url,String date);
}