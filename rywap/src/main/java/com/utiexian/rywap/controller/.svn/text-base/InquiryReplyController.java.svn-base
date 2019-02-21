package com.utiexian.rywap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Invoice;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Org;
import com.ry.core.entity.Region;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.RegionService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 查询查复
 */
@Controller
@RequestMapping("/wap/inquiryreply")
public class InquiryReplyController {
	
	@Resource
	private InquiryReplyService inquiryReplyService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	private TiexianNoticeService tiexianNoticeService;
	
	@Resource
	private OrgInfoService orginfoService;
	
	@Resource
	private RegionService regionService;
	
	/**
	 * 打开查询查复页面
	 */
	@RequestMapping("")
	public String query(){
		return "/tool/inquiryreply";
	}
	
	/**
	 * 跳转到我的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/my/page")
	public String my(){
		return "/my/my";
	}
	
	/**
	 * 列表
	 * @author MH
	 * @param form
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param model
	 */
	@RequestMapping("/list")
	public String list(InquiryReplyForm form,Integer pageNo,Integer pageSize,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(pageSize==null)pageSize = 5;
		try {
			PageResults<Map<String, Object>> page = inquiryReplyService.getPageList(form, pageNo, pageSize);
			map.put("data",page.getResults());
			map.put("response", "success");
			map.put("msg", "获取成功");
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return  "ajax";
	}
	
	/**
	 * 跳转银票查询查复页面
	 * @author MH
	 */
	@RequestMapping("/page")
	public String quiry(HttpServletRequest request,Model model){
		return "/my/inquiryreply_list";
	}
	
	/**
	 * 跳转到查询查复详细页面
	 * @author MH
	 * @param id 订单id
	 * @param model
	 * @return
	 */
	@RequestMapping("/skipdetails")
	public String detailsSkip(String id ,Model model){
		model.addAttribute("id", id);
		return "/my/inquiryreply_details";
	}
	
	/**
	 * 查询重复详情
	 * @author MH
	 * @param id
	 * @param model
	 */
	@RequestMapping("/get")
	public String getInfo(Integer id,Model model) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		InquiryReply inquiryReply = inquiryReplyService.getById(id);
		if(inquiryReply == null || inquiryReply.getNo() == null) throw new Exception("数据异常");
		Map<String,Object> result = inquiryReplyService.getInfoByNo(inquiryReply.getNo());
		if(result!=null){
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			map.put("startDate", fm.format(DateUtil.parser(result.get("start_date").toString(), DateUtil.FORMART3)));
			map.put("endDate", fm.format(DateUtil.parser(result.get("end_date").toString(), DateUtil.FORMART3)));
			map.put("data",result);
			map.put("response", "success");
			map.put("msg", "获取成功");
		}else{
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 初始化 省市区选择框
	 * @param request
	 * @param model
	 */
	@RequestMapping("/initCity")
	public String initCity(HttpServletRequest request,Model model){
	    Integer memberId = 8053;
		Map<String,Object> result = new HashMap<String, Object>();
		Org org = null;
		try{
			org = orgService.getByMemberId(memberId);
		}catch(Exception e){
			e.printStackTrace();
		}
		result.put("free", true);
		
		List<InquiryReply> list = null;
		if(org!=null){
			list = inquiryReplyService.getByOrgId(org.getId());
			if(list==null || list.size()==0){
				list = inquiryReplyService.getByMemberId(memberId);
			}
		}else{
			list = inquiryReplyService.getByMemberId(memberId);
		}
		if(list!=null && list.size()>0){
			result.put("free", false);
		}
		List<Region> p = regionService.getByType("P");//省
		List<Region> c = regionService.getByType("C");//市
		List<Region> d = regionService.getByType("D");//区
		result.put("p", p);
		result.put("c", c);
		result.put("d", d);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存查询查复
	 * @param inquiryReply
	 * @param invoice
	 * @param start
	 * @param end
	 * @param model
	 */
	@RequestMapping("/save")
	public String save(InquiryReply inquiryReply,Invoice invoice,String start,String end,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {//节假日不能提交订单
			Notice notice = tiexianNoticeService.getNoticeByNowTime();//获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
			//如果在节假日定会额度受限,所以提醒非工作日的文字
			if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
				result.put("response", "failed");
				result.put("msg", notice.getAlert());
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}
			
			if(inquiryReply==null)throw new ServiceException("请求失败");
			if(inquiryReply.getOrgId()==null && inquiryReply.getMemberId()==null)throw new ServiceException("请求失败");
			if(StringUtils.isNotBlank(start))inquiryReply.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))inquiryReply.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			
			Map<String,Object> map = orginfoService.getByMemberIdAndType(inquiryReply.getMemberId(), 0);
			if(map!=null&&map.get("org_id")!=null){
				inquiryReply.setOrgId(new Integer(map.get("org_id").toString()));
			}
			//step.0 照顾老版本 没传memberId 在org里面取值
			if(inquiryReply.getMemberId()==null){
				Org org = orgService.getById(inquiryReply.getOrgId());
				if(org!=null)inquiryReply.setMemberId(org.getMemberId());
			}
			
			List<InquiryReply> list = null;
			list = inquiryReplyService.getByMemberId(inquiryReply.getMemberId());
			if (list == null || list.size() == 0) {// 如果传入的是memberId也要验证之前在机构角色下是否查询查复过
				Org org = orgService.getByMemberId(inquiryReply.getMemberId());
				if (org != null && org.getId() != null) {
					list = inquiryReplyService.getByOrgId(inquiryReply.getOrgId());
				}
			}
			if(list!=null && list.size()>0){
				inquiryReply.setPayState(0);//提交订单默认待付款
			}else{
				inquiryReply.setPayState(30);//首单免费
			}
			
			inquiryReply.setCreateTime(new Date());
			inquiryReply.setState(0);
			if(invoice!=null)invoice.setCreateTime(new Date());//不为空才设置时间
			
			String prefix = "QY";
			inquiryReply.setOrgType(0);//pc端的都是默认为企业
			
			inquiryReply.setNo(random(prefix));//获取订单号
			inquiryReplyService.saveInquiryReplyAndInvoice(inquiryReply,invoice);
			result.put("response", "success");
			result.put("msg", "操作成功");
			result.put("no", inquiryReply.getNo());
			result.put("id", inquiryReply.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取订单编号（防止重复）
	 * @param prefix
	 */
	private String random(String prefix){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(new Date());
		for(int i = 0; i < 6; i++){
			str += (int)(Math.random()*10);
		}
		String code = prefix+str;
		List<InquiryReply> list = inquiryReplyService.getByNo(code);
		if(list!=null && list.size()>0)return random(prefix);
		else return code;
	}
	
}