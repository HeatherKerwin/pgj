package com.ry.ryapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.ryapp.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/app/discountrecordpl/")
public class DiscountrecordPlController {
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	DistributeOrderPlService distributeOrderPlService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	MemberService memberService;

	@Resource
	CommentsService commentsService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	/**
	 * 贴现下单
	 * @author WKX
	 * @param discountrecordPl
	 * @param expiry 有效期
	 * @param model
	 * @since 2016年8月15日 下午3:23:16
	 */
	@RequestMapping("tiexian")
	public String tieXian(DiscountrecordPl discountrecordPl,String endDate,Float version,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String msg = "下单成功";
		String eMsg = "操作失败,请稍后再试!";
		try {
			Notice notice = tiexianNoticeService.findByNowTime(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
			//如果在节假日定会额度受限,所以提醒非工作日的文字
			if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
				result.put("response", "failed");
				result.put("msg", notice.getAlert());
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}else{
				notice = tiexianNoticeService.findByCode("ERROR");
			}
			Date endtime = DateUtil.parser(endDate, DateUtil.FORMART3);	
			Date bijiao = DateUtil.addDays(endtime, 1);
			discountrecordPl.setEndtime(endtime);
			discountrecordPl.setNo(getNo());
			if(bijiao.before(new Date()) ){//订单过期
				discountrecordPl.setOrderflag(0);
			}else{
				discountrecordPl.setOrderflag(1);
			}
			discountrecordPl.setSource("APP");//App添加来源
			discountrecordPl.setCreateTime(new Date());
			discountrecordPl.setHandleState(0);//默认待处理
			discountrecordPlService.saveModel(discountrecordPl);
			
			result.put("data", discountrecordPl);
			result.put("response", "success");
			result.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", eMsg);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 生成订单号
	 * @author WKX
	 * @since 2016年8月15日 下午3:11:15
	 */
	private String getNo(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
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
		String orderNumber = simpleDateFormat.format(new Date())+randomStr;
		return orderNumber;
	}
	
	/**
	 * 企业批量订单列表
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @author ZY
	 * @throws ParseException 
	 */
	@RequestMapping("list")
	public String list(DiscountrecordPlForm form,Integer pageIndex,Integer pageSize,Model model) throws ParseException{
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getMemberId()!=null){
			PageResults<Map<String,Object>> page = discountrecordPlService.getPageList(pageIndex, pageSize, form);
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
	 * 根据企业主键获取详情(批量订单)
	 * @author ZY
	 * @param id
	 * @param model
	 * @since 2016年8月19日 上午9:38:29
	 */
	@RequestMapping("get")
	public String getInfo(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			Map<String,Object> info = discountrecordPlService.updateReadTaskAndGetInfoAndOrderById(id);
			if(info!=null){
				Object orgId = info.get("org_id");
				if(orgId!=null){//获取机构相关信息
					Map<String,Object> org = orgService.getInfoById(Integer.valueOf(orgId.toString()));
					if(org!=null){
						info.put("company", org.get("company"));
						info.put("mobile", org.get("phone"));
						Object memberId = org.get("member_id");
						if(memberId!=null){
							Member member = memberService.getById(Integer.valueOf(memberId.toString()));
							if(info.get("mobile")==null)info.put("mobile", member.getMobile());
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
	 * 显示接单机构的详情（评分等）
	 * @author ZY
	 * @param id
	 * @param model
	 * @since 2016年8月18日 下午3:40:32
	 */
	@RequestMapping("get/org")
	public String getOrgInfoBy(Integer id,Integer pageIndex,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Integer pageSize=null;
		try {
			if(id==null)throw new Exception("数据异常");
			DistributeOrderPl dist = distributeOrderPlService.getById(id);
			if(dist!=null){
				Map<String,Object> orgInfo = orgInfoService.getByOrgId(dist.getOrgId(), 1);
				result.put("company", orgInfo.get("company"));
				result.put("phone", orgInfo.get("phone"));
				getComment(result, dist.getOrgId());
			}
			//所有评论
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 5;
			if(id !=null && dist.getOrgId() !=null){
				PageResults<Map<String,Object>> page = discountrecordPlService.getPageList(pageIndex, pageSize, dist.getOrgId());
				result.put("data",page.getResults());
			}
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 汇总机构评价（评分）
	 * @author WKX
	 * @param org
	 * @param orgId
	 * @throws Exception
	 * @since 2016年8月18日 下午7:06:55
	 */
	private Map<String,Object> getComment(Map<String,Object> org,Integer orgId) throws Exception{
		Double door = 0D;//上门次数
		Double price = 0D;//价格
		Double service = 0D;//服务
		Double speed = 0D;//速度
		Double amount = 0D;//总评论数
		for(int i=0;i<3;i++){
			Map<String,Object> count = commentsService.getCountByOrgId(i, orgId);
			if(count!=null){
				Object d = count.get("door");
				Object p = count.get("price");
				Object se = count.get("service");
				Object sp = count.get("speed");
				Object a = count.get("amount");
				if(d!=null)door+=Double.valueOf(d.toString());
				if(p!=null)price+=Double.valueOf(p.toString());
				if(se!=null)service+=Double.valueOf(se.toString());
				if(sp!=null)speed+=Double.valueOf(sp.toString());
				if(a!=null)amount+=Double.valueOf(a.toString());
			}
		}
		if(amount!=0){
			door = door/amount;
			price = price/amount;
			service = service/amount;
			speed = speed/amount;
		}
		org.put("door", door!=0?door:"--");
		org.put("price", price!=0?price:"--");
		org.put("service", service!=0?service:"--");
		org.put("speed", speed!=0?speed:"--");
		return org;
	}
	
	/**
	 * 取消订单
	 * @author ZY
	 * @param id 贴现主键
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param version 版本
	 * @param model
	 * @since 2016年9月7日 下午8:13:21
	 */
	@RequestMapping("cancel")
	public String cancel(Integer id,Integer cancel,String reason,Integer currentState,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		try {
			DiscountrecordPl disc = discountrecordPlService.getById(id);
			if(disc!=null && disc.getOrderflag()!=currentState){
				msg = "数据已过期";
				throw new Exception(msg);
			}
			
			if(reason!=null) disc.setCancelCause(reason);
			disc.setCancel(cancel);
			disc.setOrderflag(-2);//无效订单
			discountrecordPlService.updateToInvalid(disc);
			
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
	 * 确定收到款项 订单完成
	 * @param id 贴现主键
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("update/finish")
	public String finish(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DiscountrecordPl disc = discountrecordPlService.getById(id);
			if(disc==null)throw new Exception("数据异常");
			
			disc.setOrderflag(3);//已完成
			discountrecordPlService.updateToFinish(disc);
			
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