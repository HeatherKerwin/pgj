package com.utiexian.rywap.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Address;
import com.ry.core.entity.Region;
import com.ry.core.service.AddressService;
import com.ry.core.service.RegionService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;

/**
 * 贴现地址
 */
@Controller
@RequestMapping("/wap/address")
public class AddressController {
	
	@Resource
	AddressService addressService;
	
	@Resource
	RegionService regionService;
	
	/**
	 * 打开贴现地址列表
	 * @author WKX
	 * @param memberId 用户主键
	 */
	@RequestMapping("/list")
	public String address(Integer memberId,HttpServletRequest request,Model model){
		if(memberId!=null){
			model.addAttribute("address", addressService.getByMemberId(memberId));
		}
		return "/address-list";
	}
	
	/**
	 * 保存对象（贴现地址）
	 * @author WKX
	 * @param adress 贴现地址
	 */
	@RequestMapping("/save")
	public String save(Address address,String cityCode,HttpServletRequest request,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String error = "操作失败";
		try {
			if(address==null)throw new Exception(error = "数据异常");
			if(StringUtils.isNotBlank(cityCode)){
				Region region = regionService.getByCode(cityCode);
				if(region!=null){
					address.setCityId(region.getId());
				}
			}else if(StringUtils.isNotBlank(address.getPlace())){
				List<Region> regionL = regionService.getByNameAndType(address.getPlace(), "C");
				if(regionL!=null && regionL.size()>0){
					address.setCityId(regionL.get(0).getId());//城市id
				}
			}
			if(address.getCityId()==null)address.setCityId(Constant.DEFAULT_CITY_ID);//获取不到城市则保存默认城市ID
			if(address.getId()!=null){
				Address temp = addressService.getById(address.getId());
				temp.setName(address.getName());
				temp.setMobile(address.getMobile());
				temp.setPlace(address.getPlace());
				temp.setAddress(address.getAddress());
				temp.setOther(address.getOther());
				temp.setSex(address.getSex());
				temp.setCityId(address.getCityId());
				temp.setLongitude(address.getLongitude());
				temp.setLatitude(address.getLatitude());
				address.setUpdatedate(new Date());
				addressService.updateModel(temp);
			}else{
				List<Address> list = addressService.getByMemberId(address.getMemberId());
				if(list!=null && list.size()>=10)throw new Exception(error = "操作失败，最多只能设置10个贴现地址");
				address.setCreatetime(new Date());
				addressService.saveModel(address);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", error);
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 打开页面（新增或编辑）
	 * @author WKX
	 * @param addressId 贴现地址主键
	 * @param model
	 */
	@RequestMapping("/get")
	public String getAddress(Integer addressId,Model model){
		if(addressId!=null){
			Address address = addressService.getById(addressId);
			if(address!=null){
				model.addAttribute("address",address);
				model.addAttribute("longitude",address.getLongitude()+"");
				model.addAttribute("latitude",address.getLatitude()+"");
			}
		}
		return "/address-add";
	}
	
	/**
	 * 删除地址
	 * @author WKX
	 * @param id 贴现地址主键
	 * @param model
	 */
	@RequestMapping("del")
	public String del(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			addressService.deleteById(id);
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
	 * @author WKX
	 * @param id 贴现地址主键
	 * @param memberId 用户主键
	 * @param model
	 */
	@RequestMapping("set/default")
	public String setDefault(Integer id,Integer memberId,HttpServletRequest request,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null || memberId==null)throw new Exception();
			
			addressService.updateDefaultByMemberId(id, memberId);
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
	 * 获取用户设置的贴现地址
	 * @author WKX
	 * @param addressId 地址主键
	 * @param memberId 用户主键
	 * @param model
	 */
	@RequestMapping("/get/default")
	public String getDefault(Integer addressId,Integer memberId,HttpServletRequest request,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Address address = null;
			if(addressId!=null){
				address = addressService.getById(addressId);
			}else{
				address = addressService.getDefaultByMemberId(memberId);
			}
			if(address==null)throw new Exception();
			
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
}