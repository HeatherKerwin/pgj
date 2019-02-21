package com.utiexian.website.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Member;
import com.ry.core.entity.Noticerecord;
import com.ry.core.form.AccountrecordForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.NoticerecordService;
import com.ry.core.util.Constant;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * @author KHC
 * 票据管理
 */
@Controller
public class ZhangbuController {
	
	private static final Logger logger = Logger.getLogger(ZhangbuController.class);
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	NoticerecordService noticerecordService;
	
	/**
	 * 票据管理（提醒）
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/main")
	public String main(Model model){
		return "bill_housekeeper/bill";
	}
	
	/**
	 * 票据管理（提醒：已持有）
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/hold")
	public String hold(Model model){
		return "bill_housekeeper/hold";
	}
	
	/**
	 * 票据管理（提醒：待入账）
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/in")
	public String in(Model model){
		return "bill_housekeeper/in";
	}
	
	/**
	 * 票据管理账簿(已出票)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/account/book/out")
	public String accountBookOut(Model model){
		return "bill_housekeeper/account_book_out";
	}
	
	/**
	 * 票据管理账簿(已持有)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/account/book/hold")
	public String accountBookHold(Model model){
		return "bill_housekeeper/account_book_hold";
	}
	
	/**
	 * 票据管理账簿(待入账)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/account/book/in")
	public String accountBookIn(Model model){
		return "bill_housekeeper/account_book_in";
	}
	
	/**
	 * 票据管理账簿(质押中)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/account/book/pledge")
	public String accountBookPledge(Model model){
		return "bill_housekeeper/account_book_pledge";
	}
	
	/**
	 * 票据管理(统计分析)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/statistical/analysis")
	public String statisticalAnalysis(Model model){
		return "bill_housekeeper/statistical";
	}
	
	/**
	 * 票据管理(货款合算)
	 * @author MH
	 * @param model
	 */
	@RequestMapping("zhangbu/draftrecord")
	public String draftrecord(Model model){
		return "bill_housekeeper/draftrecord";
	}
	
	/**
	 * 票据管理列表页
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 上午9:52:33
	 */
	@RequestMapping("zhangbu/index")
	public String index(Model model){
		return "zhangbu/list";
	}
	
	/**
	 * 票据管理新增页
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 上午9:53:00
	 */
	@RequestMapping("zhangbu/add")
	public String add(Model model){
		return "zhangbu/add";
	}
	
	/**
	 * 根据id获取对象
	 * @author KHC
	 * @param request
	 * @param id
	 * @since 2016年12月12日 上午9:53:17
	 */
	@RequestMapping("zhangbu/get")
	public String update(HttpServletRequest request,Integer id){
		request.setAttribute("id", id);
		return "zhangbu/update";
	}
	
	/**
	 * 查看报表
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 上午9:53:30
	 */
	@RequestMapping("zhangbu/report")
	public String report(Model model){
		return "zhangbu/report";
	}
	
	/**
	 * 获取修改的对象
	 * @author KHC
	 * @param model
	 * @param id
	 * @since 2016年12月12日 上午9:46:10
	 */
	@RequestMapping("zhangbu/toupdate")
	public String toupdate(Model model,Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Accountrecord acc = accountrecordService.getAccountrecord(id);
			Noticerecord noticerecord = noticerecordService.getByFkId(id);
			map.put("tiexiandate",DateUtil.formart(acc.getTiexiandate(), DateUtil.FORMART3));
			map.put("daoqidate",DateUtil.formart(acc.getDaoqidate(), DateUtil.FORMART3));
			if(noticerecord!=null && noticerecord.getNoticetime()!=null){
				map.put("noticetime",DateUtil.formart(noticerecord.getNoticetime(), DateUtil.FORMART3));
			}
			map.put("response", "success");
			map.put("data",acc);
			map.put("noticerecord",noticerecord);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 新增票据管理
	 * @author KHC
	 * @param form
	 * @param request
	 * @param model
	 * @since 2016年10月29日 下午3:05:56
	 */
	@RequestMapping("/zhangbu/addpiaoju")
	public String PiaoJu(AccountrecordForm form,String tixing,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Accountrecord acc = new Accountrecord();
		try {
			acc.setTiexiandate(DateUtil.parser(form.getStart(), DateUtil.FORMART3));
			acc.setDaoqidate(DateUtil.parser(form.getEnd(), DateUtil.FORMART3));
			if(form.getType().equals("1")){//已贴现时才存
				acc.setYuelilv(form.getYuelilv());
				acc.setNianlilv(form.getNianlilv());
				acc.setTen(form.getTen());
				acc.setTiexianlixi(form.getTiexianlixi());
				acc.setTiexianjine(form.getTiexianjine());
			}
			acc.setAllprice(form.getAllprice());
			acc.setType1(form.getType1());
			acc.setAccountdesc(form.getAccountdesc());
			acc.setMemberid(memberId);
			acc.setCreateTime(new Date());
			acc.setPublishtime(new Date());
			acc.setSource("PC");
			acc.setBelong(0);
			if(form.getShuxing()==1){
				acc.setPiaojushuxing("纸票");
			}else if(form.getShuxing()==2){
				acc.setPiaojushuxing("电票");
				acc.setAcceptTime(form.getAcceptTime());
			}
			acc.setIsTiexian(form.getType());
			acc.setTiexianType("手动");
			accountrecordService.saveAccountrecord(acc);
			if(StringUtils.isNotBlank(tixing)){
				Noticerecord n = noticerecordService.getByFkId(acc.getId());
				if(n == null){
					Noticerecord noticerecord = new Noticerecord();
					Integer s = (int) (acc.getAllprice()/1);
					noticerecord.setAllprice(s);
					noticerecord.setMemberid(acc.getMemberid());
					noticerecord.setExpiredate(acc.getDaoqidate());
					noticerecord.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
					noticerecord.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
					noticerecord.setFkId(acc.getId());
					noticerecordService.addNoticerecord(noticerecord);
				}else{
					n.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
					n.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
					noticerecordService.updateNoticerecord(n);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.info("新增数据错误:",e);
		}
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/zhangbu/index");
	}
	
	/**
	 * 修改票据管理
	 * @author KHC
	 * @param form
	 * @param request
	 * @param model
	 * @since 2016年10月29日 下午3:05:56
	 */
	@RequestMapping("/zhangbu/editpiaoju")
	public String editPiaoJu(AccountrecordForm form,String tixing,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Accountrecord acc = new Accountrecord();
		acc = accountrecordService.getAccountrecord(form.getId());
		try {
			acc.setTiexiandate(DateUtil.parser(form.getStart(), DateUtil.FORMART3));
			acc.setDaoqidate(DateUtil.parser(form.getEnd(), DateUtil.FORMART3));
			if(form.getType().equals("1")){//已贴现时才存
				acc.setYuelilv(form.getYuelilv());
				acc.setNianlilv(form.getNianlilv());
				acc.setTen(form.getTen());
				acc.setTiexianlixi(form.getTiexianlixi());
				acc.setTiexianjine(form.getTiexianjine());
			}
			acc.setAllprice(form.getAllprice());
			acc.setType1(form.getType1());
			acc.setAccountdesc(form.getAccountdesc());
			acc.setMemberid(memberId);
			acc.setCreateTime(new Date());
			acc.setPublishtime(new Date());
			acc.setSource("PC");
			acc.setBelong(0);
			if(form.getShuxing()==1){
				acc.setPiaojushuxing("纸票");
			}else if(form.getShuxing()==2){
				acc.setPiaojushuxing("电票");
				acc.setAcceptTime(form.getAcceptTime());
			}
			acc.setIsTiexian(form.getType());
			acc.setTiexianType("手动");
			accountrecordService.updateAccountrecord(acc);
			if(StringUtils.isNotBlank(tixing)){
				Noticerecord n = noticerecordService.getByFkId(acc.getId());
				if(n == null){
					Noticerecord noticerecord = new Noticerecord();
					Integer s = (int) (acc.getAllprice()/1);
					noticerecord.setAllprice(s);
					noticerecord.setMemberid(acc.getMemberid());
					noticerecord.setExpiredate(acc.getDaoqidate());
					noticerecord.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
					noticerecord.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
					noticerecord.setFkId(acc.getId());
					noticerecordService.addNoticerecord(noticerecord);
				}else{
					n.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
					n.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
					noticerecordService.updateNoticerecord(n);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.info("修改数据错误:",e);
		}
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/zhangbu/index");
	}
	
	/**
	 * 分页查询票据管理列表
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param model
	 * @since 2016年10月28日 下午2:52:10
	 */
	@RequestMapping("/zhangbu/search")
	public String list(AccountrecordForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer memberId = member.getId();
			form.setBelong(0);
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 15;
			PageResults<Map<String, Object>> result = accountrecordService.getPageList(form, pageIndex, pageSize, memberId);
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",result);
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "获取失败");
			logger.info("更新数据出错:",e);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 删除票据,修改状态
	 * @param id
	 * @date 2016年10月28日
	 * @author KHC
	 */
	@RequestMapping("/zhangbu/delete")
	public String updatePiaojuStatue(Integer id,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		Accountrecord accountrecord =  new Accountrecord();
		accountrecord = accountrecordService.getAccountrecord(id);
		accountrecord.setStatue("0");//修改状态
		try {
			accountrecordService.updateAccountrecord(accountrecord);
			map.put("response", "success");
			map.put("msg", "删除成功!");
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "删除失败!");
			logger.info("删除数据出错:",e);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 修改提醒时间
	 * @author KHC
	 * @param id
	 * @param tixing
	 * @param daoqidate
	 * @param allprice
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年11月1日 下午3:33:51
	 */
	@RequestMapping("/zhangbu/updatenotice")
	public String updateNotice(Integer id,String tixing,String daoqidate,String allprice,String accountdesc,HttpServletRequest request,Model model) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		if(StringUtils.isNotBlank(tixing)){
			Noticerecord n = noticerecordService.getByFkId(id);
			Accountrecord acc = accountrecordService.getAccountrecord(id);
			acc.setAccountdesc(accountdesc);
			if(n == null){
				Noticerecord noticerecord = new Noticerecord();
				Integer s = (int) (Double.parseDouble(allprice)/1);
				noticerecord.setAllprice(s);
				noticerecord.setMemberid(memberId);
				noticerecord.setExpiredate(DateUtil.parser(daoqidate,DateUtil.FORMART3));
				noticerecord.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
				noticerecord.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
				noticerecord.setFkId(id);
				noticerecordService.addNoticerecord(noticerecord);
				accountrecordService.updateAccountrecord(acc);
				map.put("response", "success");
			}else{
				n.setNoticetime(DateUtil.parser(tixing,DateUtil.FORMART3));
				n.setNoticeaddtime(DateUtil.dataTurntoInt(tixing,DateUtil.FORMART3));
				noticerecordService.updateNoticerecord(n);
				accountrecordService.updateAccountrecord(acc);
				map.put("response", "success");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 票据管理统计
	 * @param type 类型（1一个月、2三个月、3六个月、4一年）
	 * @param start
	 * @param end
	 * @param memberId
	 * @param model
	 * @author KHC
	 */
	@RequestMapping("/zhangbu/tongji")
	public String tongji(String type,String start,String end,Integer belong,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		if(belong==null)belong = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		if("1".equals(type)){
			c.add(Calendar.MONTH, -1);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("2".equals(type)){
			c.add(Calendar.MONTH, -3);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("3".equals(type)){
			c.add(Calendar.MONTH, -6);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("4".equals(type)){
			c.add(Calendar.MONTH, -12);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			result.put("start", fm.format(DateUtil.parser(start, DateUtil.FORMART3)));
			result.put("end", fm.format(DateUtil.parser(end, DateUtil.FORMART3)));
			
			SimpleDateFormat fm1 = new SimpleDateFormat("yyyy.MM.dd");
			result.put("start1", fm1.format(DateUtil.parser(start, DateUtil.FORMART3)));
			result.put("end1", fm1.format(DateUtil.parser(end, DateUtil.FORMART3)));
			
			List<Map<String,Object>> list = accountrecordService.getSumByMemberId(memberId,belong, start, end);
			if(list!=null && list.size()>0){
				Double tiexianlixi = 0D;
				Double allprice = 0D;
				for(Map<String,Object> map:list){
					if(map!=null && map.get("tiexianlixi")!=null){
						tiexianlixi += Double.valueOf(map.get("tiexianlixi").toString());
					}
					if(map!=null && map.get("allprice")!=null){
						allprice += Double.valueOf(map.get("allprice").toString());
					}
				}
				result.put("tiexianlixi", tiexianlixi);
				result.put("allprice", allprice);
			}
			result.put("response", "success");
			result.put("data", list);
			result.put("msg", "操作成功");
		} catch (Exception ex) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			logger.info("统计票据类型出错:",ex);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 导出excle
	 * @author KHC
	 * @param form
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年11月3日 下午4:21:57
	 */
	@RequestMapping("/zhangbu/excle")
	@ResponseBody
	public ModelAndView downExcle(AccountrecordForm form,HttpServletRequest request,ModelMap model)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> dataList = new ArrayList<String[]>();
		String[] headData = new String[]{"#","承兑行","总金额(万元)","贴现金额(万元)","月利率(‰)","年利率(%)","每十万扣(元)","到期日期","贴现类型","票据属性"};
		String[] endData = null;
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		form.setMemberid(memberId);
		form.setBelong(0);
		//根据条件获取票据管理列表
		List<Map<String, Object>> list = accountrecordService.getList(form, memberId);
		
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				String[] data = new String[headData.length];
				data[0] =  String.valueOf(i+1);
				data[1] = DataMatchUtil.getCDHByTypeFromNew(Integer.valueOf(list.get(i).get("type1").toString()));
				data[2] = list.get(i).get("allprice")==null?"":list.get(i).get("allprice").toString();
				data[3] = list.get(i).get("tiexianjine")==null?"":list.get(i).get("tiexianjine").toString();
				data[4] = list.get(i).get("yuelilv")==null?"":list.get(i).get("yuelilv").toString();
				data[5] = list.get(i).get("nianlilv")==null?"":list.get(i).get("nianlilv").toString();
				data[6] = list.get(i).get("ten")==null?"":list.get(i).get("ten").toString();
				data[7] = list.get(i).get("daoqidate")==null?"":DateUtil.formart((Date) list.get(i).get("daoqidate"), DateUtil.FORMART3);
				data[8] = "1".equals(list.get(i).get("istiexian").toString())?"已贴现":"未贴现";
				data[9] = list.get(i).get("piaojushuxing").toString();
				dataList.add(data);
			}
		}else{
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(new ViewExcel("票据管理数据"+sdf.format(new Date()), headData, dataList, endData),model);
	}
}
