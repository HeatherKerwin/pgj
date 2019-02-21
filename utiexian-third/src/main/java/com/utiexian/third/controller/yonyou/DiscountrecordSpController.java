package com.utiexian.third.controller.yonyou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.utiexian.utils.BaseController;
import com.utiexian.utils.utils.DateUtil;

@Controller
@RequestMapping("/yonyou/discountrecordsp")
public class DiscountrecordSpController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id 订单主键
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value="/get")
	public String get(Integer id,HttpServletRequest request,Model model) throws Exception{
		Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
		if(member == null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/get",params, String.class);
	
		Map<String, Object> map = JSON.parseObject(res);
		Map<Object,Object> maps =  (Map<Object,Object>)map.get("data");
		if(maps!=null){
			if(maps.get("memberId") == null || !member.get("id").toString().equals(maps.get("memberId").toString()))return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		}
		
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 商票贴现
	 * @author WKX
	 * @param draftNo 票号（电票下单必填）
	 * @param type1 票据金额 1纸票2电票
	 * @param type2 承兑行类型 1国股2城商3三农
	 * @param print 开票时间
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param hasTicket 票已开出（0是 1否）
	 * @param flawTicket 瑕疵票（0是 1否）
	 * @param needTodoor 要求上门（1是 0否）
	 * @param acceptTime 承兑期限（0半年期、1一年期）
	 * @param membername 贴现地址的姓名
	 * @param membersex 1男2女
	 * @param membermobile 手机号
	 * @param address 详细地址
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param cityId 城市主键
	 * @param place 城市
	 * @param bank 承兑行全称
	 * @param cnapsCode 承兑行大小额行号
	 * @param endorse 背书
	 * @param allmoney 总额
	 * @param memberother 备注
	 * @param picpath 图片
	 * @param memberid 用户主键
	 * @param backEndorse 是否有回头背书（T\F）
	 * @param model
	 */
	@PostMapping("tiexian")
	public String tieXian(String draftNo,Integer type1,Integer memberId,String print,String start,String end,Integer hasTicket,Integer flawTicket,Integer acceptTime,Integer needTodoor,
			String memberName,Integer memberSex,String memberMobile,String address,Float longitude,Float latitude,Integer cityId,String place,String bank,String cnapsCode,
			Integer endorse,Float allmoney,String picpath,String remarks,Integer jxts,String source,String backEndorse,Model model){

		if(type1==2){//电票必须传票号（基础校验）
			if(StringUtils.isBlank(draftNo))return this.DATE_FAILED("电票订单必须输入票号！", model);
			if(draftNo.length()!=30)return this.DATE_FAILED("票号输入有误！", model);
			
			String prefix = draftNo.substring(0, 1);//第一位表示票据种类，1代表电子银行承兑汇票，2代表电子商业承兑汇票；
			if(!"1".equals(prefix) && !"2".equals(prefix))return this.DATE_FAILED("票号输入有误！", model);
			
			String date = draftNo.substring(13, 21);
			if(!DateUtil.isValidDate(date))return this.DATE_FAILED("票号输入有误！", model);
		}
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("draftNo", draftNo);
		
		params.set("type1", type1);
		params.set("print",print);
		params.set("start",start);
		params.set("end",end);
		params.set("hasTicket",hasTicket);
		params.set("flawTicket",flawTicket);
		params.set("needTodoor",needTodoor);
		params.set("acceptTime",acceptTime);
		
		params.set("memberName",memberName);
		params.set("memberSex",memberSex);
		params.set("memberMobile",memberMobile);
		params.set("address",address);
		params.set("longitude",longitude);
		params.set("latitude",latitude);
		params.set("cityId",cityId);
		params.set("place",place);
		
		params.set("bank",bank);
		params.set("cnapsCode",cnapsCode);
		
		params.set("endorse",endorse);
		params.set("allmoney",allmoney);
		params.set("picpath",picpath);
		params.set("remarks",remarks);
		params.set("memberId",memberId);
		params.set("jxts",jxts);
		
		if(StringUtils.isNotBlank(source)){
			params.set("source",source);//来源
		}else{
			params.set("source","APP");//来源
		}
		params.set("handleState","0");//默认待处理
		params.set("backEndorse",backEndorse);//是否有回头背书（T\F）
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/tiexian",params, String.class);

		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 确认订单（支付、派单）
	 * @author WKX
	 * @param id 订单主键
	 * @param vAcctAcctNo 虚拟账户（兴业数金）
	 * @param selectOrgId 指定机构
	 * @param couponId 红包主键（是否使用红包）
	 */
	@PostMapping("/update")
	public String update(Integer id,String vAcctAcctNo,Integer selectOrgId,Integer couponId,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("vAcctAcctNo", vAcctAcctNo);
		params.set("selectOrgId",selectOrgId);
		
		params.set("couponId",couponId);//红包主键（是否使用红包）
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/update",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [订单流程]选择交易的机构
	 * @author WKX
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(1)
	 * @param selectDistId 选择的交易机构（订单主键）
	 * @param txlx 选择当前报价（防止资方同事修改报价）
	 */
	@PostMapping(value="/update/select")
	public String updateSelect(Integer id,Integer orderflag,Integer selectDistId,String txlx,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		params.set("selectDistId",selectDistId);
		params.set("txlx",txlx);
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/update/select",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [订单流程]确认背书（纸票上传背书照片）
	 * @author WKX
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(2)
	 * @param endorsePicPath 背书上传图片
	 */
	@PostMapping(value="/update/endorsetime")
	public String endorseTime(Integer id,Integer orderflag,String endorsePicPath,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		params.set("endorsePicPath",endorsePicPath);
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/update/endorsetime",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [订单流程]确认打款
	 * @author WKX
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(5)
	 */
	@PostMapping(value="/update/confirmaccounttime")
	public String updateConfirmAccountTime(Integer id,Integer orderflag,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/update/confirmaccounttime",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [取消订单]下单、支付派单、待选择机构（cancel、cancelCause取消订单，在选择机构时填写）
	 * @author WKX
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(1)
	 * @param cancel 拒绝理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	 * @param cancelCause 取消原因
	 * @param model
	 */
	@PostMapping(value="/cancel/unconfirm")
	public String cancelFromUnconfirm(Integer id,Integer orderflag,Integer cancel,String cancelCause,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		params.set("cancel",cancel);
		params.set("cancelCause",cancelCause);
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/cancel/unconfirm",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [取消订单]待机构打款（保证金给机构）
	 * @author WKX
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(4)
	 * @param cancel 拒绝理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	 * @param cancelCause 取消原因
	 * @param model
	 */
	@PostMapping(value="/cancel/waitpay")
	public String cancelFromWaitpay(Integer id,Integer orderflag,Integer cancel,String cancelCause,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		params.set("cancel",cancel);
		params.set("cancelCause",cancelCause);
		String res = restTemplate.postForObject("http://utiexian-server/discountrecordsp/cancel/waitpay",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
}