package com.ry.rycms.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Admin;
import com.ry.core.entity.BeatCode;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.KeyWordCount;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.entity.OrgExtendInfo;
import com.ry.core.entity.OrgInfo;
import com.ry.core.form.MemberForm;
import com.ry.core.form.PriceForm;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.ActivityService;
import com.ry.core.service.AdminService;
import com.ry.core.service.AppVisitLogService;
import com.ry.core.service.BeatCodeService;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.CrawlerService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.KeyWordCountService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NewsCrawlerService;
import com.ry.core.service.NoticeAddService;
import com.ry.core.service.OrgExtendInfoService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.PriceService;
import com.ry.core.service.ServicememberService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.SitestatisticsService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.PropertiesUtil;
import com.ry.util.Result;
import com.ry.util.SendMessagesUtil;
import com.ry.util.baofoo.util.JXMConvertUtil;

import cn.jimmyshi.beanquery.BeanQuery;
import freemarker.template.Configuration;
import net.sf.json.JSONObject;

@Controller
public class TestController {
	@Resource
	ShiborService shiborService;
	
	@Resource
	CrawlerService crawlerService;
	
	@Resource
	SitestatisticsService sitestatisticsService;
	
	@Resource
	ServicememberService servicememberService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	ClickCountService clickCountService;
	
	@Resource
	KeyWordCountService keyWordCountService;
	
	@Resource
	BeatCodeService beatCodeService;
	
	@Resource
	AdminService adminService;
	
	@Resource
	MemberService memberservice;
	
	@Resource
	private ActionlogService actionlogService;
	
	@Resource
	private Configuration configuration;
	
	private static Logger logger = Logger.getLogger(TestController.class);

	@Resource
	private MemberService memberService;
	
	@Resource
	Configuration freemarkerConfiguration;
	
	@Resource
	NewsCrawlerService newsCrawlerService;
	
	@Resource
	private AppVisitLogService appVisitLogService;
	
	@Resource
	private PriceService priceService;
	
	@Resource
	private TiexianNoticeService noticeService; 
	
	@Resource
	private NoticeAddService noticeAddService;
	
	@Resource
	private HistorypriceService historypriceService;
	
	@Resource
	ActivityService activityService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgExtendInfoService orgExtendInfoService;
	
	@RequestMapping("/getdata/shibor/")
	public String shibor(){
		crawlerService.crawShiborInfo();
//		shiborService.saveShibor(shibor);;
		return "ajax";
	}
	@RequestMapping("/test/dataemail/")
	public String dataemail(){
		sitestatisticsService.emailData();
		return "ajax";
	}
	
	@RequestMapping("/test/emailSiteData/")
	public String emailSiteData(){
		sitestatisticsService.emailSiteData();
		return "ajax";
	}
	
	@RequestMapping("/test/saledataemail/")
	public String saledataemail(){
		servicememberService.emailSaleData();
		return "ajax";
	}
	
	@RequestMapping("/test/operate/")
	public String operate() throws ParseException{
		String sendemail = Constant.properties.getProperty("OPERATIVEEMAIL");
		String operate = Constant.properties.getProperty("operateNums");
		
		logger.info("------------------递推数据统计定时任务执行begin------------------");
		
		//获取前一天的时间
		Date end = DateUtil.addDays(-1);
		String endtime = DateUtil.formart(new Date(),DateUtil.FORMART3);
		String endtime1 = endtime + " 00:00:00" ;
		//获取前61天的时间
		Date begint = DateUtil.addDays(-61);
		String beginttime = DateUtil.formart(begint,DateUtil.FORMART3);
		String beginttime1 = beginttime + " 00:00:00" ;
		
		List<Map<String, Object>> listmember = memberservice.getGroomList(beginttime1, endtime1,operate);
		
		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		for (Map<String, Object> map : listmember) {
			map.put("date",DateUtil.formart(new Date(), DateUtil.FORMART3));
			map.put("range",DateUtil.formart(begint, DateUtil.FORMART3)+"至"+DateUtil.formart(end, DateUtil.FORMART3));
			dataList.add(map);
		}
		maps.put("groom", dataList);
		List<String> targetPerson = new ArrayList<String>();
		targetPerson.add(sendemail);
		String curDay = DateUtil.formart(new Date(), DateUtil.FORMART3);
		String html = EmailUtil.getHtml(configuration, "/emailtemplate/tuijian.ftl", maps);
		EmailUtil.sendEmail(targetPerson, "递推推荐统计" + curDay, html);
		
		logger.info("------------------递推数据统计定时任务执行end------------------");
		return "ajax";
	}
	
	@RequestMapping("/test/dingshi/")
	public String dingshi() throws ParseException{
		HongbaoActivity activity = new HongbaoActivity();
		activity.setStatus(0);
		activity.setFlag(1);
		
		List<HongbaoActivity> activities = hongbaoService.getActivitys(activity);
		
		if (activities != null && activities.size() > 0) {			
			Date nowDate = new Date();
			for (int i = 0; i < activities.size(); i++) {
				if (DateUtil.daysBetween(nowDate, activities.get(i).getEnddate()) < 0 ) {
					HongbaoActivity activity2 = activities.get(i);
					activity2.setFlag(0);
					hongbaoService.updateActivity(activity2);					
				}
			}
						
			Integer shareDays = activities.get(0).getSharedays();
			
			List<HongbaoDetail> hongbaoDetails = hongbaoService.getListFlag0();
			for (int i = 0; i < hongbaoDetails.size(); i++) {
				if (shareDays != null) {
					Date date1 = DateUtil.addDays(hongbaoDetails.get(i).getUpdatetime(), shareDays-1);
					if (DateUtil.daysBetween(nowDate, date1)<0) {
						HongbaoDetail hongbaoDetail = hongbaoDetails.get(i);
						hongbaoDetail.setFlag(1);
						hongbaoService.updateHongbaoDetail(hongbaoDetail);
					}
				}				
			}
						
		}		
		return "ajax";
	}
	
	/**
	 * 百度关键字初始化（统计搜索次数和新增次数）
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @throws ParseException
	 * @since 2016年5月9日 上午10:11:16
	 */
	@RequestMapping("/keyword/init")
	public @ResponseBody String keyword(String start,String end) throws ParseException{
		Date s_date = null;
		Date e_date = null;
		if(StringUtils.isNotBlank(start))s_date = DateUtil.parser(start, DateUtil.FORMART3);
		if(StringUtils.isNotBlank(end))e_date = DateUtil.parser(end, DateUtil.FORMART3);
		List<Map<String, Object>> list = clickCountService.getCountByCurrentDate(s_date,e_date);
		if(list!=null && list.size()>0){
			KeyWordCount temp = null;
			Object day = null;
			for(Map<String, Object> map:list){
				temp = new KeyWordCount();
				day = map.get("currentDate");
				if(day!=null){
					temp.setDay(day.toString().split(" ")[0]);//只保存年月日
					if(map.get("keyword")!=null)temp.setKeyword(map.get("keyword").toString());
					if(map.get("s_amount")!=null)temp.setsAmount(Integer.valueOf(map.get("s_amount").toString()));
					if(map.get("a_amount")!=null)temp.setaAmount(Integer.valueOf(map.get("a_amount").toString()));
					keyWordCountService.saveModel(temp);
				}
			}
		}
		return "success...";
	}
	
	/**
	 * 创建公测码
	 * @author WKX
	 * @param model
	 * @param size
	 * @since 2016年8月5日 下午3:37:29
	 */
	@RequestMapping("/beatcode")
	public String createBeatCode(Model model,Integer size,String name,String pwd){
		Admin admin = adminService.login(null, name, MD5Util.getMD5Str(pwd));
		List<String> no_list = new ArrayList<String>();
		if(admin!=null){
			if(size!=null){
				BeatCode bc = null;
				for(int i=0;i<size;i++){
					bc = new BeatCode();
					bc.setCreateTime(new Date());
					bc.setState(1);//未使用
					String no = beatCodeService.saveModel(bc);
					no_list.add(no);
				}
			}
		}else{
			no_list.add("您没有创建权限");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(no_list));
		return "ajax";
	}
	@RequestMapping("/hezuo")
	public void execute() {
		String hezuo = PropertiesUtil.getConfigPropertiesValue("HEZUO", "");
		String sendemail = PropertiesUtil.getConfigPropertiesValue("SENDEMAIL", "");
		String qudao = PropertiesUtil.getConfigPropertiesValue("QUDAO", "");
		logger.info("------------------推广注册数据统计定时任务执行begin------合作："+hezuo+",sendemail:"+sendemail+"------------");
		if (StringUtils.isNotBlank(hezuo)) {
			List<String> hezuos = Arrays.asList(hezuo.split(","));
			List<String> qudaos = Arrays.asList(qudao.split(","));
			if (hezuos != null && hezuos.size() > 0 && qudaos != null && qudaos.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();

				MemberForm form = new MemberForm();
				MemberForm form1 = new MemberForm();
				Calendar c = Calendar.getInstance();

				// 设置当前一天的时间
				c.setTime(new Date());
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				c.add(Calendar.DATE,-1);
				form1.setBeginRegDate(c.getTime());

				c.set(Calendar.HOUR_OF_DAY, 23);
				c.set(Calendar.MINUTE, 59);
				c.set(Calendar.SECOND, 59);
				form1.setEndRegDate(c.getTime());

				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				form.setEndRegDate(c.getTime());

				c.add(Calendar.MONTH, 0);
				c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				form.setBeginRegDate(c.getTime());

				List<Object> list = new ArrayList<Object>();
				Map<String, Object> m = null;
				
				for (String qd : qudaos) {
					for (String hz : hezuos) {
						form.setQudao(qd);
						form1.setQudao(qd);
						form.setHezuo(hz);
						form1.setHezuo(hz);
						Long temp_m = memberService.count(form);// 月
						Long temp_d = memberService.count(form1);// 日

						m = new HashMap<String, Object>();
						m.put("hz", hz);
						m.put("qd", qd);
						m.put("m", temp_m);
						m.put("d", temp_d);
						list.add(m);
					}
				}
				map.put("hezuo", list);

				String curDay = DateUtil.formart(new Date(), DateUtil.FORMART3);
				String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/hezuo.ftl", map);
				System.err.println(map);
				List<String> targetPerson = Arrays.asList(sendemail.split(","));
				EmailUtil.sendEmail(targetPerson, "合作数据统计 " + curDay, html);
			}
		}
		logger.info("------------------推广注册数据统计定时任务执行end------------------");
	}
	
	
	/**
	 * 爬虫测试
	 * @author KHC
	 * @since 2016年8月22日 下午4:01:59
	 */
	@RequestMapping("/save/newscrawler")
	@ResponseBody
	public String saveNews(){
		String urls [] = {"http://www.yicai.com/news/",
						  "http://finance.qq.com/gdyw.htm",
						  "http://www.bbtnews.com.cn/news/",
						  "http://business.sohu.com/business_scrollnews.shtml",
						  "http://finance.21cn.com/newsdoc/zx/",
						  "http://www.jjckb.cn/yw.htm",
						  "http://finance.ce.cn/rolling/index.shtml",
						  "http://news.cnstock.com/news/sns_yw/index.html",
						  "http://www.thepaper.cn/index_masonry.jsp",
						  "http://finance.eastmoney.com/news/ccjdd.html"};
		String articleSource[]={
				"第一财经","腾讯财经","北京商报网","搜狐财经","21世纪经济报道","经济参考网","中国经济网","上海证券报","澎湃新闻","东方财富网"
		};
		for (int i = 0; i < urls.length; i++) {
			newsCrawlerService.saveAll(urls[i], null, null,articleSource[i]);
		}
//		newsCrawlerService.saveAll("http://www.bbtnews.com.cn/news/", null, null,"北京商报网");
		return "success";
	}
	
	/**
	 * http://www.yicai.com/news/  第一财经*
	 * http://finance.qq.com/gdyw.htm  腾讯财经*
	 * http://www.bbtnews.com.cn/news/  北京商报网*
	 * http://business.sohu.com/business_scrollnews.shtml  搜狐财经*
	 * http://www.ftchinese.com/channel/chinamarkets.html  FT中文网(新闻不是当天)
	 * http://roll.hexun.com/  和讯网
	 * http://stock.cngold.org/news/  中国证券网(网站打不开)
	 * http://finance.21cn.com/newsdoc/zx/  21世纪经济报道*
	 * http://www.jjckb.cn/yw.htm  经济参考网 *
	 * http://finance.ce.cn/rolling/index.shtml  中国经济网*
	 * http://news.cnstock.com/news/sns_yw/index.html 上海证券报*
	 * http://money.eastmoney.com/ 东方财富_理财
	 * http://finance.eastmoney.com/ 东方财富_财经
	 * http://www.thepaper.cn/index_masonry.jsp 澎湃新闻*
	 * http://finance.eastmoney.com/news/ccjdd.html 东方财富网
	 */
//	public static void main(String[] args) throws IOException {
//		List<Map<String,String>> list = check("http://www.yicai.com/news/",null,null);
//		System.err.println(list.size());
//		for(Map<String,String> result:list){
//			System.err.println(result.get("title"));
//			System.err.println(result.get("time"));
//		}
//	}
	
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
			if(StringUtils.isBlank(url))throw new Exception("请输入正确的链接!");
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
					//解析详情
					doc = Jsoup.connect(href).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();
					String title = doc.select(rule.get("title")).html();
					if(StringUtils.isBlank(title)){
						title = doc.select(rule.get("title1")).html();
					}
					//解析时间
					String time = "";
					Elements timeEle = doc.select(rule.get("time"));
					if(timeEle==null || StringUtils.isBlank(timeEle.html())){
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
					
					if(has(time)){
						list.add(result);
					}
				}
			}
			doc = null;
			String page = rule.get("page");
			if(StringUtils.isNotBlank(page)){
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
		
		//中国证券网
		rule = new HashMap<String, String>();
		rule.put("domain", "www.cs.com.cn");
		rule.put("getOne", "body table tr td div a");
		
		rule.put("href", "a");
		rule.put("href-prefix", "href");
		rule.put("href-add", "http://www.cs.com.cn/csnews");
		
		rule.put("title", "table tr .xltitle");
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
		
		rule.put("href", ".text .title a");
		rule.put("href-prefix", "href");
		
		rule.put("title", ".left-content .newsContent h1");
		rule.put("time",".Info .time-source .time");
		rule.put("content", "#ContentBody p");
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
			if(StringUtils.isBlank(time))throw new Exception("时间为空");
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
	
	/**
	 * 测试销售数据（上月注册并活跃数，上上月注册活跃小于2于上月活跃数）
	 * @author WKX
	 * @since 2017年3月5日 上午2:17:23
	 */
	@RequestMapping("/test/sale")
	public @ResponseBody String sale() {
		logger.info("------------------销售数据统计定时任务执行start------------------");
		Calendar cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        cal_1.set(Calendar.HOUR_OF_DAY, 0);
        cal_1.set(Calendar.MINUTE, 0);
        cal_1.set(Calendar.SECOND, 0);
        Date end = cal_1.getTime();
        
        cal_1.add(Calendar.MONTH, -1);
        Date start = cal_1.getTime();
		
		cal_1.add(Calendar.MONTH, -1);
		Date up = cal_1.getTime();
		Date down = start;
		
		appVisitLogService.createTempByStartAndEnd(up,end);//拷贝部分数据
		
		List<Integer> memberIds = new ArrayList<Integer>();
		List<Map<String, Object>> result_0 = appVisitLogService.getByStartAndEnd(up, down);
		List<Map<String, Object>> result_1 = null;//上上月
		if(result_0!=null && result_0.size()>0){
			for(Map<String, Object> map:result_0){
				if(map!=null && map.get("amount")!=null && Integer.valueOf(map.get("amount").toString())<2){
					if(map.get("memberId")!=null){
						memberIds.add(Integer.valueOf(map.get("memberId").toString()));
					}
				}
			}
			if(memberIds!=null && memberIds.size()>0)result_1 = appVisitLogService.getByStartAndEndInMemberIds(start, end,memberIds);
		}
		
        List<Map<String, Object>> result_2 = appVisitLogService.getByStartAndEnd(start, end);//上月
        List<Map<String, Object>> result_3 = appVisitLogService.getHasOrgInfoByStartAndEnd(start, end);//前两个月申请机构认证的用户（通过）的活跃数
        excel(start,result_2,up,result_1,result_3);
		logger.info("------------------销售数据统计定时任务执行end------------------");
		return "successfully...";
	}
	
	/**
	 * 创建表格
	 * @param month 上月
	 * @param result 上月
	 * @param month1 上上月
	 * @param result1 上上月
	 * @param result2 前两个月申请机构认证的用户（通过）的活跃数
	 * @since 2017年3月28日 下午5:24:03
	 */
	private void excel(Date month,List<Map<String, Object>> result,Date month1,List<Map<String, Object>> result1,List<Map<String, Object>> result2){
		//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(month, "yyyy年MM月")+"注册名单");
        sheet.setColumnWidth(0, 500*20);
        sheet.setColumnWidth(1, 400*20);
        sheet.setColumnWidth(2, 400*20);
        HSSFSheet sheet1 = wb.createSheet(DateUtil.formart(month1, "yyyy年MM月")+"注册名单");
        sheet1.setColumnWidth(0, 500*20);
        sheet1.setColumnWidth(1, 400*20);
        sheet1.setColumnWidth(2, 400*20);
        
        HSSFSheet sheet2 = wb.createSheet("上个月认证通过用户名单");
        sheet2.setColumnWidth(0, 500*20);
        sheet2.setColumnWidth(1, 400*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("用户注册月份");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(month, "yyyy年MM月"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        //--列头------------------
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("注册用户");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("推荐人");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("活跃天数");
        cell.setCellStyle(style1);
        
        //================================================================================
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row1 = sheet1.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("统计时间");
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("");
        cell1.setCellStyle(style);
        
        row1 = sheet1.createRow(1);
        cell1 = row1.createCell(0);
        cell1.setCellValue("用户注册月份");
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue(DateUtil.formart(month1, "yyyy年MM月"));
        cell1.setCellStyle(style);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("");
        cell1.setCellStyle(style);
        
        //--列头------------------
        row1 = sheet1.createRow(2);
        cell1 = row1.createCell(0);
        cell1.setCellValue("上月活跃度小于2的注册用户");
        cell1.setCellStyle(style1);
        
        cell1 = row1.createCell(1);
        cell1.setCellValue("推荐人");
        cell1.setCellStyle(style1);
        
        cell1 = row1.createCell(2);
        cell1.setCellValue("活跃天数");
        cell1.setCellStyle(style1);
        
      //================================================================================
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row2 = sheet2.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellValue("统计时间");
        cell2.setCellStyle(style);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell2.setCellStyle(style);
        
        
        row2 = sheet2.createRow(1);
        cell2 = row2.createCell(0);
        cell2.setCellValue("用户认证月份");
        cell2.setCellStyle(style);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue(DateUtil.formart(month, "yyyy年MM月"));
        cell2.setCellStyle(style);
        
        //--列头------------------
        row2 = sheet2.createRow(2);
        cell2 = row2.createCell(0);
        cell2.setCellValue("用户手机号");
        cell2.setCellStyle(style1);
        
        cell2 = row2.createCell(1);
        cell2.setCellValue("活跃天数");
        cell2.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);
            }
        }
        HSSFCell c1 = null;
        if(result1!=null){
        	for(int i = 0; i < result1.size(); i++){
            	row1 = sheet1.createRow((int) i + 3);
            	res = result1.get(i);
                c1 = row1.createCell(0);
                c1.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c1.setCellStyle(style2);
                
                c1 = row1.createCell(1);
                c1.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c1.setCellStyle(style2);
                
                c1 = row1.createCell(2);
                c1.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c1.setCellStyle(style2);
            }
        }
        HSSFCell c2 = null;
        if(result2!=null){
        	for(int i = 0; i < result2.size(); i++){
            	row2 = sheet2.createRow((int) i + 3);
            	res = result2.get(i);
                c2 = row2.createCell(0);
                c2.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c2.setCellStyle(style2);
                
                c2 = row2.createCell(1);
                c2.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c2.setCellStyle(style2);
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("SALEEMAIL");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(month, "yyyy年MM月")+"销售数据", "",path);
        }catch (Exception e){  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 洪宁需要的关键字数据（最近两周数据数据）
	 * @author WKX
	 * @since 2017年3月28日 下午5:17:43
	 */
	@RequestMapping("/test/sale1")
	public @ResponseBody String sale1() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date end = cal.getTime();
		
		cal.add(Calendar.WEEK_OF_YEAR, -2);
        Date start = cal.getTime();
		
        List<Map<String,Object>> list = clickCountService.getSrcCountByDate(start, end);
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		if(list!=null && list.size()>0){
			Map<String,Object> old = null;
			Map<String,Object> temp = null;
			for(Map<String,Object> map:list){
				if(result.size()>0){
					old = result.get(result.size()-1);
					temp = this.mergeMap(old,map);
					if(temp!=null)result.add(temp);
				}else{
					result.add(this.reName(map));
				}
			}
		}
		result = BeanQuery.select("keyword,ALL,SHENMA,SOUGOU,OTHER").orderBy("ALL").desc().executeFrom(result);//数据排序
		try {
			this.excel1(result, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "successfully...";
	}
	
	/**
	 * 整合数据（相同关键字汇总到同一条里面）
	 * @author WKX
	 * @param old 最近整合的一条数据
	 * @param map 读取的一条数据
	 * @since 2017年3月28日 下午2:56:20
	 */
	private Map<String,Object> mergeMap(Map<String,Object> old,Map<String,Object> map){
		if(old!=null && map!=null && old.get("keyword")!=null && map.get("keyword")!=null){
			if((old.get("keyword").toString()).equals(map.get("keyword").toString()) && map.get("amount")!=null){
				int count = Integer.valueOf(map.get("amount").toString());
				Object style = null;
				if(map.get("temp")!=null){
					if("SHENMA".equals(map.get("temp").toString())){
						style = old.get("SHENMA");
						if(style!=null){
							old.put("SHENMA", Integer.valueOf(style.toString())+count);
						}else{
							old.put("SHENMA",count);
						}
					}else if("SOUGOU".equals(map.get("temp").toString())){
						style = old.get("SOUGOU");
						if(style!=null){
							old.put("SOUGOU", Integer.valueOf(style.toString())+count);
						}else{
							old.put("SOUGOU",count);
						}
					}
				}else{
					old.put("OTHER",count);
				}
				this.amount(old);
				return null;
			}
		}
		return this.reName(map);
	}
	
	/**
	 * 重命名关键字KEY（神马、搜狗、其他）
	 * @author WKX
	 * @param map 原始记录
	 * @since 2017年3月28日 下午3:48:53
	 */
	private Map<String,Object> reName(Map<String,Object> map){
		if(map!=null && map.get("amount")!=null){
			int count = Integer.valueOf(map.get("amount").toString());
			if(map.get("temp")!=null){
				if("SHENMA".equals(map.get("temp").toString())){
					map.put("SHENMA",count);
				}else if("SOUGOU".equals(map.get("temp").toString())){
					map.put("SOUGOU",count);
				}
			}else{
				map.put("OTHER",Integer.valueOf(map.get("amount").toString()));
			}
			this.amount(map);
		}
		return map;
	}
	
	/**
	 * 汇总数量
	 * @author WKX
	 * @param map 单条数据
	 * @since 2017年3月28日 下午4:03:41
	 */
	private Map<String,Object>amount(Map<String,Object> map){
		int amount = 0;
		if(map.get("SHENMA")!=null){
			amount += Integer.valueOf(map.get("SHENMA").toString());
		}
		if(map.get("SOUGOU")!=null){
			amount += Integer.valueOf(map.get("SOUGOU").toString());
		}
		if(map.get("OTHER")!=null){
			amount += Integer.valueOf(map.get("OTHER").toString());
		}
		map.put("ALL",amount);
		return map;
	}
	
	/**
	 * 发送邮件（洪宁需要的数据）
	 * @author WKX
	 * @param result 数据
	 * @param start 开始日期
	 * @param end 结束日期
	 * @throws Exception
	 * @since 2017年3月28日 下午5:16:05
	 */
	private void excel1(List<Map<String, Object>> result,Date start,Date end) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(new Date(), DateUtil.FORMART3)+"关键词统计");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 600*20);
        sheet.setColumnWidth(1, 300*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 300*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        
        HSSFRow row = sheet.createRow(0);//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFCell cell = row.createCell(0);//第四步，创建单元格，并设置值表头 设置表头居中
        
        cell = row.createCell(0);
        cell.setCellValue("数据时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        //--列头------------------
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(DateUtil.formart(start, "yyyy/MM/dd") + "至" + DateUtil.formart(end, "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
      //--列头------------------
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("搜索词");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("搜狗");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("神马");
        cell.setCellStyle(style1);
        
        cell = row.createCell(3);
        cell.setCellValue("其他");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        MAX:if(result!=null){
        	int count_sg = 0;
        	int count_sm = 0;
        	int count_ot = 0;
        	Object temp = null;
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("keyword")!=null?res.get("keyword").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                temp = res.get("SOUGOU");
                if(temp!=null){
                	count_sg += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                temp = res.get("SHENMA");
                if(temp!=null){
                	count_sm += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                
                c = row.createCell(3);
                temp = res.get("OTHER");
                if(temp!=null){
                	count_ot += Integer.valueOf(temp.toString());
                	c.setCellValue(temp.toString());
                }else{
                	c.setCellValue("0");
                }
                c.setCellStyle(style2);
                if(i>=90 || (i+1)==result.size()){
                	row = sheet.createRow((int) i + 4);
                	res = result.get(i);
                    c = row.createCell(0);
                    c.setCellValue("总计");
                    c.setCellStyle(style2);
                    
                    c = row.createCell(1);
                    c.setCellValue(count_sg);
                    c.setCellStyle(style2);
                    
                    c = row.createCell(2);
                    c.setCellValue(count_sm);
                    c.setCellStyle(style2);
                    
                    c = row.createCell(3);
                    c.setCellValue(count_ot);
                    c.setCellStyle(style2);
                	break MAX;
                }
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("HONGNING");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(new Date(), DateUtil.FORMART3)+"关键词统计", "",path);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("失败！");
        }  
	}
	
	/**
	 * 于浩需要的活跃度数据（上个月，前150名活跃超过4的，不足的取上上月未被选择的用户在）
	 * @author WKX
	 * @since 2017年3月28日 下午5:17:43
	 */
	@RequestMapping("/test/sale2")
	public @ResponseBody String sale2() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date end = cal.getTime();
		
		cal.add(Calendar.MONTH, -1);
        Date start = cal.getTime();
		
        List<Map<String, Object>> result_1 = appVisitLogService.getByStartAndEnd(start, end, 0, 150);
        List<Map<String, Object>> result_2 = new ArrayList<Map<String,Object>>();
        if(result_1!=null && result_1.size()<150){//上月数据已经够了
        	int size = 150;
        	if(result_1!=null && result_1.size()>0)size -= result_1.size();
        	cal.add(Calendar.MONTH, -1);
            Date start_ = cal.getTime();
            List<Map<String, Object>> result_temp = appVisitLogService.getByStartAndEnd(start_, start, 0, 150);
            if(result_temp!=null && result_temp.size()>0){
            	List<Integer> memberIds = new ArrayList<Integer>();
            	for(Map<String, Object> map:result_temp){
            		if(map.get("memberId")!=null){
						memberIds.add(Integer.valueOf(map.get("memberId").toString()));
					}
            	}
            	if(memberIds.size()>0){
            		result_temp = appVisitLogService.getByStartAndEndNotinMemberIds(start_, start, size, memberIds);
            		if(result_temp!=null && result_temp.size()>0){
            			List<Integer> memberIds_ = new ArrayList<Integer>();
                    	for(Map<String, Object> map:result_temp){
                    		if(map.get("memberId")!=null){
        						memberIds_.add(Integer.valueOf(map.get("memberId").toString()));
        					}
                    	}
                    	result_2 = appVisitLogService.getByStartAndEndInMemberIdsNoReple(start, end, memberIds_);
            		}
            	}
            }
        }
        result_1.addAll(result_2);
        try {
			excel2(result_1, start);//绘制表格（发送邮件）
		} catch (Exception e) {
			e.printStackTrace();
			return "失败";
		}
		return "successfully...";
	}
	
	/**
	 * 绘制表格（发送邮件）余昊用户活跃度
	 * @author WKX
	 * @param result 数据
	 * @param time 时间（统计的是上个月的数据）
	 * @throws Exception
	 * @since 2017年3月29日 下午4:27:32
	 */
	private void excel2(List<Map<String, Object>> result,Date time) throws Exception{
		result = BeanQuery.select("amount,memberId,mobile,regtime,username").orderBy("amount").desc().executeFrom(result);//数据排序
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(time, DateUtil.FORMART4)+"活跃用户名单");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 300*20);
        sheet.setColumnWidth(1, 300*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 300*20);
        
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        
        HSSFRow row = sheet.createRow(0);//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFCell cell = row.createCell(0);//第四步，创建单元格，并设置值表头 设置表头居中
        
        cell = row.createCell(0);
        cell.setCellValue("用户姓名");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("手机号");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("注册日期");
        cell.setCellStyle(style1);
        
        cell = row.createCell(3);
        cell.setCellValue("活跃度");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){//amount,memberId,mobile,regtime,username
            	row = sheet.createRow((int) i + 1);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("username")!=null?res.get("username").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("regtime")!=null?res.get("regtime").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(3);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("YUHAO");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(time, DateUtil.FORMART4)+"活跃用户名单", "",path);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("失败！");
        }  
	}
	
	/**
	 * 获取所有认证通过的机构-在该段时间内报价情况（非线上推广渠道注册的用户）
	 * @author WKX
	 * @since 2017年4月24日 下午5:06:31
	 */
	@RequestMapping("/test/sale3")
	public @ResponseBody String sale3() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date end = cal.getTime();
		
		cal.add(Calendar.MONTH, -1);
        Date start = cal.getTime();
        List<Map<String, Object>> result = priceService.getPriceByOrgNoHezuo(start, end);
        try {
			excel3(result, start);//绘制表格（发送邮件）
		} catch (Exception e) {
			e.printStackTrace();
			return "失败";
		}
		return "successfully...";
	}
	
	/**
	 * 绘制表格（发邮件）
	 * @author WKX
	 * @param result 数据
	 * @param dataTime 数据的日期
	 * @throws Exception
	 * @since 2017年4月25日 上午9:58:19
	 */
	private void excel3(List<Map<String, Object>> result,Date dataTime) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();//第一步，创建一个webbook，对应一个Excel文件
        HSSFSheet sheet = wb.createSheet(DateUtil.formart(dataTime, "MM月")+"机构报价天数");//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        sheet.setColumnWidth(0, 300*20);
        sheet.setColumnWidth(1, 500*20);
        sheet.setColumnWidth(2, 300*20);
        sheet.setColumnWidth(3, 300*20);
        
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFCellStyle style3 = wb.createCellStyle();
        
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        
        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short)12);//字体大小
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        style1.setFillBackgroundColor(HSSFColor.RED.index);
        style1.setFont(f);
        
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style2.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style2.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        style3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个居中格式  
        style3.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
        style3.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
        
        
        HSSFRow row = sheet.createRow(0);//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFCell cell = row.createCell(0);//第四步，创建单元格，并设置值表头 设置表头居中
        
        cell = row.createCell(0);
        cell.setCellValue("统计时间");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(new Date(), "yyyy/MM/dd"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("数据月份");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue(DateUtil.formart(dataTime, "yyyy年MM月"));
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("用户手机号");
        cell.setCellStyle(style1);
        
        cell = row.createCell(1);
        cell.setCellValue("公司名称");
        cell.setCellStyle(style1);
        
        cell = row.createCell(2);
        cell.setCellValue("报价天数");
        cell.setCellStyle(style1);
        
        cell = row.createCell(3);
        cell.setCellValue("推荐码");
        cell.setCellStyle(style1);
        
        HSSFCell c = null;
        Map<String, Object> res = null;
        if(result!=null){
        	for(int i = 0; i < result.size(); i++){
            	row = sheet.createRow((int) i + 3);
            	res = result.get(i);
                c = row.createCell(0);
                c.setCellValue(res.get("mobile")!=null?Utility.decodeMobile(res.get("mobile").toString()):"");
                c.setCellStyle(style2);
                
                c = row.createCell(1);
                c.setCellValue(res.get("company")!=null?res.get("company").toString():"");
                c.setCellStyle(style2);
                
                c = row.createCell(2);
                c.setCellValue(res.get("amount")!=null?res.get("amount").toString():"");
                c.setCellStyle(style2);

                c = row.createCell(3);
                c.setCellValue(res.get("recommendpeople")!=null?res.get("recommendpeople").toString():"");
                c.setCellStyle(style3);
                
            }
        }
        try {  
            String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
			
			List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("SALEEMAIL");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()<=0)return;
			EmailUtil.sendEmail(targetPerson,DateUtil.formart(dataTime, DateUtil.FORMART4)+"机构报价天数", "",path);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("失败！");
        }  
	}
	
	/**
	 * 验证平台当前报价情况（过滤的节假日）
	 * @author WKX
	 * @since 2017年4月24日 下午5:06:31
	 */
	@RequestMapping("/test/price")
	public @ResponseBody String price() {
		Date day = new Date();
    	Notice notice = noticeService.getFestivalByNowTime(day);
    	if(notice!=null){
    		return "holiday...";
    	}
    	Calendar cal = Calendar.getInstance();  
    	cal.setTime(day);
        int now = cal.get(Calendar.DAY_OF_WEEK);
        if(now==Calendar.SUNDAY || now==Calendar.SATURDAY){//周六周日
        	List<NoticeAdd> list_ = noticeAddService.getByNowTime(day);
        	if(list_==null || list_.size()==0){//周末切不补班
        		return "weekend...";
        	}
        }
    	//正常上班（含假期补班）
        List<Historyprice> price = historypriceService.findbyDayAndType(DateUtil.formart(day,DateUtil.FORMART3), null, null);
    	if(price==null || price.size()==0){//当天未报价
    		String msg = "【票据管家】您好，今日11：00之前仍没有报价，请了解情况，或通知相关人员查明原因。";
    		List<String> targetPerson = new ArrayList<String>();
			String email = Constant.properties.getProperty("PRICEREPORT_EMAIL");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(email)){
				String[] emails = email.split(",");
				if(emails!=null && emails.length>0){
					for(String e:emails){
						targetPerson.add(e);
					}
				}
			}
			if(targetPerson.size()>0){//有配置的人员
				EmailUtil.sendEmail(targetPerson,"工作日报价检测", msg);
			}
			
			String phone = Constant.properties.getProperty("PRICEREPORT_PHONE");//获取配置的邮箱地址
			if(StringUtils.isNotBlank(phone)){
				String[] phones = phone.split(",");
				if(phones!=null && phones.length>0){
					for(String p:phones){
						SendMessagesUtil.sendMessages(p, msg);
					}
				}
			}
			return "no price...";
    	}else{
    		return "has price...";
    	}
	}
	
	/**
	 * 获取Udesk通话记录
	 * @author WKX
	 * @param model
	 * @throws Exception
	 * @since 2017年6月27日 下午2:17:36
	 */
	@RequestMapping("/test/udesk")
	public String udesk(Model model){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		String end = DateUtil.formart(cal.getTime(), DateUtil.FORMART3);
		cal.add(Calendar.DATE, -1);
		String start = DateUtil.formart(cal.getTime(), DateUtil.FORMART3);
		
		int count = 0;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			String path = Constant.properties.getProperty("UDESK_FILE");//获取配置的UDESK录音文件保存地址
			String sign = MD5Util.getMD5Str("start_time="+start+"&end_time="+end+""+"&76f360be075f74f7d51e52a58bc50baf");
			
			String url = "http://utiexian.udesk.cn/api/v2/ucpapp/calllogs?start_time="+start+"&end_time="+end+"&sign="+sign;
			Result r = HttpUtil.sendGet(url, null, null,"utf-8");
			HttpEntity he = r.getHttpEntity();
			if(he==null)throw new Exception();
			
			String res = EntityUtils.toString(he);
			Map<String,Object> map = JXMConvertUtil.IteratorHash(JSONObject.fromObject(res));
			list = (List<Map<String, Object>>) map.get("item");
			
			if(list!=null && list.size()>0){
				for(Map<String,Object> m:list){
					if(m.get("record_url")!=null){
						String info = "";
						String time = m.get("call_start_at").toString();
						info += DateUtil.formart(DateUtil.parser(time, DateUtil.FORMART), "yyyyMMddHHmmss");
						
						info += "【"+ m.get("call_type") +"】";
						info += "KH" + m.get("call_number");
						info += "KF" + m.get("agent_nick_name");
						
						//下载网络文件
				        String f = m.get("record_url").toString();
				        downLoadFromUrl(f, info+".mp3", path);
				        count ++;
					}
				}
			}
		} catch (org.apache.http.ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("retValue","通话记录："+ list.size() +"/下载："+count);
		return "ajax";
	}
	
	/**
	 * 下载文件（下载通话录音）
	 * @author WKX
	 * @param urlStr 外网文件地址
	 * @param fileName 文件名称
	 * @param savePath 保存路径
	 * @throws IOException
	 * @since 2017年6月27日 下午3:26:43
	 */
	public static void downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(3*1000);//设置超时间为3秒
        
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");//防止屏蔽程序抓取而返回403错误  
        InputStream inputStream = conn.getInputStream();//得到输入流
        byte[] getData = readInputStream(inputStream);//获取自己数组
        
        File saveDir = new File(savePath);//文件保存位置
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
    }
	
	/**
	 * 读取文件流
	 * @author WKX
	 * @param inputStream
	 * @throws IOException
	 * @since 2017年6月27日 下午3:27:00
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
	
	@RequestMapping("/test/piaoju")
	public String testpiaoju(){
		//"http://www.live.chinacourt.org/fygg.shtml",
		//"http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_0.html"
		try {
			String urls [] = {"http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_0.html",
					"http://www.live.chinacourt.org/fygg.shtml"};
			String articleSource[]={
					"中国法院网","人民法院公告网"
			};
			for (int i = 0; i < urls.length; i++) {
				newsCrawlerService.savepiaojuAll(urls[i], null, null,articleSource[i]);
				Thread.sleep(300);
			}
			for(int i = 1;i<10;i++){
				newsCrawlerService.savepiaojuAll("http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_"+i+".html", null, null,"人民法院公告网");
				Thread.sleep(300);
			}
		} catch (Exception e) {
			logger.error("爬虫网站新闻异常："+e.getMessage());
			e.printStackTrace();
		}
		return "ajax";
	}
	
	@RequestMapping("/test/action")
	public void getByDayAction(){
		String action [] = {"action_82","action_83","action2","action129","action_129","action41",
				"action6","action_6","action5","action_5","action4","action_4"};
		String title []={
		"银票报价","商票报价","询价","访问论坛","访问论坛","推荐好友","计算器","计算器","公催查询","公催查询","查联行号","查联行号"
		};
		Integer integral []={
				10,10,10,10,10,20,10,10,10,10,10,10
		};
		for (int i = 0; i < action.length; i++) {
			activityService.actionIntegral(integral[i],title[i],action[i] );
		}
		
	}
	
	/**
	 *  客户归属状态修改的定时
	 */
	@RequestMapping("/customerreport")
	public void CustomerReport(){
		List<OrgExtendInfo> list1 = orgExtendInfoService.getOrgExtendInfoByInfo();
		PriceForm from = null ;
		if(list1 != null){
			for (OrgExtendInfo orgExtendInfo : list1) {
				if(orgExtendInfo.getAscriptionState() == 0){//是销售营销用户
					OrgInfo orgInfo = orgInfoService.getById(orgExtendInfo.getOrgInfoId());
					Date date1 = DateUtil.getStartDate(orgExtendInfo.getSalesTime());
					Date date2 = DateUtil.getStartDate(new Date());
					int ts = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
					if(ts == 150){//判断是否转化为平台用户
						Map<String, Object> map= new HashMap<String, Object>();
						from = new PriceForm();
						from.setStartDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(),90),DateUtil.FORMART3));
						from.setEndDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 120),DateUtil.FORMART3));
						from.setOrgId(orgInfo.getId());
						map  = priceService.getPriceSvmDay(from);
						if(map != null){
							Integer count = Integer.parseInt(map.get("count").toString());
							if(count>=6){}
							else{
									Map<String, Object> map1= new HashMap<String, Object>();
									from = new PriceForm();
									from.setStartDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 120),DateUtil.FORMART3));
									from.setEndDate(DateUtil.formart(DateUtil.addDays(orgExtendInfo.getSalesTime(), 150),DateUtil.FORMART3));
									from.setOrgId(orgInfo.getId());
									map1  = priceService.getPriceSvmDay(from);
									if(map1 != null){
										Integer count1 = Integer.parseInt(map.get("count").toString());
										if(count1 >=6){
											
										}else{
											orgExtendInfo.setConversionTime(new Date());
											orgExtendInfo.setAscriptionState(2);
											orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
										}
									}
								}
						}
					}else if(ts >= 180){//转化为平台用户
						orgExtendInfo.setPermitTime(new Date());
						orgExtendInfo.setAscriptionState(3);
						orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
					}
				}
				if(orgExtendInfo.getAscriptionState() == 2){//是转化营销用户
					Date date1 = DateUtil.getStartDate(orgExtendInfo.getConversionTime());
					Date date2 = DateUtil.getStartDate(new Date());
					int ts = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
					if(ts >= 30){//判断是否转化为平台用户
						orgExtendInfo.setPermitTime(new Date());
						orgExtendInfo.setAscriptionState(3);
					}
					orgExtendInfoService.updateOrgExtendInfo(orgExtendInfo);
				}
			}
		}
	}
	
	/**
	 * 测试客服的分配
	 * @author MH
	 * @throws Exception 
	 */
	@RequestMapping("/test/customer/service")
	public void testCustomerSerivce(HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		String date = DateUtil.formart(new Date(), DateUtil.FORMART3);
		String front = Constant.properties.getProperty("FRONTCUSTOMSERVICE");
		String after = Constant.properties.getProperty("AFTERCUSTOMSERVICE");
		String [] frontArray = front.split(",");
		String [] afterArray = after.split(",");
		int b = 0;
		List<Map<String,Object>> listhezuo =  memberservice.getNoDistributionMember(date,"");
		if(listhezuo != null &&listhezuo.size()>0){
			for (Map<String, Object> map : listhezuo) {
				Member member = memberservice.getModelById(Integer.valueOf(map.get("id").toString()));
				member.setRecommendpeople(frontArray[0]);//分配给孙亚丽
				memberservice.updateMember(member);
			}
		}
		List<Map<String,Object>> listFront =  memberservice.getNoDistributionMember(date,null);
		if(listFront != null && listFront.size()>0){
			List<Map<String,Object>> listLastFront =  memberservice.getLastDistributionMember("FRONT");
			if(listLastFront != null && listLastFront.size()>0){
				Map<String,Object> mapServicenumberId = listLastFront.get(0);
				for (int i = 0; i < frontArray.length; i++) {
					if(frontArray[i].equals(mapServicenumberId.get("recommendpeople").toString())){
						b = i+1;
						break ;
					}
				}
			}
			for (Map<String, Object> map : listFront) {
				if(b == frontArray.length)b = 0;
				Member member = memberservice.getModelById(Integer.valueOf(map.get("id").toString()));
				member.setRecommendpeople(frontArray[b]);
				memberservice.updateMember(member);
				b += 1;
			}
		}
		
		List<Map<String,Object>> listAfter =  memberservice.getAuthenticationNoDistributionMember("AFTER");
		b = 0;
		if(listAfter != null && listAfter.size()>0){
			List<Map<String,Object>> listLastAfter =  memberservice.getLastDistributionMember("AFTER");
			if(listLastAfter != null && listLastAfter.size()>0){
				Map<String,Object> mapServicenumberId = listLastAfter.get(0);
				for (int i = 0; i < afterArray.length; i++) {
					if(afterArray[i].equals(mapServicenumberId.get("recommendpeople").toString())){
						b = i+1;
						break ;
					}
				}
			}
			for (Map<String, Object> map : listAfter) {
				if(b == afterArray.length)b = 0;
				Member member = memberservice.getModelById(Integer.valueOf(map.get("member_id").toString()));
				member.setRecommendpeople(afterArray[b]);
				memberservice.updateMember(member);
				b += 1;
			}
		}
		out.print("success");
		return;
	}
}