package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Appimage;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.News;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Org;
import com.ry.core.entity.PriceType;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.NewsService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.ryapp.util.CacheUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
public class AppImageController {
	
	@Resource
	PicmanageService picmanageService;
	
	@Resource
	NewsService newsService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	PriceTypeService priceTypeService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	OrgService orgService;
	
	@RequestMapping("/app/banner/")
	public void banner(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		List<Appimage> appimages = picmanageService.getPicList(code);
		StringBuilder jsonString = new StringBuilder();
		
		if(!(appimages == null || appimages.size() == 0)){
			for(Appimage appimage : appimages){				
				jsonString.append(Constant.properties.getProperty("preimgUrl") + appimage.getPath()+",");
			}
			out.print(jsonString.substring(0, jsonString.length()-1));
		}else {
			out.print("error");
		}
	}
	
	/**
	 * 根据编号获取焦点图等[编号可能多个]
	 * @author WKX
	 * @param code 日历
	 * @param sort  根据sort获取确切的日历 
	 * @param request 
	 * @param response
	 * @throws IOException
	 * @since 2016年8月30日 
	 */
	@RequestMapping("/app/bannernew/get")
	public void bannernew(String code , String sort, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		List<Appimage> appimages = picmanageService.getNewInCode(code,sort);
		
		if(!(appimages == null || appimages.size() == 0)){
			out.print(JacksonUtil.objToJson(appimages));
		}else {
			out.print("error");
		}
	}
	
	/**
	 * 根据编号获取焦点图等[编号可能多个]
	 * @author WKX
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月8日 上午8:53:44
	 */
	@RequestMapping("/app/banner/get")
	public void banner(String[] code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		List<Appimage> appimages = picmanageService.getInCode(code);
		
		if(!(appimages == null || appimages.size() == 0)){
			out.print(JacksonUtil.objToJson(appimages));
		}else {
			out.print("error");
		}
	}
	
	/**
	 * APP首页显示
	 * @author WKX
	 * @param bannerCode
	 * @param adCode
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月7日 下午6:32:14
	 */
	@RequestMapping("/app/index/all")
	public String index(String[] bannerCode,String[] adCode,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String,Object> result = new HashMap<String, Object>();
		
		Map<String,Object> params = CacheUtil.getIndexParams("banners");
		if(params!=null){
			result.put("banners", params.get("banners"));
			result.put("bannerImgList", params.get("banners"));//@2.2版本的原因，企业机构首页轮播图的显示所留
			result.put("ads", params.get("ads"));
			result.put("news", params.get("news"));
		}else{
			//获取焦点图
			List<Appimage> banners = picmanageService.getInCode(bannerCode);
			//获取广告图
			List<Appimage> ads = picmanageService.getInCode(adCode);
			//获取新闻动态[仅3条]
			PageResults<News> news = newsService.getPageList(null,1,3);
			result.put("banners", banners);
			result.put("bannerImgList", banners);//@2.2版本的原因，企业机构首页轮播图的显示所留
			result.put("ads", ads);
			result.put("news", news.getResults());
			result.put("date", new Date());//记录时间标记[模拟缓存用]
			CacheUtil.setIndexParams(result);//存模拟缓存
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 清楚缓存（banner图等）
	 * @author WKX
	 * @since 2016年2月29日 下午5:29:20
	 */
	@RequestMapping("/app/clean")
	public @ResponseBody String clean(){
		CacheUtil.setIndexParams(null);//清理缓存
		return "clean success...";
	}
	
	/**
	 * 获取广告图、banner图
	 * 获取今日是否报价，报价则显示；无报价判断今日是否节假日/周末，是则显示节假日，否则显示无报价
	 * 现用于企业首页
	 * @author BKY
	 */
	@RequestMapping("/app/getAllImgAndPrice")
	public String getPicPath(Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> params = CacheUtil.getIndexParams("banners");
			if(params!=null){
				result.put("banners", params.get("banners"));
				result.put("bannerImgList", params.get("banners"));//@2.2版本的原因，企业机构首页轮播图的显示所留		
			}else{
				List<Appimage> banners = picmanageService.getPicList("index_banner");
				result.put("banners", banners);
				result.put("bannerImgList", banners);//@2.2版本的原因，企业机构首页轮播图的显示所留				
				List<Appimage> index_ad = picmanageService.getPicList("index_ad");
				result.put("index_ad", index_ad);
				CacheUtil.setIndexParams(result);//存模拟缓存
			}
			
			//判断今天是否有报价
			Date date = new Date();
			String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
			List<Historyprice> historypriceList = historypriceService.findbyDay(day);
			List<Historyprice> historypriceList1 = historypriceService.findbyDayAndType(day,"2",10000);
			if(historypriceList1 != null && historypriceList1.size() > 0){
				for (Historyprice historyprice : historypriceList1) {
					if(historyprice.getType2() ==1){
						historypriceList.add(historyprice);
					}
				}
			}
			if (historypriceList != null && historypriceList.size() > 0) {	//有报价
				List<PriceType> typeList = priceTypeService.getPriceType();
				result.put("priceTypeList", typeList);
				result.put("historypriceList", historypriceList);
			} else {
				//注释的是老版本，判断今天是否是节假日或周末
//				//判断今天是否是节假日或周末
//				Notice notice = tiexianNoticeService.findByContent(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
//				String startDate = "";
//				String endDate = "";
//				String content = "";
//				String isHoliday = "0";	//用于前端判断周末或节假日:0-不是， 1-是
//				if (notice == null ||(notice != null && (notice.getEndDate() == null || notice.getStartDate() == null))) {
//					Calendar now = Calendar.getInstance();
//					int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//					if(dayOfWeek == Calendar.SATURDAY){		//周六
//						startDate = sdf.format(now.getTime());
//						now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
//						endDate = sdf.format(now.getTime());
//						content = "世界那么大，本管家也需要放假";
//						isHoliday = "1";
//					} else if(dayOfWeek == Calendar.SUNDAY) {
//						endDate = sdf.format(now.getTime());
//						now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
//						startDate = sdf.format(now.getTime());
//						content = "世界那么大，本管家也需要放假";
//						isHoliday = "1";
//					}
//				} else {
//					isHoliday = "0";
//				}
//				//不是节假日的话判断有无报价，无报价显示无报价，有则查报价；是节假日的话直接返回上面的假日提示
//				if ("0".equals(isHoliday)) {
//					String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//					String noticeMsg = "今日暂没有价格，请稍后查看";
//					result.put("noticeMsg", noticeMsg);
//					result.put("currentTime", currentTime);
//				} else {				
//					result.put("startDate", startDate);
//					result.put("endDate", endDate);
//					result.put("content", content);
//					result.put("notice", notice);
//				}
//				result.put("isHoliday", isHoliday);
				Notice notice = tiexianNoticeService.findByContent(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
				String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
				if(notice!=null){
					String noticeMsg = notice.getContent();
					result.put("noticeMsg", noticeMsg);
				}
				result.put("currentTime", currentTime);
				String isHoliday="0";
				result.put("isHoliday", isHoliday);
			}
			result.put("state", "success");
		} catch (Exception e) {
			result.put("state", "error");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 *  价格对比
	 * @author MH
	 * @return
	 */
	@RequestMapping("app/discountrecord/getPriceContrast")
	public String getPriceContrast(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Date date = new Date();
		String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
		List<Historyprice> historypriceList = historypriceService.findbyDay(day);
		if (historypriceList != null && historypriceList.size() > 0) {	//有报价
			result.put("historypriceList", historypriceList);
			result.put("state", "success");
		}else{
			result.put("state", "failed");
			result.put("msg", "平台暂无报价，请等待平台报价！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取banner图
	 * 统计某一用户在某段时间内的已成交金额(时间为空则计算全部)
	 * 现用于机构首页
	 * @author BKY
	 */
	@RequestMapping("app/discountrecord/getMoneyByIdAndTime")
	public String getMoneyByIdAndTime(Model model, Integer memberId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//获取轮播图(banner图)
			//原来的查询企业端，现在查询机构端，将参数index_banner改为了index_jigou
			Map<String,Object> params = CacheUtil.getIndexParams("banners_org");
			if(params!=null){
				result.put("banners_org", params.get("banners_org"));
				result.put("index_app_zx", params.get("index_app_zx"));//机构首页资讯
			}else{
				List<Appimage> banners_org = picmanageService.getPicList("index_jigou");
				List<Appimage> index_app_zx = picmanageService.getPicList("index_app_zx");//机构首页资讯
				result.put("banners_org", banners_org);
				result.put("index_app_zx", index_app_zx);
				CacheUtil.setIndexParams(result);//存模拟缓存
			}
			
			//获取总金额
			Double moneyOfWeek = 0D;
			Double moneyOfAll = 0D;
			
			Org org = orgService.getByMemberId(memberId);
			if(org!=null){
				List<Map<String,Object>>  orderList = distributeOrderService.getByOrgIdAndTime(org.getId(), "", "", 3);
				if (orderList != null && orderList.size() > 0) {
					for (Map<String,Object> order : orderList) {
						if(order.get("allmoney")!=null){
							String allmoney = order.get("allmoney").toString();
							moneyOfAll += Float.valueOf(allmoney);
						}
					}
				}
				
				//获取上周的总成交额
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.WEEK_OF_YEAR, -1);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				String starttime = sdf.format(cal.getTime()) + " 00:00:00";
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.WEEK_OF_YEAR, 1);
				String endtime = sdf.format(cal.getTime()) + " 23:59:59";
				
				List<Map<String,Object>>  orderList_ = distributeOrderService.getByOrgIdAndTime(org.getId(), starttime, endtime, 3);
				if (orderList_ != null && orderList_.size() > 0) {
					for (Map<String,Object> order : orderList_) {
						if(order.get("allmoney")!=null){
							String allmoney = order.get("allmoney").toString();
							moneyOfWeek += Float.valueOf(allmoney);
						}
					}
				}
			}
			
			result.put("moneyOfWeek", moneyOfWeek);
			result.put("moneyOfAll", moneyOfAll);
			result.put("state", "success");
		} catch (Exception e) {
			result.put("state", "error");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 拦截器（联系客服）
	 * @author cx
	 */
	@RequestMapping("/app/callcustomer")
	public String callphone(){
		return "ajax";
	}
	
	/**
	 * 拦截器（拨打电话）
	 * @author cx
	 */
	@RequestMapping("/app/callmember")
	public String callMember(){
		return "ajax";
	}
	
	/**
	 * 拦截器（联系报价方）
	 * @author cx
	 */
	@RequestMapping("/app/quotation")
	public String callQuotationPhone(){
		return "ajax";
	}
	
	/**
	 * 拦截器（退出）
	 * @author cx
	 */
	@RequestMapping("/app/loginout")
	public String loginout(){
		return "ajax";
	}
}