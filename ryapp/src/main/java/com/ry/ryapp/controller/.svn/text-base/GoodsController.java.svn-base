package com.ry.ryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Goods;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.core.service.GoodsService;
import com.ry.core.service.IntegraltradingDetailService;
import com.ry.core.service.UserSignService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 商品
 * @author MH
 */
@Controller
public class GoodsController {

	@Resource
	GoodsService goodsService;
	
	@Resource
	UserSignService userSignService;
	
	@Resource
	IntegraltradingDetailService integraltradingDetailService;

	/**
	 * 查询热门的商品
	 * @author MH
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/goods/gethotlist")
	public String getHotList(Model model,Goods goods){
		Map<String,Object> result = new HashMap<String, Object>();
		goods.setHotGoods(0);//热卖商品
		goods.setState(1);//商品已上架
		List<Goods> listgoods = goodsService. getlist(goods);
		if(listgoods.size()>0){
			result.put("data",listgoods);
			result.put("response", "success");
			result.put("msg", "查询成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	

	/**
	 * 根据商品的id获取商品对象
	 * @author MH
	 * @param id 商品id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/goods/detailed")
	public String getById(Integer id,Model model,Integer memberId) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(id == null) throw new Exception("商品主键不能够为空");
		Goods goods = goodsService.getModelById(id);
		if(memberId != null){
			IntegraltradingDetail integraltradingDetail = new IntegraltradingDetail();
			integraltradingDetail.setMemberId(memberId);
			List<IntegraltradingDetail> integral = integraltradingDetailService.getList(integraltradingDetail);
			if(integral.size()>0){
				result.put("integral", integral.get(0));
			}
		}
		if(goods != null){
			result.put("data",goods);
			result.put("response","success");
			result.put("msg","获取对象成功");
		}else{
			result.put("response","error");
			result.put("msg","获取对象失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询所有的商品
	 * @author MH
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/goods/getlist")
	public String getPagelist(Model model,GoodsRequest goodsRes,Integer pageIndex){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex == null){
			goodsRes.setStart(0L); 
		}else{
			Integer pageIndex1 = (pageIndex-1)*10;
			goodsRes.setStart(pageIndex1.longValue()); 
		}
		if(goodsRes.getLength()==null)goodsRes.setLength(10L); 
		PageResults<Goods> page = goodsService.getPage(goodsRes);
		if(page.getResults().size()>0){
			result.put("data",page.getResults());
			result.put("response", "success");
			result.put("msg", "查询成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}
