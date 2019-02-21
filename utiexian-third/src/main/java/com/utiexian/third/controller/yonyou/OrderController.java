package com.utiexian.third.controller.yonyou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.utiexian.utils.utils.StringTemplateUtils;

@Controller
@RequestMapping("/yonyou/order")
public class OrderController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * [订单中心-企业端]根据memberId分页获取贴现订单信息[接单报价机构的数量]
	 * @author MH
	 * @param pageIndex 第几页
	 * @param pageSize 一页多少条
	 * @param memberId 用户主键
	 * @param orderflags 订单状态（-2删除（待复核）、-1订单失败、0无效订单、1待确认、2验票中、3已完成、4待收款）
	 * @param orderType 订单类型[DISCOUNTRECORD银票订单,DISCOUNTRECORDSP商票订单]
	 * @param depositState 保证金（0待支付、1初始状态（已支付）、2退换自己、3我给机构、4机构给我（含我））
	 * @param comment 评价标示（0已评价,1未评价）
	 * @param minMoney 条件查询，最小金额
	 * @param maxMoney 条件查询，最大金额
	 * @param start 条件查询，开始时间
	 * @param end 条件查询，截止时间
	 * @param type2 条件查询，承兑类型
	 * @param bank 条件查询，承兑类型
	 * @param model
	 * 请求参数说明：{
	 * 	全部（memberId， orderType）
	 * 	交易中（memberId，orderflags（1,2,4,5,7）depositState （1））
	 * 	代付款（memberId，orderflags（1）depositState （0））
	 * 	待评价（memberId，orderflags（3）comment （1））
	 * 	无效订单（memberId，orderflags（0））}
	 */
	@PostMapping("/list/disc")
	public String listDisc(Integer pageIndex,Integer pageSize,String memberId,Integer[]orderflags,String orderType,Integer depositState,Integer comment,
			Double minMoney,Double maxMoney,String start,String end,Integer type2,String bank,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("pageIndex",pageIndex);
		params.set("pageSize",pageSize);
		params.set("memberId", memberId);
		params.set("orderflags",StringTemplateUtils.arraytoString(orderflags));
		params.set("orderType",orderType);
		params.set("depositState",depositState);
		params.set("comment",comment);
		params.set("minMoney",minMoney);
		params.set("maxMoney",maxMoney);
		params.set("start",start);
		params.set("end",end);
		params.set("type2",type2);
		params.set("bank",bank);
		String res = restTemplate.postForObject("http://utiexian-server/order/list/disc",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * 根据订单Id，获取订单的信息详情（包含报价机构的评价和信息）
	 * @author MH
	 * @param discId 订单Id
	 * @param order_type 订单类型
	 * @param model
	 *  id: 3182,
	 *	memberid: 91, 用户Id
	 *	orderflag: 1, 订单状态
	 *	type1: 1,1纸2电
	 *	confirmAccountTime: 180,确认收款时间
	 *	endorseTime: 180,背书速度
	 *	advanceTime: 180，打款时间
	 *	price: 4.166666666666667, 票据真实
	 *	speed: 4.5, 打款速度
	 *	score: 1,机构评分
	 *	service: 3.8333333333333335,服务
	 *	door: 0.8333333333333334, 上门率
	 *	singleRate: 25, 成单率
	 *	memberId: 91, 机构yonghuId
	 *	txje: "111"贴现金额
	 *	superiority ：优势
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
	@PostMapping("/isselect/details")
	public String getDiscInfoAndIsSelectDistByDiscId(Integer discId,String order_type,HttpServletRequest request,Model model){
		Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
		if(member == null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("discId",discId);
		params.set("order_type",order_type);
		String res = restTemplate.postForObject("http://utiexian-server/order/isselect/details",params, String.class);
		
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
	 * 根据用户Id获取交易中订单总数
	 * @author MH
	 * @param memberId 用户Id
	 * @return
	 * count 订单数量
	 */
	@PostMapping("/get/count")
	public String getDiscountCount(Integer memberId,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		String res = restTemplate.postForObject("http://utiexian-server/order/get/count",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
}