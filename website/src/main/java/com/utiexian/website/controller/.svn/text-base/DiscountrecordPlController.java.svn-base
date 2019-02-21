package com.utiexian.website.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Member;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.AddressService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.RegionService;
import com.ry.core.service.SalepriceService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;


/**
 * 批量贴现
 * @author MH
 *
 */
@Controller
public class DiscountrecordPlController {
		
	@Resource
	OrgService orgService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	AddressService addressService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	SalepriceService salepriceService;
	
	/**
	 * 银票贴现生成订单
	 * @author MH
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discountrecordpl/tiexian")
	public String tieXian(HttpServletRequest request) throws Exception{
		DiscountrecordPl discountrecordPl = new DiscountrecordPl();
		
		String type1 = request.getParameter("type1");
		discountrecordPl.setType1(Integer.valueOf(type1));
		String type2 =request.getParameter("type2");
		discountrecordPl.setType2(type2);
		
		String allmoney = request.getParameter("allmoney");
		discountrecordPl.setAllmoney(Double.parseDouble(allmoney));
		String minmoney = request.getParameter("minmoney");
		discountrecordPl.setMinMoney(Double.parseDouble(minmoney));
		String maxmoney = request.getParameter("maxmoney");
		discountrecordPl.setMaxMoney(Double.parseDouble(maxmoney));
		String num = request.getParameter("num");
		discountrecordPl.setAmount(Integer.valueOf(num));
		String min_expiry_day = request.getParameter("min_expiry_day");
		discountrecordPl.setMinExpiryDay(Integer.valueOf(min_expiry_day));
		String max_expiry_day = request.getParameter("max_expiry_day");
		discountrecordPl.setMaxExpiryDay(Integer.valueOf(max_expiry_day));
		String flawTicket = request.getParameter("flawTicket");
		discountrecordPl.setFlawTicket(Integer.valueOf(flawTicket));
		String needTodoor = request.getParameter("needTodoor");
		discountrecordPl.setNeedTodoor(Integer.valueOf(needTodoor));
		String remarks = request.getParameter("remarks");
		discountrecordPl.setRemarks(remarks);
		
		discountrecordPl.setCreateTime(new Date());

		String firstdate = request.getParameter("firstdate");
		Date endtime = DateUtil.parser(firstdate, DateUtil.FORMART3);	
		Date bijiao = DateUtil.addDays(endtime, 1);
		discountrecordPl.setEndtime(endtime);
		
		if(bijiao.before(new Date()) ){//订单过期
			discountrecordPl.setOrderflag(0);
		}else{
			discountrecordPl.setOrderflag(1);
		}

		discountrecordPl.setSource("PC");
		
		Member member =(Member) request.getSession().getAttribute("member");
		Integer  memberId = member.getId();
		discountrecordPl.setMemberId(memberId);
		
		String randomStr = String.valueOf(Math.random()*1000).split("\\.")[0];
		if(randomStr.length()==0){
			randomStr = "000";
		}
		if(randomStr.length()==1){
			randomStr = randomStr+"00";
		}
		if(randomStr.length()==2){
			randomStr = randomStr+"0";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String no = simpleDateFormat.format(new Date())+randomStr;
		discountrecordPl.setNo(no);
		discountrecordPl.setHandleState(0);//默认待处理
		discountrecordPlService.saveModel(discountrecordPl);
		return  StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/discountrecordpl/tiexian2?no="+no); 
	}
	
	/**
	 *  添加完成后页面的选择贴现地址的展示
	 * @author MH
	 * @param request 
	 * @param no 订单号
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/discountrecordpl/tiexian2")
	public String tiexian2(HttpServletRequest request,String no) throws ParseException{
		DiscountrecordPl discountrecordPl = discountrecordPlService.getByOrdernumber(no);
		
		String [] a= discountrecordPl.getType2().split(",");
		Integer guogu=0;
		Integer dashang=0;
		Integer xiaoshang=0;
		Integer sannong=0;
		Integer qita=0;
		for(int i=0;i<a.length;i++){
			if(Integer.valueOf(a[i])==1){
				guogu=1;
			}
			if(Integer.valueOf(a[i])==2){
				dashang=2;
			}
			if(Integer.valueOf(a[i])==3){
				xiaoshang=3;
			}
			if(Integer.valueOf(a[i])==4){
				sannong=4;
			}
			if(Integer.valueOf(a[i])==5){
				qita=5;
			}
		}			
		String firstdate = DateUtil.formart(discountrecordPl.getEndtime(),DateUtil.FORMART3);
		request.setAttribute("discountrecordPl",discountrecordPl);
		request.setAttribute("firstdate",firstdate);
		request.setAttribute("guogu",guogu);
		request.setAttribute("dashang",dashang);
		request.setAttribute("xiaoshang",xiaoshang);
		request.setAttribute("sannong",sannong);
		request.setAttribute("qita",qita);
		
		return "discountrecord/discount_pl2";
	}
	
	/**
	 * 根据企业主键获取详情（含机构报价）
	 * @param id
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountrecordpl/get")
	public String getInfo(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			Map<String,Object> info = discountrecordService.updateReadTaskAndGetInfoAndOrderById(id);
			if(info!=null){
				Object ordernumber = info.get("ordernumber");
				if(ordernumber!=null){//企业订单编号（查询使用的红包）
					HongbaoDetail hb = hongbaoService.getByOrdernumber(ordernumber.toString());
					if(hb!=null)info.put("hb",hb.getPrice());
				}
				Object orgId = info.get("org_id");
				if(orgId!=null){//获取机构相关信息
					Map<String,Object> org = orgService.getInfoById(Integer.valueOf(orgId.toString()));
					if(org!=null){
						info.put("company", org.get("company"));
						Object memberId = org.get("member_id");
						if(memberId!=null){
							Member member = memberService.getById(Integer.valueOf(memberId.toString()));
							info.put("mobile", member.getMobile());
							if(info.get("company")==null)info.put("company", member.getUsername());
							if(info.get("company")==null)info.put("company", member.getMobile());
						}
					}
				}
			}
			result.put("data",info);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 下单保存后保存（贴现地址信息确认订单）
	 * @author MH
	 * @param ordernumber 订单号
	 * @param ycname 用户名
	 * @param ycsex 性别
	 * @param ycphone 联系方式
	 * @param ycaddress 详细地址
	 * @param ycplace 交易城市
	 * @param yclon 经度
	 * @param yclat 纬度
	 * @param model
	 * @since 2016年9月6日 上午9:46:20
	 */
	@RequestMapping("/discountrecordpl/qrdd")
	public String savePayRecordBefore(String ordernumber,String ycname,String ycsex,
		   String ycaddress,String ycplace,String yclon,String yclat ,String ycphone,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DiscountrecordPl discountrecordPl = discountrecordPlService.getByOrdernumber(ordernumber);
			if(discountrecordPl==null)throw new Exception("数据异常");
			
			discountrecordPl.setMemberMobile(ycphone);
			discountrecordPl.setMemberName(ycname);
			discountrecordPl.setMemberSex(Integer.valueOf(ycsex));
			discountrecordPl.setAddress(ycaddress);
			discountrecordPl.setPlace(ycplace);
			discountrecordPl.setLongitude(Float.parseFloat(yclon));
			discountrecordPl.setLatitude(Float.parseFloat(yclat));
			discountrecordPl.setIsValid("T");
			discountrecordPlService.updateModel(discountrecordPl);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "订单失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "discountrecord/discount_pl3";
	}
	
}
