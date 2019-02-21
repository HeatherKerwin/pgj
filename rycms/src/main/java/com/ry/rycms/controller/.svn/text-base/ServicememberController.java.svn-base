/**
* @Title: ServicememberController.java
* @Package com.ry.rycms.controller
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.rycms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Servicemember;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.ServicememberService;

/**
 * @ClassName: ServicememberController
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
@Controller
public class ServicememberController {
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	ServicememberService servicememberService;
	@Resource
	ActivecountService activecountService;
	/**
	 * 
	* @Title: add
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param sm
	* @param @param request
	* @param @param response
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/servicemember/add/")
	public String add(Servicemember sm,HttpServletRequest request, HttpServletResponse response){
		sm.setFlag(0);
		servicememberService.saveServicemember(sm);
		request.setAttribute("message", "添加成功");
		try {
//			request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
		} catch (Exception e) {
			log.info("Servicemember save  error！", e);
			
		}
		return "redirect:/servicemember/list/";
	}
	
	@RequestMapping("/servicemember/toupdate/")
	public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response){
		try {
			request.setAttribute("servicemember", servicememberService.getServicememberById(id));
			//request.getRequestDispatcher("/manage/servicememberUpdate.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/customerActivity/add";
	}
	
	
	@RequestMapping("/servicemember/update/")
	public String update(Servicemember sm,HttpServletRequest request, HttpServletResponse response){
		
		servicememberService.modifyServicemember(sm);
		request.setAttribute("message", "修改成功");
		try {
//			request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
//			request.getRequestDispatcher("/servicemember/list/").forward(request, response);
		} catch (Exception e) {
			log.info("Servicemember save  error！", e);
			
		}
		return "redirect:/servicemember/list/";
	}
	/**
	 * 
	* @Title: list
	* @Description: 分页查询推荐人
	* @param @param sm
	* @param @param request
	* @param @param response
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/servicemember/list/")
	public String list(Model model){
		try{
//			if(pageModel.getCurrentPage()==null){
//				pageModel.setCurrentPage(1);
//			}
//			pageModel = servicememberService.getPageList(null, pageModel.getCurrentPage(), 20);		
			
			/**
			 * 上月活跃度
			 */
			List lastMonthData = servicememberService.saleLivenessData("lastMonthCount");
			model.addAttribute("lastMonthData", lastMonthData);
			/**
			 * 本月活跃度
			 */
			List<Object[]> monthData = servicememberService.saleLivenessData("monthCount");
			Map monthDataMap = new HashMap();
			for(Object[] temp:monthData){
				monthDataMap.put(temp[1], temp[2]);
			}
			model.addAttribute("monthDataMap", monthDataMap);
			/**
			 * 本周活跃度
			 */
			List<Object[]> weekData = servicememberService.saleLivenessData("weekCount");
			Map weekDataMap = new HashMap();
			for(Object[] temp:weekData){
				weekDataMap.put(temp[1], temp[2]);
			}
			model.addAttribute("weekDataMap", weekDataMap);
			/**
			 * 上周活跃度
			 */
			List<Object[]> lastWeekData = servicememberService.saleLivenessData("lastWeekCount");
			Map lastWeekDataMap = new HashMap();
			for(Object[] temp:lastWeekData){
				lastWeekDataMap.put(temp[1], temp[2]);
			}
			model.addAttribute("lastWeekDataMap", lastWeekDataMap);
			/**
			 * 本月新增用户
			 */
			List<Object[]> monthCustomData = servicememberService.saleCustomCount("monthCount");
			Map monthCustomDataMap = new HashMap();
			for(Object[] temp:monthCustomData){
				monthCustomDataMap.put(temp[1], temp[2]);
			}
			model.addAttribute("monthCustomDataMap", monthCustomDataMap);
			/**
			 * 本周新增用户
			 */
			List<Object[]> weekCustomData = servicememberService.saleCustomCount("weekCount");
			Map weekCustomDataMap = new HashMap();
			for(Object[] temp:weekCustomData){
				weekCustomDataMap.put(temp[1], temp[2]);
			}
			model.addAttribute("weekCustomDataMap", weekCustomDataMap);
//			request.setAttribute("allactivecount", activecountService.getAllCount());
//			request.setAttribute("pageModel", pageModel);
//			request.getRequestDispatcher("/manage/servicememberList.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/customerActivity/list";
	}
	/**
	 * 
	* @Title: delete
	* @Description: 删除推荐人
	* @param @param sm
	* @param @param request
	* @param @param response
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/servicemember/delete/")
	public String delete(Integer  id,HttpServletRequest request, HttpServletResponse response){
		try{
			servicememberService.removeServicemember(id);
//			request.getRequestDispatcher("/servicemember/list/").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			try {
				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/servicemember/list/";
	}
	
	/**
	 * 跳转添加推荐人页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/servicemember/toAdd")
	public String delete(HttpServletRequest request, HttpServletResponse response){
		return "/customerActivity/add";
	}
	
	
}
