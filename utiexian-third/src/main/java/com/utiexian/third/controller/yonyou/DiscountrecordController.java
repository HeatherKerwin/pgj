package com.utiexian.third.controller.yonyou;

import java.io.IOException;
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
@RequestMapping("/yonyou/discountrecord")
public class DiscountrecordController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 根据主键获取对象
	 * @author MH
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
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/get",params, String.class);

		Map<String, Object> map = JSON.parseObject(res);
		Map<Object,Object> maps =  (Map<Object,Object>)map.get("data");
		if(maps!=null){
			if(maps.get("memberid") == null || !member.get("id").toString().equals(maps.get("memberid").toString()))return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		}
		
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 银票贴现
	 * @author MH
	 * @param draftNo 票号（电票下单必填）
	 * @param type1 票据金额 1纸票2电票
	 * @param type2 承兑行类型 1国股2城商3三农
	 * @param begintime 开始时间
	 * @param endtime 结束时间
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
	 * @param source 来源
	 * @param allmoney 总额
	 * @param memberother 备注
	 * @param picpath 图片
	 * @param memberid 用户主键
	 * @param backEndorse 是否有回头背书（T\F）
	 * @param model
	 */
	@PostMapping("/tiexian")
	public String tiexian(String draftNo,Integer type1,Integer type2,String begintime,String endtime,Integer hasTicket,Integer flawTicket,Integer needTodoor,Integer acceptTime,
			String membername,Integer membersex,String membermobile,String address,Float longitude,Float latitude,Integer cityId,String place,String bank,String cnapsCode,
			Integer endorse,Float allmoney,String memberother,String picpath,Integer memberid,Integer jxts,String source,String backEndorse,Model model) throws IOException {
		
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
		params.set("type2",type2);
		params.set("start",begintime);
		params.set("end",endtime);
		params.set("hasTicket",hasTicket);
		params.set("flawTicket",flawTicket);
		params.set("needTodoor",needTodoor);
		params.set("acceptTime",acceptTime);
		
		params.set("membername",membername);
		params.set("membersex",membersex);
		params.set("membermobile",membermobile);
		params.set("address",address);
		params.set("longitude",longitude);
		params.set("latitude",latitude);
		params.set("cityId",cityId);
		params.set("place",place);
		
		params.set("bank",bank);
		params.set("cnapsCode",cnapsCode);
		
		params.set("endorse",endorse);
		params.set("allmoney",allmoney);
		params.set("memberother",memberother);
		params.set("picpath",picpath);
		params.set("memberid",memberid);
		params.set("jxts",jxts);
		
		if(StringUtils.isNotBlank(source)){
			params.set("source",source);//来源
		}else{
			params.set("source","APP");//来源
		}
		params.set("handleState","0");//默认待处理
		params.set("backEndorse",backEndorse);//是否有回头背书（T\F）
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/tiexian",params, String.class);

		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 确认订单（支付、派单）
	 * @author MH
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
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/update",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
	
	/**
	 * [订单流程]选择交易的机构
	 * @author MH
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
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/update/select",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [订单流程]确认背书（纸票上传背书照片）
	 * @author MH
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
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/update/endorsetime",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [订单流程]确认打款
	 * @author MH
	 * @param id 订单主键
	 * @param orderflag 当前提交状态(5)
	 */
	@PostMapping(value="/update/confirmaccounttime")
	public String updateConfirmAccountTime(Integer id,Integer orderflag,Model model) throws Exception{
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("id",id);
		params.set("orderflag",orderflag);
		
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/update/confirmaccounttime",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [取消订单]下单、支付派单、待选择机构（cancel、cancelCause取消订单，在选择机构时填写）
	 * @author MH
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
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/cancel/unconfirm",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [取消订单]待机构打款（保证金给机构）
	 * @author MH
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
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/cancel/waitpay",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 订单详细信息的展示
	 * @author MH
	 * @param discId 订单Id
	 * @param order_type 订单类型
	 * （下面的为返回参数）
	 * id: 3182,
	 *	memberid: 91, 用户Id
	 *	orderflag: 1, 订单状态
	 *	type1: 1,1纸2电
	 *	listmap: 机构评价集合
	 *			confirmAccountTime: 180,确认收款时间
	 *			endorseTime: 180,背书速度
	 *			advanceTime: 180，打款时间
	 *			price: 4.166666666666667, 票据真实
	 *			speed: 4.5, 打款速度
	 *			score: 1,机构评分
	 *			service: 3.8333333333333335,服务
	 *			door: 0.8333333333333334, 上门率
	 *			singleRate: 25, 成单率
	 *			memberId: 91, 机构yonghuId
	 *			txje: "111"贴现金额
	 *			superiority ：优势
	 *	create_time: "2017-06-29 14:17:51",派单时间
	 *	flaw_ticket: 1,是否瑕疵
	 *	type2: 1,国股，大商，小商，外资，三农，城镇
	 *	ordernumber: "170629141751103",订单号
	 *	endorse: 1,背书
	 *	need_todoor: 1,是否需要上门
	 *	jxts: "187",计息天数
	 *	allmoney: 169,总金额
	 *	order_type: "DISCOUNTRECORD" 订单类别（银：DISCOUNTRECORD，商 ：DISCOUNTRECORDSP）
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value="/details")
	public String getDiscInfoById(Integer discId,String order_type,HttpServletRequest request,Model model){
		Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
		if(member == null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("discId",discId);
		params.set("order_type",order_type);
		String res = restTemplate.postForObject("http://utiexian-server/discountrecord/details",params, String.class);
		
		Map<String, Object> map = JSON.parseObject(res);
		Map<Object,Object> maps =  (Map<Object,Object>)map.get("data");
		if(maps!=null){
			if(maps.get("memberid") == null || !member.get("id").toString().equals(maps.get("memberid").toString()))return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		}
		
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
}