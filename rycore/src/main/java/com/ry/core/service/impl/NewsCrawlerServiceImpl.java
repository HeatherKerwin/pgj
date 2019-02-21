package com.ry.core.service.impl;


import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.NewsCrawlerDao;
import com.ry.core.entity.NewsCrawler;
import com.ry.core.entity.ReptileTicket;
import com.ry.core.form.NewsCrawlerForm;
import com.ry.core.service.NewsCrawlerService;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.Result;
import com.ry.util.page.PageResults;

@Service
public class NewsCrawlerServiceImpl extends BaseDao<NewsCrawler, Integer> implements NewsCrawlerService {
	
	private static Map<String, Object> index_params = new HashMap<String, Object>();
	
	@Resource
	NewsCrawlerDao newsCrawlerDao;
	
	@Resource
	ReptileTicketServiceImpl reptileTicketServiceImpl;
	
	@Override
	public NewsCrawler getById(Integer id) {
		return newsCrawlerDao.getById(id);
	}
	
	@Override
	public void saveModel(NewsCrawler newscrawler) {
		newsCrawlerDao.saveModel(newscrawler);
	}
	
	@Override
	public void updateModel(NewsCrawler newscrawler) {
		newsCrawlerDao.updateModel(newscrawler);
	}
	
	/**
	 * 缓存值
	 * @author KHC
	 * @param params
	 * @param newsKey
	 * @since 2017年2月10日 上午10:15:32
	 */
	public static synchronized Map<String,Object> setIndexParams(Map<String,Object> params,String newsKey){
		if(params==null){
			return index_params = new HashMap<String, Object>();
		}
		if(index_params!=null){
			index_params = params;
			if(params.get(newsKey)==null)index_params.put(newsKey, 0);//设置时间
		}else {
			if(params.get(newsKey)==null)params.put(newsKey, 0);//设置时间
			index_params = params;
		}
		return index_params;
	}
	
	@Override
	public PageResults<NewsCrawler> getPageList(NewsCrawlerForm ncf, Integer currentPage, Integer pageSize) {
		PageResults<NewsCrawler> pageResults = newsCrawlerDao.getPageList(ncf, currentPage, pageSize);
		List<NewsCrawler> newsList1 = pageResults.getResults();
		List<NewsCrawler> newsList2 = new ArrayList<NewsCrawler>();
		if(!(newsList1 == null || newsList1.size() == 0)){
			for(NewsCrawler baseEntity : newsList1){
				NewsCrawler news = (NewsCrawler)baseEntity;
				String title = news.getTitle();
				if (news.getCreateTime() != null) {
					news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getCreateTime()));
				}				
				news.setTitleShow(title.length()>30?title.substring(0,30)+"...":title);
				newsList2.add(news);
			}
		}
		pageResults.setResults(newsList2);
		return pageResults;
	}

	@Override
	public void deleteById(Integer id) {
		newsCrawlerDao.deleteById(id);
	}
	
	/**
	 * 解析请求
	 * @author WKX
	 * @param url 请求路径
	 * @param pageIndex 从第几页开始（初始化调用时传null）
	 * @param list 结果集（初始化调用时传null）
	 */
	private static List<Map<String,String>> check(String url,Integer pageIndex,List<Map<String,String>> list) {
		if(list==null)list = new ArrayList<Map<String,String>>();
		Map<String,String> result = null;
		try {
			url = URLDecoder.decode(url,"utf-8");
			if(org.springframework.util.StringUtils.isEmpty(url))throw new Exception("请输入正确的链接!");
			Map<String,String> rule = findRule(url);
			if(rule == null)throw new Exception("请输入正确的链接!");
			Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();
			int size = doc.select(rule.get("getOne")).size();
			if(size>0){
				Elements all = doc.select(rule.get("getOne"));
				for(Element first:all){
					result = new HashMap<String, String>();
					//解析文章链接
					String href = first.select(rule.get("href")).attr(rule.get("href-prefix"));
					if(href.contains("./")){
						String [] hrefs = href.split("\\./");
						href = hrefs[1];
					}
					//解析图片
					String img = "";
					if(rule.get("img")!=null){
						img = first.select(rule.get("img")).attr(rule.get("img-prefix"));
					}
					if(rule.get("href-add")!=null){
						href = rule.get("href-add") + href;
					}
					
					if(rule.get("content-info") != null && rule.get("content-info") != ""){
						int index = href.lastIndexOf(rule.get("content-index")); //位置下标
						StringBuilder sb1 = new StringBuilder(href);//构造一个StringBuilder对象
						sb1.insert(index+ Integer.valueOf(rule.get("addindex")).intValue(), rule.get("content-info"));//在指定的位置插入指定的字符串
						href = sb1.toString();
					}
					
					//解析详情
					doc = Jsoup.connect(href).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();
					String title = doc.select(rule.get("title")).html();
					if(org.springframework.util.StringUtils.isEmpty(title)){
						title = doc.select(rule.get("title1")).html();
					}
					//解析时间
					String time = "";
					Elements timeEle = doc.select(rule.get("time"));
					if(timeEle==null || org.springframework.util.StringUtils.isEmpty(timeEle.html())){
						timeEle = doc.select(rule.get("time1"));
					}
					if(rule.get("time-num")!=null){
						String t = rule.get("time-num");
						time = timeEle.get(Integer.valueOf(t)).html();
						if(time.contains("<a")){
							String [] times = time.split("<a");
							time = times[0];
						}
					}else{
						time = timeEle.get(0).html();
					}
					time = getTime(time);
					//解析内容
					String content = doc.select(rule.get("content")).html();
					result.put("title", title);
					result.put("time", time);
					result.put("img", img);
					result.put("href", href);
					result.put("content", content);
					
					if(rule.get("court") != null){
						String court = doc.select(rule.get("court")).html();
						result.put("court", court);
					}
					
					if(has(time)){
						list.add(result);
					}
				}
			}
			doc = null;
			if("ok".equals(rule.get("json"))){
				Map<String,String>  params1 = new HashMap<String, String>();
				//"http://rmfygg.court.gov.cn/psca/lgnot/solr/searchBulletinInterface.do?callback=jQuery152024430807740464644_1494485926176&start=1&limit=16&wd=rmfybulletin&list%5B0%5D=bltntype%3A&_=1494486834250"
				Result r = HttpUtil.sendGet(rule.get("href"), null,params1,"utf-8");
				String abc =  EntityUtils.toString(r.getHttpEntity());
		    	int index1= abc.indexOf("[");
				int index2 = abc.lastIndexOf("]"); //位置下标
				String as = abc.substring(index1,index2+1);
				ArrayList<Object> list4 = JSON.parseObject(as, ArrayList.class); 
				for(int i = 0;i<  Integer.parseInt(rule.get("num"));i++){
					result = new HashMap<String, String>();
					Gson gson = new Gson();
				    Map<String, Object> map1 = new HashMap<String, Object>();
				    map1 = gson.fromJson(list4.get(i).toString(), map1.getClass());
				    
				    result.put("title", map1.get("bltntypename").toString());
					result.put("content",map1.get("content").toString());
					result.put("court", map1.get("courtcode").toString());
					result.put("time", map1.get("publishdate").toString());
				    
					if(has(map1.get("publishdate").toString())){
						list.add(result);
					}
				}
				
				
			}
			String page = rule.get("page");
			if(org.springframework.util.StringUtils.isEmpty(page)==false){
				String[] pages = page.split(",");
				if(pageIndex==null){
					pageIndex = 0;
				}else{
					pageIndex ++;
				}
				if(pageIndex>=pages.length)return list;
				check(pages[pageIndex], pageIndex, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static String getTime(String time) throws ParseException{
		if(time.contains("间：") && time!=null){
			String [] times = time.split("间：");
			time = times[1];
		}
		if(time.contains("日期：") && time!=null){
			String [] times = time.split("日期：");
			time = times[1];
		}
		if(time.contains("来") && time!=null){
			String [] times = time.split("来");
			time = times[0];
		}
		if(time.contains("年")||time.contains("月")||time.contains("日")){
			Date d = DateUtil.parser(time, "yyyy年MM月dd日");
			time = DateUtil.formart(d, "yyyy-MM-dd");
		}
		return time;
	}
	
	/**
	 * 匹配规则（匹配请求来源）
	 * @param domain
	 */
	private static Map<String,String> findRule(String domain) {
		for (Map<String, String> map : loadRules()) {
			if(domain.indexOf(map.get("domain"))>-1){
				return map;
			}
		}
		return null;
	}
	
	/**
	 * 解析规则配置
	 * @WKX
	 */
	private static List<Map<String,String>> rules = null;
	private static List<Map<String, String>> loadRules() {
		if(rules != null)return rules;
		rules = new ArrayList<Map<String,String>>();
		Map<String,String> rule = new HashMap<String,String>();
		//第一财经新闻
		rule.put("domain", "yicai.com");
		rule.put("getOne", "#news_List dl");
		
		rule.put("href", "dd H3 a");
		rule.put("href-prefix", "href");
		
		rule.put("img", "dt a img");
		rule.put("img-prefix", "data-original");
		
		rule.put("title", ".m-title H1");
		rule.put("time", ".m-title H2 span");
		rule.put("time-num", "1");
		rule.put("content", ".m-text");
		rules.add(rule);
		
		rule = new HashMap<String, String>();
		//腾讯财经
		rule.put("domain", "finance.qq.com");
		rule.put("getOne", "#listZone div");
		rule.put("page", "http://finance.qq.com/c/gdyw_2.htm,http://finance.qq.com/c/gdyw_3.htm");
		
		
		rule.put("href", "em a");
		rule.put("href-prefix", "href");
		rule.put("href-add", "http://finance.qq.com/");
		
		rule.put("title", ".qq_article div H1");
		rule.put("time", ".qq_article div .a_time");
		rule.put("content", "#Cnt-Main-Article-QQ");
		
		rule.put("title1", "#C-Main-Article-QQ div H1");
		rule.put("time1", "#C-Main-Article-QQ div .pubTime");
		rules.add(rule);
		
		//北京商报网
		rule = new HashMap<String, String>();
		rule.put("domain", "bbtnews.com.cn");
		rule.put("getOne", ".list-box ul li");
		rule.put("page", "http://www.bbtnews.com.cn/news/index_2.shtml,http://www.bbtnews.com.cn/news/index_3.shtml");
		
		rule.put("href", "h3 a");
		rule.put("href-prefix", "href");
		
		rule.put("img", ".mar-t-5 a img");
		rule.put("img-prefix", "src");
		
		rule.put("title", ".con-page H1");
		rule.put("time", ".con-page div .pubDate");
		rule.put("content", ".con-page .pic-summary3");
		rules.add(rule);
		
		//搜狐财经
		rule = new HashMap<String, String>();
		rule.put("domain", "business.sohu.com");
		rule.put("getOne", ".f14list ul li a");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		
		rule.put("title", "#container h1");
		rule.put("time", "#pubtime_baidu");
		rule.put("content", "#contentText div");
		rules.add(rule);
		
		//FT中文网
		rule = new HashMap<String, String>();
		rule.put("domain", "ftchinese.com");
		rule.put("getOne", ".items .M-half");
		
		rule.put("img", ".item-inner a figure img");
		rule.put("img-prefix", "data-backupimage");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		rule.put("href-add", "http://www.ftchinese.com");
		
		rule.put("time2",".item-inner .item-time");
		rule.put("title", ".story-container H1");
		rule.put("content", ".story-body p");
		rules.add(rule);
		
		//和讯网
		rule = new HashMap<String, String>();
		rule.put("domain", "roll.hexun.com");
		rule.put("getOne", "#immeList ul li");
		
		rule.put("href", "li a");
		rule.put("href-prefix", "href");
		
		rule.put("title", ".articleName H1");
		rule.put("time",".pr20");
		rule.put("content", ".art_contextBox p");
		rules.add(rule);
		
		//21世纪经济报道
		rule = new HashMap<String, String>();
		rule.put("domain", "finance.21cn.com");
		rule.put("getOne", ".mod .bd .art-list");
		rule.put("page", "http://finance.21cn.com/newsdoc/zx/list2.shtml,http://finance.21cn.com/newsdoc/zx/list3.shtml");
		
		rule.put("href", ".pic-list h3 a");
		rule.put("href-prefix", "href");
		
		rule.put("img", ".p-thumb a img");
		rule.put("img-prefix", "original");
		
		rule.put("title", "#article-cmt .hd h1");
		rule.put("time","table tr td .pubTime");
		rule.put("content", "#article_text p");
		rules.add(rule);
		
		//经济参考网
		rule = new HashMap<String, String>();
		rule.put("domain", "www.jjckb.cn");
		rule.put("getOne", ".box_left ul li");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		
		rule.put("title", ".xl_left .top_tit");
		rule.put("time",".sj_scro span");
		rule.put("time-num", "0");
		rule.put("content", "#content p");
		rules.add(rule);
		
		//http://finance.ce.cn/rolling/index.shtml  中国经济网
		rule = new HashMap<String, String>();
		rule.put("domain", "finance.ce.cn");
		rule.put("getOne", ".list_left table tr .font14 a");
		rule.put("href-add", "http://finance.ce.cn/rolling/");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		
		rule.put("title", "#articleTitle");
		rule.put("time","#articleTime");
		rule.put("content", ".TRS_Editor p");
		rules.add(rule);
		
		//http://news.cnstock.com/news/sns_yw/index.html 上海证券报
		rule = new HashMap<String, String>();
		rule.put("domain", "news.cnstock.com");
		rule.put("getOne", ".new-list li a");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		
		rule.put("title", "#pager-content H1");
		rule.put("time",".bullet .timer");
		rule.put("content", "#qmt_content_div p");
		rules.add(rule);
		
		//http://www.thepaper.cn/  澎湃新闻
		rule = new HashMap<String, String>();
		rule.put("domain", "www.thepaper.cn/index_masonry.jsp");
		rule.put("getOne", ".newsbox .news_li");
		
		rule.put("img", ".news_tu a img");
		rule.put("img-prefix", "src");
		
		rule.put("href", ".news_tu a");
		rule.put("href-prefix", "href");
		rule.put("href-add", "http://www.thepaper.cn/");
		
		rule.put("title", ".main_lt .newscontent h1");
		rule.put("time",".news_about p");
		rule.put("time-num", "1");
		rule.put("content", ".news_txt");
		rules.add(rule);
		
		//http://finance.eastmoney.com/news/ccjdd.html 东方财富网
		rule = new HashMap<String, String>();
		rule.put("domain", "finance.eastmoney.com/news/ccjdd.html");
		rule.put("getOne", "#newsListContent li");
		rule.put("page", "http://finance.eastmoney.com/news/ccjdd_2.html,http://finance.eastmoney.com/news/ccjdd_3.html");
		
		rule.put("img", ".image a img");
		rule.put("img-prefix", "src");
		
		rule.put("href", ".text .title a");
		rule.put("href-prefix", "href");
		
		rule.put("title", ".left-content .newsContent h1");
		rule.put("time",".Info .time-source .time");
		rule.put("content", "#ContentBody p");
		rules.add(rule);
		
		//http://www.live.chinacourt.org/fygg.shtml 中国法院网
		//"http://rmfygg.court.gov.cn/psca/lgnot/solr/searchBulletinInterface.do?callback=jQuery152024430807740464644_1494485926176&start=1&limit=16&wd=rmfybulletin&list%5B0%5D=bltntype%3A&_=1494486834250"
		rule = new HashMap<String, String>();
		rule.put("domain", "http://www.live.chinacourt.org/fygg.shtml");
		rule.put("getOne", ".ggcx_index table");
		
		rule.put("json", "ok");
		rule.put("href", "http://rmfygg.court.gov.cn/psca/lgnot/solr/searchBulletinInterface.do?callback=jQuery152045206369452445383_1494551146702&start=0&limit=50&wd=%E7%A5%A8%E6%8D%AE&_=1494551167257");
		rule.put("num", "50");//注意和上面查询的条数相匹配
		rules.add(rule);
		
		//http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_0.html 人民法院公告网
		rule = new HashMap<String, String>();
		rule.put("domain", "http://rmfygg.court.gov.cn/psca/lgnot/bulletin");
		rule.put("getOne", "table tr .pb5");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		rule.put("href-add", "http://rmfygg.court.gov.cn");
		
		rule.put("content-info", "block");
		rule.put("addindex", "1");
		rule.put("content-index", "/");
		rule.put("title", ".d2 .d21 .fsb");
		rule.put("time",".d2 .d23 .ft1424");
		rule.put("time-num", "2");
		rule.put("content", ".d2 .d22 .ft1424");
		rule.put("court",".d2 .d23 .fsb");
		rules.add(rule);
		return rules;
	}
	
	/**
	 * 验证时间（限制解析到的新闻的日期）
	 * @author WKX
	 */
	private static Boolean has(String time) {
		Boolean need = false;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		try {
			if(org.springframework.util.StringUtils.isEmpty(time))throw new Exception("时间为空");
			Date thiz = null;
			if(time.indexOf(":")==2){
				thiz = DateUtil.parser(time, DateUtil.FORMART);
				if(DateUtil.calSeconds(c.getTime(), thiz)>0){
					need = true;
				}
			}else if(time.contains(":")){
				thiz = DateUtil.parser(time, DateUtil.FORMART2);
				if(DateUtil.calSeconds(c.getTime(), thiz)>0){
					need = true;
				}
			}else{
				thiz = DateUtil.parser(time, DateUtil.FORMART3);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(c.getTime());
				if(time.equals(date)){
					need = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return need;
	}

	@Override
	public void saveAll(String url, Integer pageIndex, List<Map<String, String>> list,String articleSource) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String,String>> list1 = check(url,pageIndex,list);
		NewsCrawler newsCrawler = null;
		String newsKey = url+DateUtil.formart(new Date(), DateUtil.FORMART3);
		if(list1!=null){
			for(Map<String,String> result:list1){
				newsCrawler = new NewsCrawler();
				newsCrawler.setArticleSource(articleSource);
				newsCrawler.setCreateTime(new Date());
				newsCrawler.setUrlSource(url);
				newsCrawler.setContent(result.get("content"));
				newsCrawler.setPic(result.get("img"));
				newsCrawler.setPic1(result.get("img"));
				newsCrawler.setState(1);
				newsCrawler.setTitle(result.get("title"));
				if(result.get("title").contains("票据") || result.get("content").contains("票据")){
					newsCrawler.setType(1);
				}else{
					newsCrawler.setType(2);
				}
				Long list2 = newsCrawlerDao.getByTitle(result.get("title"));
				if(index_params.get(newsKey)==null){
					params.put(newsKey, 0);
					setIndexParams(params,newsKey);
				}
				Integer newsCount = Integer.valueOf(index_params.get(newsKey).toString());
				if(newsCount>=19){
					break;
				}
				if(list2==0){
					newsCrawlerDao.saveModel(newsCrawler);
					newsCount++;
					params.put(newsKey, newsCount);
					setIndexParams(params,newsKey);
				}
			}
		}
	}
	
	@Override
	public void savepiaojuAll(String url, Integer pageIndex, List<Map<String, String>> list,String articleSource) throws Exception {
		
		List<Map<String,String>> list1 = check(url,pageIndex,list);
		ReptileTicket reptileTicket = null;
		if(list1!=null){
			for (Map<String,String> result:list1) {
				reptileTicket = new ReptileTicket();
				reptileTicket.setContent(result.get("content"));
				reptileTicket.setCourt(result.get("court"));
				reptileTicket.setCreateTime(new Date());
				reptileTicket.setTitle(result.get("title"));
				Date releaseTime = DateUtil.parser(result.get("time"), DateUtil.FORMART3);
				reptileTicket.setReleaseTime(releaseTime);
				reptileTicketServiceImpl.saveModel(reptileTicket);
			}
		}
	}
}
