package com.ry.rycms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.OrgWarn;
import com.ry.core.entity.Price;
import com.ry.core.service.OrgService;
import com.ry.core.service.OrgWarnService;
import com.ry.core.service.PriceService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("app/orgwarn")
public class OrgWarnController {

	@Resource
	OrgWarnService orgWarnService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	OrgService orgService;

	/**
	 * 预警列表
	 * @author ZY
	 * @param pr
	 * @param model
	 * @since 2016年8月12日 上午9:40:15
	 */
	@RequestMapping("/list")
	public String list(PageResults<Map<String,Object>> pr,Model model){
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		pr = orgWarnService.getPageList(pr.getCurrentPage(), 10,null);	
		;
		model.addAttribute("pageModel", pr);
		System.out.println(pr);
		//String orgDate = DateUtil.formart(new Date(), DateUtil.FORMART3);
		return "/yujing/orgwarnlist";
	}
	
	/**
	 * 根据名字模糊查询预警列表
	 * @author ZY
	 * @param pr
	 * @param model
	 * @param name
	 * @since 2016年8月12日 上午10:16:35
	 */
	@RequestMapping("/chaxun")
	public String search(PageResults<Map<String,Object>> pr,Model model,String name){
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		pr = orgWarnService.getPageList(pr.getCurrentPage(), 10,name);
		model.addAttribute("pageModel", pr);
		return "/yujing/orgwarnlist";
	
	}
	
	/**
	 * 传递机构相关信息
	 * @author ZY
	 * @param id预警id，orgId机构主键
	 * @param model
	 * @since 2016年8月9日 下午12:01:47
	 */
	@RequestMapping("/price/get")
	public String getOrgWarm(Integer id, Integer orgId,HttpServletRequest request){
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("orgId", orgId);
		return "/yujing/orgwarndetail";
	}
	
	/**
	 * 确定为非恶意报价，注意这里的id是表的id，不是机构的主键org_id
	 * @author ZY
	 * @param model
	 * @param id
	 * @since 2016年8月12日 下午4:10:35
	 */
	@RequestMapping("/noteyi")
	public String isnoteyi(Model model, String id){
		Map<String, Object> result = new HashMap<String, Object>();
		OrgWarn orgWarn = orgWarnService.getById(Integer.valueOf(id));
		if(orgWarn == null){
			result.put("state", "没有该机构");
		}
		if(orgWarn!=null && orgWarn.getState()==0){
			orgWarn.setState(2);
			orgWarnService.updateModel(orgWarn);
			result.put("state", "success");
		}
		else{
			result.put("state", "failed");
		}
		
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
		
	}
	
	/**
	 * 记录为恶意报价
	 * @author ZY
	 * @param model
	 * @param id
	 * @since 2016年8月12日 下午4:33:40
	 */
	@RequestMapping("/eyi")
	public String iseyi(Model model, String id){
		Map<String, Object> result = new HashMap<String, Object>();
		OrgWarn orgWarn = orgWarnService.getById(Integer.valueOf(id));
		if(orgWarn == null){
			result.put("state", "出现错误，没有该机构");
		}
		if(orgWarn!=null && orgWarn.getState()==0){
			orgWarn.setState(1);
			orgWarn.setAmount( orgWarn.getAmount() + 1);
			orgWarnService.updateModel(orgWarn);
			result.put("state", "success");
		}
		else{
			result.put("state", "failed");
		}
		
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
		
	}
	
	/**
	 * 保存price,只保存预警的price
	 * @author ZY
	 * @param model
	 * @since 2016年8月25日 下午1:39:51
	 */
	@RequestMapping("/update")
	public String saveprice(Model model,Price price) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (price.getCityId() == null) {
			price.setCityId(Constant.DEFAULT_CITY_ID); 
		}
		try {
			
			List<Price> list = priceService.getByOrgId(price.getOrgId() , DateUtil.formart(new Date(), DateUtil.FORMART3), price.getPriceTypeId(), price.getCityId());
			if(list!=null && list.size()>0){
				Price temp = list.get(0);
				System.out.println(temp.getId());
				System.out.println(temp.getOrgId());
				if(price.getGuogu() != null)
				{temp.setGuogu(price.getGuogu());}
				if(price.getGuogu2() != null)
				{temp.setGuogu2(price.getGuogu2());}
				if(price.getChengshang() != null)
				{temp.setChengshang(price.getChengshang());}
				if(price.getChengshang2() != null)
				{temp.setChengshang2(price.getChengshang2());}
				if(price.getDashang() != null)
				{temp.setDashang(price.getDashang());}
				if(price.getDashang2() != null)
				{temp.setDashang2(price.getDashang2());}
				if(price.getWaizi() != null)
				{temp.setWaizi(price.getWaizi());}
				if(price.getWaizi2() != null)
				{temp.setWaizi2(price.getWaizi2());}
				if(price.getNongshang() != null)
				{temp.setNongshang(price.getNongshang());}
				if(price.getNongshang2() != null)
				{temp.setNongshang2(price.getNongshang2());}
				if(price.getNonghe() != null)
				{temp.setNonghe(price.getNonghe());}
				if(price.getNonghe2() != null)
				{temp.setNonghe2(price.getNonghe2());}
				if(price.getNongxin() != null)
				{temp.setNongxin(price.getNongxin());}
				if(price.getNongxin2() != null)
				{temp.setNongxin2(price.getNongxin2());}
				if(price.getCunzhen() != null)
				{temp.setCunzhen(price.getCunzhen());}
				if(price.getCunzhen2() != null)
				{temp.setCunzhen2(price.getCunzhen2());}
				priceService.savePrice(temp);
			
		} 
			result.put("state", "success");
			result.put("msg", "操作成功");	
		}catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构ID和日期查询报价(用于后台显示报价price)
	 * @author ZY
	 * @param model
	 * @param orgId
	 * @param orgDate
	 * @param cityId
	 * @since 2016年8月30日 下午1:37:23
	 */
	@RequestMapping("/searchPrice")
	public String searchPrice1(Model model, Integer orgId, String orgDate, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			if (orgDate == null) {
				orgDate = DateUtil.formart(new Date(), DateUtil.FORMART3);
			}
			
			// APP2.2版本的cms后台用法
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Map<String, Object>> list = priceService.getPriceAndTypeByOrgId1(orgId, orgDate, cityId, null);
			if (list != null && list.size() > 0) {
				result.put("priceList", list);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 加载预警交易城市下拉框
	 * @author ZY
	 * @param memberId
	 * @param model
	 * @since 2016年8月31日 下午6:02:05
	 */
	@RequestMapping("/search/citylist")
	public String searchPhoneList(Integer orgId,Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> cityList = orgService.searchCityByOrgId(orgId);
			List<Map<String, Object>> citys = new ArrayList<Map<String, Object>>();
			if (cityList != null && cityList.size() > 0) {
			for(Map<String, Object> city:cityList){
				Integer cityId=Integer.valueOf(city.get("city_id").toString());
				boolean isCity  = priceService.getPriceByCityAndOrgId(orgId, DateUtil.formart(new Date(), DateUtil.FORMART3), cityId, null);
			    if(isCity){
				citys.add(city);
			    }
			}
			result.put("citys", citys);
			}
			result.put("state", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
