package com.utiexian.website.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.service.CommentsService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 评论
 * @author ZY
 */
@Controller
@RequestMapping("/comments/")
public class CommentsController {
	
	@Resource
	OrgService orgService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	CommentsService commentsService;
	
	/**
	 * 评论跳转
	 * @author ZY
	 * @since 2016年11月4日 上午10:09:36
	 */
	@RequestMapping("comments")
	public String tiaozhuan(){
		return "comments/comments";
	}
	
	/**
	 * 统计机构端评分
	 * @author ZY
	 * @param memberId
	 * @param model
	 * @since 2016年8月25日 上午10:31:59
	 */
	@RequestMapping("count")
	public String getCount(Integer memberId,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map=orgInfoService.getByMemberIdAndType(memberId, 1);
		if(map!=null && map.get("org_id")!=null){
			try {
				Integer orgId=Integer.valueOf(map.get("org_id").toString());
				if(orgId==null)throw new Exception("数据异常");
				Map<String, Object> counts = commentsService.getAllByOrgId(orgId);
				if(counts!=null){
					result.put("data1", counts);
					result.put("response", "success");
					result.put("msg", "获取成功");
				}else{
					result.put("response", "failed");
					result.put("msg", "暂无评价");
				}
			} catch (Exception e) {
				result.put("response", "failed");
				result.put("msg", "获取失败");
				e.printStackTrace();
			}
		}else{
			result.put("response", "fail");
			result.put("msg", "没有认证！");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 分页获取机构端所有评价
	 * @author ZY
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年8月25日 上午10:08:28
	 */
	@RequestMapping("list")
	public String list(Integer orgId,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			PageResults<Map<String, Object>> page = commentsService.getPageListByOrgId(orgId, pageIndex, pageSize);
			if(page!=null){
				result.put("data", page);
				result.put("response", "success");
				result.put("msg", "获取成功");
			}
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}