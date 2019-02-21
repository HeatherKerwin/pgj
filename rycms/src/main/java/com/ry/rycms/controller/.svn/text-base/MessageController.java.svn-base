package com.ry.rycms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Message;
import com.ry.core.form.MessageForm;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.MessageService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

/**
 * 意见反馈
 */
@Controller
public class MessageController {
	
	@Resource
	MessageService messageService;
	
	@Resource
	ActivecountService activecountService;
	
	@RequestMapping("/message/search/")
	public String list(PageResults<?> pr,String mobile,String beginDate,String endDate,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(1);
		}
		MessageForm nf = new MessageForm();
		
		if(StringUtils.isNotBlank(mobile))nf.setMessagemobile(mobile);
		if(StringUtils.isNotBlank(beginDate)){
			nf.setBeginDate(DateUtil.parser(beginDate,DateUtil.FORMART3));
		}
		if(StringUtils.isNotBlank(endDate)){
			nf.setEndDate(DateUtil.parser(endDate,DateUtil.FORMART3));
		}
		
		pr = messageService.getPageList(nf, pr.getCurrentPage(), 20);
		
		request.setAttribute("mobile", mobile);
		request.setAttribute("begintimeStr", beginDate);
		request.setAttribute("endtimeStr",endDate);
		request.setAttribute("pageModel", pr);
		request.setAttribute("messageList", pr.getResults());
		//request.getRequestDispatcher("/manage/messageList.jsp").forward(request, response);
		return "/opinion/list";
	}
	
	@RequestMapping("/message/toupdate/")
	public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response){
		try {
			request.setAttribute("message", messageService.getMessageDTOById(id));
			//request.getRequestDispatcher("/manage/messageUpdate.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/opinion/info";
	}
	
	@RequestMapping("/message/delete/")
	public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			messageService.removeMessage(id);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			try {
				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/message/search/";
	}
	
	/**
	 * 保存用户反馈[保存回访备注]
	 * @param message
	 * @author WKX
	 */
	@RequestMapping("/message/save")
	public String save(Message message){
		Integer id = message.getId();
		Message tmp = messageService.getMessageById(id);
		if(tmp!=null){
			tmp.setReturnVisit(message.getReturnVisit());
			messageService.modifyMessage(tmp);
		}
		return "redirect:/message/search/";
	}
	
//	@RequestMapping("/message/add/")
//	public String add(Message nw, HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		 nw.setMessagetime(new Date());
//		messageService.saveMessage(nw);
//		request.setAttribute("message", "添加成功");
//		try {
////			request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
//		} catch (Exception e) {
//			log.info("message save  error！", e);
//			
//		}
//		return "redirect:/message/search/";
//	}
}