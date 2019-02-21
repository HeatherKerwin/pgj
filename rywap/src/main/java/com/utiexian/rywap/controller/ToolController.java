package com.utiexian.rywap.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.City;
import com.ry.core.entity.Currentprice;
import com.ry.core.entity.Gongcui;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Lianhang;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Provice;
import com.ry.core.entity.Region;
import com.ry.core.entity.Shibor;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.CityService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.GongcuiService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.LianhangService;
import com.ry.core.service.ProviceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/wap/tool")
public class ToolController {
	
	@Resource
	private GongcuiService gongcuiService;

	@Resource
	private DiscountrecordService discountrecordService;

	@Resource
	private HistorypriceService historypriceService;

	@Resource
	private RegionService regionService;
	
	@Resource
	private TiexianNoticeService tiexianNoticeService;

	@Resource
	private ActivecountService activecountService;
	
	@Resource
	private ProviceService proviceService;
	
	@Resource
	private CityService cityService;

	@Resource
	private LianhangService lianhangService;
	
	@Resource
	private ShiborService shiborService;
	private Long count;
	
	/**
	 * 工具
	 */
	@RequestMapping("")
	public String tool(){
		return "tool";
	}
	/**
	 * 公催查询
	 * @author ZWD
	 */
	@RequestMapping("/urge")
	public String urge() {
		return "tool/urge";
	}
	
	/**
	 * 询价
	 * @author ZWD
	 */
	@RequestMapping("/inquiry")
	public String inquiry() {
		return "tool/inquiry";
	}
	
	/**
	 * 联行号查询
	 * @author ZWD
	 */
	@RequestMapping("/bank")
	public String bank() {
		return "tool/bank";
	}
	
	/**
	 * shibor查询
	 * @author ZWD
	 */
	@RequestMapping("/shibor")
	public String shibor() {
		return "tool/shibor";
	}

	/**
	 * 公催 查询
	 * @author RY_java
	 * @param model
	 * @param gongcuinumber
	 */
	@RequestMapping("/urgeSearch")
	public String urgeSearch(String gongcuinumber, Model model) {
		List<Gongcui> lists = gongcuiService.getList(gongcuinumber);
		if (lists.size() > 0) {
			model.addAttribute("gongcui", lists.get(0));
		} else {
			model.addAttribute("gongcui", null);
		}
		return "tool/urge-list";
	}

	/**
	 * 计算器页面
	 */
	@RequestMapping("/calculator")
	public String calaculator() {
		return "tool/calculator";
	}

	/**
	 * 计算数据
	 * @param type6
	 * @param startDate
	 * @param endDate
	 * @throws Exception
	 */
	@RequestMapping("/jixidate")
	public @ResponseBody String jixidate(Integer type6, String startDate, String endDate) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Date s = DateUtil.parser(startDate, DateUtil.FORMART3);
		Date e = DateUtil.parser(endDate, DateUtil.FORMART3);
		Integer num = DateUtil.daysBetween(s, e) < 0 ? 0 : DateUtil.daysBetween(s, e);
		int tzts = 0;
		if (type6 != null)
			tzts = discountrecordService.getTzts(type6, e) < 0 ? 0 : discountrecordService.getTzts(type6, e);// 调整天数（根据票据类型）

		result.put("tzts", tzts);
		result.put("jxts", tzts + num);
		String json = JacksonUtil.objToJson(result);
		return json;
	}

	/**
	 * 计算器
	 * @param query
	 * @param allmoney
	 * @param start
	 * @param end
	 * @param cityId
	 * @param model
	 * @param limit
	 */
	@RequestMapping("/calculator/init")
	public String myExcel(Historyprice query, Float allmoney, String start, String end, Integer cityId,Model model, String limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);// 天数（对应几个月）
			query.setType3(1);// 买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);// 地域 1长三角2珠三角3华中4环渤海5西南

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

			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			query.setDay(day);// 查询当天报价

			int tzts = getTzts(query.getType6(), e);// 调整天数（根据票据类型）
			int jxts = shengDay + tzts;// 计息天数
			if(query.getType6()==2){
				// 小于等于90天0、91-178天1、大于等于179天2
				if (allmoney < 500) {// 大票没有票据属性
					if (179 <= jxts) {
						query.setType5(3);
					} else if (91 <= jxts && jxts < 179) {
						query.setType5(2);
					} else if (0 <= jxts && jxts < 91) {
						query.setType5(1);
					}
				}
			}else {
				query.setType5(2);
			}
			if (query.getType6() == 2) {
				if (allmoney >= 500) {// 大于500 设置type7清掉type2
					query.setType7(new Integer(limit));
					query.setType2(null);
				}
			}
			if (cityId > 10000) {
				Region region = new Region();
				region = regionService.getByCode(cityId.toString());
				query.setCityId(region.getId());// 根据城市进行一个查询
			}
			List<Historyprice> list = historypriceService.getList(allmoney,query);
			if (list != null && list.size() > 0) {
				Historyprice temp = list.get(0);
				result.put("data", temp);

				String rate = temp.getPrice();
				String rate1 = temp.getPrice1();
				String rate2 = temp.getPrice2();
				result.put("rate", rate);
				result.put("rate1", rate1);
				result.put("rate2", rate2);

				if (1 == query.getType6()) {// 纸票
					if (500 <= allmoney) {// 大票
						Float r = 0F;
						if (StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())) {
							r = (allmoney * 10000) * jxts * ((Float.valueOf(rate) / 30) / 1000);
						}
						result.put("txlx", r);
					} else {// 小票
						Float r = 0F;
						if (StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())) {
							r = (allmoney * 10000) * jxts * ((Float.valueOf(rate) / 30) / 1000);
							if (StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())) {
								r += (allmoney / 10) * Float.valueOf(rate1);
							}
						} else if (StringUtils.isNotBlank(rate2) && !"--".equals(rate2.trim())) {
							Float r2 = Float.valueOf(rate2);
							r = (allmoney / 10) * r2;
							if (StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())) {
								r2 -= Float.valueOf(rate1);
							}
							Float r_ = r2 / jxts / 100000 * 1000 * 30;
							result.put("rate", (float) (Math.round(r_ * 100)) / 100);
						}
						result.put("txlx", r);
					}
				} else {// 电票
					Float r = 0F;
					if (StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())) {// 年利率
						r = ((allmoney * 10000) * jxts * (Float.valueOf(rate) / 100)) / 360;
					}
					result.put("txlx", r);
				}
				result.put("allmoney", allmoney);
				result.put("response", "success");
				result.put("msg", "操作成功");
			} else {
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
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 根据节日获取调整天数
	 * @param type1
	 * @param end
	 * @throws ParseException
	 */
	private int getTzts(Integer type1, Date end) throws ParseException {
		int init = 0;
		Notice notice = tiexianNoticeService.findFestivalByNowTime(end);// 查询（当前日期在假期内）本年度提示
		if (notice != null && notice.getEndDate() != null) {
			init = DateUtil.daysBetween(end, notice.getEndDate()) + 1;// 天数（对应几个月）
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (6 == w) {
				init += 2;
			} else if (7 == w) {
				init += 1;
			}
		}
		if (1 == type1) init += 3;// 纸票加3天
		return init;
	}
	
	/**
	 * 重新计算
	 * @param type1 票据属性（1纸票、2电票）
	 * @param type2 承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	 * @param flag 电票（1半年、2一年）
	 * @param allmoney 票据金额
	 * @param start 贴现日期
	 * @param end 到期日期
	 * @param rate 月（年）利率
	 * @param rate1 参数
	 * @param model
	 */
	@RequestMapping("/reexcel/price")
	public String reExcel(Integer type1,Integer type2,Integer flag,Float allmoney,String start,String end,String rate,String rate1,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
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
			
			if(1==type1){//纸票
				if(500<=allmoney){//大票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						r = (float) (r/1.2);
					}
					result.put("txlx", r);
				}else{//小票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
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
				}
				result.put("txlx", r);
			}
			
			result.put("tzts", tzts);
			result.put("jxts", jxts);
			result.put("allmoney", allmoney);
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
	
	@RequestMapping("/xunjia2")
	public String xunjia(Integer memberId,Float allmoney, Historyprice query, String dates,Model model,String cityName,Integer cityId,Integer limit) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Activecount activecount = new Activecount();
			Long activetime = System.currentTimeMillis();
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime)) + " 00:00:00").getTime();
			Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime)) + " 23:59:59").getTime();
			count = activecountService.countActive(begintimelong, endtimelong, memberId);
			if (count != null || count <= 0) {

			} else {
				activecount.setActivetime(activetime);
				activecount.setMemberid(memberId);
				activecountService.addActivecount(activecount);
			}
			
			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			query.setDay(day);// 查询当天报价

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
			if(limit != null){
				if(allmoney>=500){//大于500 设置type7清掉type2
					query.setType7(limit);
					query.setType2(null);
				}
			}else{
				if(query.getType6()==2){//如果选择了电票且limt为空 默认1年
					if(allmoney>=500){//大于500 设置type7清掉type2
						query.setType7(limit);
						query.setType2(null);
					}
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
	 * @param p 报价
	 * @param allmoney 总金额
	 * @param jxts 计息天数
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
	 * 联行号查询加载城市
	 * @author ZWD
	 * @param model
	 * @throws IOException
	 * 2017年4月6日09:22:56
	 */
	@RequestMapping("provicecity")
	public String lianhang(Model model) throws IOException {
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
	 * @author ZWD
	 * @param yinhang 银行
	 * @param provice 省份
	 * @param city 城市
	 * @param keyword 关键字
	 * @param model
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 * 2016年12月12日下午5:15:09
	 */
	@RequestMapping("/lianhangSearch")
	public String lianhangSearch(String yinhang,String provice,String city,String keyword,Integer pageNo,Integer pageSize , Model model){
		Map<String,Object> result = new HashMap<String,Object>();
		String returnStr = "";
		if(pageNo==null){
			pageNo = 0;
			returnStr = "/tool/bank-list";
		}else{
			returnStr = "ajax";
		}
		if(pageSize==null)pageSize = 5;
		if(provice.endsWith("省")||provice.endsWith("市")){
			provice = provice.substring(0, provice.length()-1);
		}
		if(city.endsWith("市")){
			city = city.substring(0, city.length()-1);
		}
		Lianhang lianhang = new Lianhang();
		if (!"请选择城市".equals(city)) {
			lianhang.setCity(city);
		}
		if (!"请选择银行".equals(yinhang)) {
			lianhang.setYinhang(yinhang);
		}
		lianhang.setProvice(provice);
		lianhang.setYinhangdesc(keyword);
		PageResults<Lianhang> page = lianhangService.getListBylianhang(lianhang, pageNo, pageSize);
		if(page!=null){
			result.put("data", page.getResults());
			result.put("response", "success");
			result.put("msg", "获取成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		if(pageNo==0){
			model.addAttribute("retValue", page.getResults());
		}else{
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
		}
		return returnStr;
	}
	
	/**
	 * shibor初始化，默认最近一条
	 * @author ZY
	 * @param model
	 * 2016年12月12日下午5:11:46
	 */
	@RequestMapping("/shibor/init")
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
	 * shibor 查询
	 * @author ZWD
	 * @param model
	 * @param day 日期
	 * 2016年12月12日下午5:11:16
	 */
	@RequestMapping("/findshibor")
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
	
}