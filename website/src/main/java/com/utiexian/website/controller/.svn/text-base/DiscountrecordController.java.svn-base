package com.utiexian.website.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Address;
import com.ry.core.entity.Comments;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.AddressForm;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.AddressService;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgImageService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.SalepriceService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.JPushUtil;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;
/**
 * 银票贴现
 * @author MH
 *
 */
@Controller
public class DiscountrecordController {
	
	private static Logger logger = Logger.getLogger(DiscountrecordController.class);
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	
	@Resource
	OrgService orgService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;	
	
	@Resource
	DiscountrecordPlService discountrecordPlService;	
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	AddressService addressService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	SalepriceService salepriceService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	CommentsService commentsService;
	
	@Resource
	OrgImageService orgImageService;
	
	@Resource
	PriceService priceService;
	
	/**
	 *  免责声明的跳转
	 *  @author MH
	 */
	@RequestMapping("/receivable")
	public String receivable(){
		return "agreements/receivable";
	}
	/**
	 *  票据应收款转让服务合同的跳转
	 *  @author MH
	 */
	@RequestMapping("/statement")
	public String statement(){
		return "agreements/statement";
	}
	/**
	 *  单位授权书/企业授权委托书的跳转
	 *  @author MH
	 */
	@RequestMapping("/authorization")
	public String authorization(Integer type,Integer cibId,Model modle){
		modle.addAttribute("type", type);
		modle.addAttribute("cibId", cibId);
		return "agreements/authorization";
	}
	
	/**
	 * 根据用户主键获取未评论订单
	 * @author ZY
	 * @param request
	 * @param model
	 * 2016年11月2日下午1:22:05
	 */
	@RequestMapping("/discountrecord/uncomments")
	public String getUnCommentsByMemberId(HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			if(memberId!=null){
				for(int i=0;i<3;i++){
					list=commentsService.getUnCommentsByMemberAndtype(memberId,i);
					for(Map<String,Object> m :list){
					m.put("type", i);
					}
					list1.addAll(list);
				}
			}
			result.put("data", list1);
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 列表中角标的加载
	 * @author MH
	 * @param type sp,yp,pl
	 * @param request
	 * @param model
	 */
	@RequestMapping("discountrecord/bns/badge")
	public String badge(String type,Model model,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		DiscountrecordForm form = new DiscountrecordForm();
		DiscountrecordSpForm form1 = new DiscountrecordSpForm();
		DiscountrecordPlForm form2 = new DiscountrecordPlForm();
		if(type == null ) return null;
		Member member=(Member) request.getSession().getAttribute("member");
		Integer memberId=member.getId();
		
		if(type.equals("yp")){
			form.setMemberid(memberId);
			form.setOrderflag(1);
			form.setDepositState(0);
			PageResults<Map<String,Object>> page = discountrecordService.getPageList(0, 10, form);
			result.put("list_4",page.getTotalCount());
			form.setOrderflag(null);
			form.setDepositState(null);
			form.setState(new Integer[]{1,4});
			PageResults<Map<String,Object>> page1 = discountrecordService.getPcPageList(0, 10, form);
			result.put("list_",page1.getTotalCount());
			form.setOrderflag(3);
			form.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordService.getPageList(0, 10, form);
			result.put("list_2",page2.getTotalCount());
			result.put("response","success");
		}else if(type.equals("sp")){
			form1.setMemberId(memberId);
			form1.setOrderflag(1);
			PageResults<Map<String,Object>> page = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_4",page.getTotalCount());
			form1.setOrderflag(2);
			PageResults<Map<String,Object>> page1 = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_",page1.getTotalCount());
			form1.setOrderflag(3);
			form1.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_2",page2.getTotalCount());
			result.put("response","success");
		}else if(type.equals("pl")){
			form2.setMemberId(memberId);
			form2.setOrderflag(1);
			PageResults<Map<String,Object>> page = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_4",page.getTotalCount());
			form2.setOrderflag(2);
			PageResults<Map<String,Object>> page1 = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_",page1.getTotalCount());
			form2.setOrderflag(3);
			form2.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_2",page2.getTotalCount());
			result.put("response","success");
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取订单详情（评论显示）
	 * @param id
	 * @param type APP2.3根据Id和Type获取订单详情（评论显示）app2.3新增type，3个类型的公用一个评论页面，根据type区分
	 * @param model
	 * @author ZY
	 * @EDIT  2016年11月2日 上午10:45:08
	 */
	@RequestMapping("/discountrecord/comments/show")
	public String getInfoAndOrderById(Integer id,String type,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(org.apache.commons.lang.StringUtils.isBlank(type) || type.equals("0")){
				map = discountrecordService.getInfoAndOrderById(id);
			}
			else if(type.equals("1")){
				map = discountrecordSpService.getInfoAndOrderById(id);
			}
			else if(type.equals("2")){
				map = discountrecordPlService.getInfoAndOrderById(id);
			}
			if(map!=null && map.get("org_id")!=null){
				Object orgId = map.get("org_id");
				Map<String,Object> orgInfo = orgInfoService.getByOrgId(Integer.valueOf(orgId.toString()), 1);//机构
				
				if(orgInfo!=null && orgInfo.get("company")!=null){
					map.put("company", orgInfo.get("company"));
				}else if(orgInfo!=null && orgInfo.get("member_id")!=null){
					Object mid = orgInfo.get("member_id");
					Member m = memberService.getById(Integer.valueOf(mid.toString()));
					if(m!=null && org.apache.commons.lang.StringUtils.isNotBlank(m.getUsername())){
						map.put("company", m.getUsername());
					}else{
						map.put("company", m.getMobile());
					}
				}
			}
			result.put("data", map);
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 评价（保存）
	 * @param comments
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("/discountrecord/comments/save")
	public String saveComment(Comments comments,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(comments==null || comments.getDcrdId()==null)throw new Exception();
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
	
	/**
	 * 贴现评价判断进入哪个页面（贴现、评价、认证）
	 * @author ZY
	 * @param request
	 * @throws Exception
	 * 2016年12月12日上午10:13:00
	 */
	@RequestMapping("/discountrecord/assess")
	public String discountyp(HttpServletRequest request) throws Exception{
		Member member=(Member) request.getSession().getAttribute("member");
		Integer memberId=member.getId();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		if(memberId!=null){
			for(int i=0;i<3;i++){
				list=commentsService.getUnCommentsByMemberAndtype(memberId,i);
				for(Map<String,Object> m :list){
				m.put("type", i);
				}
				list1.addAll(list);
			}
		}
		if(list1!=null &&list1.size()>0){
			return "discountrecord/assess";
		}else{
			boolean flag = false;Integer amount=0;
			Map<String, Object> info = null;
			info = orgInfoService.getByMemberIdAndType(memberId, 0);// 贴现
			if (info != null) {
				Object state = info.get("state");
				if (state != null && !"2".equals(state.toString())) {// 说明是：未认证、待审核、审核不通过
					flag = true;
				} else if(state !=null && "2".equals(state.toString())) {
                    return "discountrecord/discount_yp1";
				}
			} else {
				flag = true;
			}
			if (flag) {
				// 贴现(3次贴现)
				DiscountrecordForm disf = new DiscountrecordForm();
				disf.setMemberid(memberId);
				List<Discountrecord> list3 = discountrecordService.getList(disf);
				if (list != null) {
					amount += list3.size();
				}
				DiscountrecordPl disp = new DiscountrecordPl();
				disp.setMemberId(memberId);
				List<DiscountrecordPl> list4 = discountrecordPlService.getByDiscountrecordPl(disp);
				if (list != null) {
					amount += list4.size();
				}
				List<DiscountrecordSp> list5 = discountrecordSpService.getByMemberId(memberId);
				if (list != null) {
					amount += list5.size();
				}
			}
			if (amount < 3) {
				return "discountrecord/discount_yp1";
			} else {
				return "discountrecord/renzheng";
			}
		}
		
	}
	
	/**
	 * 票据贴现跳转
	 * 注：登录拦截器参照web.xml
	 * @author MH
	 * @param request
	 * @param ym 判断跳转类型
	 */
	@RequestMapping("/discountrecord/discount")
	public String discount(HttpServletRequest request,String ym){
		if(ym==null){
			ym="yp";
		} 
		if(ym.equals("yp")){
			return "discountrecord/discount_yp1";
		}else if(ym.equals("sp")){
			return "discountrecord/discount_sp1";
		}else if(ym.equals("pl")){
			return "discountrecord/discount_pl1";
		}
		return "discountrecord/discount_yp1";
	}
	
	
	/**
	 * @author MH
	 * 确认订单的查询所有贴现地址
	 * @param memberId 用户Id
	 * @param model
	 * @return
	 */
	@RequestMapping("discountrecord/addresslist")
	public String list(String memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Integer pageIndex = 0;
		Integer pageSize = 10;
		AddressForm form = new AddressForm();
		form.setMemberId(Integer.valueOf(memberId));
		PageResults<Address> page = addressService.getPageList(pageIndex, pageSize, form);
		result.put("data",page.getResults());
		result.put("num",page.getResults().size());
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 银票贴现生成订单
	 * @param request 
	 * @author MH
	 * @throws Exception
	 */
	@RequestMapping("/discountrecord/tiexian")
	public String tieXian(HttpServletRequest request) throws Exception{
		Discountrecord discountrecord = new Discountrecord ();
		Integer type1 = Integer.valueOf(request.getParameter("type1"));
		discountrecord.setType1(type1);
		
		if(type1!=null && type1==2){
			Integer acceptTime =  Integer.valueOf(request.getParameter("acceptTime"));
			discountrecord.setAcceptTime(acceptTime);
			/*Integer tradeModel =  Integer.valueOf(request.getParameter("tradeModel"));
			discountrecord.setTradeModel(tradeModel);*/
		}
		
		discountrecord.setType2(Integer.valueOf(request.getParameter("type2")));
		String bank = request.getParameter("bank");
		discountrecord.setBank(bank);
		String allmoney = request.getParameter("allmoney");
		discountrecord.setAllmoney(Float.parseFloat(allmoney));
		String endorse = request.getParameter("endorse");
		discountrecord.setEndorse(Integer.valueOf(endorse));
		Integer  needTodoor = Integer.valueOf(request.getParameter("needTodoor"));
		discountrecord.setNeedTodoor(needTodoor);
		String memberother = request.getParameter("memberother");
		discountrecord.setMemberother(memberother);
		Integer hasTicket = Integer.valueOf(request.getParameter("hasTicket"));
		discountrecord.setHasTicket(hasTicket);
		String a = request.getParameter("flawTicket");
		Integer flawTicket = Integer.valueOf(a);
		discountrecord.setFlawTicket(flawTicket);
		
//		String deposit =  request.getParameter("deposit");
//		discountrecord.setDeposit(Float.parseFloat(deposit));
		
		String salepriceid = request.getParameter("salepriceid");
		discountrecord.setSalepriceid(Integer.valueOf(salepriceid));
		
		String start = request.getParameter("begintime");
		String end = request.getParameter("endtime");
		
		Date begintime = new SimpleDateFormat("yyyy-MM-dd").parse(start);
		Date endtime = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		
		discountrecord.setBegintime(begintime);
		discountrecord.setEndtime(endtime);
		
		discountrecord.setOrdertime(new Date());
		discountrecord.setOrderflag(Constant.ORDER_DAIQUEREN);
		discountrecord.setSource("PC");
		discountrecord.setHandleState(0);//生成订单时，处理状态默认待处理
		Member member =(Member) request.getSession().getAttribute("member");
		Integer  memberId = member.getId();
		discountrecord.setMemberid(memberId);
		String randomStr = String.valueOf(Math.random()*1000).split("\\.")[0];
		if(randomStr.length()==0){
			randomStr = "000";
		}
		if(randomStr.length()==1){
			randomStr = randomStr+"00";
		}
		if(randomStr.length()==2){
			randomStr = randomStr+"0";
		}
		
		String picpath = request.getParameter("picpath");
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
						FileUtil.moveFile(tString, pString);
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
		discountrecord.setPicpath(picpath);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNumber = simpleDateFormat.format(new Date())+randomStr;
		discountrecord.setOrdernumber(orderNumber);
		discountrecordService.addDiscountrecord(discountrecord);
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/discountrecord/tiexian2?no="+orderNumber);
	}
	
	/**
	 * @author MH
	 * @return
	 * 			不支付，修改 保证金 跳转到3 页面
	 */
	@RequestMapping("/discountrecord/nopay")
	public String nopay(String ordernumber){
		Discountrecord discountrecord = discountrecordService.getByOrdernumber(ordernumber);
		discountrecord.setDeposit(0F);
		discountrecord.setDepositState(1);
		return "discountrecord/discount_yp3";
	}
	
	/**
	 *  添加完成后页面的选择贴现地址的展示
	 * @param request 
	 * @param no 订单号
	 * @param jxts 计息天数
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/discountrecord/tiexian2")
	public String tiexian2(HttpServletRequest request,String no) throws ParseException{
		Discountrecord discountrecord=discountrecordService.getByOrdernumber(no);
		
		Integer num = DateUtil.daysBetween(discountrecord.getBegintime(),discountrecord.getEndtime());
		int tzts = 0;
		tzts = discountrecordService.getTzts(discountrecord.getType1(),discountrecord.getEndtime());//调整天数（根据票据类型）
		Integer jxts = tzts+num;
		
		String start = DateUtil.formart(discountrecord.getBegintime(),DateUtil.FORMART3);
		String end = DateUtil.formart(discountrecord.getEndtime(),DateUtil.FORMART3);
		request.setAttribute("discountrecord",discountrecord);
		request.setAttribute("start",start);
		request.setAttribute("end",end);
		request.setAttribute("jxts", jxts);
		return "discountrecord/discount_yp2";
	}
	
	/**
	 * 根据企业主键获取详情（含机构报价）
	 * @param id
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountrecord/get")
	public String getInfo(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			Map<String,Object> info = discountrecordService.updateReadTaskAndGetInfoAndOrderById(id);
			if(info!=null){
				Object ordernumber = info.get("ordernumber");
				if(ordernumber!=null){//企业订单编号（查询使用的红包）
					HongbaoDetail hb = hongbaoService.getByOrdernumber(ordernumber.toString());
					if(hb!=null)info.put("hb",hb.getPrice());
				}
				Object orgId = info.get("org_id");
				if(orgId!=null){//获取机构相关信息
					Map<String,Object> org = orgService.getInfoById(Integer.valueOf(orgId.toString()));
					if(org!=null){
						info.put("company", org.get("company"));
						Object memberId = org.get("member_id");
						if(memberId!=null){
							Member member = memberService.getById(Integer.valueOf(memberId.toString()));
							info.put("mobile", member.getMobile());
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
	 * 下单保存后保存（贴现地址信息）
	 * @author MH
	 * @param membername 用户名
	 * @param membersex 性别
	 * @param phone 联系方式
	 * @param address 详细地址
	 * @param place 交易城市
	 * @param cityId 城市主键
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param payWay 支付方式
	 * @param ordernumber 订单号
	 * @param model
	 * @since 
	 */
	
	/*选择机构*/
	@RequestMapping("discountrecord/choseJG")
	public String choseJG(){
		return "discountrecord/mechanism";
	}
	/*贴现成功*/
	@RequestMapping("discountrecord/discountSuccess")
	public String discountSuccess(){
		return "discountrecord/discountSuccess";
	}
	
	@RequestMapping("/discountrecord/qrdd")
	public String savePayRecordBefore(String ordernumber,String membername,String membersex,String payWay,
			String address,String place,String cityId,String longitude,String latitude,String phone,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Discountrecord discountrecord = discountrecordService.getByOrdernumber(ordernumber);
			if(discountrecord==null)throw new Exception("数据异常");
			Map<String, Object> pay = toPay(discountrecord.getDeposit(), Integer.parseInt(payWay));//预支付（APP2.2）
			if(discountrecord.getBnsNo() == null || discountrecord.getBnsNo() == ""){
				discountrecord.setBnsNo(pay.get("bnsNo").toString());
			}
			discountrecord.setMembermobile(phone);
			discountrecord.setPayWay(Integer.valueOf(payWay));
			discountrecord.setMembername(membername);
			discountrecord.setMembersex(Integer.valueOf(membersex));
			discountrecord.setAddress(address);
			discountrecord.setPlace(place);
			discountrecord.setCityId(Integer.valueOf(cityId));
			discountrecord.setLongitude(Float.parseFloat(longitude));
			discountrecord.setLatitude(Float.parseFloat(latitude));
			String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
			if("off".equals(switch_)){
				discountrecord.setDeposit(0F);
				discountrecord.setDepositState(1); //保证金为0，改为已经支付
			}
			if(discountrecord.getDeposit() == 0){ //快钱，app下单保证金为0
				discountrecord.setDepositState(1); //保证金为0，改为已经支付
			}
			discountrecordService.updateModel(discountrecord);
			result.put("discountrecord", discountrecord);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "支付失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 银票贴现保证金
	 * @author MH
	 * @param memberId 用户Id
	 * @param allmoney 保证金额
	 * @param model
	 * @since 2016年5月17日 下午4:04:44
	 */
	@RequestMapping("/discountrecord/deposit")
	public String initDeposit(HttpServletRequest request,Integer memberId,Float allmoney,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("data", getDeposit(memberId, allmoney));
			result.put("response", "success");
			result.put("msg", "初始化成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "初始化异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	@RequestMapping("/discountrecord/jiejia")
	public String jiejia(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Notice notice = tiexianNoticeService.getNoticeByNowTime();//获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
		//如果在节假日定会额度受限,所以提醒非工作日的文字
		if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
			result.put("response", "success");
			result.put("msg", notice.getAlert());
		}else{
			notice = tiexianNoticeService.findByCode("ERROR");
			result.put("response", "fail");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取保证金金额（根据用户和贴现金额）
	 * @param memberId 用户主键
	 * @param allmoney 总金额
	 * @throws ParseException
	 * @author WKX
	 */
	private Integer getDeposit(Integer memberId,Float allmoney) throws ParseException{
		Integer money = 0;
		//step1 大小票（大票：20；小票：10）
		if(allmoney!=null && allmoney>500){
			money += 20;
		}else{
			money += 10;
		}
		//step2 认证过：0；未认证：20
		Map<String,Object> info = orgInfoService.getByMemberIdAndType(memberId,0);
		if(info!=null && info.get("state")!=null){
			if(2!=Integer.valueOf(info.get("state").toString())){
				money += 20;
			}
		}else{
			money += 20;
		}
		//step3 上月有已完成订单：10；上月无已完成订单：20
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		String end = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date e = DateUtil.parser(end+" 23:59:59", DateUtil.FORMART);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String start = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date s = DateUtil.parser(start+" 00:00:00", DateUtil.FORMART);
		Long amount = discountrecordService.countbyOrdertime(s, e, 3);//已完成
		if(amount!=null && amount>0){
			money += 10;
		}else{
			money += 20;
		}
		//step4 上月有拒绝订单：30；上月无拒绝订单：0
		Long bmount = discountrecordService.countbyOrdertime(s, e, 0);//拒绝
		if(bmount!=null && bmount>0){
			money += 30;
		}
		return money;
	}
	
	/**
	 * PC 银票  计算计息天数
	 * @author MH
	 * @param type6 票据类型（1纸票、2电票）
	 * @param startDate 贴现日期
	 * @param endDate 到期日期
	 * @throws Exception
	 * @since 2016年6月12日 下午6:32:07
	 */
	@RequestMapping("discountrecord/jixidate")
	public String jixidate(Integer type6,String startDate,String endDate,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Date s = DateUtil.parser(startDate, DateUtil.FORMART3);
		Date e = DateUtil.parser(endDate, DateUtil.FORMART3);
		Integer num = DateUtil.daysBetween(s,e);
		int tzts = 0;
		if(type6!=null)tzts = discountrecordService.getTzts(type6,e);//调整天数（根据票据类型）
		
		result.put("tzts", tzts);
		result.put("jxts", tzts+num);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 快钱付款
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param deposit 付款金额
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/discountrecord/bill99pay")
	public String bill99pay(String deposit,String out_trade_no,HttpServletRequest request,Model model) throws Exception{
		String path = Constant.properties.getProperty("alipayUrl");
		Discountrecord discountrecord = discountrecordService.getModelByBnsNoDiscount(out_trade_no);
		Integer deposit1 = (Float.valueOf(discountrecord.getDeposit())).intValue()*100;
		//指定付款人
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("payerId", discountrecord.getMemberid().toString());//付款人标识
		paras.put("orderId", out_trade_no);//商户订单号
		paras.put("orderAmount", deposit1.toString());//订单金额
//		paras.put("orderAmount", "100");//订单金额
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//数量
		paras.put("productName", "银票贴现保证金");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path+"/discountrecordpay/pay/bill99cb");//回调地址
		paras.put("pageUrl", path+"/discountrecordpay/pay/cb/page");//回调页面
		
		model.addAttribute("retValue",Bill99Util.buildRequestPay(paras));
		return "ajax";
	}
	
	/**
	 * 快钱支付回调
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/discountrecordpay/pay/bill99cb")
	public @ResponseBody  String payBill99Cb(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model){
		String path = Constant.properties.getProperty("alipayUrl");
//		Discountrecord discountrecord = discountrecordService.getByBnsNoDiscount(orderId);
		Discountrecord discountrecord = discountrecordService.getModelByBnsNoDiscount(orderId);
		if(discountrecord != null){
			String des;
			if( discountrecord.getDepositState() == 0){
				des="未支付";
			}else{
				des="已支付";
				return null;
			}
			if("10".equals(payResult)){
				discountrecord.setDepositState(1);//初始状态（已支付）
				try {
					discountrecord.setJyh(dealId);
					discountrecord.setCard(bindCard);
					des="已支付";
				} catch (Exception e) {
					e.printStackTrace();
				}
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, "已支付", "亲，您的订单保证金已支付成功，我们将立马处理您的订单！", "SMS_10621004");
			}else {
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			paidanService.updateAndPayRecord(discountrecord, 0, des);
			payLog.info("下单支付：更新支付状态完成savePayRecord...用户主键:"+discountrecord.getMemberid()+"订单主键："+discountrecord.getId());
		}
		return "<result>1</result><redirecturl>"+ path +"/discountrecordpay/pay/cb/page</redirecturl>";
	}
	
	/**
	 * 快钱回调页面
	 * @author MH
	 * @throws Exception
	 * @since 2017年2月24日 上午10:39:43
	 */
	@RequestMapping("/discountrecordpay/pay/cb/page")
	public String cbPayPage(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/discountrecord/redirect1");
	}
	
	/**
	 * 快钱订单查询
	 * @author MH
	 * @param bnsno 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月2日 下午2:01:12
	 */
	@RequestMapping("/discountrecord/bill99query")
	public String query(String bnsno,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();
		paras.put("orderId", bnsno);//商户订单号
		model.addAttribute("retValue",JacksonUtil.objToJson(Bill99Util.query(paras)));
		return "ajax";
	}
	
	/**
	 * 支付宝付款
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param deposit 付款金额
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("discountrecord/pay")
	public String list(String deposit,String out_trade_no,HttpServletRequest request,Model model) throws Exception{
		String path = Constant.properties.getProperty("alipayUrl");
		
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
		paras.put("return_url", path+"/discountrecordpay/pay/callback");//此链接需要配置线上地址（并外网可访问）
		paras.put("notify_url", path+"/discountrecordpay/pay/cb");//此链接需要配置线上地址（并外网可访问）
		paras.put("out_trade_no", out_trade_no);
		paras.put("subject", "银票贴现支付保证金");//商品名称
		paras.put("total_fee",deposit);
		paras.put("body", "银票贴现支付保证金");//商品描述
		model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
		return "ajax";
	}
	
	/**
	 * 支付回调
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("discountrecordpay/pay/callback")
	public String payCallback(String out_trade_no,String is_success,String trade_no,String buyer_email,String deposit){
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/discountrecord/redirect1");
	}
	
	/**
	 * 支付回调（重定向订单列表）
	 * @author MH
	 * @param request
	 * @since 
	 */
	@RequestMapping("discountrecord/redirect1")
	public String redirectCallback(){
		return "discountrecord/discount_yp3";
	}
	
	/**
	 * 支付回调
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/discountrecordpay/pay/cb")
	public String payCb(String out_trade_no,String trade_status,String trade_no,String buyer_email){
		Map<String, Object> result = new HashMap<String, Object>();
		Discountrecord discountrecord = discountrecordService.getByBnsNoDiscount(out_trade_no);
		if(discountrecord != null){
			String des;
			if( discountrecord.getDepositState() == 0){
				des="未支付";
			}else{
				des="已支付";
				return null;
			}
			payLog.info("下单支付：进入保存支付状态方法savePayRecord...订单主键："+discountrecord.getId()+"支付状态："+des);
			if(trade_status.equals("TRADE_SUCCESS")){
				discountrecord.setDepositState(1);//初始状态（已支付）
				try {
					discountrecord.setJyh(trade_no);
					discountrecord.setCard(buyer_email);
					des="已支付";
				} catch (Exception e) {
					e.printStackTrace();
				}
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, "已支付", "亲，您的订单保证金已支付成功，我们将立马处理您的订单！", "SMS_10621004");
			}else {
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			paidanService.updateAndPayRecord(discountrecord, 0, des);
			result.put("response", "success");
			result.put("msg", "操作成功");
			payLog.info("下单支付：更新支付状态完成savePayRecord...用户主键:"+discountrecord.getMemberid()+"订单主键："+discountrecord.getId());
		}else{
			result.put("response", "failed");
			result.put("msg", "网络异常");
		}
		return "ajax";
	}
	
	/**
	 * 推送消息、保存消息、发送短信
	 * @author MH
	 * @param memberId
	 * @param fkId
	 * @param type
	 * @param alert
	 * @param des
	 * @param smsCode
	 * @since 2016年6月15日 下午9:22:08
	 */
	private void pushAndSend(Integer memberId,Integer fkId,Type type,String alert,String des,String smsCode){
		try {
			Member member = memberService.getById(memberId);
			if(member!=null){
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(member.getId());
				systeminfo.setType(type);
				systeminfo.setAlert(alert);
				systeminfo.setContent(des);
				systeminfo.setDiscountrecordId(fkId);//外键（对应类型）
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				Map<String,String> param = new HashMap<String, String>();
				SendMessagesUtil.sendMessage(member.getMobile(), smsCode, param);
				
				JPushUtil.doPushJob(member.getMobile(), type, des);
			}
		} catch (Exception e) {
			logger.error("订单流转消息发送异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 下单支付接口
	 * @author WKX
	 * @param deposit 保证金（元）
	 * @param payWay 付款方式（0支付宝A、1微信W、2银联U、3块钱K、4宝付B）
	 * @param model
	 * @since 2016年5月21日 下午7:13:07
	 */
	private Map<String, Object> toPay(Float deposit,Integer payWay){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String prefix = payWay == null ? "N" : payWay == 0 ? "A" : payWay == 1 ? "W" : payWay == 2 ? "U":payWay == 3 ? "K":payWay == 4 ? "B":"N";//N代表无支付方式的
			String bnsNo = discountrecordService.randomBnsNo(prefix);
			result.put("bnsNo", bnsNo);
			result.put("payWay", payWay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
