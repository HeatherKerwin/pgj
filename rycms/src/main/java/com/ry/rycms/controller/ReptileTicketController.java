package com.ry.rycms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.ReptileTicket;
import com.ry.core.service.ReptileTicketService;
import com.ry.util.page.PageResults;

/**
 * 	公催
 * @author MH
 */
@Controller
public class ReptileTicketController {

	@Resource
	ReptileTicketService reptileTicketService;
	
	@RequestMapping("/gongcui/search")
	public String list(PageResults<ReptileTicket> pr, HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		pr = reptileTicketService.getPageList(pr.getCurrentPage(), 20);				
		model.addAttribute("pr", pr);
		return "/reptileTicket/gongcuiList";
	}
	
	@RequestMapping("/gongcui/toupdate/")
	public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReptileTicket reptileTicket = reptileTicketService.getById(id);
		request.setAttribute("reptile", reptileTicket);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/reptileTicket/gongcuixiangqing";
	}
}
