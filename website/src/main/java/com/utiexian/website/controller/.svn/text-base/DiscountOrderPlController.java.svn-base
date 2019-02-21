package com.utiexian.website.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.ry.core.entity.Comments;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.Member;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 批量订单列表
 * @author MH
 *
 */
@Controller
public class DiscountOrderPlController {
	
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
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;

	/**
	 * 企业批量订单列表
	 * @author MH
	 * @param form
	 * @param xqid 详情页面的订单号
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param start1 时间1
	 * @param end1 时间2
	 * @param model 
	 * @param wuxiao1 无效0
	 * @param wuxiao2 无效-2
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("discountorderpl/list")
	public String list(DiscountrecordPlForm form,String xqid,Integer pageIndex,Integer pageSize,HttpServletRequest request,String start1,String end1,Model model,Integer wuxiao1,Integer wuxiao2) throws NumberFormatException, Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		form.setMemberId(memberId);
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(wuxiao1!=null && wuxiao2!= null){
			Integer [] a = {wuxiao1,wuxiao2} ;
			form.setState(a);
		}
		
		if(start1!=null){
			Date start =DateUtil.parser(start1, DateUtil.FORMART3);
			form.setCreateTime(start);
		}
		if(end1!=null){
			Date end =DateUtil.parser(end1, DateUtil.FORMART3);
			form.setEndtime(end);
		}
		if(xqid!=null){
			form.setNo(xqid);
		}
		if(form!=null && form.getMemberId()!=null){
			PageResults<Map<String,Object>> page = discountrecordPlService.getPcPageList(pageIndex, pageSize, form);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(page!=null && page.getResults()!=null){
				Map<String,Object> temp = null;
				DecimalFormat df = new DecimalFormat(".00");
				for (Map<String, Object>  map : page.getResults()) {
					temp = new HashMap<String, Object>();
					temp = map;
					Map<String, Object>  maps = null;
					if(map.get("org_id")!=null && map.get("org_id")!=""){
						maps = orgService.getComment(map, Integer.valueOf(map.get("org_id").toString()));
					}
					
					if(maps!=null){
						if(maps.get("_speed")!=null && maps.get("_speed")!="" && maps.get("_speed")!="--"){
							Double pjspeed =(Double) maps.get("_speed")*2;
							temp.put("pjspeed", df.format(pjspeed));
						}else{
							temp.put("pjspeed","--");
						}
						if(maps.get("_price")!=null && maps.get("_price")!="" && maps.get("_price")!="--"){
							Double pjprice = (Double)maps.get("_price")*2;
							temp.put("pjprice", df.format(pjprice));
						}else{
							temp.put("pjprice","--");
						}
						if(maps.get("_service")!=null && maps.get("_service")!="" && maps.get("_service")!="--"){
							Double pjservice = (Double)maps.get("_service")*2;
							temp.put("pjservice", df.format(pjservice));
						}else{
							temp.put("pjservice","--");
						}
						if(maps.get("_door")!=null && maps.get("_door")!="" && maps.get("_door")!="--"){
							Double pjdoor = (Double)maps.get("_door")*100;
							temp.put("pjdoor", df.format(pjdoor)+"%");
						}else{
							temp.put("pjdoor","--");
						}
					}
					list.add(temp);
				}
			}
			
			page.setResults(list);
			
			result.put("data",page);
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
	 *  根据条件跳转到详情页面
	 * @param no  订单号
	 * @return
	 * @author MH
	 * @throws Exception
	 */
	@RequestMapping("discountorderpl/optpage")
	public String optPage(String no,HttpServletRequest request) throws Exception{
		if( StringUtils.isBlank(no)) throw new Exception();
		request.setAttribute("id", no);
		DiscountrecordPl discountrecordPl = discountrecordPlService.getByOrdernumber(no);
		List<Comments> comment = commentsService.getByDcrdId(discountrecordPl.getId(),2);
		if(discountrecordPl.getOrderflag() == 0 || -1 == discountrecordPl.getOrderflag() || -2 == discountrecordPl.getOrderflag() ){
			return "discountorder/discountorder_pl6";
		}
		if(discountrecordPl.getOrderflag() == 1){
			return "discountorder/discountorder_pl2";
		}
		if(discountrecordPl.getOrderflag() == 2){
			return "discountorder/discountorder_pl3";
		}
		if(discountrecordPl.getOrderflag() == 3  && comment.size() == 0){
			return "discountorder/discountorder_pl4";
		}
		if(discountrecordPl.getOrderflag() == 3   && comment.size() != 0){
			return "discountorder/discountorder_pl5";
		}
		return null;
	}
	
	/**
	 * 取消订单
	 * @author MH
	 * @param id 贴现的订单号
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param model
	 * @since 
	 */
	@RequestMapping("discountorderpl/cancel")
	public String cancel(String id,Integer cancel,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		try {
			DiscountrecordPl disc = discountrecordPlService.getByOrdernumber(id);
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
	 * @param id 贴现的订单号
	 * @param model
	 * @author MH
	 */
	@RequestMapping("discountorderpl/update/finish")
	public String finish(String id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DiscountrecordPl disc = discountrecordPlService.getByOrdernumber(id);
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
	
	/**
	 * 根据企业主键获取详情(批量订单)
	 * @author MH
	 * @param id 贴现的订单号
	 * @param model
	 * @since 2016年8月19日 上午9:38:29
	 */
	@RequestMapping("discountorderpl/get")
	public String getInfo(String id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			DiscountrecordPl disc = discountrecordPlService.getByOrdernumber(id);
			Map<String,Object> info = discountrecordPlService.updateReadTaskAndGetInfoAndOrderById(disc.getId());
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
	 * 评价（保存）
	 * @param comments
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountorderpl/comments/save")
	public String saveComment(Comments comments,Model model,String no,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(no==null)throw new Exception();
			DiscountrecordPl disc = discountrecordPlService.getByOrdernumber(no);
			comments.setDcrdId(disc.getId());
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			comments.setOperatorId(memberId);
			if(comments==null || comments.getDcrdId()==null || comments.getType()==null)throw new Exception();
			List<Comments> list = commentsService.getByDcrdId(comments.getDcrdId(),comments.getType());
			if(list==null || list.size()==0){
				Map<String,Object> map = null;
				if(comments.getType()==null || comments.getType()==0){
					map = discountrecordService.getInfoAndOrderById(comments.getDcrdId());
				}else if(comments.getType()==1){
					map = discountrecordSpService.getInfoAndOrderById(comments.getDcrdId());
				}else if(comments.getType()==2){
					map = discountrecordPlService.getInfoAndOrderById(comments.getDcrdId());
				}
				if(map!=null && map.get("dtboId")!=null){
					comments.setDtboId(Integer.valueOf(map.get("dtboId").toString()));
				}
				comments.setCreateTime(new Date());
				commentsService.saveModel(comments);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
