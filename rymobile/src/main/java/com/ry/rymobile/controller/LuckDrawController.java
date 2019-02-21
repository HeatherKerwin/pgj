package com.ry.rymobile.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.Enum.CouponSrc;
import com.ry.core.Enum.CouponState;
import com.ry.core.Enum.CouponType;
import com.ry.core.entity.Coupon;
import com.ry.core.entity.LuckDraw;
import com.ry.core.entity.Member;
import com.ry.core.service.CouponService;
import com.ry.core.service.LuckDrawService;
import com.ry.core.service.MemberService;
import com.ry.util.DateUtil;

@Controller
@RequestMapping("/luckdraw")
public class LuckDrawController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	CouponService couponService;
	
	@Resource
	LuckDrawService luckDrawService;
	
	/**
	 * 用户兑换奖品
	 * @param prizes 奖品（多个 1：红包2：抱枕3：充电宝）
	 * @param mobile 手机号
	 * @param code 验证码
	 * @param name 姓名
	 * @param address 收货地址
	 * @throws Exception 
	 */
	@RequestMapping("/prize")
	public void LuckDraw(Integer[] prizes,String mobile ,String code,String name,String address,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		PrintWriter out = response.getWriter(); 
		try {
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("phone");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				out.print("手机验证码不正确！");
				return;
			}
			List<Member> list = memberService.getListByMobile(mobile);
			Integer memberId = null;
			if(list!= null && list.size()>0){
				Member temp = list.iterator().next();
				memberId = temp.getId();
			}else{
				Member member = new Member();
				member.setRegtime(new Date());
				member.setMobile(mobile);
				member.setUsername(name);
				memberService.addMember(member);
				memberId = member.getId();
			}
			
			//创建红包
			Coupon coupon = new Coupon();
			coupon.setCreateTime(new Date());
			coupon.setMoney(20F);
			coupon.setStartDate(new Date());
			coupon.setEndDate(DateUtil.addDays(new Date(),360));
			coupon.setCouponType(CouponType.GENERAL);
			coupon.setCouponSrc(CouponSrc.ACTIVITY);
			coupon.setCouponState(CouponState.UNUSED);
			coupon.setMemberId(memberId);
			
			LuckDraw luckDraw = new LuckDraw();
			luckDraw.setAddress(address);
			luckDraw.setMemberId(memberId);
			luckDraw.setName(name);
			luckDraw.setMobile(mobile);
			luckDraw.setCreateTime(new Date());
			if(prizes != null){
				for (Integer s : prizes) {
					/**保存中奖记录 */
					/**发送红包 */
					if(s == 1){//发送红包
						couponService.saveModel(coupon);
						luckDraw.setType(0);
						luckDraw.setGoods("红包");
					}else if(s == 2){
						luckDraw.setType(1);
						luckDraw.setGoods("靠枕");
					}else if(s == 3){
						luckDraw.setType(1);
						luckDraw.setGoods("充电宝");
					}
					luckDrawService.saveModel(luckDraw);
				}
				out.print("success"); 
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("发送失败");
		}
	}
}