package com.ry.ryapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.ShippingAddress;
import com.ry.core.service.ShippingAddressService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;


/**
 * 商品地址
 * @author MH
 */
@Controller
public class ShippingAddressController {
	
	@Resource
	ShippingAddressService shippingAddressService;
	
	/**
	 * 获取默认贴现地址（当不存在默认时则选择其一）
	 * @author MH
	 * @param addressId 选择的地址
	 * @param memberId 用户主键
	 * @param model
	 */
	@RequestMapping("/app/goodsaddress/get/default")
	public String getDefault(Integer addressId,Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			ShippingAddress address = null;
			if(addressId!=null){
				address = shippingAddressService.getById(addressId);
			}else{
				address = shippingAddressService.getDefaultByMemberId(memberId);
			}
			result.put("data", address);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 分页贴现地址
	 * @author MH
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 */
	@RequestMapping("/app/goodsaddress/list")
	public String list(ShippingAddress form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getMemberId()!=null){
			PageResults<ShippingAddress> page = shippingAddressService.getPageList(pageIndex, pageSize, form);
			result.put("data",page.getResults());
			result.put("response", "success");
			result.put("msg", "获取成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键获取对象
	 * @author MH
	 * @param addressId
	 * @param model
	 */
	@RequestMapping("/app/goodsaddress/get")
	public String get(Integer addressId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			ShippingAddress address =shippingAddressService.getById(addressId);
			String a = "["+address.getAddress_index()+"]";
			address.setAddress_index(a);
			result.put("data",address);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存对象
	 * @author MH
	 * @param address
	 * @param model
	 */
	@RequestMapping("app/goodsaddress/save")
	public String save(ShippingAddress address,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(address==null)throw new Exception("数据异常");
			if(address!=null && address.getId()!=null){
				shippingAddressService.updateModel(address);
			}else{
				shippingAddressService.saveModel(address);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 设置默认交易城市
	 * @author MH
	 * @param id 贴现地址主键
	 * @param memberId 用户主键
	 * @param model
	 * @since 2016年8月9日 下午7:08:32
	 */
	@RequestMapping("/app/goodsaddress/set/default")
	public String setDefault(Integer id,Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null || memberId==null)throw new Exception();
			shippingAddressService.updateDefaultByMemberId(id, memberId);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 删除地址
	 * @author MH
	 * @param id
	 * @param memberId
	 * @param model
	 * @since 2016年8月9日 下午7:46:56
	 */
	@RequestMapping("/app/goodsaddress/del")
	public String del(Integer id,Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null || memberId==null)throw new Exception();
			shippingAddressService.deleteById(id);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
