package com.ry.ryapp.controller;

import java.io.File;
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

import com.ry.core.entity.BeatCode;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.Image;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgCity;
import com.ry.core.entity.OrgImage;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.Region;
import com.ry.core.service.BeatCodeService;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgImageService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.Utility;
import com.ry.ryapp.util.EmailUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

import freemarker.template.Configuration;

/**
 * 认证信息
 * @author WKX
 */
@Controller
@RequestMapping("/app/org/")
public class OrgController {
	
	@Resource
	OrgService orgService;
	
	@Resource
	BeatCodeService beatCodeService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgCityService orgCityService;
	
	@Resource
	OrgImageService orgImageService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	private Configuration configuration;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	CommentsService commentsService;
	
	/**
	 * 保存认证（选择的类型）
	 * @author WKX
	 * @param org
	 * @param model
	 * @since 2016年3月22日 下午4:35:13
	 */
	@RequestMapping("save")
	public String save(Org org,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(org==null || org.getMemberId()==null)throw new Exception();
			Org temp = orgService.getByMemberId(org.getMemberId());
			if(temp!=null){
				if(org.getType()!=temp.getType()){
					temp.setType(org.getType());
				}
				orgService.saveModel(temp);
				result.put("data", temp);
				
				String beatCode = variablesService.getByCode("BEATCODE", "on");
				if("on".equals(beatCode)){
					Map<String,Object> bc = beatCodeService.getByMemberId(org.getMemberId());
					result.put("bc", bc);//如果之前输入过公测码就不用输入了
				}else{
					result.put("bc", "off");//这里只要有值作为标示就可以略过公测码
				}
				
				Map<String,Object> info = orgInfoService.getCurrentTypeInfoById(temp.getId());
				result.put("info", info);//查询之前时候认证过
			}else{
				org.setCreateTime(new Date());
				Org tmp = orgService.saveModel(org);
				result.put("data", tmp);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 验证公测码
	 * @author WKX
	 * @param orgId
	 * @param beatCode
	 * @param model
	 * @since 2016年3月23日 下午1:22:42
	 */
	@RequestMapping("validate/beatCode")
	public String validate(Integer orgId,String beatCode ,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> member = memberService.getInfoByOrgId(orgId);
			if(member==null || member.get("mobile")==null)throw new Exception();
			String mobile = Utility.encodeMobile(member.get("mobile").toString());
			
			BeatCode has = beatCodeService.getByNoAndPhone(beatCode, mobile);
			
			if(has==null){
				BeatCode bc = beatCodeService.getEnableByNo(beatCode);
				if(bc==null)throw new Exception();
				bc.setPhone(mobile);
				bc.setState(0);//已使用
				beatCodeService.updateModel(bc);//更新
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "您输入的公测码不存在");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存认证信息（承诺函再次上传不限制是否审核通过）
	 * @param orgInfo
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("info/save")
	public String saveInfo(OrgInfo orgInfo,String[] images,final String email,Float version,Model model,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String msg = "操作失败";
		boolean infoSave = true;
		try {
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
				if(orgInfo.getType()==1 && orgInfo.getOrgId()!=null){//只对机构保存该数据
					org = orgService.getById(orgInfo.getOrgId());
					if(org!=null)orgInfo.setMemberId(org.getMemberId());//@APP2.2 保存用户主键（拢余审核状态）
				}else{
					org = orgService.getByMemberId(orgInfo.getMemberId());
					if(org!=null)orgInfo.setOrgId(org.getId());
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
			//发送承诺函模板（线程）
			if(org.apache.commons.lang.StringUtils.isNotBlank(email)){
				final String path = request.getSession().getServletContext().getRealPath(File.separator + "download") + File.separator + "cnh.pdf";
				new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	List<String> targetPerson = new ArrayList<String>();
	        			targetPerson.add(email);
	        			Map<String, Object> map = new HashMap<String, Object>();
	        			String template = "/emailtemplate/cnhEmail.ftl";
	        			String html = EmailUtil.getHtml(configuration,template,map);
	        			
	        			EmailUtil.sendEmail(targetPerson,"《报价方合作银行承诺书》", html,path);
	                }
	            }).start();
			}
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
	 * 根据机构Id查询该机构的所有交易城市
	 * @param orgId 机构ID
	 * @author BKY
	 */
	@RequestMapping("check/searchCityByOrgId")
	public String searchCityByOrgId(Integer orgId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = orgService.searchCityByOrgId(orgId);
			if (list != null && list.size() > 0) {
				List<String> cityName = new ArrayList<String>();
				List<String> cityId = new ArrayList<String>();
				for(Map<String, Object> map : list) {
					cityId.add(map.get("city_id").toString());
					cityName.add(map.get("name").toString());
				}
				result.put("cityId", cityId);
				result.put("cityName", cityName);
				result.put("state","success");
				result.put("msg","操作成功");
			}
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","获取机构交易城市操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * @APP2.2版本 查询机构的所有交易城市
	 * @param orgId 机构ID
	 */
	@RequestMapping("getCityByOrgId")
	public String getCityByOrgId(Integer orgId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> cityList = orgService.searchCityByOrgId(orgId);
			if (cityList != null && cityList.size() > 0) {
				result.put("cityList", cityList);
				result.put("state","success");
				result.put("msg","操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg","操作失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存用户的交易城市
	 * @param id   机构orgId
	 * @param citys 要保存的城市主键数组
	 */
	@RequestMapping("save/setCity")
	public String setCity(Integer orgId,Integer[] citys, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (orgId != null && citys.length > 0) {
				orgCityService.updateByOrgId(citys, orgId);
				result.put("state","success");
				result.put("msg","操作成功");
			} else {
				result.put("msg", "操作失败");
			}
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","查询用户信息操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据认证主键获取机构的 相关 认证信息（基本信息、承诺函）
	 * @param orgId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("get")
	public String get(Integer orgId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(orgId==null)throw new Exception();
			Map<String,Object> info = orgInfoService.getCurrentTypeInfoById(orgId);
			result.put("info", info);//查询之前时候认证过(基本信息)
			
			Map<String,Object> org_img = orgImageService.getByOrgId(orgId);
			result.put("img", org_img);//查询之前时候认证过(承诺函)
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * APP2.2 检查认证权限（贴现、报价3次）兼容APP2.1
	 * @author WKX
	 * @param orgId
	 * @param memberId
	 * @param fun 贴现1、报价2
	 * @param model
	 * @since 2016年5月17日 下午6:12:39
	 */
	@RequestMapping("get/auth")
	public String getAuth(Integer orgId,Integer memberId,Integer fun,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String url = "customer.html";
		boolean flag = false;
		try {
			if(orgId==null && memberId==null)throw new Exception();
			if(memberId==null){
				Org org = orgService.getById(orgId);
				if(org!=null)memberId = org.getMemberId();
			}
			
			Map<String,Object> info = null;
			if(1==fun){//贴现
				info = orgInfoService.getByMemberIdAndType(memberId, 0);
			}else if(2==fun){//报价2
				info = orgInfoService.getByMemberIdAndType(memberId, 1);
			}
			if(info!=null){
				Object state = info.get("state");
				if(state!=null && !"2".equals(state.toString())){//说明是：未认证、待审核、审核不通过
					flag = true;
				}
				if(!flag && fun==2){//认证信息已经通过
					Map<String,Object> org_img = orgImageService.getByOrgId(orgId);
					if(org_img==null){
						flag = true;
					}
				}
			}else{
				flag = true;
			}
			if(flag){
				if(1==fun){//贴现
					List<Discountrecord> list = discountrecordService.getByMemberId(memberId);
					if(list!=null && list.size()>=3){
						url = "customer.html";
						throw new Exception();
					}
				}else if(2==fun){//报价
					List<Map<String,Object>> list = priceService.getByOrgId(orgId);
					if(list!=null && list.size()>=3){
						url = "customer.html";
						throw new Exception();
					}
				}
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
			result.put("url", url);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查看当前已经认证的（认证失败和未认证跳转填写页面，否则条提示页面）
	 * @author WKX
	 * @param orgId
	 * @param memberId
	 * @param fun 贴现1、报价2
	 * @param model
	 * @since 2016年5月17日 下午6:25:08
	 */
	@RequestMapping("get/authed")
	public String getAuthEd(Integer orgId,Integer memberId,Integer fun,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> info = null;
		String url = "customer.html";
		try {
			if(orgId==null && memberId==null)throw new Exception();
			if(memberId==null){
				Org org = orgService.getById(orgId);
				if(org!=null)memberId = org.getMemberId();
			}
			
			if(1==fun){//贴现
				url = "customer_bns.html";
				info = orgInfoService.getByMemberIdAndType(memberId, 0);
			}else if(2==fun){//报价
				url = "customer_org2.html";
				info = orgInfoService.getByMemberIdAndType(memberId, 1);
			}
			if(info==null)throw new Exception();
			
			Object state = info.get("state");
			if(state==null)throw new Exception();
			
			if(1==fun){//贴现（角色：企业）
				if("1".equals(state.toString())){//审核中
					url = "customer_wait.html";
				}else if("3".equals(state.toString())){//未通过
					url = "customer_bns.html";
				}
			}else if(2==fun){//报价（角色：机构）
				if("1".equals(state.toString())){//审核中
					url = "customer_wait.html";
				}else if("3".equals(state.toString())){//未通过
					url = "customer_org2.html";
				}
			}
			result.put("response", "success");
			result.put("url", url);
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("url", url);
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 发送承诺函
	 * @author WKX
	 * @param email
	 * @param model
	 * @param request
	 * @since 2016年4月11日 下午5:24:44
	 */
	@RequestMapping("sendmail")
	public String sendMail(String email,Model model,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<String> targetPerson = new ArrayList<String>();
			targetPerson.add(email);
			Map<String, Object> map = new HashMap<String, Object>();
			String template = "/emailtemplate/cnhEmail.ftl";
			String html = EmailUtil.getHtml(configuration,template,map);
			
			String path = request.getSession().getServletContext().getRealPath(File.separator + "download") + File.separator + "cnh.pdf";
			EmailUtil.sendEmail(targetPerson,"《报价方合作银行承诺书》", html,path);
			
			result.put("response", "success");
			result.put("msg", "发送成功，请注意查收");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "发送失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存认证（选择的类型）APP2.2
	 * @author WKX
	 * @param org(type角色：企业0、机构1)
	 * @param model
	 * @since 2016年5月12日 下午4:40:38
	 */
	@RequestMapping("save2")
	public String save2(Org org,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> info = null;
		try {
			if(org==null || org.getMemberId()==null || org.getType()==null)throw new Exception();
			
			if(0==org.getType()){//企业认证
				info = orgInfoService.getByMemberIdAndType(org.getMemberId(), 0);
			}else if(1==org.getType()){//机构认证
				info = orgInfoService.getByMemberIdAndType(org.getMemberId(), 1);
				Org temp = orgService.getByMemberId(org.getMemberId());//用于返回页面(主键)
				if(temp==null){
					org.setCreateTime(new Date());
					orgService.saveModel(org);
				}else{
					org.setId(temp.getId());//传到前台切换角色用
				}
			}
			result.put("info", info);//查询之前时候认证过
			String beatCode = variablesService.getByCode("BEATCODE", "on");
			if("on".equals(beatCode)){
				Map<String,Object> bc = beatCodeService.getByMemberId(org.getMemberId());
				result.put("bc", bc);//如果之前输入过公测码就不用输入了
			}else{
				result.put("bc", "off");//这里只要有值作为标示就可以略过公测码
			}
			result.put("data", org);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 初始化省市（认证页面用）
	 * @param model
	 * @throws Exception
	 * @author WKX
	 */
	@RequestMapping("init/city")
	public String initCity(Integer orgCityId,Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<Region> p = regionService.getByType("P");//省
		List<Region> c = regionService.getByType("C");//市
		result.put("p", p);
		result.put("c", c);
		
		if(orgCityId!=null){
			Map<String, Object> city = orgService.getOrgCityInfoById(orgCityId);
			result.put("city", city);
		}
		
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构主键获取交易城市
	 * @param orgId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("get/orgcity")
	public String getOrgCityByOrgId(Integer orgId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = orgService.searchCityByOrgId(orgId);
			result.put("data", list);
			result.put("response","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("response","failed");
			result.put("msg","网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据交易城市主键删除对象
	 * @param id
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("del/orgcity")
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
	 * @author WKX
	 * @param orgCity
	 * @param model
	 * @since 2016年5月16日 下午1:42:22
	 *        新增加判断城市是否已经存在
	 */
	@RequestMapping("save/orgcity")
	public String saveOrgCity(OrgCity orgCity, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
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
	 * 根据省市名称，获取级联的省市id
	 * @author WKX
	 * @param p
	 * @param c
	 * @param model
	 * @throws Exception
	 * @since 2016年6月17日 下午5:03:15
	 */
	@RequestMapping("getpc")
	public String getpc(String p,String c,Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		Region p_ = null;
		Region c_ = null;
		List<Region> pList = regionService.getByNameAndType(p, "P");
		List<Region> cList = regionService.getByNameAndType(c, "C");
		if(pList!=null & pList.size()>0){
			p_ = pList.get(0);
			if(cList!=null && cList.size()>0){
				c_ = cList.get(0);
				if(p_!=null && c_ !=null && p_.getId()==c_.getParentId()){
					result.put("p", p_.getId());
					result.put("c", c_.getId());
				}
			}
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		System.err.println(result);
		return "ajax";
	}
	
	/**
	 * 分页获取机构端所有评价
	 * @author KHC
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年8月25日 上午10:08:28
	 */
	@RequestMapping("comments/list")
	public String list(Integer orgId,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			PageResults<Map<String, Object>> page = commentsService.getPageListByOrgId(orgId, pageIndex, pageSize);
			result.put("data", page.getResults());
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
	 * 统计机构端评分
	 * @author KHC
	 * @param orgId
	 * @param model
	 * @since 2016年8月25日 上午10:31:59
	 */
	@RequestMapping("comments/count")
	public String getCount(Integer orgId,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(orgId==null)throw new Exception("数据异常");
			Map<String, Object> counts = commentsService.getAllByOrgId(orgId);
			result.put("data1", counts);
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
	 * @author MH 
	 *        根据城市Id,orgId,判断城市是否可以修改或者删除（查看某人今天某个城市是否报价）
	 * @param orgId  认证的org表的id
	 * @param cityId 城市id
	 * @param model
	 * @return
	 */
	@RequestMapping("judge/updateCity")
	public String judgeUpdateCity(Integer orgId, Integer cityId,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		String day = DateUtil.formart(new Date(),DateUtil.FORMART3);
		map.put("orgId", orgId);
		map.put("cityId", cityId);
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
	 * 判断是否已经认证
	 * @author MH
	 * @param orgId  Org表主键
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("attestation")
	public String judgeAttestation(Integer orgId,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Org org = orgService.getById(orgId);
		if(org != null){
			if(org.getState() == 2){
				result.put("response", "success");
				result.put("msg", "获取成功");
			}else{
				result.put("response", "failed");
				result.put("msg", "获取失败");
			}
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}