package com.ry.core.service;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.NewsCrawler;
import com.ry.core.form.NewsCrawlerForm;
import com.ry.util.page.PageResults;


/**
 * @author ry-java-7
 *
 */
public interface NewsCrawlerService {
	
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
	 * 更新修改NewsCrawler对象
	 * @author ZY
	 * @param newscrawler
	 * @since 2016年7月28日 下午3:46:01
	 */
	public void updateModel (NewsCrawler newscrawler);
	
	/**
	 * 获取爬虫资讯分页列表
	 * @author KHC
	 * @param ncf
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年8月9日 下午6:20:15
	 */
	public PageResults<NewsCrawler> getPageList(NewsCrawlerForm ncf,Integer currentPage,Integer pageSize);
	
	/**
	 * 根据id删除对象
	 * @author KHC
	 * @param id
	 * @since 2016年8月9日 下午6:31:56
	 */
	public void deleteById(Integer id);
	
	/**
	 * 根据不同网址爬虫数据到数据库
	 * @author KHC
	 * @param url
	 * @param pageIndex
	 * @param list
	 * @param articleSource 
	 * @since 2016年8月23日 上午10:30:51
	 */
	public void saveAll(String url,Integer pageIndex,List<Map<String,String>> list,String articleSource);
	
	/**
	 * 根据不同网址爬虫数据到数据库
	 * @author MH
	 * @param url
	 * @param pageIndex
	 * @param list
	 * @param articleSource 
	 * @throws Exception 
	 */
	public void savepiaojuAll(String url,Integer pageIndex,List<Map<String,String>> list,String articleSource) throws Exception;
	
}