package com.utiexian.website.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.VariablesService;
import com.ry.util.DateUtil;

/**
 * @author KHC
 * 推广
 */
@Controller
public class TgController {
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	/**
	 * 获取交易金额
	 * @author WKX
	 * @throws IOException
	 */
	@RequestMapping("/tg/jiaoyijine")
	public @ResponseBody Map<String, Object> jiaoyijine() throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> result = getJye();
			map.put("state", "success");
			map.put("month", result.get("month"));
			map.put("all", result.get("all"));
		}catch(Exception e){
			map.put("state", "error");
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
}