package com.ry.ryapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.IntegralOrders;
import com.ry.core.entity.ShippingAddress;
import com.ry.core.service.IntegralOrdersService;
import com.ry.core.service.OrdersGoodsService;
import com.ry.core.service.ShippingAddressService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 积分订单
 * @author MH
 */
@Controller
public class IntegralOrdersController {

	@Resource
	IntegralOrdersService integralOrdersService;
	
	@Resource
	OrdersGoodsService ordersGoodsService; 
	
	@Resource
	ShippingAddressService shippingAddressService;
	
	/**
	 * 保存订单
	 * @author MH
	 * @param model
	 * @param integralOrders
	 * @param goodsId
	 * @param integral
	 * @param count
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/integralorders/save")
	public String save(Model model,IntegralOrders integralOrders,Integer goodsId ,Integer integral,Integer count) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		integralOrders.setCreateTime(new Date());
		String prefix = "HG";
		String no = randomBnsNo(prefix);
		integralOrders.setNo(no);
		integralOrdersService.saveModel(integralOrders, goodsId, integral, count);
		result.put("response", "success");
		result.put("msg", "兑换成功");
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 生成随机订单号
	 * @author MH
	 * @param prefix
	 * @return
	 */
	public static String randomBnsNo(String prefix){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(new Date());
		for(int i = 0; i < 6; i++){
			str += (int)(Math.random()*10);
		}
		String code = prefix+str;
		return code;
	}
	
	/**
	 * 获取兑换记录的订单
	 * @author MH
	 * @param model
	 * @param memberId
	 * @param indexPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/integralorders/list")
	public String getList(Model model,Integer memberId,Integer pageIndex,Integer pageSize) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex == null )pageIndex = 1;
		if(pageSize == null)pageSize = 10;
		memberId = 8025;
		Integer state = 2;//无效的订单
		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>(); 
		PageResults<Map<String, Object>> page = integralOrdersService.getPageList(pageIndex, pageSize, memberId,state);
		if(page != null){
			for (Map<String, Object> map : page.getResults()) {
				List<Map<String, Object>>  list2 = ordersGoodsService.getByGoodsOrderId(Integer.parseInt(map.get("id").toString()));
				map.put("goods",list2);
				list.add(map);
			}
			result.put("data", list);
			result.put("response", "success");
		}else{
			result.put("response", "error");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据no 获取订单详情
	 * @author MH
	 * @param model
	 * @param no 订单编号
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/integralorders/details")
	public String getOrdersDetails(Model model,String no) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(no == null) throw new Exception();
		IntegralOrders integralOrders = integralOrdersService.getByNo(no);
		if(integralOrders!= null){
			List<Map<String,Object>> listordergoods = ordersGoodsService.getByGoodsOrderId(integralOrders.getId());
			ShippingAddress address = shippingAddressService.getById(integralOrders.getShipingAddressId());
			result.put("response", "success");
			result.put("integralOrders", integralOrders);
			result.put("data", listordergoods);
			result.put("address", address);
		}else{
			result.put("response", "error");
			result.put("msg", "获取订单详情失败");
		}
		
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据订单号删除订单（修改订单）
	 * @author MH
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/integralorders/del")
	public String ordersDel(Model model ,Integer id) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(id == null) throw new Exception();
		IntegralOrders integralOrders = integralOrdersService.getById(id);
		if(integralOrders != null){
			integralOrders.setState(2);//设置无效订单
			integralOrdersService.updateOrders(integralOrders);
			result.put("response", "success");
			result.put("msg", "订单删除成功");
		}else{
			result.put("response", "error");
			result.put("msg", "获取订单详情失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	
	/**
	 * 根据主键获取订单表信息
	 * @author MH
	 * @param model
	 * @param id 订单主键
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/integralorders/getorders")
	public String getByOrder(Model model ,Integer id) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(id == null) throw new Exception();
		IntegralOrders integralOrders = integralOrdersService.getById(id);
		if(integralOrders!=null){
			result.put("data",integralOrders);
			result.put("response", "success");
		}else{
			result.put("response", "error");
			result.put("msg", "获取快递失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}	
