package com.utiexian.rywap.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Appimage;
import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Notice;
import com.ry.core.service.BillQuotaService;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.service.VariablesService;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.utiexian.rywap.util.CacheUtil;

/**
 * 首页
 */
@Controller
@RequestMapping("/wap/index")
public class IndexController {
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	PriceTypeService priceTypeService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	PicmanageService picmanageService;
	
	@Resource
	private BillQuotaService billQuotaService;
	
	@Resource
	ClickCountService clickCountService;
	
	/**
	 * 首页（总贴现金额）
	 * @author WKX
	 * @param model
	 */
	@RequestMapping("")
	public String index(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		
		//step.1 获取总贴现金额（取自缓存中）
		Object allMoney = CacheUtil.getIndexParams("ALLMONEY");
		if(allMoney!=null){
			result.put("allMoney", allMoney);
		}else{
			String all = this.getJye();
			result.put("allMoney", all);
			CacheUtil.setIndexParams("ALLMONEY", all);
		}
		
		//step.2 日历图
		Object rilis = CacheUtil.getIndexParams("RILIS");
		if(rilis!=null){
			result.put("rilis", rilis);
		}else{
			List<Appimage> temps = picmanageService.getXzPiclist("index_rili");
			result.put("rilis", temps);
			CacheUtil.setIndexParams("RILIS", temps);
		}
		
		model.addAttribute("result",result);
		return "index";
	}
	
	/**
	 * 首页（总贴现金额）神马推广等页面使用
	 * @author WKX
	 * @param model
	 */
	@RequestMapping(value={"/{src}"})
	public String index(@PathVariable("src") String src,HttpServletRequest req,Model model){
		if(StringUtils.isNotBlank(src)){
			ClickCount clickCount = new ClickCount();
			clickCount.setIp(HttpUtil.getIpAddr(req));
			clickCount.setStyle("WAP");
			clickCount.setCurrentDate(new Date());
			clickCount.setCode(src);
			clickCount.setReferrerUrl(req.getRequestURI());
			clickCount.setUrl(req.getRequestURL().toString());
			clickCountService.saveClickCount(clickCount);
		}
		return index(model);
	}
	
	/**
	 * 首页展示-报价
	 * @author WKX
	 * @param model
	 */
	@RequestMapping("/price")
	public String price(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			List<Historyprice> historypriceList = historypriceService.findbyDay(day);
//			List<Historyprice> historypriceList1 = historypriceService.findbyDayAndType(day,"2",1);
//			if(historypriceList1 != null && historypriceList1.size() > 0){
//				for (Historyprice historyprice : historypriceList1) {
//					if(historyprice.getType2()==1){
//						historypriceList.add(historyprice);
//					}
//				}
//			}
			if (historypriceList != null && historypriceList.size() > 0) {//有报价
				for (Historyprice historyprice : historypriceList) {
					if(historyprice.getType2() ==2){//将电票100-500万的指定为500万以上的
						if(historyprice.getType5() ==3){
							historyprice.setType2(1);
						}
					}
				}
				result.put("historypriceList", historypriceList);
			} else {
				Notice notice = tiexianNoticeService.getNoticeByNowTime();
				if(notice!=null){
					String notice_content = notice.getContent();
					result.put("notice", notice_content);
				}
				result.put("currentTime", DateUtil.formart(new Date(), DateUtil.FORMART));
			}
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取成功");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	
	/**
	 * 首页-获取票据指数
	 * @author WKX
	 * @param type 类型（一个月2、三个月3）
	 * @param model
	 */
	@RequestMapping("/billquota")
	public String billQuota(Integer type,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(type!=null && type==2){
				Object one = CacheUtil.getIndexParams("BILLQUOTA_ONE");
				if(one!=null){
					result.put("data", one);
				}else{
					Map<String,Object> temp_one = this.billQuota(2);
					result.put("data", temp_one);
					CacheUtil.setIndexParams("BILLQUOTA_ONE", temp_one);
				}
			}else if(type!=null && type==3){
				Object three = CacheUtil.getIndexParams("BILLQUOTA_THREE");
				if(three!=null){
					result.put("data", three);
				}else{
					Map<String,Object> temp_three = this.billQuota(3);
					result.put("data", temp_three);
					CacheUtil.setIndexParams("BILLQUOTA_THREE", temp_three);
				}
			}
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取成功");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取指数（内部方法）
	 * @author WKX
	 * @param type 类型（一个月2、三个月3）
	 */
	public Map<String,Object> billQuota(Integer type){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> zhi = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dian = new ArrayList<Map<String, Object>>();
		
		List<Map<String, Object>> zhi1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dian1 = new ArrayList<Map<String, Object>>();
		if (type == 2) {
			Date cur = new Date();
			Calendar g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH, -1);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			zhi = billQuotaService.getList(null, date, 0);
			dian = billQuotaService.getList(null, date, 1);
		} else if (type == 3) {
			Date cur = new Date();
			Calendar g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH, -3);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			zhi = billQuotaService.getList(null, date, 0);
			dian = billQuotaService.getList(null, date, 1);
		}
		Collections.reverse(zhi);
		Collections.reverse(dian);
		List<String> xData = new ArrayList<String>();
		Iterator<Map<String, Object>> iz = zhi.iterator();
		Map<String, Object> temp = null;
		while (iz.hasNext()) {
			temp = iz.next();
			String x = temp.get("xValue").toString();
			if (!StringUtils.isBlank(x)){
				xData.add(x);
				
				temp.put("x", temp.get("xValue"));
				temp.put("y", temp.get("yValue"));
				zhi1.add(temp);
			}
		}
		Iterator<Map<String, Object>> id = dian.iterator();
		while (id.hasNext()) {// 删除多余电票日期
			temp = id.next();
			String x = temp.get("xValue").toString();
			if (xData.contains(x)){
				temp.put("x", temp.get("xValue"));
				temp.put("y", temp.get("yValue"));
				dian1.add(temp);
			}
		}
		result.put("xData", xData);
		result.put("zhi", zhi1);
		result.put("dian", dian1);
		return result;
	}
	
	/**
	 * 总贴现金额
	 * @author WKX
	 */
	private String getJye(){
		Double all = discountrecordService.getMoneyAllFinish();
		if(all == null){
			all = 0.0;
		}
		Double allMoney = all + 600000;
		String add = variablesService.getByCode("ADD_TURNOVER", null);//获取额外配置额
		if(StringUtils.isNotBlank(add)){
			allMoney += Double.valueOf(add);
		}
		
		DecimalFormat df = new DecimalFormat("0");
		return df.format(allMoney);
	}
}