package com.utiexian.website.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.City;
import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Currentprice;
import com.ry.core.entity.Gongcui;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Lianhang;
import com.ry.core.entity.Member;
import com.ry.core.entity.Message;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Provice;
import com.ry.core.entity.Shibor;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.BillQuotaService;
import com.ry.core.service.CityService;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.GongcuiService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.LianhangService;
import com.ry.core.service.MessageService;
import com.ry.core.service.ProviceService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;


@Controller
@RequestMapping("/homepage/")
public class HomepageController {
	
	private Long count;
	
	private static final Logger logger = Logger.getLogger(HomepageController.class);
	
	private final String CODE = "hezuo";//合作
	private final String STYLE = "PC_HYT";//电脑端（默认：红眼兔）还有用友
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
    BillQuotaService billQuotaService;
	
	@Resource
	ShiborService shiborService;
	
	@Resource					
	GongcuiService gongcuiService;
	
	@Resource					
	ProviceService proviceService;
	
	@Resource					
	CityService cityService;
	
	@Resource					
	LianhangService lianhangService;
	
	@Resource					
	TiexianNoticeService tiexianNoticeService;
	
	@Resource					
	MessageService messageService;
	
	@Resource
	ClickCountService clickCountService;

	@RequestMapping("/tool/inquiry")
	public String xunjiatool(){
		return "tool/tool_inquiry";
	}
	
	@RequestMapping("/tool/shibor")
	public String shibor(){
		return "tool/tool_shibor";
	}
	
	@RequestMapping("/tool/gongcui")
	public String gongcui(){
		return "tool/tool_gongcui";
	}
	
	@RequestMapping("/tool/lianhang")
	public String lianhang(){
		return "tool/tool_lianhang";
	}
	
	@RequestMapping("/tool/calculator")
	public String calculator(){
		return "tool/tool_calculator";
	}
	
	/**
	 * 询价  计算计息天数
	 * @author ZY
	 * @param type6 票据类型（1纸票、2电票）
	 * @param startDate 贴现日期
	 * @param endDate 到期日期
	 * @param model 
	 * @throws Exception
	 * @since 2016年6月12日 下午6:32:07
	 */
	@RequestMapping("/xunjia/jixidate")
	public String jixidate(Integer type6,String startDate,String endDate,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Date s = DateUtil.parser(startDate, DateUtil.FORMART3);
		Date e = DateUtil.parser(endDate, DateUtil.FORMART3);
		Integer num = DateUtil.daysBetween(s,e);
		int tzts = 0;
		if(type6!=null)tzts = discountrecordService.getTzts(type6,e);//调整天数（根据票据类型）
		
		result.put("tzts", tzts);
		result.put("jxts", tzts+num);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 加载报价城市下拉框
	 * @author ZY
	 * @param model 
	 * @since 2016年11月10日
	 */
	@RequestMapping("/xunjia/cityid")
	public String searchPhoneList(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> cityList = historypriceService.getCity();
			Map<String, Object> m=new HashMap<String, Object>();
			if (cityList != null && cityList.size() > 0) {
				for(int i=0;i<cityList.size();i++){
					if(cityList.get(i).get("cityid").equals(107)){
						m=cityList.get(i);
						cityList.remove(i);
						break;
					}
				}
				cityList.add(m);
				Collections.reverse(cityList);
				result.put("orgCityList", cityList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("根据memberId获取cityList失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * @author ZY
	 * @param request
	 * @param allmoney 金额
	 * @param query 报价
	 * @param model
	 * @param dates 
	 * @param limit 电票期限
	 * @param _s 电票期限（PC_HYT 红眼兔合作方电脑端）
	 * @throws IOException
	 * @since 2016年11月11日 下午5:40:43
	 */
	@RequestMapping("/xunjia/xunjia")
	public String xunjia(HttpServletRequest request,Float allmoney, Historyprice query, String dates,Model model,Integer limit,String _s) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		Member member=(Member) request.getSession().getAttribute("member");
		Integer memberId=null;
		if(member!=null){
		    memberId=member.getId();
		}
		try{
			if(StringUtils.isNotBlank(_s)){
				this.log(request, "INQUIRY");
			}
			
			if(memberId!=null){
				Activecount activecount = new Activecount();
				Long activetime = System.currentTimeMillis();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime)) + " 00:00:00").getTime();
				Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime)) + " 23:59:59").getTime();
				count = activecountService.countActive(begintimelong, endtimelong, memberId);//查询当天登录信息
				if (count != null || count <= 0) {
	
				} else {
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberId);
					activecountService.addActivecount(activecount);//保存活跃的时间
				}
			}
			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			query.setDay(day);// 设置为当天报价

			// 票据金额 :1:500以上 2:100-500 3:50-100 4:<50
			if (500 <= allmoney) {
				query.setType2(1);
			} else if (100 <= allmoney && allmoney < 500) {
				query.setType2(2);
			} else if (50 <= allmoney && allmoney < 100) {
				query.setType2(3);
			} else if (allmoney < 50) {
				query.setType2(4);
			}

			//APP2.2 电票期限 
			if(query.getType6()==2 && limit != null){
				if(allmoney>=500){//大于500 设置type7清掉type2
					query.setType7(limit);
					query.setType2(null);
				}
			}
			
			query.setType4(1);// 地域 1长三角2珠三角3华中4环渤海5西南
			List<Historyprice> list = historypriceService.getList(allmoney,query);
			if (list != null && list.size() != 0) {
				Historyprice price = list.get(0);
				if(StringUtils.isNotBlank(price.getPrice()) || StringUtils.isNotBlank(price.getPrice2())){
					if (dates != null) {
						result.put("txlx", excel(price, allmoney, Float.parseFloat(dates)));
					}
					result.put("response", "success");
					result.put("msg", "操作成功");
					result.put("data", price);
				}else{
					result.put("response", "failed");
					result.put("msg", "暂无数据，请尝试更改条件");
				}
			} else {
				result.put("response", "failed");
				result.put("msg", "暂无数据，请尝试更改条件");
			}
			result.put("type2", query.getType2());// 大票小票
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取信息失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 计算贴现利息
	 * @author ZY
	 * @param p 报价
	 * @param allmoney 总金额
	 * @param jxts 计息天数
	 * @since 2016年6月21日 下午4:40:31
	 */
	private Float excel(Historyprice p,Float allmoney,Float jxts){
		Float r = 0f;
		if(1==p.getType6()){//纸票
			if(500<=allmoney){//大票
				if(StringUtils.isNotBlank(p.getPrice()) && !"--".equals(p.getPrice().trim())){
					r = (allmoney*10000)*jxts*((Float.valueOf(p.getPrice())/30)/1000);
				}
			}else{//小票
				if(StringUtils.isNotBlank(p.getPrice()) && !"--".equals(p.getPrice().trim())){
					r = (allmoney*10000)*jxts*((Float.valueOf(p.getPrice())/30)/1000);
					if(StringUtils.isNotBlank(p.getPrice1()) && !"--".equals(p.getPrice1().trim())){
						r += (allmoney/10)*Float.valueOf(p.getPrice1());
					}
				}else if(StringUtils.isNotBlank(p.getPrice2()) && !"--".equals(p.getPrice2().trim())){
					r = (allmoney/10)*Float.valueOf(p.getPrice2());
				}
			}
		}else{//电票
			if(StringUtils.isNotBlank(p.getPrice()) && !"--".equals(p.getPrice().trim())){//年利率
				r = ((allmoney*10000)*jxts*(Float.valueOf(p.getPrice())/100))/360;
			}
		}
		return r;
	}

	/**
	 * 获取纸票近期票据指数 纸票
	 * @param model
	 * @param type (1:首页10个工作日;2：1个月;3:3个月)
	 * @param type1 1：一个月    2：三个月
	 * @author ZY
	 * @date 2016年5月13日
	 */
	@RequestMapping("/piaojuzhishu")
	public String getdata( Model model ,Integer type,Integer type1) {//一个月纸票
		Map<String,Object> result = new HashMap<String, Object>();
		String name=null;
		List<Map<String, Object>> zhi=new ArrayList<Map<String,Object>>();
		List<String> xDate=new ArrayList<String>();
	    List<Double> xZhi=new ArrayList<Double>();
	    if(type==1){//一个月
			Date cur = new Date();
			Calendar  g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH,-1);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			if(type1==1){
				name="纸票";
				zhi=billQuotaService.getList(null,date, 0);
			}else{
				name="电票";
				zhi=billQuotaService.getList(null,date, 1);
			}
			Collections.reverse(zhi);
		    
		    for(int i=0;i<zhi.size();i++){//间隔5天
		    	Map<String, Object> m=zhi.get(i);
		    	xDate.add(m.get("xValue")+" ");
				xZhi.add((Double)m.get("yValue"));
		    }
		}else if(type==2){//三个月
			Date cur = new Date();
			Calendar  g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH,-3);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			if(type1==1){
				name="纸票";
				zhi=billQuotaService.getList(null,date, 0);
			}else{
				name="电票";
				zhi=billQuotaService.getList(null,date, 1);
			}
			Collections.reverse(zhi);
		    for(int i=0;i<zhi.size();i++){//间隔15天
		    	Map<String, Object> m=zhi.get(i);
		    	xDate.add(m.get("xValue")+" ");
				xZhi.add((Double)m.get("yValue"));
		    }
		}
	    result.put("response", "success");
	    result.put("xZhi", xZhi);
	    result.put("jianju", zhi.size()/4);
	    result.put("xName", name);
	    result.put("xDate", xDate);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * shibor 查询
	 * @author ZY
	 * @param model
	 * @param day 日期
	 * 2016年12月12日下午5:11:16
	 */
	@RequestMapping("shibor")
	public String getshibor( Model model ,String day){
		Map<String,Object> result = new HashMap<String, Object>();
		Shibor shibor = shiborService.getShibor(day);
		if(shibor!=null){
			result.put("data", shibor);
			result.put("response", "success");
		}else{
			result.put("response", "failed");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
		
	}
	
	/**
	 * shibor初始化，默认最近一条
	 * @author ZY
	 * @param model
	 * 2016年12月12日下午5:11:46
	 */
	@RequestMapping("shibor/init")
	public String getshiborinit( Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Shibor> shibors = shiborService.getList(0, 1);
		Shibor shibor = shibors.get(0);
		if(shibor!=null){
			result.put("data", shibor);
			result.put("response", "success");
		}else{
			result.put("response", "failed");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
		
	}
	/**
	 * gongcui查询
	 * @author ZY
	 * @param model
	 * @param gongcuinumber
	 * @throws IOException
	 * 2016年12月12日下午5:12:22
	 */
	@RequestMapping("gongcui")
	public String gongcui( Model model , String gongcuinumber) throws IOException {		
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			List<Gongcui> baseEntityList = gongcuiService.getList(gongcuinumber); //.findObjectList("from Gongcui where gongcuinumber like ?",new Object[]{gongcuinumber});
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Gongcui gongcui = (Gongcui)baseEntityList.get(0);
				if(gongcui.getGongcuidate()!=null){
					gongcui.setGongcuidateStr(new SimpleDateFormat("yyyy-MM-dd").format(gongcui.getGongcuidate()));
				}
				result.put("response", "success");
				result.put("msg", gongcui);
			}else{
				result.put("response", "failed");
				result.put("msg", "无数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "无数据");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 联行号查询加载城市
	 * @author ZY
	 * @param model
	 * @throws IOException
	 * 2016年12月12日下午5:14:22
	 */
	@RequestMapping("provicecity")
	public String gongcui(Model model) throws IOException {
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			List<Provice> baseEntityList1 = proviceService.getList();
			List<City> baseEntityList2 = cityService.getList();
			Map<Integer,Provice> pmap = new HashMap<Integer,Provice>();
			for(Provice baseEntity : baseEntityList1){
				Provice provice = (Provice)baseEntity;
				pmap.put(provice.getId(), provice);
			}
			List<City> cityList = new ArrayList<City>();
			for(City baseEntity : baseEntityList2){
				City city = (City)baseEntity;
				Provice provice = pmap.get(city.getPid());
				city.setPname(provice.getName());
				cityList.add(city);
			}
			result.put("response", "success");
			result.put("provicelist", baseEntityList1);
			result.put("citylist", cityList);
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 联行号查询
	 * @author ZY
	 * @param pageIndex
	 * @param pageSize
	 * @param yinhang 银行
	 * @param provice 省份
	 * @param city 城市
	 * @param keyword 关键字
	 * @param model
	 * @param _s 电票期限（PC_HYT 红眼兔合作方电脑端）
	 * @throws IOException
	 * 2016年12月12日下午5:15:09
	 */
	@RequestMapping("lianhang/search")
	public String lianhang(Integer pageIndex,Integer pageSize,String yinhang,String provice,String city,String keyword,Model model,String _s,HttpServletRequest request) throws IOException {	
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try{
			if(StringUtils.isNotBlank(_s)){
				this.log(request, "BANK");
			}
			
			if(provice.endsWith("省")||provice.endsWith("市")){
				provice = provice.substring(0, provice.length()-1);
			}
			if(city.endsWith("市")){
				city = city.substring(0, city.length()-1);
			}
			Lianhang lianhang = new Lianhang();
			if (!"请选择城".equals(city)) {
				lianhang.setCity(city);
			}
			if (!"请选择银行".equals(yinhang)) {
				lianhang.setYinhang(yinhang);
			}
			lianhang.setProvice(provice);
			lianhang.setYinhangdesc(keyword);
			PageResults<Lianhang> page = lianhangService.getListBylianhang(lianhang, pageIndex, pageSize); 
			if(page!=null&&page.getResults().size()>0){
				result.put("response", "success");
				result.put("data", page);
			}else{
				result.put("response", "failed");
				result.put("msg", "无数据");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
			
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 计算器动态初始化，有数据则默认，没数据就不显示
	 * @author ZY
	 * @param type1
	 * @param type2
	 * @param allmoney
	 * @param start
	 * @param end
	 * @param cityId
	 * @param model
	 * @param limit
	 * @param _s 电票期限（PC_HYT 红眼兔合作方电脑端）
	 * 2016年12月12日下午5:16:32
	 */
	@RequestMapping("calculator/init")
	public String myExcel(Integer type1,Integer type2,Float allmoney,String start,String end,Integer cityId,Model model,String limit,String _s,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(_s)){
				this.log(request, "CALCULATOR");
			}
			
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
			Historyprice query = new Historyprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(500<=allmoney){
				query.setType2(1);
			}else if(100<=allmoney && allmoney<500){
				query.setType2(2);
			}else if(50<=allmoney && allmoney<100){
				query.setType2(3);
			}else if(allmoney<50){
				query.setType2(4);
			}
			
			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			query.setDay(day);//查询当天报价
			
			int tzts = getTzts(type1,e);//调整天数（根据票据类型）
			int jxts = shengDay + tzts;//计息天数
			
			//小于等于90天0、91-178天1、大于等于179天2
			if(allmoney<500){//大票没有票据属性
				if(179<=jxts){
					query.setType5(3);
				}else if(91<=jxts && jxts<179){
					query.setType5(2);
				}else if(0<=jxts && jxts<91){
					query.setType5(1);
				}
            }
			
			if(type1 == 1){
				query.setType5(2);
			}
			//APP2.2 电票期限 
			if(type1 == 2){
				if(allmoney>=500){//大于500 设置type7清掉type2
					query.setType7(new Integer(limit));
					query.setType2(null);
				}
			}
			
			query.setCityId(cityId);//根据城市进行一个查询
			
			List<Historyprice> list = historypriceService.getList(allmoney,query);
			if(list!=null && list.size()>0){
				Historyprice temp = list.get(0);
				result.put("data", temp);
				
				String rate = temp.getPrice();
				String rate1 = temp.getPrice1();
				String rate2 = temp.getPrice2();
				result.put("rate",rate);
				result.put("rate1",rate1);
				result.put("rate2",rate2);
				
				if(1==type1){//纸票
					if(500<=allmoney){//大票
						Float r = 0F;
						if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						}
						result.put("txlx", r);
					}else{//小票
						Float r = 0F;
						if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
							if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
								r += (allmoney/10)*Float.valueOf(rate1);
							}
						}else if(StringUtils.isNotBlank(rate2) && !"--".equals(rate2.trim())){
							Float r2 = Float.valueOf(rate2);
							r = (allmoney/10)*r2;
							if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
								r2 -= Float.valueOf(rate1);
							}
							Float r_ = r2/jxts/100000*1000*30;
							result.put("rate",(float) (Math.round(r_ * 100)) / 100);
						}
						result.put("txlx", r);
					}
				}else{//电票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
						r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
					}
					result.put("txlx", r);
				}
				result.put("allmoney", allmoney);
				result.put("response", "success");
				result.put("msg", "操作成功");
			}else{
				result.put("response", "success");
				result.put("msg", "暂无数据，请尝试更改条件");
			}
			result.put("tzts", tzts);
			result.put("jxts", jxts);
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据节日获取调整天数
	 * @author ZY
	 * @param type1
	 * @param end
	 * @throws ParseException 
	 * @since 2016年4月11日 下午7:49:50
	 */
	private int getTzts(Integer type1,Date end) throws ParseException{
		int init = 0;
		Notice notice = tiexianNoticeService.findFestivalByNowTime(end);//查询（当前日期在假期内）本年度提示
		if(notice!=null && notice.getEndDate()!=null){
			init = DateUtil.daysBetween(end, notice.getEndDate())+1;//天数（对应几个月）
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(6==w){
				init += 2;
			}else if(7==w){
				init += 1;
			}
		}
		if(1==type1)init += 3;//纸票加3天
		return init;
	}
	
	/**
	 * 计算器计算（首页计算器）
	 * @param type1 票据属性（1纸票、2电票）
	 * @param type2 承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	 * @param allmoney 票据金额
	 * @param start 贴现日期
	 * @param end 到期日期
	 * @param rate 月（年）利率
	 * @param rate1 参数
	 * @param jxts 计息天数
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("calculator")
	public String reExcel(Integer jxts,Integer type1,Integer type2,Float allmoney,String start,String end,String rate,String rate1,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			
			Currentprice query = new Currentprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(500<=allmoney){
				query.setType2(1);
			}else if(100<=allmoney && allmoney<500){
				query.setType2(2);
			}else if(50<=allmoney && allmoney<100){
				query.setType2(3);
			}else if(allmoney<50){
				query.setType2(4);
			}
			
			//小于等于90天0、91-178天1、大于等于179天2
			if(allmoney<500){//大票没有票据属性
				if(179<=jxts){
					query.setType5(3);
				}else if(91<=jxts && jxts<179){
					query.setType5(2);
				}else if(0<=jxts && jxts<91){
					query.setType5(1);
				}
            }
			
			if(1==type1){//纸票
				if(500<=allmoney){//大票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
					}
					result.put("txlx", r);
				}else{//小票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate1)/30)/1000);
					}
					result.put("txlx", r);
				}
			}else{//电票
				Float r = 0F;
				if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
					r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
				}
				result.put("txlx", r);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 重新计算（工具的计算器）
	 * @param type1 票据属性（1纸票、2电票）
	 * @param type2 承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	 * @param flag 电票（1半年、2一年）
	 * @param allmoney 票据金额
	 * @param start 贴现日期
	 * @param end 到期日期
	 * @param rate 月（年）利率
	 * @param rate1 参数
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("/calculator1")
	public String reExcel1(Integer jxts,Integer type1,Integer type2,Integer flag,Float allmoney,String start,String end,String rate,String rate1,String rate2,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Currentprice query = new Currentprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(500<=allmoney){
				query.setType2(1);
			}else if(100<=allmoney && allmoney<500){
				query.setType2(2);
			}else if(50<=allmoney && allmoney<100){
				query.setType2(3);
			}else if(allmoney<50){
				query.setType2(4);
			}
			
			//小于等于90天0、91-178天1、大于等于179天2
			if(allmoney<500){//大票没有票据属性
				if(179<=jxts){
					query.setType5(3);
				}else if(91<=jxts && jxts<179){
					query.setType5(2);
				}else if(0<=jxts && jxts<91){
					query.setType5(1);
				}
            }
			
			if(1==type1){//纸票
				if(allmoney>=500){//大票 
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
							r += (allmoney/10)*Float.valueOf(rate1);
						}
					}
					result.put("txlx", r);
				}else{//小票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate2) && !"--".equals(rate2.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate2)/30)/1000);
						if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
							r += (allmoney/10)*Float.valueOf(rate1);
						}
					}
					result.put("txlx", r);
				}
			}else{//电票
				Float r = 0F;
				if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
					r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
					if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
						r += (allmoney/10)*Float.valueOf(rate1);
					}
				}
				result.put("txlx", r);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 意见反馈
	 * @author ZY
	 * @param messagecontent 反馈内容
	 * @param messagenumber 反馈所留号码
	 * @param request
	 * @param model
	 * @throws IOException
	 * 2016年12月12日下午5:23:53
	 */
	@RequestMapping("/message/save")
	public String message(String messagecontent,String messagenumber,HttpServletRequest request,Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Message message = new Message();
		Member member = (Member) request.getSession().getAttribute("member");
		if(member!=null){
			message.setMemberid(member.getId());
		}
		try {
			message.setMessagenumber(messagenumber);
			message.setMessagecontent(messagecontent);
			message.setMessagetime(new Date());
			message.setSource("PC");
			messageService.saveMessage(message);
			map.put("response", "success");
			map.put("msg", "反馈成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "反馈失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 记录访问日志（合作方）
	 * @param req
	 * @param record 功能（QUERY查询查复、INQUIRY询价、CALCULATOR计算器、BANK联行号）
	 * @since 2017年8月7日 下午3:47:05
	 */
	private void log(HttpServletRequest req,String record){
		String url = req.getRequestURL().toString() ;
		ClickCount cc = new ClickCount();
		cc.setCode(CODE);
		cc.setCurrentDate(new Date());
		cc.setIp(HttpUtil.getIpAddr(req));
		String _s = req.getParameter("_s");//PC_HYT红眼兔、PC_YY用友
		if(StringUtils.isNotBlank(_s)){
			cc.setStyle(_s);
		}else{
			cc.setStyle(STYLE);
		}
		cc.setUrl(url);
		cc.setRecord(record);
		clickCountService.saveClickCount(cc);
	}
}