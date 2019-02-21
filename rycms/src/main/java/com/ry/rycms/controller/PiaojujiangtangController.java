package com.ry.rycms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.PiaojujiangtangService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

/**
 *  
 * @author Ry-wk
 *
 */
@Controller
public class PiaojujiangtangController {
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	PiaojujiangtangService piaojujiangtangService;
	@Resource
	ActivecountService activecountService;
	
//	@RequestMapping("/news/search/")
//	public void search(String title, String content, String topflag, String publishtime,  HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Admin admin = (Admin)request.getSession().getAttribute("admin");
//		if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
//			response.sendRedirect(request.getContextPath()+"/login.jsp");
//			return;
//		}
//	}

//		@RequestMapping("/news/search/")
//		//if("badd".equals(method))
//		public String badd( HttpServletRequest request, HttpServletResponse response) throws Exception{
//		{
//			request.setAttribute("publishtimeStr", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			request.getRequestDispatcher("/WEB-INF/manage/newsAdd.jsp").forward(request, response);
//			}
//		return null;
//		}
//		
//		//if("add".equals(method))
		@RequestMapping("/piaojujiangtang/add/")
		public String add(Piaojujiangtang nw, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			nw.setPublishtime(new Date());
			piaojujiangtangService.savePiaojujiangtang(nw);
			request.setAttribute("message", "添加成功");
			try {
//				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e) {
				log.info("piaojujiangtang save  error！", e);
				
			}
			return "redirect:/piaojujiangtang/search/";
		}
		@RequestMapping("/piaojujiangtang/toupdate/")
		public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response){
			try {
				request.setAttribute("piaojujiangtang", piaojujiangtangService.getPiaojujiangtangById(id));
				//request.getRequestDispatcher("/manage/piaojujiangtangUpdate.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return "/information/billUpdate";
		}
//		@RequestMapping("/news/bupdate/")
//		public String bupdate( HttpServletRequest request, HttpServletResponse response) throws Exception{
//			Integer id = Integer.valueOf(request.getParameter("id"));
//			try{
//				List<BaseEntity> baseEntityList = newsService.findObjectList("from News where id like ?",new Object[]{Integer.valueOf(id)});
//				if(baseEntityList!=null&&baseEntityList.size()!=0){
//					News news = (News)baseEntityList.get(0);
//					request.setAttribute("news", news);
//					request.setAttribute("publishtimeStr", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(news.getPublishtime()));
//					request.getRequestDispatcher("/WEB-INF/manage/newsUpdate.jsp").forward(request, response);
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		//if("update".equals(method))
		@RequestMapping("/piaojujiangtang/update/")
		public String update(Piaojujiangtang sm,HttpServletRequest request, HttpServletResponse response){
			piaojujiangtangService.modifyPiaojujiangtang(sm);
			request.setAttribute("message", "修改成功");
			try {
//				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
//				request.getRequestDispatcher("/servicemember/list/").forward(request, response);
			} catch (Exception e) {
				log.info("Piaojujiangtang save  error！", e);
				
			}
			return "redirect:/piaojujiangtang/search/";
		}
//			@RequestMapping("/news/search/")
//		public String update(String title,String content,String publishtime,String pic,Integer type,Integer topflag, HttpServletRequest request, HttpServletResponse response) throws Exception{
//			try{
//				Integer id = Integer.valueOf(request.getParameter("id"));
//				//String title = request.getParameter("title");
//				//String content = request.getParameter("content");
//				//String publishtimeStr = request.getParameter("publishtime");
//				//String pic = request.getParameter("pic");
//				//Integer type = Integer.valueOf(request.getParameter("type"));
//				//Integer topflag = Integer.valueOf(request.getParameter("topflag"));
//				List<BaseEntity> baseEntityList = newsService.findObjectList("from News where id like ?",new Object[]{Integer.valueOf(id)});
//				if(baseEntityList!=null&&baseEntityList.size()!=0){
//					News news = (News)baseEntityList.get(0);
//					news.setTitle(title); 
//					news.setContent(content);
//					news.setPublishtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(publishtimeStr));
//					news.setPic(pic);
//					news.setType(type);
//					news.setTopflag(topflag);
//					newsService.updateNews(news);
//					request.setAttribute("message", "修改成功");
//					request.getRequestDispatcher("/WEB-INF/manage/tip.jsp").forward(request, response);
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		//if("list".equals(method))
			@RequestMapping("/piaojujiangtang/search/")
			public String list(PageResults pr,String begintime,String endtime,  HttpServletRequest request, HttpServletResponse response) throws Exception{
				if(pr.getCurrentPage()==null){
					pr.setCurrentPage(1);
				}
				
				PiaojujiangtangForm nf = new PiaojujiangtangForm();
				if(StringUtils.hasText(begintime)){
					nf.setBeginDate(DateUtil.parser(begintime,  DateUtil.FORMART3));
					request.setAttribute("begintimeStr", begintime);
				}
				if(StringUtils.hasText(endtime)){
					nf.setEndDate(DateUtil.parser(endtime,  DateUtil.FORMART3));
					request.setAttribute("endtimeStr",endtime);
				}
				pr = piaojujiangtangService.getPageList(nf, pr.getCurrentPage(), 20);				
				
//				String hql = "from News where publishtime >= ? and publishtime <= ? order by id desc";
//				List<News> newsList = newsService.findNewsByPageModel(hql, new Object[]{begintimed,endtimed}, pageModel, 20);
				request.setAttribute("pr", pr);
				//request.getRequestDispatcher("/manage/piaojujiangtangList.jsp").forward(request, response);
				return "/information/billList";
		}
			
			
			//if("delete".equals(method))
			@RequestMapping("/piaojujiangtang/delete/")
			public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception
				{
					try{
						piaojujiangtangService.removePiaojujiangtang(id);
					}catch(Exception e){
						e.printStackTrace();
						request.setAttribute("message", "删除失败");
						try {
							request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					return "redirect:/piaojujiangtang/search/";
				}
			
			
			@RequestMapping("/piaojujiangtang/addPiaojujiangtang/")
			public String toUpdate(HttpServletRequest request, HttpServletResponse response){
				return "/information/billAdd";
			}
}
//						Integer id = Integer.valueOf(request.getParameter("newsid"));
//						List<BaseEntity> baseEntityList = newsService.findObjectList("from News where id like ?",new Object[]{Integer.valueOf(id)});
	//					if(baseEntityList!=null&&baseEntityList.size()!=0){
	//						News news = (News)baseEntityList.get(0);
		//					newsService.deleteNews(news);
//				}
//						request.setAttribute("message", "删除成功");
//						request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
//					}catch(Exception e){
//						e.printStackTrace();
//						request.setAttribute("message", "删除失败");
//						request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
//					}
//					return null;
//				}
//			}
//	


