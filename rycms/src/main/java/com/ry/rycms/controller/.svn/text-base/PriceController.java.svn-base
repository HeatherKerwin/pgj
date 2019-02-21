package com.ry.rycms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.entity.PriceType;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.PriceService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.service.RegionService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.excel.ViewExcel;

@Controller
@RequestMapping("/price")
public class PriceController {

	@Resource
	private PriceService priceService;

	@Resource
	private OrgInfoService orgInfoService;
	
	@Resource
	private PriceTypeService priceTypeService;

	@Resource
	private OrgCityService orgCityService;
	
	@Resource
	private RegionService regionService;
	
	/**
	 * 导出excel报表
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public ModelAndView exportExcel(ModelMap model, HttpServletRequest request) {
		Map<String,List<String[]>> dataMap = new HashMap<String, List<String[]>>();//excel数据
		String[] sheets = getSheets();//以交易城市作为sheets
		String[] endData = null;
		// 第一行所有的头数据：所有的机构名称
		String[] headData = setHeadList();
		Integer[] cityIds = getCityIds();//报价城市列表
		String createTime = request.getParameter("time");
		if (!StringUtils.isNotBlank(createTime)) {
			createTime = DateUtil.formart(new Date(), DateUtil.FORMART3);
		}
		try {
			//获取所有满足条件的机构
			List<Map<String, Object>> orgList = orgInfoService.getOrgNameAndLimit(1, createTime);
			//获取所有报价类型
			List<PriceType> priceTypeList =  priceTypeService.getPriceType();
			if(sheets==null||cityIds==null) throw new Exception("无法获取报价城市列表，尝试检查org_city表");
			for(int index=0;index<cityIds.length;index++){
				List<String[]> dataList = new ArrayList<String[]>();
				Integer cityId = cityIds[index];
				//获取某天的所有报价
				List<Map<String, Object>> priceList = priceService.getPriceNTypeByDateNCity(createTime,cityId);//加入cityId条件
				//newList为orgList中去掉了未报价机构剩余部分
				List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
				if(orgList.size()>0&&priceList.size()>0){//从priceList中找到报价的org
					//****外层循环
					for(Map<String, Object> orgMap : orgList){
						String orgId = orgMap.get("orgid").toString();
						//内层循环-----
						for(Map<String, Object> priceMap:priceList){
							String curOrgId = priceMap.get("orgid").toString();
							if(orgId.equals(curOrgId)){//外层循环中的orgId有有报价  newList加入它 内层循环不必继续
								newList.add(orgMap);
								break;
							}
						}
						//内层循环------
					}
					//****外层循环
				}
				//去掉未报价结束
				if (priceList != null && priceList.size() > 0) {
					for (Map<String, Object> orgMap : newList) {//改为newList
						String orgId = orgMap.get("orgid").toString();
						String orgName = orgMap.get("company").toString();
						String limitPrice = orgMap.get("price") != null ? orgMap.get("price").toString() : "";
						String phone = orgMap.get("phone")!= null ? orgMap.get("phone").toString() : "";//手机号
						List<String> rowData = new ArrayList<String>();
						rowData.add(orgName);
						rowData.add(limitPrice);
						rowData.add("+86 "+phone);//手机号
						List<String> rowData1 = setBodyList(priceTypeList, priceList, orgId);
						rowData.addAll(rowData1);
						String[] row = new String[rowData.size()];
						for (int i = 0; i < rowData.size(); i ++) {
							row[i] = rowData.get(i);
						}
						dataList.add(row);
					}
				}
				dataMap.put(sheets[index], dataList);//将sheet名 与一个sheet的数据塞入
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new ViewExcel("机构报价" + createTime, headData, dataMap, endData,sheets),model);
	}
	
	//设置excel的正文主体部分的数据
	public List<String> setBodyList(List<PriceType> priceTypeList, List<Map<String, Object>> priceList, String orgId) {
		//需要返回的数据
		List<String> dataList = new ArrayList<String>();
		//当前机构的所有报价的列表信息
		List<Map<String, Object>> currentOrgPriceList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> priceMap : priceList) {
			String priceOrgId = priceMap.get("orgid").toString();
			if (orgId.equals(priceOrgId)) {
				currentOrgPriceList.add(priceMap);
			}
		}
		dataList.add(currentOrgPriceList.get(0).get("create_time").toString());
		//需要设置每一个报价类型(没有或者'--'则填空)
		for (PriceType pt : priceTypeList) {
			String priceTypeId = pt.getId().toString();
			Map<String, Object> price = new HashMap<String, Object>();
			boolean hasPriceType = false;
			for (Map<String, Object> priceMap : currentOrgPriceList) {
				String ptid = priceMap.get("ptid").toString();
				if (priceTypeId.equals(ptid)) {
					hasPriceType = true;
					price = priceMap;
				}
			}
			if (hasPriceType) {	//有该类型的报价
				setRow(dataList, price);
			} else {			//无该类型的报价
				setRow1(dataList, pt);
			}
		}
		return dataList;
	}
	
	/**
	 * 设置报价(该机构有该类型的报价)
	 * @param dataList 最终的数据(代表的是每一行)
	 * @param price 报价的数据
	 */
	public void setRow(List<String> dataList, Map<String, Object> price) {
		String gg = "", ds = "", cs = "", wz = "", ns = "", nh = "", nx = "", cz = "";
		String type1 = price.get("t1") != null ? price.get("t1").toString() : "";
		String type2 = price.get("t2") != null ? price.get("t2").toString() : "";
		if ("0".equals(type1) && "0".equals(type2)) {			//纸票大票为月利率例如3.5‰
			gg = price.get("gg") != null && !"--".equals(price.get("gg")) ? price.get("gg").toString()  : "";
			ds = price.get("ds") != null && !"--".equals(price.get("ds")) ? price.get("ds").toString()  : "";
			cs = price.get("cs") != null && !"--".equals(price.get("cs")) ? price.get("cs").toString()  : "";
			wz = price.get("wz") != null && !"--".equals(price.get("wz")) ? price.get("wz").toString()  : "";
			ns = price.get("ns") != null && !"--".equals(price.get("ns")) ? price.get("ns").toString()  : "";
			nh = price.get("nh") != null && !"--".equals(price.get("nh")) ? price.get("nh").toString()  : "";
			nx = price.get("nx") != null && !"--".equals(price.get("nx")) ? price.get("nx").toString()  : "";
			cz = price.get("cz") != null && !"--".equals(price.get("cz")) ? price.get("cz").toString()  : "";
			dataList.add(gg);
			dataList.add(ds);
		} else if ("0".equals(type1) && "1".equals(type2)) {	//电票大票为年利率例如3.5%
			gg = price.get("gg") != null && !"--".equals(price.get("gg")) ? price.get("gg").toString()  : "";
			ds = price.get("ds") != null && !"--".equals(price.get("ds")) ? price.get("ds").toString()  : "";
			cs = price.get("cs") != null && !"--".equals(price.get("cs")) ? price.get("cs").toString()  : "";
			wz = price.get("wz") != null && !"--".equals(price.get("wz")) ? price.get("wz").toString()  : "";
			ns = price.get("ns") != null && !"--".equals(price.get("ns")) ? price.get("ns").toString()  : "";
			nh = price.get("nh") != null && !"--".equals(price.get("nh")) ? price.get("nh").toString()  : "";
			nx = price.get("nx") != null && !"--".equals(price.get("nx")) ? price.get("nx").toString()  : "";
			cz = price.get("cz") != null && !"--".equals(price.get("cz")) ? price.get("cz").toString()  : "";
			dataList.add(gg);
			dataList.add(ds);
		} else if ("1".equals(type1) && "0".equals(type2)) {	//纸票小票为每十万XX例如1350
			gg = price.get("gg2") != null && !"--".equals(price.get("gg2")) ? price.get("gg2").toString() : "";
			cs = price.get("cs2") != null && !"--".equals(price.get("cs2")) ? price.get("cs2").toString() : "";
			wz = price.get("wz2") != null && !"--".equals(price.get("wz2")) ? price.get("wz2").toString() : "";
			ns = price.get("ns2") != null && !"--".equals(price.get("ns2")) ? price.get("ns2").toString() : "";
			nh = price.get("nh2") != null && !"--".equals(price.get("nh2")) ? price.get("nh2").toString() : "";
			nx = price.get("nx2") != null && !"--".equals(price.get("nx2")) ? price.get("nx2").toString() : "";
			cz = price.get("cz2") != null && !"--".equals(price.get("cz2")) ? price.get("cz2").toString() : "";
			dataList.add(gg);
		} else if ("1".equals(type1) && "1".equals(type2)) {	//电票小票为年利率例如3.5%
			gg = price.get("gg") != null && !"--".equals(price.get("gg")) ? price.get("gg").toString()  : "";
			cs = price.get("cs") != null && !"--".equals(price.get("cs")) ? price.get("cs").toString()  : "";
			wz = price.get("wz") != null && !"--".equals(price.get("wz")) ? price.get("wz").toString()  : "";
			ns = price.get("ns") != null && !"--".equals(price.get("ns")) ? price.get("ns").toString()  : "";
			nh = price.get("nh") != null && !"--".equals(price.get("nh")) ? price.get("nh").toString()  : "";
			nx = price.get("nx") != null && !"--".equals(price.get("nx")) ? price.get("nx").toString()  : "";
			cz = price.get("cz") != null && !"--".equals(price.get("cz")) ? price.get("cz").toString()  : "";
			dataList.add(gg);
		}
		dataList.add(cs);
		dataList.add(wz);
		dataList.add(ns);
		dataList.add(nh);
		dataList.add(nx);
		dataList.add(cz);
	}
	
	/**
	 * 设置报价(该机构无该类型的报价)
	 * @param dataList 最终的数据(代表的是每一行)
	 * @param pt 报价类型
	 */
	public void setRow1(List<String> dataList, PriceType pt) {
		Integer type1 = pt.getType1();
		//小票无大商，大票有大商
		int len = 7;
		if (type1 == 0) {
			len += 1;
		}
		for (int i = 1; i <= len;i++) {
			dataList.add("");
		}
	}
	
	/**
	 * 设置excel的第一行部分
	 */
	public String[] setHeadList() {
		// 第一行所有的头数据：所有的机构名称
		String[] headData = new String[] {
			"机构名称\\价格类型","额度(万元)","手机号","报价时间",
			"500万以上买断纸票国股‰", "500万以上买断纸票大商‰", "500万以上买断纸票小商‰", "500万以上买断纸票外资‰",
			"500万以上买断纸票农商‰", "500万以上买断纸票农合‰", "500万以上买断纸票农信‰", "500万以上买断纸票村镇‰",
			
			"500万以上买断电票国股%", "500万以上买断电票大商%", "500万以上买断电票小商%", "500万以上买断电票外资%",
			"500万以上买断电票农商%", "500万以上买断电票农合%", "500万以上买断电票农信%", "500万以上买断电票村镇%",
			
			"100万以上(含100万)小于等于90天纸票国股‰", "100万以上(含100万)小于等于90天纸票城商‰", "100万以上(含100万)小于等于90天纸票外资‰", "100万以上(含100万)小于等于90天纸票农商‰",
			"100万以上(含100万)小于等于90天纸票农合‰", "100万以上(含100万)小于等于90天纸票农信‰", "100万以上(含100万)小于等于90天纸票村镇‰",
			
			"100万以上(含100万)91-178天纸票国股‰", "100万以上(含100万)91-178天纸票城商‰", "100万以上(含100万)91-178天纸票外资‰", "100万以上(含100万)91-178天纸票农商‰",
			"100万以上(含100万)91-178天纸票农合‰", "100万以上(含100万)91-178天纸票农信‰", "100万以上(含100万)91-178天纸票村镇‰",
			
			"100万以上(含100万)大于等于179天纸票国股‰", "100万以上(含100万)大于等于179天纸票城商‰", "100万以上(含100万)大于等于179天纸票外资‰", "100万以上(含100万)大于等于179天纸票农商‰",
			"100万以上(含100万)大于等于179天纸票农合‰", "100万以上(含100万)大于等于179天纸票农信‰", "100万以上(含100万)大于等于179天纸票村镇‰",
			
			"50-100万(含50万)小于等于90天纸票国股‰", "50-100万(含50万)小于等于90天纸票城商‰", "50-100万(含50万)小于等于90天纸票外资‰", "50-100万(含50万)小于等于90天纸票农商‰",
			"50-100万(含50万)小于等于90天纸票农合‰", "50-100万(含50万)小于等于90天纸票农信‰", "50-100万(含50万)小于等于90天纸票村镇‰",
			
			"50-100万(含50万)91-178天纸票国股‰", "50-100万(含50万)91-178天纸票城商‰", "50-100万(含50万)91-178天纸票外资‰", "50-100万(含50万)91-178天纸票农商‰",
			"50-100万(含50万)91-178天纸票农合‰", "50-100万(含50万)91-178天纸票农信‰", "50-100万(含50万)91-178天纸票村镇‰",
			
			"50-100万(含50万)大于等于179天纸票国股‰", "50-100万(含50万)大于等于179天纸票城商‰", "50-100万(含50万)大于等于179天纸票外资‰", "50-100万(含50万)大于等于179天纸票农商‰",
			"50-100万(含50万)大于等于179天纸票农合‰", "50-100万(含50万)大于等于179天纸票农信‰", "50-100万(含50万)大于等于179天纸票村镇‰",
			
			"50万以下小于等于90天纸票国股‰", "50万以下小于等于90天纸票城商‰", "50万以下小于等于90天纸票外资‰", "50万以下小于等于90天纸票农商‰",
			"50万以下小于等于90天纸票农合‰", "50万以下小于等于90天纸票农信‰", "50万以下小于等于90天纸票村镇‰",
			
			"50万以下91-178天纸票国股‰", "50万以下91-178天纸票城商‰", "50万以下91-178天纸票外资‰", "50万以下91-178天纸票农商‰",
			"50万以下91-178天纸票农合‰", "50万以下91-178天纸票农信‰", "50万以下91-178天纸票村镇‰",
			
			"50万以下大于等于179天纸票国股‰", "50万以下大于等于179天纸票城商‰", "50万以下大于等于179天纸票外资‰", "50万以下大于等于179天纸票农商‰",
			"50万以下大于等于179天纸票农合‰", "50万以下大于等于179天纸票农信‰", "50万以下大于等于179天纸票村镇‰",
			
			"100万以上(含100万)小于等于90天电票国股%", "100万以上(含100万)小于等于90天电票城商%", "100万以上(含100万)小于等于90天电票外资%", "100万以上(含100万)小于等于90天电票农商%",
			"100万以上(含100万)小于等于90天电票农合%", "100万以上(含100万)小于等于90天电票农信%", "100万以上(含100万)小于等于90天电票村镇%",
			
			"100万以上(含100万)91-178天电票国股%", "100万以上(含100万)91-178天电票城商%", "100万以上(含100万)91-178天电票外资%", "100万以上(含100万)91-178天电票农商%",
			"100万以上(含100万)91-178天电票农合%", "100万以上(含100万)91-178天电票农信%", "100万以上(含100万)91-178天电票村镇%",
			
			"100万以上(含100万)大于等于179天电票国股%", "100万以上(含100万)大于等于179天电票城商%", "100万以上(含100万)大于等于179天电票外资%", "100万以上(含100万)大于等于179天电票农商%",
			"100万以上(含100万)大于等于179天电票农合%", "100万以上(含100万)大于等于179天电票农信%", "100万以上(含100万)大于等于179天电票村镇%",
			
			"50-100万(含50万)小于等于90天电票国股%", "50-100万(含50万)小于等于90天电票城商%", "50-100万(含50万)小于等于90天电票外资%", "50-100万(含50万)小于等于90天电票农商%",
			"50-100万(含50万)小于等于90天电票农合%", "50-100万(含50万)小于等于90天电票农信%", "50-100万(含50万)小于等于90天电票村镇%",
			
			"50-100万(含50万)91-178天电票国股%", "50-100万(含50万)91-178天电票城商%", "50-100万(含50万)91-178天电票外资%", "50-100万(含50万)91-178天电票农商%",
			"50-100万(含50万)91-178天电票农合%", "50-100万(含50万)91-178天电票农信%", "50-100万(含50万)91-178天电票村镇%",
			
			"50-100万(含50万)大于等于179天电票国股%", "50-100万(含50万)大于等于179天电票城商%", "50-100万(含50万)大于等于179天电票外资%", "50-100万(含50万)大于等于179天电票农商%",
			"50-100万(含50万)大于等于179天电票农合%", "50-100万(含50万)大于等于179天电票农信%", "50-100万(含50万)大于等于179天电票村镇%",
			
			"50万以下小于等于90天电票国股%", "50万以下小于等于90天电票城商%", "50万以下小于等于90天电票外资%", "50万以下小于等于90天电票农商%",
			"50万以下小于等于90天电票农合%", "50万以下小于等于90天电票农信%", "50万以下小于等于90天电票村镇%",
			
			"50万以下91-178天电票国股%", "50万以下91-178天电票城商%", "50万以下91-178天电票外资%", "50万以下91-178天电票农商%",
			"50万以下91-178天电票农合%", "50万以下91-178天电票农信%", "50万以下91-178天电票村镇%",
			
			"50万以下大于等于179天电票国股%", "50万以下大于等于179天电票城商%", "50万以下大于等于179天电票外资%", "50万以下大于等于179天电票农商%",
			"50万以下大于等于179天电票农合%", "50万以下大于等于179天电票农信%", "50万以下大于等于179天电票村镇%",

			"500万以上买断电票国股(半)%", "500万以上买断电票大商(半)%", "500万以上买断电票小商(半)%", "500万以上买断电票外资(半)%",
			"500万以上买断电票农商(半)%", "500万以上买断电票农合(半)%", "500万以上买断电票农信(半)%", "500万以上买断电票村镇(半)%",
		};
		return headData;
	}

	/**
	 * 获取交易城市的所有信息 包含城市的名称
	 * @param orgId 机构ID
	 * @author BKY
	 */
	@RequestMapping("/getCityByOrgId")
	public String getCityByOrgId(Model model, Integer orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> orgCityList = orgCityService.getCityByOrgId(orgId);
			result.put("orgCityList", orgCityList);
			result.put("response", "success");
			result.put("msg", "查询交易城市成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查询交易城市失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取所有org_city名作为sheets 去重
	 */
	public String[] getSheets() {
		List<Map<String, Object>> cityList = orgCityService.getOrgCityList();
		if(cityList==null||cityList.size()==0) return null;
		String[] sheets = new String[cityList.size()];
		for(int i=0;i<cityList.size();i++){
			Map<String, Object> map =  cityList.get(i);
			String cityName = map.get("name").toString();
			sheets[i] = cityName;
		}
		return sheets;
	}
	
	/**
	 * 获取所有org_city city_id 去重
	 */
	public Integer[] getCityIds() {
		List<Map<String, Object>> cityList = orgCityService.getOrgCityList();
		if(cityList==null||cityList.size()==0) return null;
		Integer[] ids = new Integer[cityList.size()];
		for(int i=0;i<cityList.size();i++){
			Map<String, Object> map =  cityList.get(i);
			Integer cityId = Integer.valueOf(map.get("id").toString());
			ids[i] = cityId;
		}
		return ids;
	}
}
