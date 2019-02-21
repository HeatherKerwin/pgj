package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Address;
import com.ry.core.entity.Region;
import com.ry.core.form.AddressForm;
import com.ry.core.service.AddressService;
import com.ry.core.service.RegionService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 贴现地址
 * @author WKX
 */
@Controller
@RequestMapping("/app/address/")
public class AddressController {
	
	@Resource
	AddressService addressService;
	
	@Resource
	RegionService regionService;
	
	
	/**
	 * 初始化省市（认证页面用）
	 * @param model
	 * @throws Exception
	 * @author WKX
	 */
	@RequestMapping("init/city")
	public String initCity(Integer orgCityId,Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<Region> p = regionService.getByType("P");//省
		List<Region> c = regionService.getByType("C");//市
		result.put("p", p);
		result.put("c", c);
		
		if(orgCityId!=null){
			Map<String, Object> city = addressService.getAddressInfoById(orgCityId);
			result.put("city", city);
		}
		
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 分页贴现地址
	 * @author WKX
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年8月9日 下午2:33:53
	 */
	@RequestMapping("list")
	public String list(AddressForm form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getMemberId()!=null){
			PageResults<Address> page = addressService.getPageList(pageIndex, pageSize, form);
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
	 * 保存对象
	 * @author WKX
	 * @param address
	 * @param model
	 * @since 2016年8月9日 下午4:58:55
	 */
	@RequestMapping("save")
	public String save(Address address,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(address==null)throw new Exception("数据异常");
			
			if(address.getCityId()==null && org.apache.commons.lang.StringUtils.isNotBlank(address.getPlace())){
				List<Region> regionL = regionService.getByNameAndType(address.getPlace(), "C");
				if(regionL!=null && regionL.size()>0){
					address.setCityId(regionL.get(0).getId());//城市id
				}else{
					address.setCityId(Constant.DEFAULT_CITY_ID);//城市id
				}
			}else if(address.getCityId()==null){
				address.setCityId(Constant.DEFAULT_CITY_ID);//城市id
			}
			if(address!=null && address.getId()!=null){
				Address address1 = addressService.getById(address.getId());
				Date updatedate = new Date();
				address.setCreatetime(address1.getCreatetime());
				address.setUpdatedate(updatedate);
				addressService.updateModel(address);
			}else{
				Date founddate = new Date();
				address.setCreatetime(founddate);
				addressService.saveModel(address);
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
	 * 根据主键获取对象
	 * @author WKX
	 * @param addressId
	 * @param model
	 * @since 2016年8月9日 下午5:29:46
	 */
	@RequestMapping("get")
	public String get(Integer addressId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			result.put("data", addressService.getById(addressId));
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
	 * @since 2016年8月9日 下午7:08:32
	 */
	@RequestMapping("set/default")
	public String setDefault(Integer id,Integer memberId,Model model){
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
	 * 获取默认贴现地址（当不存在默认时则选择其一）
	 * @author WKX
	 * @param addressId 选择的地址
	 * @param memberId 用户主键
	 * @param model
	 * @since 2016年8月12日 下午1:38:49
	 */
	@RequestMapping("get/default")
	public String getDefault(Integer addressId,Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Address address = null;
			if(addressId!=null){
				address = addressService.getById(addressId);
			}else{
				address = addressService.getDefaultByMemberId(memberId);
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
	 * 删除地址
	 * @author WKX
	 * @param id
	 * @param memberId
	 * @param model
	 * @since 2016年8月9日 下午7:46:56
	 */
	@RequestMapping("del")
	public String del(Integer id,Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null || memberId==null)throw new Exception();
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
}