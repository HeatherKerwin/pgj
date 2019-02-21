package com.utiexian.website.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Image;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgCity;
import com.ry.core.entity.OrgImage;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.Region;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgImageService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.ServicememberService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;
import com.utiexian.website.util.EmailUtil;

import cn.jimmyshi.beanquery.BeanQuery;
import freemarker.template.Configuration;



/**
 * @author MH
 * 基本信息
 */
@Controller
public class BisicMessageController {
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	OrgCityService orgCityService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgImageService orgImageService;
	
	@Resource
	ServicememberService servicememberService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	private Configuration configuration;
	
	
	/**
	 * 贴现地址
	 * @author MH
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessage/discountplace")
	public String discountplace(){
		return "bisic_message/discountplace";
	}
	
	
	/**
	 * 我的会员
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/vipMember")
	public String vipMember(){
		return "bisic_message/vipMember";
	}
	
	/**
	 * 我的红包
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/redPackets")
	public String redPackets(){
		return "bisic_message/redPackets";
	}
	
	
	/**
	 * 推荐人
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/men")
	public String men(){
		return "bisic_message/men";
	}
	
	/**
	 * 保证金
	 * @author MH
	 * @return
	 */
	/*@RequestMapping("/bisicmessage/deposit")
	public String deposit(){
		return "bisic_message/deposit";
	}*/
	@RequestMapping("/bisicmessage/deposit")
	public String deposit(){
		return "bisic_message/account";
	}
	/*记录*/
	@RequestMapping("/bisicmessage/depositRecord")
	public String depositRecord(){
		return "bisic_message/accountRecord";
	}
	
	/**
	 * 充值记录
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/accountRecord")
	public String accountRecord(){
		return "bisic_message/accountRecord";
	}
	
	/**
	 * 修改密码
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/password")
	public String password(){
		return "bisic_message/password";
	}
	
	/**
	 * 基本信息
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/information")
	public String information(){
		return "bisic_message/information";
	}
	
	/**
	 * 认证信息
	 * @author MH
	 * @return
	 */
	@RequestMapping("/bisicmessage/authentication")
	public String authentication(){
		return "bisic_message/authentication_open";
	}
	
	/**
	 * 交易双方合同（兴业）
	 * @author MH
	 * @param distId 接单订单主键
	 * @param orderType 订单类型（银：DISCOUNTRECORD 商：DISCOUNTRECORSP）
	 * @param buyout 一口价
	 * @param discId 贴现订单主键
	 */
	@RequestMapping("/agreements/contract")
	public String contract(Integer distId,String orderType,Integer discId, boolean buyout,Model model){
		model.addAttribute("distId", distId);
		model.addAttribute("orderType", orderType);
		model.addAttribute("buyout", buyout);
		model.addAttribute("discId", discId);
		return "agreements/receivable";
	}
	
	/**
	 * 交易双方合同（京东）
	 * @author MH
	 * @param distId 接单订单主键
	 * @param orderType 订单类型（银：DISCOUNTRECORD 商：DISCOUNTRECORSP）
	 * @param buyout 一口价
	 * @param discId 贴现订单主键
	 * @param bindId 京东卡Id
	 */
	@RequestMapping("/jd/agreements/contract")
	public String jdContract(Integer distId,String orderType,Integer discId, boolean buyout,String bindId,Model model){
		model.addAttribute("distId", distId);
		model.addAttribute("orderType", orderType);
		model.addAttribute("buyout", buyout);
		model.addAttribute("discId", discId);
		model.addAttribute("bindId", bindId);
		return "agreements/yingshoukuan";
	}
	
	/**
	 * 认证信息先判断是否认证（企业）
	 * @param request
	 * @param model
	 * @author MH
	 * @return 
	 */
	@RequestMapping("/bisicmessage/getorg")
	public String getOrg(HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		try {
			Map<String,Object> orginfo = orgInfoService.getByMemberIdAndType(memberId,0);
			if(orginfo!=null){
				m = memberService.getById(memberId);
				if(m!=null && m.getEmail()!=null){
					orginfo.put("email", m.getEmail());
				}
				map.put("data", orginfo);
				map.put("response", "success");
			}else{
				map.put("response", "failed");
			}
		} catch (Exception e) {
			map.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 根据id获取交易城市，进行编辑
	 * @param request
	 * @param model
	 * @param id
	 * @author MH
	 * @return 
	 */
	@RequestMapping("/bisicmessage/getcity")
	public String getCity(HttpServletRequest request,Model model,Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			OrgCity orgcity = orgCityService.getById(id);
			if(orgcity!=null){
				map.put("response","success");
				map.put("data", orgcity);
			}else{
				map.put("response","success");
			}
		} catch (Exception e) {
			map.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * @author MH 
	 *        根据城市Id,orgId,判断城市是否可以修改或者删除（查看某人今天某个城市是否报价）
	 * @param orgId  认证的org表的id
	 * @param cityId 城市id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/bisicmessage/judge/updateCity")
	public String judgeUpdateCity(Integer id,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		OrgCity orgCity = orgCityService.getById(id);		
		if(orgCity == null)throw new Exception();
		String day = DateUtil.formart(new Date(),DateUtil.FORMART3);
		map.put("orgId", orgCity.getOrgId());
		map.put("cityId", orgCity.getCityId());
		map.put("day", day);
		List<Map<String, Object>> listmap  =  priceService.judgeUpdateCity(map);
		if(listmap.size() > 0){
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
	 * 保存认证信息（承诺函再次上传不限制是否审核通过）
	 * @param orgInfo
	 * @param model
	 * @param email
	 * @param images
	 * @author MH
	 */
	@RequestMapping("/bisicmessage/info/save")
	public String saveInfo(OrgInfo orgInfo,String[] images,String email,Model model,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		orgInfo.setMemberId(memberId);
		String msg = "操作失败";
		boolean infoSave = true;
		try {
			if(orgInfo.getType() ==1){
				Org org = orgService.getByMemberId(memberId);
				if(org == null)throw new Exception();
				orgInfo.setOrgId(org.getId());
			}
			if(orgInfo==null || (orgInfo.getType()==1 && orgInfo.getOrgId()==null))throw new Exception();
			if (orgInfo.getType() == 1) {
				List<Map<String,Object>> orgCityList = orgCityService.getRegionByOrgId(orgInfo.getOrgId());
				if (orgCityList == null || orgCityList.size() == 0){
					msg = "请填写交易城市";
					throw new Exception();
				}
			}
			
			Map<String,Object> info = orgInfoService.getByMemberIdAndType(orgInfo.getMemberId(),orgInfo.getType());
			if(info!=null && info.get("state")!=null){
				if(1==Integer.valueOf(info.get("state").toString())){
					msg = "您近期已申请过认证";
					infoSave = false;
				}else if(2==Integer.valueOf(info.get("state").toString())){
					msg = "您的认证已通过";
					infoSave = false;
				}
			}
			if(infoSave){//可以新增信息
				Member member = null;
				if(orgInfo.getMemberId()!=null){//把邮箱也保存一下
					member = memberService.getById(orgInfo.getMemberId());
					if(member!=null && org.apache.commons.lang.StringUtils.isNotBlank(email))member.setEmail(email);
				}
				Org org = null;
				if(orgInfo.getOrgId()!=null){
					org = orgService.getById(orgInfo.getOrgId());
					if(org!=null)orgInfo.setMemberId(org.getMemberId());//@APP2.2 保存用户主键（拢余审核状态）
				}
				orgInfo.setState(1);//审核中
				if(orgInfo.getType() == 1 && org!=null){//机构
					org.setState(1);//审核中
				}else{//企业
					if(member!=null)member.setState(1);//审核中
				}
				orgInfo.setCreateTime(new Date());
				orgInfoService.updateOrgOrMember(org, member, orgInfo);
			}
			
			if(images!=null && images.length>0){//上传的图片可以保存
				OrgImage orgImage = new OrgImage();
				orgImage.setCreateTime(new Date());
				orgImage.setOrgId(orgInfo.getOrgId());
				
				String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
				//检查目录
				File uploadDir = new File(uploadPath);
				if(!uploadDir.isDirectory()){
					uploadDir.mkdirs();
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String extpath = df.format(new Date());
				List<Image> list = new ArrayList<Image>();
				Image image = null;
				for (String img:images) {
					image = new Image();
					if (!"null".equalsIgnoreCase(img)) {
						String path = img;
						if(img.indexOf(Constant.TEMP_PATH)!=-1){//临时文件夹 的文件 转到 保存目录中(如果不是临时的图片则直接引用)
							String pString = uploadPath + extpath;
							String tString = temp + "image" + File.separator + extpath;
							if (StringUtils.hasText(img)) {
								tString += img.substring(img.lastIndexOf("/"), img.length());
								FileUtil.moveFile(tString, pString);
							}
							path = Constant.UPLOAD_SRC + extpath +img.substring(img.lastIndexOf("/"), img.length());
						}
						image.setImgPath(path);
						list.add(image);
					}
				}
				orgImageService.saveModelAndImage(orgImage,list);
			}
			//发送承诺函模板
			if(org.apache.commons.lang.StringUtils.isNotBlank(email))sendEmail(email, request);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", msg);
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 发送邮件（承诺函）
	 * @author MH
	 * @param email
	 * @param request
	 * @since 2016年5月16日 下午4:03:07
	 */
	private void sendEmail(String email,HttpServletRequest request) {
		try {
			List<String> targetPerson = new ArrayList<String>();
			targetPerson.add(email);
			Map<String, Object> map = new HashMap<String, Object>();
			String template = "/emailtemplate/cnhEmail.ftl";
			String html = EmailUtil.getHtml(configuration,template,map);
			
			String path = request.getSession().getServletContext().getRealPath(File.separator + "download") + File.separator + "cnh.pdf";
			EmailUtil.sendEmail(targetPerson,"《报价方合作银行承诺书》", html,path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据机构Id查询该机构的所有交易城市(机构)同时判断是否已认证
	 * @author MH
	 */
	@RequestMapping("bisicmessage/check/searchcitybyorgId")
	public String searchCityByOrgId(Model model,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		try {
			Map<String,Object> info = orgInfoService.getByMemberIdAndType(memberId,1);
			if(info==null){
				result.put("state","info_success");
			}else{
				m = memberService.getById(memberId);
				if(m!=null && m.getEmail()!=null){
					info.put("email", m.getEmail());
				}
				result.put("state","success");
				result.put("info", info);
			}
			Org org =orgService.getByMemberId(memberId);
			if(org==null)throw new Exception();
			List<Map<String, Object>> list = orgService.searchCityByOrgId(org.getId());
			result.put("data", list);
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","获取机构交易城市操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 初始化省市（认证页面用）
	 * @param model
	 * @throws Exception
	 * @author MH
	 */
	@RequestMapping("bisicmessage/init/city")
	public String initCity(Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<Region> p = regionService.getByType("P");//省
		List<Region> c = regionService.getByType("C");//市
		result.put("p", p);
		result.put("c", c);
		result.put("response", "success");
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据交易城市主键删除对象
	 * @param id
	 * @param model
	 * @author MH
	 */
	@RequestMapping("bisicmessage/del/orgcity")
	public String deleteOrgCityById(Integer id, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			orgCityService.deleteById(id);
			result.put("response","success");
			result.put("orgcityId",id);
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("response","failed");
			result.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存交易城市
	 * @author MH
	 * @param orgCity
	 * @param model
	 *        新增加判断城市是否已经存在
	 */
	@RequestMapping("bisicmessage/save/orgcity")
	public String saveOrgCity(OrgCity orgCity, Model model,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			Org org =orgService.getByMemberId(memberId);
			if(org==null)throw new Exception();
			orgCity.setOrgId(org.getId());
			OrgCity city = orgCityService.getOrgCity(orgCity);
			if(city!=null){
				result.put("response","failed");
				result.put("msg","你已经添加过该交易地点");
			}else{
				orgCityService.save(orgCity);
				result.put("response","success");
				result.put("msg","操作成功");
			}
		} catch (Exception e) {
			result.put("response","failed");
			result.put("msg",e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 *  加载信息时获取基本信息
	 * @param request
	 * @param model
	 * @author MH
	 * @return
	 */
	@RequestMapping("bisicmessagemember/member/get")
	public String memberget(HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			Member member = memberService.getById(memberId);
			Map<String,Object> result = BeanQuery.select("id,invitationCode,myInvitationCode,recommendpeople").executeFrom(member);
			result.put("count",memberService.getByInvitationCode(member.getMyInvitationCode()));
			
			map.put("state","success");
			map.put("msg","操作成功");
			map.put("data",result);
		
		} catch (Exception e) {
			map.put("state","failed");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 *  保存基本信息和推荐号
	 * @param request
	 * @param picpath
	 * @param zhiwu
	 * @param weixin
	 * @param zuoji
	 * @param qq
	 * @param model
	 * @param minv
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bisicmessagemember/member/update")
	public String memberupdate(HttpServletRequest request,String picpath,String zhiwu,String weixin,String zuoji,String qq,Model model ,String minv )throws IOException{
			Map<String, Object> map = new HashMap<String, Object>();
			
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			Member member = memberService.getById(memberId);
			if(member!=null){
				if(weixin!=null){
					member.setWeixin(weixin);
				}
				if(qq!=null){
					member.setQq(qq);
				}
				if(zuoji!=null){
					member.setZuoji(zuoji);
				}
				if(zhiwu!=null){
					member.setZhiwu(zhiwu);
				}
			}
			
			if(picpath!=null){
				List<String> tempstrs = new ArrayList<String>();
				//将临时目录文件转移到目录文件中
				if (org.apache.commons.lang.StringUtils.isNotBlank(picpath)) {
					String[] picpaths = picpath.split(",");
					String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
					String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
					//检查目录
					File uploadDir = new File(uploadPath);
					if(!uploadDir.isDirectory()){
						uploadDir.mkdirs();
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					String extpath = df.format(new Date());
					for (int i = 0; i < picpaths.length; i++) {
						if (!"null".equalsIgnoreCase(picpaths[i])) {
							//临时文件夹 的文件 转到 保存目录中
							String pString = uploadPath + extpath;
							String tString = temp + "image" + File.separator + extpath;
							if (StringUtils.hasText(picpaths[i])) {
								tString += picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
								try {
									FileUtil.moveFile(tString, pString);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							String path = Constant.UPLOAD_SRC + extpath + picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
							tempstrs.add(path);
						}
					}
				}
				
				picpath = "";
				if (tempstrs.size() > 0) {
					for (String tempstr : tempstrs) {
						picpath += tempstr ;
					}
				}								
				member.setHeadpic(picpath);
			}
			
			if(minv!=null){
				member.setRecommendpeople(minv);
			}
			memberService.updateMember(member);
			map.put("state","success");
			map.put("msg","操作成功");
			model.addAttribute("retValue", JacksonUtil.objToJson(map));
			return "ajax";
	}
	
	/**
	 * 查询登录用户的基本信息
	 * @param id 主键
	 */
	@RequestMapping("/bisicmessagemember/searchmyinfobyid")
	public String searchMyInfoById(HttpServletRequest request, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			
			Member member = memberService.getById(memberId);
			result.put("data",member);
			result.put("state","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","查询用户信息操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	
	/**
	 * 企业保证金列表
	 * @author MH
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/bnsbaojinlist")
	public String baoJinList( HttpServletRequest request,Integer pageIndex,Integer pageSize, Model model){
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		String memberId = Integer.toString( m.getId());
		if(pageIndex==null)pageIndex = 1;
		if(pageSize==null)pageSize = 10;
		Map<String,Object> result = new HashMap<String, Object>();
		PageResults<Map<String,Object>> page = discountrecordService.getPcBnsDeposit(pageIndex,pageSize,memberId);
		Double alldeposit=0.0;
		for (Map<String,Object> map : page.getResults()) {
			if(map.get("qydeposit")!="" && map.get("qydeposit")!=null){
				Double deposit = Double.parseDouble( map.get("qydeposit").toString());
				alldeposit = alldeposit + deposit;
			}
		}
		result.put("data", page);
		result.put("alldeposit", alldeposit);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	
	/**
	 * 机构保证金列表
	 * @author MH
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("bisicmessagemember/jigoubaozhengjin")
	public String jigouBaoZhengJin(HttpServletRequest request,Integer pageIndex,Integer pageSize,Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer memberId = m.getId();
		Org org = orgService.getByMemberId(memberId);
		if(pageIndex==null)pageIndex = 1;
		if(pageSize==null)pageSize = 10;
		PageResults<Map<String,Object>> page = distributeOrderService.getPcOrgDeposit(pageIndex,pageSize,org.getId());
		Double alldeposit=0.0;
		for (Map<String,Object> map : page.getResults()) {
			if(map.get("qydeposit")!="" && map.get("qydeposit")!=null){
				Double deposit = Double.parseDouble( map.get("qydeposit").toString());
				alldeposit = alldeposit + deposit;
			}
		}
		result.put("data", page);
		result.put("alldeposit", alldeposit);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 去开户1
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/toAccount1")
	public String ToAccount1(HttpServletRequest request) {
		return "bisic_message/authentication_open1";
	}
	
	/**
	 * 去开户2
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/toAccount2")
	public String ToAccount2(HttpServletRequest request) {
		return "bisic_message/authentication_open2";
	}
	
	/**
	 * 去开户2
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/orgUpload")
	public String orgUpload(HttpServletRequest request) {
		return "bisic_message/authentication_orgUpload";
	}
	
	/**
	 * 去审核
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_orgExamine")
	public String authentication_orgExamine(HttpServletRequest request) {
		return "bisic_message/authentication_orgExamine";
	}
	
	/**
	 * 开户材料
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_material")
	public String authentication_material(HttpServletRequest request) {
		return "bisic_message/authentication_material";
	}
	
	/**
	 * 绑定1
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_orgUser1")
	public String authentication_orgUser1(HttpServletRequest request) {
		return "bisic_message/authentication_orgUser1";
	}
	
	/**
	 * 绑定2
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_orgUser2")
	public String authentication_orgUser2(HttpServletRequest request) {
		return "bisic_message/authentication_orgUser2";
	}
	
	/**
	 * 绑定3
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_orgUser3")
	public String authentication_orgUser3(HttpServletRequest request) {
		return "bisic_message/authentication_orgUser3";
	}
	
	/**
	 * 执剑人电子账户
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_holder")
	public String authentication_holder(HttpServletRequest request) {
		return "bisic_message/authentication_holder";
	}
	
	/**
	 * 开户成功
	 * @param request
	 * @return
	 */
	@RequestMapping("/bisicmessagemember/authentication_orgFinish")
	public String authentication_orgFinish(HttpServletRequest request) {
		return "bisic_message/authentication_orgFinish";
	}
	
	
}
