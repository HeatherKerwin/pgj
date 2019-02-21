package com.ry.rycms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Admin;
import com.ry.core.entity.Appversion;
import com.ry.core.entity.Member;
import com.ry.core.entity.da.AdminRole;
import com.ry.core.service.AdminRoleService;
import com.ry.core.service.AdminService;
import com.ry.core.service.AppversionService;
import com.ry.core.service.AuthService;
import com.ry.core.service.MemberService;
import com.ry.util.MD5Util;
import com.ry.util.PropertiesUtil;
import com.ry.util.page.PageResults;


@Controller
public class AdminController {
	
	@Resource
	AdminService adminService;		
	
	@Resource
	MemberService memberService;
	
	@Resource
	AppversionService appversionService;
	
	@Resource
	AuthService authService;
	
	@Resource
	AdminRoleService adminRoleService;
	
	@RequestMapping("/j_spring_cas_security_check")
	public String login(HttpServletRequest req,String username,String password,Model model) throws Exception{
		return "ajax";
	}
	
	/**
	 * CAS认证后回调
	 * @author WKX
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @since 2017年1月6日 上午9:53:33
	 */
	@RequestMapping("/admin/index")
	public String index(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String cas_un = userDetails.getUsername();
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		boolean need_get = false;
		if(admin!=null){
			String username = admin.getUsername();
			if(StringUtils.isBlank(username) || !username.equals(cas_un)){
				need_get = true;
			}
		}else{
			need_get = true;
		}
		if(need_get && StringUtils.isNotBlank(cas_un)){//获取登录用户
			List<Admin> list = adminService.getAdminByName(cas_un);
			if(list!=null && list.size()>0){
				admin = list.get(0);
			}
		}
		if (admin != null) {
			this.isAdmin(admin, request);//登录后验证是否是管理员（用于显示手机号码）
			
			request.getSession().setAttribute("admin", admin);
			String cmsId = PropertiesUtil.getConfigPropertiesValue("SYSTEMID",null);
			if(StringUtils.isNotBlank(admin.getRealname())){
				request.getSession().setAttribute("name", admin.getRealname());
			}else{
				request.getSession().setAttribute("name", admin.getUsername());
			}
			lists = authService.getMenuListById(admin.getId());//根据用户登录的id获取菜单功能集合
			if(lists==null || lists.size()==0 || StringUtils.isBlank(cmsId)){
				request.getSession().setAttribute("msg", "您暂无访问权限，请联系管理员");
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}
			request.getSession().setAttribute("menus", bindChildByParent(null, cmsId));
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		} else {
			request.getSession().setAttribute("msg", "用户名或密码错误");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		return "ajax";
	}
	
	/**
	 * 退出
	 * @author WKX
	 * @throws Exception
	 * @since 2016年12月27日 下午4:32:29
	 */
	@RequestMapping("/j_spring_cas_security_logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		request.getSession().removeAttribute("admin");
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return "ajax";
	}
	
	@RequestMapping("/admin/login/")
	public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		password = MD5Util.getMD5Str(password);
		Admin admin = adminService.login(null,username, password);
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			String cmsId = PropertiesUtil.getConfigPropertiesValue("SYSTEMID",null);
			if(StringUtils.isNotBlank(admin.getRealname())){
				request.getSession().setAttribute("name", admin.getRealname());
			}else{
				request.getSession().setAttribute("name", admin.getUsername());
			}
			lists = authService.getMenuListById(admin.getId());//根据用户登录的id获取菜单功能集合
			if(lists==null || lists.size()==0 || StringUtils.isBlank(cmsId)){
				request.getSession().setAttribute("msg", "您暂无访问权限，请联系管理员");
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}
			request.getSession().setAttribute("menus", bindChildByParent(null, cmsId));
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			
			this.isAdmin(admin, request);//登录后验证是否是管理员（用于显示手机号码）
		} else {
			request.getSession().setAttribute("msg", "用户名或密码错误");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		return "ajax";
	}
	
	/**
	 * 登录后验证是否是管理员（用于显示手机号码）
	 * @author WKX
	 * @param admin 用户
	 * @param request
	 * @since 2017年8月14日 下午5:34:39
	 */
	private void isAdmin(Admin admin,HttpServletRequest request){
		String roleId = PropertiesUtil.getConfigPropertiesValue("ADMIN_ROLE_ID",null);//管理员角色主键
		List<AdminRole> ars = adminRoleService.getByAdminIdAndRoleId(admin.getId(), Integer.valueOf(roleId));
		if(ars!=null && ars.size()>0){
			request.getSession().setAttribute("isAdmin", true);//是管理员
		}else{
			request.getSession().setAttribute("isAdmin", false);//不是管理员
		}
	}
	
	/**
	 * 组装菜单
	 * @param parentNode
	 * @param parent
	 */
	private static List<Map<String, Object>> lists = null;
	public static List<Map<String, Object>> bindChildByParent(List<Map<String, Object>> parentNode,String parent){
		if(parentNode==null)parentNode = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list = selectChildByParent(parent);
        for(Map<String, Object> item:list){
        	
            List<Map<String, Object>> childs = (List<Map<String, Object>>)item.get("childs");
            if(childs==null)childs = new ArrayList<Map<String,Object>>();
            if (hasChild(item.get("id").toString())){
            	List<Map<String, Object>> temp = bindChildByParent(childs, item.get("id").toString());
            	item.put("childs", temp);
            }
            parentNode.add(item);
        }
        return parentNode;
    }
	
	/**
	 * 根据父级获取子集
	 * @param parentId
	 */
	public static List<Map<String, Object>> selectChildByParent(String parentId){
		List<Map<String, Object>> childs = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> menu:lists){
			if(menu.get("parent_id")!=null && parentId.equals(menu.get("parent_id").toString())){
				childs.add(menu);
			}
		}
        return childs;
    }
	
	/**
	 * 是否有子集
	 * @param parentId
	 */
	public static Boolean hasChild(String parentId){
		Boolean flag = false;
		for(Map<String, Object> menu:lists){
			if(menu.get("parent_id")!=null && parentId.equals(menu.get("parent_id").toString())){
				flag = true;
			}
		}
        return flag;
    }
	
	@RequestMapping("/admin/logout/")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getSession().removeAttribute("admin");
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return "ajax";
	}
	
	@RequestMapping("/admin/appversion/")
	public String appversion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try{			
			Appversion appversion = appversionService.getAppversion();
			request.setAttribute("appversion", appversion);
			//request.getRequestDispatcher("/manage/appversion.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/version/info";
	}
	
	@RequestMapping("/admin/appversionupdate/")
	public String appversionupdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try{			
			Appversion appversion = appversionService.getAppversion();
			String iosversion = request.getParameter("iosversion");
			String androidversion = request.getParameter("androidversion");
			String iosdesc = request.getParameter("iosdesc");
			String androiddesc = request.getParameter("androiddesc");
			Integer iosflag = Integer.valueOf(request.getParameter("iosflag"));
			Integer androidflag = Integer.valueOf(request.getParameter("androidflag"));
			appversion.setAndroiddesc(androiddesc);
			appversion.setAndroidversion(androidversion);
			appversion.setIosdesc(iosdesc);
			appversion.setIosversion(iosversion);
			appversion.setAndroidflag(androidflag);
			appversion.setIosflag(iosflag);
			
			appversionService.updateAppversion(appversion);
			
			request.setAttribute("appversion", appversion);
		//	request.getRequestDispatcher("/manage/appversion.jsp").forward(request, response);
		}catch(Exception e){
			
		}
		return "redirect:/admin/appversion/";
	}
	
	@RequestMapping("/admin/bupdate/")
	public String bupdateAdmin(Integer id,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try{			 
			 List<Admin> baseEntityList2 = adminService.getAdminList(id, null);
			 if(baseEntityList2!=null&&baseEntityList2.size()!=0){
				 Admin admin = (Admin)baseEntityList2.get(0);
				 request.setAttribute("admin1", admin);
				// request.getRequestDispatcher("/manage/adminUpdate.jsp").forward(request, response);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/info";
	}
	
	@RequestMapping("/admin/bupdatepwd/")
	public String bupdateAdmin(HttpServletRequest request, HttpServletResponse response){		
	/*	try{
			request.getRequestDispatcher("/manage/adminUpdatePwd.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return "/admin/update";
	}
	
	@RequestMapping("/admin/updatepwd/")
	public String updatepwd(String oldpassword, String newpassword, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin.getPassword().equals(MD5Util.getMD5Str(oldpassword))){
			admin.setPassword(MD5Util.getMD5Str(newpassword));
			try{
				adminService.updateAdmin(admin);
				request.setAttribute("message", "修改密码成功");
				return "redirect:/admin/logout/";				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			request.setAttribute("message", "原始密码不正确");
			//request.getRequestDispatcher("/manage/adminUpdatePwd.jsp").forward(request, response);
			
		}
		return "/admin/update";
	}
	
	@RequestMapping("/admin/update/")
	public String update(Integer id, String username, String []usertype, String currentPage, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{		
		try{
			Admin admin2 = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin2.getUsername()))){
				response.sendRedirect("../login.jsp");
				return "ajax";
			}			 
			 List<Admin> baseEntityList2 = adminService.getAdminList(id, null);
			 if(baseEntityList2!=null&&baseEntityList2.size()!=0){
				 Admin admin = (Admin)baseEntityList2.get(0);				 
				 String usertypes = "";
				 if (usertype != null && usertype.length>0) {
					 for(String type : usertype){
						 usertypes += type+",";
					 }
				 }
				 
				 if(admin.getUsername().equals(username)){
					 admin.setUsername(username);
					 admin.setUsertype(usertypes);
					 adminService.updateAdmin(admin);
				 }else{
					 List<Admin> baseEntityList = adminService.getAdminList(null, username);
					 if(!(baseEntityList!=null&&baseEntityList.size()!=0)){//						 
						 admin.setUsername(username);
						 admin.setUsertype(usertypes);
						 adminService.updateAdmin(admin);
					 }
				 }
				 
				Integer page = 0;
				if(currentPage!=null&&!"".equals(currentPage)){
					page = Integer.valueOf(currentPage);
				}
				PageResults<Admin> pageModel = adminService.findPageModel(page, 20, null);
				
				request.setAttribute("adminList", pageModel.getResults());
				request.setAttribute("pageModel", pageModel);
				//request.getRequestDispatcher(request.getContextPath()+ "../../manage/adminList.jsp").forward(request, response);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/admin/list/";
	}
	
	@RequestMapping("/admin/badd/")
	public String baddAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
 		if(admin == null || !("admin".equals(admin.getUsername()))){
			//response.sendRedirect(request.getContextPath()+ "/login.jsp");
			return request.getContextPath()+ "/login.jsp";
		}
		//System.out.println(request.getContextPath());
		//request.getRequestDispatcher("/manage/adminAdd.jsp").forward(request, response);
		return "/admin/add";
	}
		
	@RequestMapping("/admin/add/") 
	public String addAdmin(String username, String password, String realname, String[] usertype, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try{
			Admin admin1 = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin1.getUsername()))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}

			 password = MD5Util.getMD5Str(password);
			 String[] usertypeArray = usertype;
			 String usertypes = "";
			 if (usertypeArray != null && usertypeArray.length > 0) {
				 for(String type : usertypeArray){
					 usertypes += type+",";
				 }
			 }				 
			 List<Admin> adminList1 = adminService.getAdminList(null,username);
			 if(!(adminList1!=null && adminList1.size()!=0)){
				 Admin admin2 = new Admin();
				 admin2.setUsername(username);
				 admin2.setPassword(password);
				 admin2.setUsertype(usertypes);
				 admin2.setRealname(realname);
				 //新增用户 使用户生效
				 admin2.setStatue(1);
				 admin2.setRegistDate(new Date());
				 adminService.addAdmin(admin2);
			 }
			 List<Admin> adminList2 = adminService.getAdminList(null,null);
			 List<Admin> adminList = new ArrayList<Admin>();
			 if(adminList2!=null&&adminList2.size()>0){
				for(Admin admin : adminList2){
					adminList.add(admin);
				}
			 }
				request.setAttribute("adminList", adminList);
			//	request.getRequestDispatcher("/manage/adminList.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/admin/list/";
	}
	
	@RequestMapping("/admin/list/") 
	public String list(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		try{
			Admin admin2 = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin2.getUsername()))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}
			List<Admin> adminList = adminService.getAdminList(null,null);			
			request.setAttribute("adminList", adminList);
			//request.getRequestDispatcher("/manage/adminList.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/list";
	}
	
	@RequestMapping("/admin/delete/")
	public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		adminService.deleteAdmin(id);
		return "redirect:/admin/list/";
	}
	
	/**
	 * 为老用户（我的推荐码为NULL）初始化其我的推荐码
	 * @author WKX
	 * @param code
	 * @since 2016年1月21日 上午11:43:00
	 */
	@RequestMapping("/member/init")
	public @ResponseBody String initOldMember(String name,String pwd){
		int count = 0;
		try {
			if(StringUtils.isBlank(name)||StringUtils.isBlank(pwd))throw new Exception(" Please enter name and pwd... ");
			pwd = MD5Util.getMD5Str(pwd);
			Admin admin = adminService.login(null,name, pwd);
			if(admin!=null){
				List<Member> list = memberService.getByMyInvitationCodeISNULL();
				if(list!=null && list.size()>0){
					for(Member member:list){
						memberService.updateMemberByMyInvitationCodeISNULL(member);
						count ++;
					}
				}
				return "("+count+") update finish...";
			}else{
				return "[failed] name or pwd error...";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "[failed]"+e.getMessage();
		}
	}
}