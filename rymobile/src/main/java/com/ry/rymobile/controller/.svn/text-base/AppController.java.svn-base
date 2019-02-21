package com.ry.rymobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Award;
import com.ry.core.entity.City;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.HongbaoAction;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.Provice;
import com.ry.core.form.MemberForm;
import com.ry.core.service.AwardService;
import com.ry.core.service.CityService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PriceService;
import com.ry.core.service.ProviceService;
import com.ry.core.util.Constant;
import com.ry.core.util.Utility;
import com.ry.util.Base64Util;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.PropertiesUtil;

@Controller
@RequestMapping("/app/")
public class AppController {
	
	private Logger log = Logger.getLogger(AppController.class);
	
	@Resource
	MemberService memberService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	AwardService awardService;
	
	@Resource
	ProviceService proviceService;
	
	@Resource
	CityService cityService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	OrgService orgService;
	
	@RequestMapping("index")
	public String openIndex(){
		return "index";
	}
	
	/**
	 * 注册页面
	 * @author WKX
	 * @throws IOException
	 * @since 2016年1月18日 下午3:36:46
	 */
	@RequestMapping("register.htm")
	public String register(HttpServletResponse response, HttpServletRequest request,String code,Model model) throws IOException{
		//@2016-05-27 暂把页面 换成新的页面（老的是注释掉的部分）
		model.addAttribute("invitationCode",code);
		Member m = memberService.getByMyInvitationCode(code);
		if(m!=null)model.addAttribute("memberId",m.getId());
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "UTIEXIAN";
		try {
			String token = Base64Util.encryptBASE64(date.getBytes()).trim();
			HttpUtil.addCookie(response, "registerToken", token, 1800);		//cookie保存30分钟
			request.getSession().setAttribute("registerToken", token);
		} catch (Exception e) {
			log.error("跳转至分享注册页面操作失败",  e);
		}
		return "shareRegister/shareRegister";
//		model.addAttribute("invitationCode",code);
//		Member m = memberService.getByMyInvitationCode(code);
//		model.addAttribute("memberId",m.getId());
//		return "register/register";
	}
	
	/**
	 * 注册领红包都成功
	 * @author WKX
	 * @throws IOException
	 * @since 2016年1月18日 下午3:27:17
	 */
	@RequestMapping("success.htm")
	public String success() throws IOException{
		return "register/success";
	}
	
	/**
	 * 注册成功（但活动已过期）
	 * @author WKX
	 * @throws IOException
	 * @since 2016年1月18日 下午3:36:25
	 */
	@RequestMapping("over.htm")
	public String over() throws IOException{
		return "register/over";
	}
	
	/**
	 * 已经注册过
	 * @author WKX
	 * @throws IOException
	 * @since 2016年1月18日 下午3:37:25
	 */
	@RequestMapping("registered.htm")
	public String registered(String code,Model model) throws IOException{
		model.addAttribute("invitationCode",code);
		Member m = memberService.getByMyInvitationCode(code);
		model.addAttribute("memberId",m.getId());
		return "register/registered";
	}
	
	/**
	 * 注册领取红包（动作）[活动期间内且金额充足,则推荐人和注册人各发放红包]
	 * @author WKX
	 * @param memberForm
	 * @param request
	 * @param model
	 * @since 2016年1月19日 下午1:22:31
	 */
	@RequestMapping("registerToDo.htm")
	public String register(MemberForm memberForm,HttpServletRequest request,Model model){
		String url = "register/callback";
		String invitationCode = memberForm.getInvitationCode();
		String mobile = memberForm.getMobile();
		
		List<Member> list = memberService.getListByMobile(mobile);
		if(list!= null && list.size()>0){
			Member temp = list.iterator().next();
			model.addAttribute("result","registered");
			model.addAttribute("invitationCode",temp.getMyInvitationCode());
			return url;
		}
		String sessioncode = (String)request.getSession().getAttribute("code");
		String sessionmobile = (String)request.getSession().getAttribute("mobile");
		if(!memberForm.getCode().equals(sessioncode) || !mobile.equals(sessionmobile)){
			model.addAttribute("result","error");
			model.addAttribute("invitationCode",invitationCode);
			model.addAttribute("msg","验证码不正确！");
			return url;
		}
		try {
			Member member = new Member();
			member.setRegtime(new Date());
			member.setMobile(mobile);
			member.setInvitationCode(memberForm.getInvitationCode());
			memberService.addMember(member);
			member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
			//获取红包分类主键（已贴现完成）
			String tagId = PropertiesUtil.getConfigPropertiesValue("NEWMEMBERTAGID","102");
			
			HongbaoActivity activity = hongbaoService.getActivityByTagId(Integer.valueOf(tagId));
			if(activity!=null){//在红包有效期内则分发红包[保存红包明细]
				model.addAttribute("result","success");
				Member share = memberService.getByMyInvitationCode(invitationCode);
				if(share==null){
					model.addAttribute("result","error");
					model.addAttribute("invitationCode",memberForm.getInvitationCode());
					model.addAttribute("msg","邀请码不正确！");
				}else{//推荐码正确[发红包]
					Integer[] memberIds = {member.getId(),share.getId()};
					String[] mobiles = {member.getMobile(),share.getMobile()};
					Date date = new Date();
					Integer price = getPriceByHb(activity);//根据发放类型取金额
					for(int i=0;i<memberIds.length;i++){
						Integer mId = memberIds[i];
						String mob = mobiles[i];
						if(mId!=null && mId>0){
							HongbaoDetail hongbaoDetail = new HongbaoDetail();
							hongbaoDetail.setFlag(0);
							hongbaoDetail.setStatus(0);
							hongbaoDetail.setHid(activity.getId());
							hongbaoDetail.setPhone(mob);
							hongbaoDetail.setMemberid(mId);
							hongbaoDetail.setPrice(price);							
							hongbaoDetail.setCreatetime(date);
							hongbaoDetail.setUpdatetime(date);
							hongbaoService.addHongbaoDetail(hongbaoDetail);
							//用户行为记录
							HongbaoAction hongbaoAction = new HongbaoAction();
							hongbaoAction.setCode(Constant.SENDHONGBAO);//系统活动派送
							hongbaoAction.setCreatetime(date);
							hongbaoAction.setPhone(mob);
							hongbaoAction.setHid(activity.getId());
							hongbaoAction.setPrice(price);
							hongbaoService.addHongbaoAction(hongbaoAction);
						}
					}
				}
			}else{
				model.addAttribute("result","over");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result","error");
			model.addAttribute("invitationCode",memberForm.getInvitationCode());
		}
		return url;
	}
	
	/**
	 * 发送验证码
	 * @author WKX
	 * @param mobile
	 * @param request
	 * @param response
	 * @throws Exception
	 * @since 2016年1月21日 上午9:26:42
	 */
	@RequestMapping("sendcode.htm")
	public void sendCode(String mobile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		try{
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
			
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("mobile", mobile);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("error");
		}
	}
	
	/**
	 * 转盘活动、发送验证码
	 * @author WKX
	 * @param mobile 手机号码
	 * @param token 标识（线下转盘活动设置标识：老用户提示下载APP参与抽奖）
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年4月8日 下午5:04:05
	 */
	@RequestMapping("sendcodezp.htm")
	public String sendCodeForZp(String mobile, HttpServletRequest request,Model model) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = "发送失败，请稍后...";
		try{
			List<Member> list = memberService.getListByMobile(mobile);
			if(list!=null && list.size()>0){
				Member m = list.get(0);
				List<Award> award_list = awardService.getByMemberId(m.getId());
				if(award_list!=null && award_list.size()>0){
					msg = "该号码已参与过本次活动";
					throw new Exception();
				}
			}
			
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
			
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("mobile", mobile);
			
			result.put("response", "success");
			result.put("msg", "获取成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", msg);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据发放类型取金额
	 * @author WKX
	 * @param hb
	 * @since 2016年1月29日 上午10:52:39
	 */
	private Integer getPriceByHb(HongbaoActivity hb){
		Integer result = hb.getPrice();//固定金额（直接取值并返回）
		if(hb.getType()==1){//随机金额
			Integer max = hb.getMaxprice();
			Integer min = hb.getPrice();
			Random random = new Random();
	        if(max!=null && min!=null){
	        	result = random.nextInt(max)%(max-min+1) + min;
	        }
		}
		return result;
	}
	
	/**
	 * 快速登录（参加转盘活动）
	 * @author WKX
	 * @param mobile 手机号码
	 * @param code 验证码
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年2月22日 下午4:57:23
	 */
	@RequestMapping("quicklogin.htm")
	public String quickLogin(String mobile,String code,String hezuo,HttpServletRequest request,Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sessioncode = (String) request.getSession().getAttribute("code");
			if (!code.equals(sessioncode)) {
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
			}else{
				List<Member> list = memberService.getListByMobile(mobile);
				if(list!=null && list.size()>0){//查询用户
					Member temp = list.get(0);
					map.put("data", temp.getId());
					map.put("isOld", true);//老用户（线下，直接跳转到老用户下载app参与）
					request.getSession().setAttribute("member",temp);
				}else{//不存在该用户则新增
					Member member = new Member();
					member.setMobile(mobile);
					member.setRegtime(new Date());
					member.setQudao("AC");//来源转盘活动
					member.setHezuo(hezuo);//合作就写转盘活动的主键
					memberService.addMember(member);
					member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
					request.getSession().setAttribute("member", member);
					map.put("data", member.getId());
				}
				map.put("response", "success");
				map.put("msg", "登录成功");
			}
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "系统繁忙请稍后");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 查看该用户的中奖权限（是否可参与抽奖）
	 * @author WKX
	 * @param memberId
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年2月23日 上午9:48:16
	 */
	@RequestMapping("lotteryAuth.htm")
	public String lotteryAuth(Integer memberId,HttpServletRequest request,Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if(memberId==null){
			map.put("response", "failed");
		}else{
			List<Award> list = awardService.getByMemberId(memberId);
			if(list!=null && list.size()>0){
				map.put("response", "failed");
			}else{
				map.put("response", "success");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 保存中奖信息
	 * @author WKX
	 * @param award
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年2月23日 下午3:13:28
	 */
	@RequestMapping("save.htm")
	public String save(Award award,String mId,HttpServletRequest request,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(mId) && award!=null && award.getGenre()!=null){
			Integer memberId = Integer.valueOf(mId);
			List<Award> list = awardService.getByMemberId(memberId);
			if(list==null || list.size()==0){//防止重复提交
				Date date = new Date();
				award.setMemberId(memberId);
				award.setCreateTime(date);//创建时间
				if(3==award.getGenre()){//线下
					award.setRemarks("此为线下参与记录");
				}
				awardService.save(award);
				map.put("response", "success");
				map.put("msg", "感谢您的参与");
			}else{
				map.put("response", "failed");
				map.put("msg", "您已经参与过该活动了");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 获取省市
	 * @param model
	 * @throws IOException
	 * @author WKX
	 */
	@RequestMapping("provicecity.htm")
	public String proviceCity(Model model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<Provice> provices = proviceService.getList();
			List<City> citys = cityService.getList();
			Map<Integer,Provice> pmap = new HashMap<Integer,Provice>();
			for(Provice baseEntity : provices){
				pmap.put(baseEntity.getId(), baseEntity);
			}
			List<City> cityList = new ArrayList<City>();
			for(City baseEntity : citys){
				City city = baseEntity;
				Provice provice = pmap.get(city.getPid());
				city.setPname(provice.getName());
				cityList.add(city);
			}
			map.put("response", "success");
			map.put("data", new Object[]{provices,cityList});
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 机构报价分享展示页
	 * @author WKX
	 * @throws IOException
	 * @since 2016年6月14日 上午9:36:17
	 */
	@RequestMapping("offer.htm")
	public String offer(Integer orgId,Integer cityId,Float version,String day,Model model) throws IOException{
		if(orgId!=null){
			Map<String,Object> info = orgInfoService.getByOrgId(orgId, 1);//机构
			if(info!=null)model.addAttribute("orgName", info.get("company"));
			if(version!=null && version>=2.3F){
			List<Map<String,Object>> list=priceService.getCityIdByOrgIdAndDate(orgId, day);
			if(list!=null && list.size()>0)model.addAttribute("cityId",list.get(0).get("city_id").toString() );		
		    }
		}
		if(cityId != null)model.addAttribute("cityId", cityId);
		model.addAttribute("orgId", orgId);
		model.addAttribute("day", day);
		return "offer/offer";
	}
	
	/**
	 * 根据机构城市和日期 获取那天的 报价（A全部报价显示纸票大票、电票大票；B纸票小票50万以下179天以上优先）
	 * @author WKX
	 * @param orgId
	 * @param cityId
	 * @param day
	 * @throws IOException
	 * @since 2016年6月14日 下午1:18:58
	 */
	@RequestMapping("offer/get.htm")
	public String getOffer(Integer orgId,Integer cityId,String day,Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<Map<String,Object>> list = priceService.getPriceAndTypeByOrgId(orgId, day, cityId,null);
			result.put("response", "success");
			result.put("data", list);
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取最合适的报价
	 * @author WKX
	 * @param result
	 * @param map
	 * @param flag
	 * @since 2016年6月14日 下午3:52:37
	 */
	@SuppressWarnings("unused")
	private Map<String,Object> getSuitable(Map<String,Object> result,Map<String,Object> map,Integer flag,String title){
		if(result!=null && result.get("flag")!=null){
			Integer temp = Integer.valueOf(result.get("flag").toString());
			if(temp>flag){
				result = map;
				result.put("flag",flag);
				result.put("title",title);
			}
		}
		return result;
	}
	
	/**
	 * 报价分享页面注册
	 * @author WKX
	 * @param mobile 手机号
	 * @param code 验证码
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年6月14日 下午5:36:22
	 */
	@RequestMapping("offer/register.htm")
	public String register(String mobile,String code, HttpServletRequest request,Model model) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<Member> memberList1 = memberService.getListByMobile(mobile);	
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile))throw new Exception("验证码不正确");
			if(memberList1==null || memberList1.size()==0){
				Member member = new Member();
				member.setRegtime(new Date());
				member.setMobile(mobile);
				
				member.setHezuo("price");//报价
				member.setQudao("WX");//微信
				memberService.addMember(member);
				member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
				result.put("data", member);
			}
			result.put("response", "success");
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", e.getMessage());
		}	
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询查复回调页面
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("inquiryReply/pay/cb/page")
	public String cbPayPage(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		model.addAttribute("merchantAcctId",merchantAcctId);
		model.addAttribute("bankId",bankId);
		model.addAttribute("orderId",orderId);
		if(StringUtils.isNotBlank(orderTime)){
			Date src = DateUtil.parser(orderTime, "yyyyMMddHHmmss");
			model.addAttribute("orderTime",DateUtil.formart(src, "yyyy/MM/dd"));
		}
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("bindCard",bindCard);
		model.addAttribute("bindMobile",bindMobile);
		model.addAttribute("dealId",dealId); 
		model.addAttribute("bankDealId",bankDealId);
		model.addAttribute("fee",fee);
		model.addAttribute("payResult",payResult);
		model.addAttribute("productName","查询查复（服务费）");
		model.addAttribute("payer",this.getPayer(orderId,0));
		model.addAttribute("way", "快钱支付");//支付方式
		return "bill99/org_orderpay_success";
	}
	
	/**
	 * 银票贴现回调页面（企业）
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("discountrecord/pay/cb/page")
	public String cbPayPageDisc(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		model.addAttribute("merchantAcctId",merchantAcctId);
		model.addAttribute("bankId",bankId);
		model.addAttribute("orderId",orderId);
		if(StringUtils.isNotBlank(orderTime)){
			Date src = DateUtil.parser(orderTime, "yyyyMMddHHmmss");
			model.addAttribute("orderTime",DateUtil.formart(src, "yyyy/MM/dd"));
		}
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("bindCard",bindCard);
		model.addAttribute("bindMobile",bindMobile);
		model.addAttribute("dealId",dealId);
		model.addAttribute("bankDealId",bankDealId);
		model.addAttribute("fee",fee);
		model.addAttribute("payResult",payResult);
		model.addAttribute("productName","贴现保证金");
		model.addAttribute("payer",this.getPayer(orderId,1));
		model.addAttribute("way", "快钱支付");//支付方式
		return "bill99/bns_discount_pay_success";
	}
	
	/**
	 * 银票接单回调页面（机构）
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("distributeOrder/pay/cb/page")
	public String cbPayPageDist(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		model.addAttribute("merchantAcctId",merchantAcctId);
		model.addAttribute("bankId",bankId);
		model.addAttribute("orderId",orderId);
		if(StringUtils.isNotBlank(orderTime)){
			Date src = DateUtil.parser(orderTime, "yyyyMMddHHmmss");
			model.addAttribute("orderTime",DateUtil.formart(src, "yyyy/MM/dd"));
		}
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("bindCard",bindCard);
		model.addAttribute("bindMobile",bindMobile);
		model.addAttribute("dealId",dealId);
		model.addAttribute("bankDealId",bankDealId);
		model.addAttribute("fee",fee);
		model.addAttribute("payResult",payResult);
		model.addAttribute("productName","接单保证金");
		model.addAttribute("payer",this.getPayer(orderId,2));
		model.addAttribute("way", "快钱支付");//支付方式
		return "bill99/org_receviepay_success";
	}
	
	/**
	 * 获取付款方（公司名称优先，没有则显示手机号）
	 * @author WKX
	 * @param orderId 商户订单号
	 * @param type 类型（0查询查复、1贴现、2接单）
	 * @throws Exception 
	 * @since 2017年3月14日 上午10:22:48
	 */
	private String getPayer(String orderId,Integer type) throws Exception{
		String payer = "";
		Integer memberId = null;
		Map<String,Object> io = new HashMap<String, Object>();
		if(type==0){//查询查复
			List<InquiryReply> list = inquiryReplyService.getByNo(orderId);
			if(list!=null && list.size()>0){
				InquiryReply ir = list.get(0);
				if(ir.getMemberId()!=null && ir.getOrgType()!=null){
					io = orgInfoService.getByMemberIdAndType(ir.getMemberId(), ir.getOrgType());
					memberId = ir.getMemberId();
				}
			}
		}else if(type==1){//贴现
			Discountrecord disc = discountrecordService.getByBnsNoDiscount(orderId);
			if(disc!=null){
				io = orgInfoService.getByMemberIdAndType(disc.getMemberid(), 0);
				memberId = disc.getMemberid();
			}
		}else if(type==2){//接单
			DistributeOrder dist = distributeOrderService.getByBnsno(orderId);
			if(dist!=null){
				io = orgInfoService.getByOrgIdAndType(dist.getOrgId(), 1);
				if(io!=null && io.get("member_id")!=null){
					memberId = Integer.valueOf(io.get("member_id").toString());
				}else{
					Org o = orgService.getById(dist.getOrgId());
					memberId = o.getMemberId();
				}
			}
		}
		if(io!=null && io.get("company")!=null){
			payer = io.get("company").toString();
		}else if(memberId!=null){
			Member member = memberService.getById(memberId);
			if(member!=null){
				payer = Utility.decodeMobile(member.getMobile());
			}
		}
		return payer;
	}
	
	/**
	 * 查询查复
	 * @author WKX
	 * @param orderNo 订单号
	 * @param orderAmount 金额（元）
	 */
	@RequestMapping("baofoo/inquiryReply")
	public String cbPayPage(String orderNo,String orderAmount,Model model) throws Exception{
		model.addAttribute("orderId",orderNo);
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("payer",this.getPayer(orderNo,0));
		model.addAttribute("way", "宝付支付");//支付方式
		return "bill99/org_orderpay_success";
	}
	
	/**
	 * 银票贴现
	 * @author WKX
	 * @param orderNo 订单号
	 * @param orderAmount 金额（元）
	 */
	@RequestMapping("baofoo/discountrecord")
	public String cbPayPageDisc(String orderNo,String orderAmount,Model model) throws Exception{
		model.addAttribute("orderId",orderNo);
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("payer",this.getPayer(orderNo,1));
		model.addAttribute("way", "宝付支付");//支付方式
		return "bill99/bns_discount_pay_success";
	}
	
	/**
	 * 银票接单
	 * @author WKX
	 * @param orderNo 订单号
	 * @param orderAmount 金额（元）
	 */
	@RequestMapping("baofoo/distributeOrder")
	public String cbPayPageDist(String orderNo,String orderAmount,Model model) throws Exception{
		model.addAttribute("orderId",orderNo);
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("payer",this.getPayer(orderNo,2));
		model.addAttribute("way", "宝付支付");//支付方式
		return "bill99/org_receviepay_success";
	}
}