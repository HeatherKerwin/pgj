package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.Currentprice;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Region;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.CurrentpriceService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;


@Controller
public class XunjiaController {
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	CurrentpriceService currentpriceService; 
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	RegionService regionService;

	private Long count;
	
	private static final Logger logger = Logger.getLogger(XunjiaController.class);
	
	/**
	 * 暂时废弃（/app/xunjia/）
	 * @author WKX
	 * @param memberid
	 * @param c
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年4月15日 下午10:51:51
	 */
	@RequestMapping("/app/xunjia/old")
	public void xunjia(Integer memberid, Currentprice c, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
			try{				
				Activecount activecount = new Activecount();
				Long activetime = System.currentTimeMillis();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 00:00:00").getTime();
				Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 23:59:59").getTime();
				count = activecountService.countActive(begintimelong, endtimelong, memberid);
				if(count != null || count <= 0){
				
				}else{
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberid);
					activecountService.addActivecount(activecount);
				}

				c.setType4(1);
				List<Currentprice> baseEntityList = currentpriceService.getList(c);				
				if(baseEntityList!=null&&baseEntityList.size()!=0){
					Currentprice currentprice = (Currentprice)baseEntityList.get(0);				
					map.put("response", "success");
					map.put("msg", currentprice);
					out.print(JSONArray.fromObject(map));
				}else{
					map.put("response", "failed");
					map.put("msg", "暂无该选项数据");
					out.print(JSONArray.fromObject(map));
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("response", "failed");
				map.put("msg", "获取信息失败");
				out.print(JSONArray.fromObject(map));
			}
		
	}
	
	/**
	 * 兼容APP2.1
	 * @author WKX
	 * @param memberid
	 * @param c
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年4月15日 下午10:52:33
	 */
	@RequestMapping("/app/xunjia/")
	public void xunjia(Integer memberid, Historyprice c, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
			try{				
				Activecount activecount = new Activecount();
				Long activetime = System.currentTimeMillis();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 00:00:00").getTime();
				Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 23:59:59").getTime();
				count = activecountService.countActive(begintimelong, endtimelong, memberid);
				if(count != null || count <= 0){
				
				}else{
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberid);
					activecountService.addActivecount(activecount);
				}

				if(c.getType2()==1){//大票不计算月份
					c.setType5(null);
				}
				c.setType4(1);
				c.setType6(1);//默认纸票
				List<Historyprice> baseEntityList = historypriceService.getList(c);
				if(baseEntityList!=null&&baseEntityList.size()!=0){
					Historyprice currentprice = baseEntityList.get(0);
					if(currentprice!=null && 1!=currentprice.getType2()){//小票
						currentprice.setPrice(currentprice.getPrice2());//暂时兼容新的数据格式
					}
					map.put("response", "success");
					map.put("msg", currentprice);
					out.print(JSONArray.fromObject(map));
				}else{
					map.put("response", "failed");
					map.put("msg", "暂无该选项数据");
					out.print(JSONArray.fromObject(map));
				}
			}catch(Exception e){
				e.printStackTrace();
				map.put("response", "failed");
				map.put("msg", "获取信息失败");
				out.print(JSONArray.fromObject(map));
			}
		
	}
	
	@RequestMapping("/app/paomadeng/")
	public void paomadeng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		try{
			List<Discountrecord> discountrecordList = discountrecordService.getList(3, null, null);
			map.put("response", "success");
			map.put("msg", discountrecordList);
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "error");
			map.put("msg", "获取失败!");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 查询上月及总交易金额
	 * @author RY
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/app/amounts/")
	@ResponseBody
	public Map<String, Object> jiaoyijine() throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> jye = getJye();
			map.put("response", "success");
			map.put("month", jye.get("month"));
			map.put("all", jye.get("all"));
		}catch(Exception e){
			logger.info("查询数据库出错:[/app/amounts/]",e);
			map.put("response", "error");
			map.put("msg", "获取失败!");
		}
		return map;
	}
	
	/**
	 * 缓存交易额
	 */
	private static Map<String,Object> index_params = null;
	private Map<String,Object> getJye(){
		if(index_params!=null){
			if(index_params.get("date")!=null){
				String tag1 = index_params.get("date").toString();
				String tag2 = DateUtil.formart(new Date(),"yyyy-MM-dd");
				if(!tag1.equals(tag2)){//已过期
				}else{//未过期
					return index_params;
				}
			}else{//缓存中不存在过期标示
			}
		}
		index_params = new HashMap<String, Object>();
		//缓存中没有数据需要重新获取
		Double month = discountrecordService.getMoneyLastMonth();
		Double temp = discountrecordService.getMoneyAllFinish();
		if(month==null){
			month = 0.0;
		}
		if(temp == null){
			temp = 0.0;
		}
		Double allMoney = temp + 600000;
		String add = variablesService.getByCode("ADD_TURNOVER", null);//获取额外配置额
		if(StringUtils.isNotBlank(add)){
			allMoney += Double.valueOf(add);
		}
		String add_m = variablesService.getByCode("ADD_TURNOVER_MONTH", null);//获取额外配置额(上月交易额)
		if(StringUtils.isNotBlank(add_m)){
			month += Double.valueOf(add_m);
		}
		
		DecimalFormat df = new DecimalFormat("0");
		index_params.put("date", DateUtil.formart(new Date(),"yyyy-MM-dd"));
		index_params.put("month", df.format(month));
		index_params.put("all", df.format(allMoney));
		return index_params;
	}
	
	/* ------------------------------APP2.1----------------------------------------------------------- */
	
	/**
	 * APP2.1询价
	 * @author WKX
	 * @param memberId 用户主键
	 * @param allmoney 金额
	 * @param query 报价
	 * @param model
	 * @param cityId 城市id
	 * @param cityName 城市名称
	 * @param limit 电票期限
	 * @throws IOException
	 * @since 2016年3月28日 下午5:40:43
	 */
	@RequestMapping("/app/xunjia2")
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

			//APP2.2之后加入交易城市（老版本默认上海）
			if(cityId!=null){
				query.setCityId(cityId);//城市id
			}else if(StringUtils.isNotBlank(cityName)){
				List<Region> regionL = regionService.getByNameAndType(cityName, "C");
				if(regionL!=null && regionL.size()>0){
					query.setCityId(regionL.get(0).getId());//城市id
				}else{
					query.setCityId(Constant.DEFAULT_CITY_ID);//城市id
				}
			}else{
				query.setCityId(Constant.DEFAULT_CITY_ID);//城市id
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
			List<Historyprice> list = historypriceService.getList(query);
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
	 * @author WKX
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
	 * APP2.2询价  计算计息天数
	 * @author RY
	 * @param type6 票据类型（1纸票、2电票）
	 * @param startDate 贴现日期
	 * @param endDate 到期日期
	 * @throws Exception
	 * @since 2016年6月12日 下午6:32:07
	 */
	@RequestMapping("app/xunjia2/jixidate")
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
}