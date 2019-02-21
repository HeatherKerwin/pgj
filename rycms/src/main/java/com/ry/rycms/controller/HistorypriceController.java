package com.ry.rycms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ry.core.entity.Admin;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.PriceLog;
import com.ry.core.entity.PriceType;
import com.ry.core.entity.RequirementSp;
import com.ry.core.entity.Servicemember;
import com.ry.core.form.OrgInfoForm;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.OrgService;
import com.ry.core.service.OrgWarnService;
import com.ry.core.service.PriceLogService;
import com.ry.core.service.PriceService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.service.RequirementSpService;
import com.ry.core.service.ServicememberService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * @author Ry-wk
 */
@Controller
public class HistorypriceController {
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	PriceTypeService priceTypeService;
	
	@Resource
	VariablesService variablesService; 
	
	@Resource
	OrgLimitService orgLimitService; 
	
	@Resource
	private DistributeOrderService distributeOrderService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgWarnService orgWarnService;
	
	@Resource
	RequirementSpService requirementSpService;
	
	@Resource
	PriceLogService priceLogService;
	
	@Resource
	ServicememberService serviceMemberService;
	
	private static final Logger logger = Logger.getLogger(HistorypriceController.class);
	
	@RequestMapping("/historyprice/search/")
	public String search(Historyprice hy, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{ 
		try{			
			if(!StringUtils.hasText(hy.getDay()) ){
				hy.setDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			}
			if(hy.getType4()==null){
				hy.setType4(1);
			}
			List<Historyprice> baseEntityList = historypriceService.getList(null,hy);
//			List<Historyprice> historypriceList = new ArrayList<Historyprice>();
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				for(Historyprice baseEntity : baseEntityList){
					Historyprice historyprice = baseEntity;
					if(historyprice.getType1()==1){//国股
						if(historyprice.getType2()==1){
							request.setAttribute("hb1", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs1", historyprice);
						}
					}
					if(historyprice.getType1()==2){//城商
						if(historyprice.getType2()==1){
							request.setAttribute("hb2", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs2", historyprice);
						}
					}
					if(historyprice.getType1()==3){//外资
						if(historyprice.getType2()==1){
							request.setAttribute("hb3", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs3", historyprice);
						}
					}
					if(historyprice.getType1()==4){//农商
						if(historyprice.getType2()==1){
							request.setAttribute("hb4", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs4", historyprice);
						}
					}
					if(historyprice.getType1()==5){//农合
						if(historyprice.getType2()==1){
							request.setAttribute("hb5", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs5", historyprice);
						}
					}
					if(historyprice.getType1()==6){//农信
						if(historyprice.getType2()==1){
							request.setAttribute("hb6", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs6", historyprice);
						}
					}
					if(historyprice.getType1()==7){//村镇
						if(historyprice.getType2()==1){
							request.setAttribute("hb7", historyprice);
						}
						if(historyprice.getType2()==2){
							request.setAttribute("hs7", historyprice);
						}
					}
					
				}
			}
			String day = hy.getDay()==null?"":hy.getDay();
			request.setAttribute("day", day);
			request.setAttribute("type4", hy.getType4());
			//request.getRequestDispatcher("/manage/historyprice.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "price/historyprice";
	}
	@RequestMapping("/historyprice/updateprice/")
	public String updateprice(String day, Integer type4 , String b1, String b2, String b3, String b4, String b5, String b6, String b7,
			String bd1, String bd2, String bd3, String bd4, String bd5, String bd6, String bd7, 
			String s1, String s2, String s3, String s4, String s5, String s6, String s7,
			String sd1, String sd2, String sd3, String sd4, String sd5, String sd6, String sd7,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
	List<Historyprice> historypriceList = new ArrayList<Historyprice>();
	Historyprice hb1 = new Historyprice();
	hb1.setDay(day);
	hb1.setPrice(b1);
	hb1.setType1(1);
	hb1.setType2(1);
	hb1.setType4(type4);
	hb1.setUpdown(bd1);
	historypriceList.add(hb1);

	Historyprice hs1 = new Historyprice();
	hs1.setDay(day);
	hs1.setPrice(s1);
	hs1.setType1(1);
	hs1.setType2(2);
	hs1.setType4(type4);
	hs1.setUpdown(sd1);
	historypriceList.add(hs1);
	
	Historyprice hb2 = new Historyprice();
	hb2.setDay(day);
	hb2.setPrice(b2);
	hb2.setType1(2);
	hb2.setType2(1);
	hb2.setType4(type4);
	hb2.setUpdown(bd2);
	historypriceList.add(hb2);

	Historyprice hs2 = new Historyprice();
	hs2.setDay(day);
	hs2.setPrice(s2);
	hs2.setType1(2);
	hs2.setType2(2);
	hs2.setType4(type4);
	hs2.setUpdown(sd2);
	historypriceList.add(hs2);
	
	Historyprice hb3 = new Historyprice();
	hb3.setDay(day);
	hb3.setPrice(b3);
	hb3.setType1(3);
	hb3.setType2(1);
	hb3.setType4(type4);
	hb3.setUpdown(bd3);
	historypriceList.add(hb3);

	Historyprice hs3 = new Historyprice();
	hs3.setDay(day);
	hs3.setPrice(s3);
	hs3.setType1(3);
	hs3.setType2(2);
	hs3.setType4(type4);
	hs3.setUpdown(sd3);
	historypriceList.add(hs3);
	
	Historyprice hb4 = new Historyprice();
	hb4.setDay(day);
	hb4.setPrice(b4);
	hb4.setType1(4);
	hb4.setType2(1);
	hb4.setType4(type4);
	hb4.setUpdown(bd4);
	historypriceList.add(hb4);

	Historyprice hs4 = new Historyprice();
	hs4.setDay(day);
	hs4.setPrice(s4);
	hs4.setType1(4);
	hs4.setType2(2);
	hs4.setType4(type4);
	hs4.setUpdown(sd4);
	historypriceList.add(hs4);
	
	Historyprice hb5 = new Historyprice();
	hb5.setDay(day);
	hb5.setPrice(b5);
	hb5.setType1(5);
	hb5.setType2(1);
	hb5.setType4(type4);
	hb5.setUpdown(bd5);
	historypriceList.add(hb5);

	Historyprice hs5 = new Historyprice();
	hs5.setDay(day);
	hs5.setPrice(s5);
	hs5.setType1(5);
	hs5.setType2(2);
	hs5.setType4(type4);
	hs5.setUpdown(sd5);
	historypriceList.add(hs5);
	
	Historyprice hb6 = new Historyprice();
	hb6.setDay(day);
	hb6.setPrice(b6);
	hb6.setType1(6);
	hb6.setType2(1);
	hb6.setType4(type4);
	hb6.setUpdown(bd6);
	historypriceList.add(hb6);

	Historyprice hs6 = new Historyprice();
	hs6.setDay(day);
	hs6.setPrice(s6);
	hs6.setType1(6);
	hs6.setType2(2);
	hs6.setType4(type4);
	hs6.setUpdown(sd6);
	historypriceList.add(hs6);
	
	Historyprice hb7 = new Historyprice();
	hb7.setDay(day);
	hb7.setPrice(b7);
	hb7.setType1(7);
	hb7.setType2(1);
	hb7.setType4(type4);
	hb7.setUpdown(bd7);
	historypriceList.add(hb7);

	Historyprice hs7 = new Historyprice();
	hs7.setDay(day);
	hs7.setPrice(s7);
	hs7.setType1(7);
	hs7.setType2(2);
	hs7.setType4(type4);
	hs7.setUpdown(sd7);
	historypriceList.add(hs7);
	
	historypriceService.updateAllHistoryprice(historypriceList);
	return "redirect:/historyprice/search/?day="+day+"&type4="+type4;
	}
	
	/**
	 * 跳转至报价页面
	 * @author BKY
	 */
	@RequestMapping("/historyprice/baojia")
	public String baojia(Model model) {
		return "price/baojia";
	}
	
	/**
	 * 查询报价类型，用于动态展示页面
	 * @author BKY
	 */
	@RequestMapping("/historyprice/search/type")
	public String searchType(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<PriceType> typeList = priceTypeService.getPriceType();
			result.put("priceTypeList", typeList);
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("查询报价类型用于动态展示页面操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构ID和日期查询报价(用于后台显示报价price)
	 * @param orgId
	 * @param memberId
	 * @param orgDate
	 */
	@RequestMapping("/historyprice/baojia/searchPrice")
	public String searchPrice(Model model,Integer orgId, String orgDate, Integer cityId) {
		Map<String,Object> result = new HashMap<String, Object>();
		if ("".equals(orgDate)) {
			orgDate = DateUtil.formart(new Date(), DateUtil.FORMART3);
		}
		OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, orgDate);	//获取机构的报价额度
		if(orgLimit != null ){
		result.put("orgLimit", orgLimit);}
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Map<String,Object>> list= priceService.getPriceAndTypeByOrgId(orgId, orgDate, cityId,null);
			if (list != null && list.size() > 0) {
				result.put("priceList", list);
			}
			List <RequirementSp> lists = requirementSpService.getByOrgId(orgId,orgDate);
			if(lists != null && lists.size() > 0){
				if(lists.get(0) != null && lists.get(0).getType() == 1){//纸票
					result.put("zhipiao",lists.get(0));
				}else if(lists.get(0) != null && lists.get(0).getType() == 2){//电票
					result.put("dianpiao",lists.get(0));
				}
				if(lists.size() == 2){
				if(lists.get(1) != null && lists.get(1).getType() == 1){
					result.put("zhipiao",lists.get(1));
				}else if(lists.get(1) != null && lists.get(1).getType() == 2){//电票
					result.put("dianpiao",lists.get(1));
				}
				}
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("根据机构ID和日期查询报价操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取所有机构信息(仅是org表)
	 * @author BKY
	 */
	@RequestMapping("/historyprice/baojia/searchOrg")
	public String searchOrg(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Org> orgList = orgService.getByType(1);
			if (orgList != null && orgList.size() > 0) {
				result.put("orgList", orgList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("获取所有机构信息操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取所有机构信息(含orgInfo中的名称)
	 * @author BKY
	 */
	@RequestMapping("/historyprice/baojia/searchOrgAndName")
	public String searchOrgAndName(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> orgList = orgService.getOrgAndNameByType(1);
			if (orgList != null && orgList.size() > 0) {
				result.put("orgList", orgList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("获取所有机构信息操作(含orgInfo中的名称)失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取orgInfo信息
	 * @author BKY
	 */
	@RequestMapping("/historyprice/baojia/searchOrgAndName1")
	public String searchOrgAndName1(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> orgList = orgService.getOrgAndNameByType1(1);
			if (orgList != null && orgList.size() > 0) {
				result.put("orgList", orgList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("获取所有机构信息操作(含orgInfo中的名称)失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 跳转至修改页面
	 */
	@RequestMapping("/historyprice/xiugai")
	public String xiugai(Model model) {
		try {
			String minDay = variablesService.getByCode("MINDAY", null);
			String midDay = variablesService.getByCode("MIDDAY", null);
			String maxDay = variablesService.getByCode("MAXDAY", null);
			model.addAttribute("minDay", minDay);
			model.addAttribute("midDay", midDay);
			model.addAttribute("maxDay", maxDay);
		} catch (Exception e) {
			logger.info("跳转至修改页面操作失败" + e.getMessage());
			e.printStackTrace();
		}
		return "price/xiugai";
	}
	
	/**
	 * 获取historyprice中当天最新的交易城市
	 * 
	 */
	@RequestMapping("/historyprice/getCityListByDay")
	public String getCityListByDay(Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> cityList = historypriceService.getCityList();
			if (cityList != null && cityList.size() > 0) {
				result.put("cityList", cityList);
			}
			result.put("response", "success");
			result.put("msg", "查询交易城市成功！");
		} catch (Exception e) {
			result.put("response", "error");
			result.put("msg", "查询交易城市失败！");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询今日最新的历史报价historyprice
	 * @author BKY
	 */
	@RequestMapping("/historyprice/xiugai/searchHistoryPrice")
	public String searchHistoryPrice(Model model, String type, Integer cityId) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
			//Map<String, Object> maiDuanPager = new HashMap<String, Object>();
			Map<String, Object> maiDuanElec = new HashMap<String, Object>();
			Map<String, Object> maiDuanElecHalf = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> minMoney = new HashMap<String, Object>();
			Map<String, Object> midMoney = new HashMap<String, Object>();
			Map<String, Object> maxMoney = new HashMap<String, Object>();
//			String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			List<Historyprice> list = historypriceService.findbyDayAndType(day, type, cityId);
			if (list != null && list.size() > 0) {
				for(Historyprice hp : list) {
					Integer type3 = hp.getType3();	//@WKX 对应 APP2.1 买断带票 1买断 2带票  ：现在默认都是买断
					Integer type6 = hp.getType6();	//@WKX 对应 APP2.1机构报价表Price（纸票1、电票2）
					Integer type2 = hp.getType2();	//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
					Integer type5 = hp.getType5();	//@WKX 对应 APP2.1 月份（1三个月、2六个月）APP2.1增加：3六个月以上
					Integer type7 = hp.getType7();	//@APP2.2  期限：半年期0、一年期1
					if(type.equals("1")){
						if(type3 == 1 && (type5!=null && type5 != 3) &&  type6 == 1 && type2 ==4){
							minMoney.put("id", "minMoney");
							minMoney = setHp(minMoney, hp, "small");
						}else if(type3 == 1 && (type5!=null && type5 != 3) && type6 == 1 && type2 ==3){
							midMoney.put("id", "midMoney");
							midMoney = setHp(midMoney, hp, "small");
						}else if(type3 == 1 && (type5!=null && type5 != 3) &&  type6 == 1 && type2 ==2){
							maxMoney.put("id", "maxMoney");
							maxMoney = setHp(maxMoney, hp, "small");
						}
					}else{
						/*if(type2 == 1 && type3 == 1 && type6 == 1 ){
							maiDuanElec.put("id", "maiDuanPager");
							maiDuanElec = setHp(maiDuanPager, hp, "big");
						}*/
						if(type2 == 1 && type3 == 1 && type6 == 2 && (type7 == null || type7 == 1)) {
							maiDuanElec.put("id", "maiDuanElec");
							maiDuanElec = setHp(maiDuanElec, hp, "big");
						}else if(type2 == 1 && type3 == 1 && type6 == 2 && type7 == 0) {
							maiDuanElecHalf.put("id", "maiDuanElecHalf");
							maiDuanElecHalf = setHp(maiDuanElecHalf, hp, "big");
						}else if(type3 == 1 && type2 == 2 &&  type5 == 1) {
							maxMoneyMinDay.put("id", "maxMoneyMinDay");
							maxMoneyMinDay = setHp(maxMoneyMinDay, hp, "small");
						}else if(type3 == 1 && type2 == 2 &&  type5 == 2) {
							maxMoneyMidDay.put("id", "maxMoneyMidDay");
							maxMoneyMidDay = setHp(maxMoneyMidDay, hp, "small");
						}else if(type3 == 1 && type2 == 2 &&  type5 == 3) {
							maxMoneyMaxDay.put("id", "maxMoneyMaxDay");
							maxMoneyMaxDay = setHp(maxMoneyMaxDay, hp, "small");
						}else if(type3 == 1 && type2 == 3 &&  type5 == 1) {
							midMoneyMinDay.put("id", "midMoneyMinDay");
							midMoneyMinDay = setHp(midMoneyMinDay, hp, "small");
						}else if(type3 == 1 && type2 == 3 &&  type5 == 2) {
							midMoneyMidDay.put("id", "midMoneyMidDay");
							midMoneyMidDay = setHp(midMoneyMidDay, hp, "small");
						}else if(type3 == 1 && type2 == 3 &&  type5 == 3) {
							midMoneyMaxDay.put("id", "midMoneyMaxDay");
							midMoneyMaxDay = setHp(midMoneyMaxDay, hp, "small");
						}else if(type3 == 1 && type2 == 4 &&  type5 == 1) {
							minMoneyMinDay.put("id", "minMoneyMinDay");
							minMoneyMinDay = setHp(minMoneyMinDay, hp, "small");
						}else if(type3 == 1 && type2 == 4 &&  type5 == 2) {
							minMoneyMidDay.put("id", "minMoneyMidDay");
							minMoneyMidDay = setHp(minMoneyMidDay, hp, "small");
						}else if(type3 == 1 && type2 == 4 &&  type5 == 3) {
							minMoneyMaxDay.put("id", "minMoneyMaxDay");
							minMoneyMaxDay = setHp(minMoneyMaxDay, hp, "small");
						}
					}
				}
				resultMapList.add(maiDuanElec);
				resultMapList.add(maiDuanElecHalf);
				resultMapList.add(maxMoneyMinDay);
				resultMapList.add(maxMoneyMidDay);
				resultMapList.add(maxMoneyMaxDay);
				resultMapList.add(midMoneyMinDay);
				resultMapList.add(midMoneyMidDay);
				resultMapList.add(midMoneyMaxDay);
				resultMapList.add(minMoneyMinDay);
				resultMapList.add(minMoneyMidDay);
				resultMapList.add(minMoneyMaxDay);
				resultMapList.add(minMoney);
				resultMapList.add(midMoney);
				resultMapList.add(maxMoney);
				result.put("historyPriceList",resultMapList);
				maiDuanElec = null;
				maiDuanElecHalf = null;
				maxMoneyMinDay = null;
				maxMoneyMidDay = null;
				maxMoneyMaxDay = null;
				midMoneyMinDay = null;
				midMoneyMidDay = null;
				midMoneyMaxDay = null;
				minMoneyMinDay = null;
				minMoneyMidDay = null;
				minMoneyMaxDay = null;
			}else {
				result.put("historyPriceList", null);				
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("查询今日最新的历史报价操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存或更新historyprice
	 * @author BKY
	 */
	@RequestMapping("/historyprice/xiugai/saveOrUpdatePrice")
	public String saveOrUpdatePrice(Model model,String priceList, Integer cityId){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			List<Historyprice> historypriceList = JSON.parseArray(priceList, Historyprice.class);
			List<Historyprice> saveList = new ArrayList<Historyprice>();//遍历historypriceList同时对其remove空报价会出错，所以建立新数组
			for(Historyprice historyprice : historypriceList) {
				historyprice.setDay(day);
				historyprice.setCityId(cityId);
				saveList.add(historyprice);
			}
			historypriceService.saveOrUpdateHistoryprice(saveList);//historypriceList改为saveList
			result.put("state", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			logger.info("查询今日最新的历史报价操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 判断historyprice是否为空 （是空返回true,不是空返回false）
	 * @param historyprice 待存入数据
	 * @return 是true  否 false
	 * @author GXW
	 * @date 2016年6月17日
	 */
	public Boolean judgeEmpty(Historyprice historyprice) {
		if(StringUtils.isEmpty(historyprice.getPrice())||"--".equals(historyprice.getPrice())){// 不是A方式
			if((StringUtils.isEmpty(historyprice.getPrice1())||"--".equals(historyprice.getPrice1()))
					&&(StringUtils.isEmpty(historyprice.getPrice2())||"--".equals(historyprice.getPrice2()))){//也不是B(全空)
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查询参数配置操作
	 * @author BKY
	 */
	@RequestMapping("/historyprice/search/getVariableByCode")
	public String getVariableByCode (Model model, String code) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			String value = variablesService.getByCode(code, "187");
			result.put("day", value);
			result.put("state", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			logger.info("查询参数配置操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	public Map<String, Object> setHp(Map<String, Object> map,Historyprice hp, String style) {
		if (hp.getType1() == 1) map.put("guogu", hp.getPrice());
		else if (hp.getType1() == 2) map.put("chengshang", hp.getPrice());
		else if (hp.getType1() == 3) map.put("waizi", hp.getPrice());
		else if (hp.getType1() == 4) map.put("nongshang", hp.getPrice());
		else if (hp.getType1() == 5) map.put("nonghe", hp.getPrice());
		else if (hp.getType1() == 6) map.put("nongxin", hp.getPrice());
		else if (hp.getType1() == 7) map.put("cunzhen", hp.getPrice());
		else if (hp.getType1() == 8) map.put("dashang", hp.getPrice());
		
		if ("small".equals(style)) {
			if(hp.getType6()==1){//纸票
				if (hp.getType1() == 1){
					map.put("guogu1", hp.getPrice1());
					map.put("guogu3", hp.getMatrueprice());
				}else if (hp.getType1() == 2){
					map.put("chengshang1", hp.getPrice1());
					map.put("chengshang3", hp.getMatrueprice());
				}else if (hp.getType1() == 3){
					map.put("waizi1", hp.getPrice1());
					map.put("waizi3", hp.getMatrueprice());
				}else if (hp.getType1() == 4){
					map.put("nongshang1", hp.getPrice1());
					map.put("nongshang3", hp.getMatrueprice());
				}else if (hp.getType1() == 5){
					map.put("nonghe1", hp.getPrice1());
					map.put("nonghe3", hp.getMatrueprice());
				}else if (hp.getType1() == 6){
					map.put("nongxin1", hp.getPrice1());
					map.put("nongxin3", hp.getMatrueprice());
				}else if (hp.getType1() == 7){
					map.put("cunzhen1", hp.getPrice1());
					map.put("cunzhen3", hp.getMatrueprice());
				}
			}else if(hp.getType6()==2){
				if (hp.getType1() == 1){
					map.put("guogu1", hp.getPrice1());
				}else if (hp.getType1() == 2){
					map.put("chengshang1", hp.getPrice1());
				}else if (hp.getType1() == 3){
					map.put("waizi1", hp.getPrice1());
				}else if (hp.getType1() == 4){
					map.put("nongshang1", hp.getPrice1());
				}else if (hp.getType1() == 5){
					map.put("nonghe1", hp.getPrice1());
				}else if (hp.getType1() == 6){
					map.put("nongxin1", hp.getPrice1());
				}else if (hp.getType1() == 7){
					map.put("cunzhen1", hp.getPrice1());
				}
			}
		}
		return map;
	}
	
	/**
	 * 加载报价手机号下拉框
	 * @author GXW
	 * @param company 公司名 
	 * @since 2016年6月18日
	 */
	@RequestMapping("/historyprice/baojia/search/phonelist")
	public String searchPhoneList(Model model,String company) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> memberList = orgService.getMemberListByCompany(company);
			if (memberList != null && memberList.size() > 0) {
				result.put("memberList", memberList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("根据orgId获取memberList失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 加载报价交易城市下拉框
	 * @author GXW
	 * @param orgId
	 * @param company 公司名 
	 * @since 2016年6月18日
	 */
	@RequestMapping("/historyprice/baojia/search/citylist")
	public String searchPhoneList(Integer memberId,Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> cityList = orgService.getCityListByMemId(memberId);
			if (cityList != null && cityList.size() > 0) {
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
	 * 加载每日报价交易城市下拉框
	 * @author ZY
	 * @param memberId
	 * @param model
	 * 2016年10月17日下午1:59:25
	 */
	@RequestMapping("/historyprice/baojia/search/minprice/citylist")
	public String searchBaojiaList(Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			String date=DateUtil.formart(new Date(), DateUtil.FORMART3);
			List<Map<String, Object>> cityList = priceService.getCityListByDate(date);
			if (cityList != null && cityList.size() > 0) {
				result.put("orgCityList", cityList);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("根据日期获取cityList失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 导出表格,根据orgId
	 * @author ZY
	 * @param idStr
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年8月15日 下午2:45:23
	 */
	@RequestMapping(value = "historyprice/downloadExcel")
	public ModelAndView downloadExcel(String idStr,String isMy,HttpServletRequest request, ModelMap model, OrgInfoForm orgInfoForm)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		String[] headData = new String[] { "机构名", "联系人" , "手机号", "有无报价", };
		try {
  			List<String> orgIds = null;
			if (idStr != "" && idStr != null) {
				orgIds = Arrays.asList(idStr.split(","));
				orgInfoForm.setIds(orgIds);
			}
			//如果isMy=1则显示我的用户报价
			if(isMy!=null){
				if(isMy.equals("1")){ 
					Integer adminId = ((Admin)request.getSession().getAttribute("admin")).getId();
					//model.addAttribute("isMy", isMy);
					request.setAttribute("isMy", isMy);
					orgInfoForm.setAdminId(adminId);
				}
			}
			List<Map<String, Object>> list = orgInfoService.getByObj(orgInfoForm);
			if(list.size() > 0){
				for (Map<String, Object> map : list) {
					if(orgInfoForm.getIds() == null){
						if(orgInfoForm.getYin()!=null){
							if(orgInfoForm.getYin()==0){
								map.put("isnew","有");
							}else{
								map.put("isnew","无");
							}
						}else if(orgInfoForm.getShang()!=null){
							if(orgInfoForm.getShang()==0){
								map.put("isnew","有");
							}else{
								map.put("isnew","无");
							}
						}else{
							Date today = new Date(),d = null;
							Object time = map.get("maxt");
							if(time!=null){
								d = format.parse(time.toString());
								if(d.getTime()>=today.getTime()){
									map.put("isnew","有");
								}else{
									map.put("isnew","无");
								}
							}else{
								map.put("isnew","无");
							}
						}
					}else{
						Date today = new Date(),d = null;
						Object time = map.get("maxt");
						if(time!=null){
							d = format.parse(time.toString());
							if(d.getTime()>=today.getTime()){
								map.put("isnew","有");
							}else{
								map.put("isnew","无");
							}
						}else{
							map.put("isnew","无");
						}
					}
					String[] data = new String[headData.length];
					data[0] = map.get("org").toString();
					data[1] = map.get("name").toString();
					data[2] = map.get("phone").toString();
					data[3] = map.get("isnew").toString();
					dataList.add(data);
				}
			}else{
				String[] data = new String[1];
				data[0] = "暂无数据";
				dataList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new ViewExcel("机构报价列表", headData, dataList, endData),model);
	}
	
	/**
	 * 输出机构报价的机构名字和有无报价
	 * @author ZY
	 * @param pr
	 * @param model
	 * @since 2016年8月8日 下午3:43:18
	 */
	@RequestMapping("/historyprice/baojia/list")
	public String list(PageResults<Map<String, Object>> pr, OrgInfoForm orgInfoForm,String isMy, Model model,HttpServletRequest req) {
		if (pr.getCurrentPage() == null) {
			pr.setCurrentPage(0);
		}
		//如果isMy=1则显示我的用户报价
		if(isMy!=null){
			Integer adminId = ((Admin)req.getSession().getAttribute("admin")).getId();
			//model.addAttribute("isMy", isMy);
			req.removeAttribute("isMy");
			req.setAttribute("isMy", isMy);
			orgInfoForm.setAdminId(adminId);
		}
		try {
			Servicemember sm = new Servicemember(); 
			sm.setFlag(0);
			List<Servicemember> servicemember = serviceMemberService.getList(sm);
			model.addAttribute("ServiceMember",servicemember);
			pr = orgInfoService.getPageList(pr.getCurrentPage(), 10, orgInfoForm);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if (pr != null && pr.getResults() != null) {
			for (Map<String, Object> map : pr.getResults()) {
				Object time = map.get("maxt");
				if(time != null){
					Date d = null,d1 = null, d2 = null, d3 = null;
					String today = format.format(new Date());
					if(map.get("orgId")!=null){
						RequirementSp listsp = requirementSpService.getByAll(Integer.valueOf(map.get("orgId").toString()), null,
								DateUtil.formart(new Date(), DateUtil.FORMART3));
						if (listsp != null) {
							map.put("isnew1", "有");
						} else {
							map.put("isnew1", "无");
						}
						try {
							d = format.parse(time.toString());
							d1 = format.parse(today);
							if(orgInfoForm.getBeginDate() != null){
								d2 = format.parse(orgInfoForm.getBeginDate());
							}
							if(orgInfoForm.getEndDate() != null){
								d3 = format.parse(orgInfoForm.getEndDate());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(d2 == null && d3 == null){
							if (d.getTime() != d1.getTime()) {
								map.put("isnew", "无");
							} else {
								map.put("isnew", "有");
							}
						}else{
							if (d.getTime() > d3.getTime()&& d.getTime()<d2.getTime()) {
								map.put("isnew", "无");
							} else {
								map.put("isnew", "有");
							}
						}
					}else{
						map.put("isnew", "无");
						map.put("isnew1", "无");
					}
				}else{
					map.put("isnew", "无");
					map.put("isnew1", "无");
				}
			}
		}
		model.addAttribute("pageModel", pr);
		return "price/historylist";
	}

	/**
	 * 根据搜索条件查询结果
	 * 
	 * @author ZY
	 * @param pr
	 * @param model
	 * @param name
	 * @param isnew
	 * @since 2016年8月12日 上午11:10:19
	 */
	@RequestMapping("/historyprice/chaxun")
	public String list2(PageResults<Map<String, Object>> pr,String isMy, Model model, HttpServletRequest request,
			OrgInfoForm orgInfoForm) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null,d1 = null, d2 = null, d3 = null;
		String today = format.format(new Date());
		if (pr.getCurrentPage() == null) {
			pr.setCurrentPage(0);
		}
		try {
			//如果isMy=1则显示我的用户报价
			if(StringUtils.hasText(isMy)){
				Integer adminId = ((Admin)request.getSession().getAttribute("admin")).getId();
				//model.addAttribute("isMy", isMy);
				request.removeAttribute("isMy");
				request.setAttribute("isMy", isMy);
				orgInfoForm.setAdminId(adminId);
			}
			Servicemember sm = new Servicemember(); 
			sm.setFlag(0);
			List<Servicemember> servicemember = serviceMemberService.getList(sm);
			model.addAttribute("ServiceMember",servicemember);
			pr = orgInfoService.getPageList(pr.getCurrentPage(), 10, orgInfoForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (pr != null && pr.getResults() != null) {
			for (Map<String, Object> map : pr.getResults()) {
				if(map.get("orgId")!=null){
					Integer orgId = Integer.valueOf(map.get("orgId").toString());
					RequirementSp listsp = requirementSpService.getByAll(orgId, null,
							DateUtil.formart(new Date(), DateUtil.FORMART3));
					if (listsp != null) {
						map.put("isnew1", "有");
					} else {
						map.put("isnew1", "无");
					}
					Object time = map.get("maxt"); 
					if(time!=null){
						try {
							d = format.parse(time.toString());
							d1 = format.parse(today);
							if(StringUtils.hasText(orgInfoForm.getBeginDate())){
								d2 = format.parse(orgInfoForm.getBeginDate());
							}
							if(StringUtils.hasText(orgInfoForm.getEndDate())){
								d3 = format.parse(orgInfoForm.getEndDate());
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(d2 == null && d3 == null){
							map.put("isnew", "有");
							map.put("isnew1", "有");
						}else{
							if (d.getTime() > d3.getTime()&& d.getTime()<d2.getTime()) {
								map.put("isnew", "无");
							} else {
								map.put("isnew", "有");
							}
						}
					}else{
						map.put("isnew", "无");
						map.put("isnew1", "无");
					}
				}else{
					map.put("isnew", "无");
					map.put("isnew1", "无");
				}
			}
		}
		model.addAttribute("pageModel", pr);

		// String orgDate = DateUtil.formart(new Date(), DateUtil.FORMART3);
		return "/price/historylist";

	}

	/**
	 * 传递id给报价页面
	 * @author ZY
	 * @param id
	 * @param model
	 * @since 2016年8月9日 下午12:01:47
	 */
	@RequestMapping("/historyprice/get")
	public String update(Integer orgId, String memberId,String phone, HttpServletRequest request) {
		request.getSession().setAttribute("orgId", orgId);
		request.getSession().setAttribute("phone", phone);
		request.getSession().setAttribute("memberId", memberId);
		return "price/baojia1";
	}

	/**
	 * 异步获取机构的相关信息
	 * @author ZY
	 * @param model
	 * @since 2016年8月9日 下午2:14:56
	 */
	@RequestMapping("/historyprice/baojia/searchOrgAndName2")
	public String searchOrgAndName2(Model model, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<OrgInfo> orgInfoList = orgInfoService.getOrgInfoById(id, null);
			if (orgInfoList != null && orgInfoList.size() > 0) {
				result.put("time", new Date());
				result.put("orgInfoList", orgInfoList.get(0));
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("获取所有机构信息操作(含orgInfo中的名称)失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 最低价格
	 * @author ZY
	 * @param model
	 * @since 2016年9月18日 下午3:26:29
	 */
	@RequestMapping("/historyprice/minprice/get")
	public String getminprice(Model model) {
		try {
			String minDay = variablesService.getByCode("MINDAY", null);
			String midDay = variablesService.getByCode("MIDDAY", null);
			String maxDay = variablesService.getByCode("MAXDAY", null);
			model.addAttribute("minDay", minDay);
			model.addAttribute("midDay", midDay);
			model.addAttribute("maxDay", maxDay);
		} catch (Exception e) {
			logger.info("跳转至修改页面操作失败" + e.getMessage());
			e.printStackTrace();
		}
		return "price/minprice";
	}
	
	/**
	 * 查询今日最新的最低报价
	 * @author ZY
	 * @since 2016年9月18日 下午2:11:59
	 */
	@RequestMapping("/historyprice/minprice/searchminprice")
	public String searchMinPrice(Model model, String type,String cityId) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> resultMapList = new ArrayList<Map<String,Object>>();
			Map<String, Object> maiDuanElec = new HashMap<String, Object>();
			Map<String, Object> maiDuanElecHalf = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> maxMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> midMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMinDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMidDay = new HashMap<String, Object>();
			Map<String, Object> minMoneyMaxDay = new HashMap<String, Object>();
			Map<String, Object> minMoney = new HashMap<String, Object>();
			Map<String, Object> midMoney = new HashMap<String, Object>();
			Map<String, Object> maxMoney = new HashMap<String, Object>();
			if(type.equals("1")){//纸票
				minMoney.put("id", "minMoney");
				midMoney.put("id", "midMoney");
				maxMoney.put("id", "maxMoney");
				setMinPrice(minMoney, 11,cityId);
				setMinPrice(midMoney, 8,cityId);
				setMinPrice(maxMoney, 5,cityId);
			}else{//电票
				maiDuanElec.put("id", "maiDuanElec");
				maiDuanElecHalf.put("id", "maiDuanElecHalf");
				maxMoneyMinDay.put("id", "maxMoneyMinDay");
				maxMoneyMidDay.put("id", "maxMoneyMidDay");
				maxMoneyMaxDay.put("id", "maxMoneyMaxDay");
				midMoneyMinDay.put("id", "midMoneyMinDay");
				midMoneyMidDay.put("id", "midMoneyMidDay");
				midMoneyMaxDay.put("id", "midMoneyMaxDay");
				minMoneyMinDay.put("id", "minMoneyMinDay");
				minMoneyMidDay.put("id", "minMoneyMidDay");
				minMoneyMaxDay.put("id", "minMoneyMaxDay");
				setMinPrice(maiDuanElec, 2,cityId);
				setMinPrice(maiDuanElecHalf, 21,cityId);
				setMinPrice(maxMoneyMinDay, 12,cityId);
				setMinPrice(maxMoneyMidDay, 13,cityId);
				setMinPrice(maxMoneyMaxDay, 14,cityId);
				setMinPrice(midMoneyMinDay, 15,cityId);
				setMinPrice(midMoneyMidDay, 16,cityId);
				setMinPrice(midMoneyMaxDay, 17,cityId);
				setMinPrice(minMoneyMinDay, 18,cityId);
				setMinPrice(minMoneyMidDay, 19,cityId);
				setMinPrice(minMoneyMaxDay, 20,cityId);
			}
			resultMapList.add(maiDuanElec);
			resultMapList.add(maiDuanElecHalf);
			resultMapList.add(maxMoneyMinDay);
			resultMapList.add(maxMoneyMidDay);
			resultMapList.add(maxMoneyMaxDay);
			resultMapList.add(midMoneyMinDay);
			resultMapList.add(midMoneyMidDay);
			resultMapList.add(midMoneyMaxDay);
			resultMapList.add(minMoneyMinDay);
			resultMapList.add(minMoneyMidDay);
			resultMapList.add(minMoneyMaxDay);
			resultMapList.add(minMoney);
			resultMapList.add(midMoney);
			resultMapList.add(maxMoney);
			
			result.put("historyPriceList", resultMapList);
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			logger.info("查询今日最新的历史报价操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	public void setMinPrice(Map<String, Object> map,Integer type,String cityId) {
		Map<String, Object> min=priceService.getMinPrice(type,cityId);
		map.put("guogu", min.get("guogu").toString());
		map.put("guogu1", min.get("guogu1").toString());
		map.put("guogu2", min.get("guogu2").toString());
		map.put("guogu3", min.get("guogu3").toString());
		map.put("chengshang", min.get("chengshang").toString());
		map.put("chengshang1", min.get("chengshang1").toString());
		map.put("chengshang2", min.get("chengshang2").toString());
		map.put("chengshang3", min.get("chengshang3").toString());
		map.put("waizi", min.get("waizi").toString());
		map.put("waizi1", min.get("waizi1").toString());
		map.put("waizi2", min.get("waizi2").toString());
		map.put("waizi3", min.get("waizi3").toString());
		map.put("nongshang", min.get("nongshang").toString());
		map.put("nongshang1", min.get("nongshang1").toString());
		map.put("nongshang2", min.get("nongshang2").toString());
		map.put("nongshang3", min.get("nongshang3").toString());
		map.put("nonghe", min.get("nonghe").toString());
		map.put("nonghe1", min.get("nonghe1").toString());
		map.put("nonghe2", min.get("nonghe2").toString());
		map.put("nonghe3", min.get("nonghe3").toString());
		map.put("nongxin", min.get("nongxin").toString());
		map.put("nongxin1", min.get("nongxin1").toString());
		map.put("nongxin2", min.get("nongxin2").toString());
		map.put("nongxin3", min.get("nongxin3").toString());
		map.put("cunzhen", min.get("cunzhen").toString());
		map.put("cunzhen1", min.get("cunzhen1").toString());
		map.put("cunzhen2", min.get("cunzhen2").toString());
		map.put("cunzhen3", min.get("cunzhen3").toString());
		map.put("dashang", min.get("dashang").toString());
	}
	
	/**
	 * 复制historyprice
	 * @author ZY
	 * @since 2016年9月18日 下午6:37:05
	 */
	@RequestMapping("/historyprice/minprice/updateprice")
	public String updateHistoryPrice(HttpServletRequest request,Model model,String priceList, Integer cityId){
		Map<String,Object> result = new HashMap<String, Object>();
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		PriceLog log = new PriceLog();
		log.setOperatorId(admin.getId());
		log.setOperatorCode(admin.getUsername());
		log.setOperatorType(OperatorType.ADMIN);
		log.setCreateTime(new Date());
		log.setIp(HttpUtil.getIpAddr(request));
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			List<Historyprice> historypriceList = JSON.parseArray(priceList, Historyprice.class);
			List<Historyprice> saveList = new ArrayList<Historyprice>();//遍历historypriceList同时对其remove空报价会出错，所以建立新数组
			for(Historyprice historyprice : historypriceList) {
				historyprice.setDay(day);
				historyprice.setCityId(cityId);
				if(!judgeEmpty(historyprice)){//报价不为空 ，加入saveList
					saveList.add(historyprice);
				}
			}
			historypriceService.saveOrUpdateHistoryprice(saveList);//historypriceList改为saveList
			result.put("state", "success");
			result.put("msg", "保存成功");
			log.setDescription("【复制至价格修改】：操作成功！");
		} catch (Exception e) {
			logger.info("查询今日最新的历史报价操作失败" + e.getMessage());
			result.put("state", "failed");
			result.put("msg", "操作失败");
			log.setDescription("【复制至价格修改】：操作失败！" + e.getMessage());
			e.printStackTrace();
		}
		priceLogService.saveModel(log);//保存操作日志
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}