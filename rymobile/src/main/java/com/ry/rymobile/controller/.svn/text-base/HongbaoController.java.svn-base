package com.ry.rymobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.HongbaoAction;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.HongbaoSend;
import com.ry.core.entity.Member;
import com.ry.core.entity.Sitestatistics;
import com.ry.core.form.MemberForm;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.SitestatisticsService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.MD5Util;

import net.sf.json.JSONArray;


@Controller
public class HongbaoController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	SitestatisticsService sitestatisticsService;
	
	@Resource 
	HongbaoService hongbaoService;		
	
	private volatile Object lock = new Object();
	
	@RequestMapping("/hongbao/bupdatemobile.htm")
	public String bupdatemobile(String sid, String phone, String enddate, String price,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
	
		model.addAttribute("price", price);
		model.addAttribute("enddate", enddate);
		model.addAttribute("sid", sid);		
		model.addAttribute("phone",phone);
		return "/hongbao/xgsj";
	}
	
	@RequestMapping("/hongbao/syhb.htm")
	public String syhb(String sid, String phone, String price, String enddate, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		model.addAttribute("sid", sid);
		model.addAttribute("price",price);
		model.addAttribute("phone",phone);
		model.addAttribute("enddate", enddate);
		return "/hongbao/syhb";
	}

	
	@RequestMapping("/hongbao/updatemobile.htm")
	public void updatemobile(String sid, String phone, String oldphone, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html; charset=utf-8");		
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
				
		List<HongbaoDetail> hongbaoDetails = hongbaoService.getHongbaoDetail(Integer.parseInt(sid), oldphone);
		if (hongbaoDetails!= null && hongbaoDetails.size() >0) {
			HongbaoDetail detail = hongbaoDetails.get(0);
			//修改用户行为记录
			Integer hid = detail.getHid();
			HongbaoAction hongbaoAction = hongbaoService.getHongbaoActionList(hid, oldphone, Integer.parseInt(sid)).get(0);
			hongbaoAction.setPhone(phone);
			hongbaoService.updateHongbaoAction(hongbaoAction);
			
			List<Member> members = memberService.getListByMobile(phone);
			Integer memberId = null;
			if (members != null && members.size() >0) {
				//修改红包明细
				memberId = members.get(0).getId();
			} else {
				Member member = new Member();			
				member.setRegtime(new Date());			
				member.setMobile(phone);
				member.setHezuo(String.valueOf(hid));
				member.setQudao("HB");			
				memberService.addMember(member);	
				memberId = member.getId();
			}
			detail.setPhone(phone);
			detail.setMemberid(memberId);
			hongbaoService.updateHongbaoDetail(detail);
			
			map.put("response", "success");
			map.put("msg", "修改成功!");
			out.print(JSONArray.fromObject(map));
									
			return;
			
			
		} else {			
			String hid = (String) request.getSession().getAttribute("ryhdid");
			if (hid != null) {
				HongbaoAction hongbaoAction = hongbaoService.getHongbaoActionList(Integer.parseInt(hid), oldphone, Integer.parseInt(sid)).get(0);
				hongbaoAction.setPhone(phone);
				//修改用户行为记录
				hongbaoService.updateHongbaoAction(hongbaoAction);
				List<Member> members = memberService.getListByMobile(phone);
				if (members != null && members.size() >0) {
					HongbaoDetail hongbaoDetail = new HongbaoDetail();
					hongbaoDetail.setCreatetime(new Date());
					hongbaoDetail.setFlag(0);
					hongbaoDetail.setStatus(0);
					hongbaoDetail.setPhone(phone);
					hongbaoDetail.setMemberid(members.get(0).getId());
					hongbaoDetail.setHid(null);
					hongbaoDetail.setPrice(hongbaoAction.getPrice());
					
					hongbaoService.addHongbaoDetail(hongbaoDetail);
				}
				map.put("response", "success");
				map.put("msg", "修改成功!");
				out.print(JSONArray.fromObject(map));
										
				return;
			}
			
		}
		
		map.put("response", "success");
		map.put("msg", "操作失败");
		out.print(JSONArray.fromObject(map));
	}
	
	@RequestMapping("/add/sitestatistics.htm")
	public void tongji(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {				
			//统计合作网站过来的访问量
			String ip = HttpUtil.getIpAddr(request);
			String url = request.getParameter("url");
			if (!"180.166.201.178".equals(ip)) {
				Sitestatistics sitestatistics = new Sitestatistics();					
				sitestatistics.setHezuo("");							
				sitestatistics.setUrl(url);
				sitestatistics.setInvitedate(new Date());	
				sitestatistics.setIp(ip);			
				sitestatistics.setType("hongbao");
				sitestatisticsService.saveSitestatistics(sitestatistics);
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		} 				
	}	
	
	@RequestMapping("/hongbao/lingqu.htm")
	public String lingqu(HttpServletRequest request, HttpServletResponse response, Model model,Integer sid) throws Exception{		
		String memberid = request.getParameter("memberid");
		String hid = request.getParameter("hid");
		String phone = request.getParameter("phone");
		if (phone == null || "".equals(phone)) {
			return "";
		}
		request.getSession().setAttribute("ryhdid", hid);
		if(sid==null){
			List<HongbaoSend> hongbaoSends = hongbaoService.getList(Integer.parseInt(memberid),Integer.parseInt(hid));
			if (hongbaoSends == null || hongbaoSends.size() <= 0) return null; 		
			HongbaoSend hongbaoSend = hongbaoSends.get(0);	
			sid = hongbaoSend.getId();
		}
		
		Date nowDate = new Date();
		if (hid != null && !"".equals(hid.trim())) {
			HongbaoActivity hongbaoActivity = new HongbaoActivity();
			hongbaoActivity.setId(Integer.parseInt(hid));
			hongbaoActivity.setStatus(0);
			hongbaoActivity = hongbaoService.getActivity(hongbaoActivity);
			
			//活动是否在进行中
			if (hongbaoActivity.getFlag()==1) {	
				// 分享有效期 date
				Date enddate = hongbaoActivity.getEnddate();
				int datenum = DateUtil.daysBetween(nowDate, enddate);
							    
				if (datenum < 0) {
//					map.put("response", "error");
//					map.put("msg", "分享已过期");
//					out.print(JSONArray.fromObject(map));
					return "/hongbao/yjsx"; //已过期
				}
				
				List<Member> members = memberService.getListByMobile(phone);
			//	List<HongbaoDetail> hongbaoDetail1 = hongbaoService.getHongbaoDetail(sid, phone);
				List<HongbaoAction> actionList = hongbaoService.getHongbaoActionList(Integer.parseInt(hid), phone, sid);
				//用户是否抢过
				if (actionList != null && actionList.size() > 0 ) {
					return "/hongbao/yjqg"; //已经抢过
				}
				
				Member member1 = null;
				if (members != null && members.size() > 0) {
					member1 = members.get(0);
				}else{
					Member member = new Member();			
					member.setRegtime(new Date());			
					member.setMobile(phone);
//					member.setPwd(MD5Util.getMD5Str(memberForm.getPwd()));			
					member.setHezuo(hid);
					member.setQudao("HB");			
					memberService.addMember(member);	
					member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
					member1 = member;
				}
				
				synchronized (lock) {					
					int price = 0;
					// 0  固定金额  1 随机金额
					if (hongbaoActivity.getType()==0) {			
						BigInteger count1 = hongbaoService.countHongbaoAction(Integer.parseInt(hid));	
						Integer count2 = hongbaoActivity.getTotalnum();
						if (count1.intValue() >= count2) {
//							map.put("response", "error");
//							map.put("msg", "红包已抢完");
//							out.print(JSONArray.fromObject(map));
							return "/hongbao/yjqw"; //已经抢完
						} 
						//已注册用户抢红包
						if (member1 != null) {
							HongbaoDetail hongbaoDetail = new HongbaoDetail();
							hongbaoDetail.setFlag(0);
							hongbaoDetail.setHid(Integer.parseInt(hid));
							hongbaoDetail.setSid(sid);
							hongbaoDetail.setPhone(phone);
							hongbaoDetail.setCreatetime(new Date());							
							hongbaoDetail.setStatus(0);
							hongbaoDetail.setUpdatetime(new Date());
							hongbaoDetail.setPrice(hongbaoActivity.getPrice());		
							hongbaoDetail.setMemberid(member1.getId());
							hongbaoService.addHongbaoDetail(hongbaoDetail);
						} else {
//							//非注册用户是否抢过红包
//							HongbaoAction action = hongbaoService.getHongbaoActionList(Integer.parseInt(hid), phone, sid).get(0);
//							if (action != null) {
//								return "/hongbao/yjqg"; //已经抢过
//							}
						}
						
						//用户行为记录
						HongbaoAction hongbaoAction = new HongbaoAction();
						hongbaoAction.setCode(Constant.GETHONGBAO);
						hongbaoAction.setCreatetime(new Date());
						hongbaoAction.setPhone(phone);
						hongbaoAction.setHid(Integer.parseInt(hid));
						hongbaoAction.setPrice(hongbaoActivity.getPrice());
						hongbaoAction.setSid(sid);
						hongbaoService.addHongbaoAction(hongbaoAction);
						
						model.addAttribute("sid", sid);
						model.addAttribute("price",hongbaoActivity.getPrice());
						model.addAttribute("phone",phone);
						model.addAttribute("enddate", DateUtil.formart(enddate, DateUtil.FORMART3));
						return "/hongbao/syhb"; //使用红包页面
						
					} else if (hongbaoActivity.getType()==1) {			//随机金额
						//红包剩余金额 
						BigDecimal totalprice = null;
						List<Object[]> objList = hongbaoService.countHongbaoPrice(Integer.parseInt(hid));
						Object[] objs;
						if (objList != null && objList.size() > 0) {
							objs = objList.get(0);																	
							if (objs != null && objs.length >0 && objs[0] != null && objs[1] !=null ) {								
								totalprice = (BigDecimal)objs[0];
								BigInteger totalnum = (BigInteger)objs[1];							
								
								if (totalnum.intValue() >= hongbaoActivity.getTotalnum()) {
//									map.put("response", "error");
//									map.put("msg", "红包已抢完");
//									out.print(JSONArray.fromObject(map));
									return "/hongbao/yjqw"; //已经抢完
								}
								
								if (totalprice.intValue() >= hongbaoActivity.getTotalprice()) {
//									map.put("response", "error");
//									map.put("msg", "红包已抢完");
//									out.print(JSONArray.fromObject(map));
									return "/hongbao/yjqw"; //已经抢完
								} 																						
																							
							}
						}						
						//红包剩余金额 
						if (totalprice != null) {
							int shengyujine = hongbaoActivity.getTotalprice() - totalprice.intValue();
							//判断红包剩余金额 是否大于 随机金额的最大值
							if (shengyujine < hongbaoActivity.getMaxprice()) {
								price = shengyujine;
							} else {
								Random random = new Random();
						        price = random.nextInt(hongbaoActivity.getMaxprice())%(hongbaoActivity.getMaxprice()-hongbaoActivity.getPrice()+1) + hongbaoActivity.getPrice();
							}
						} else {
							Random random = new Random();
					        price = random.nextInt(hongbaoActivity.getMaxprice())%(hongbaoActivity.getMaxprice()-hongbaoActivity.getPrice()+1) + hongbaoActivity.getPrice();
						}
							
						//已注册用户抢红包
						if (member1 != null) {
							HongbaoDetail hongbaoDetail = new HongbaoDetail();
							hongbaoDetail.setFlag(0);
							hongbaoDetail.setHid(Integer.parseInt(hid));
							hongbaoDetail.setSid(sid);
							hongbaoDetail.setPhone(phone);
							hongbaoDetail.setCreatetime(new Date());
							hongbaoDetail.setMemberid(member1.getId());
							hongbaoDetail.setStatus(0);
							hongbaoDetail.setUpdatetime(new Date());
							hongbaoDetail.setPrice(price);							
							hongbaoService.addHongbaoDetail(hongbaoDetail);
						} else if (member1 == null) {
//							HongbaoAction action = hongbaoService.getHongbaoActionList(Integer.parseInt(hid), phone, sid).get(0);
//							if (action != null) {
//								return "/hongbao/yjqg"; //已经抢过
//							}
						}
																		
						//用户行为记录
						HongbaoAction hongbaoAction = new HongbaoAction();
						hongbaoAction.setCode(Constant.GETHONGBAO);
						hongbaoAction.setCreatetime(new Date());
						hongbaoAction.setPhone(phone);
						hongbaoAction.setPrice(price);
						hongbaoAction.setHid(Integer.parseInt(hid));
						hongbaoAction.setSid(sid);
						hongbaoService.addHongbaoAction(hongbaoAction);
						
						model.addAttribute("sid", sid);
						model.addAttribute("price",price);
						model.addAttribute("phone",phone);
						model.addAttribute("enddate", DateUtil.formart(DateUtil.addDays(nowDate, hongbaoActivity.getUsedays()), DateUtil.FORMART3));
						return "/hongbao/syhb"; //使用红包
						
					}
									
				}							
			}
			
			if (hongbaoActivity.getFlag()==0) {				
//				map.put("response", "error");
//				map.put("msg", "活动已结束");
//				out.print(JSONArray.fromObject(map));
				return "/hongbao/yjsx";
			}
			
			if (hongbaoActivity.getFlag()==2) {				
//				map.put("response", "error");
//				map.put("msg", "活动未开始");
//				out.print(JSONArray.fromObject(map));
				return "";
			}
		}			
		return "";
	}
	
	@RequestMapping("/hongbao/register.htm")
	public void register(MemberForm memberForm, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		try{
			List<Member> memberList1 = memberService.getListByMobile(memberForm.getMobile());//@WKX注册前检查是否已经注册过
			if(memberList1!= null && memberList1.size()!=0){
				out.print("registered");
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!memberForm.getCode().equals(sessioncode) || !memberForm.getMobile().equals(sessionmobile)){
				out.print("验证码不正确");
				return;
			}
			Member member = new Member();			
			member.setRegtime(new Date());			
			member.setMobile(memberForm.getMobile());
			member.setPwd(MD5Util.getMD5Str(memberForm.getPwd()));			
			member.setHezuo(memberForm.getHezuo());
			member.setQudao("AC");			
			memberService.addMember(member);			
						
			out.print("success");			
		}catch(Exception e){
			e.printStackTrace();
			out.print("注册失败");		
		}	
		
	}

}
