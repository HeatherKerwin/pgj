package com.utiexian.website.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.Member;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.OrgService;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;


/**
 * 商票订单列表
 * @author MH
 *
 */
@Controller
public class DiscountOrderSpController {
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	CommentsService commentsService;

	/**
	 * 订单列表
	 * @author MH
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws ParseException 
	 * @since 2016年8月19日 下午3:32:47
	 */
	@RequestMapping("discountordersp/list")
	public String list(DiscountrecordSpForm form,HttpServletRequest request,String bank1,Integer pageIndex,String xqid,Integer pageSize,String start1,String end1,Model model) throws ParseException{
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(start1!=null){
			Date start =DateUtil.parser(start1, DateUtil.FORMART3);
			form.setBegintime(start);
		}
		if(end1!=null){
			Date end =DateUtil.parser(end1, DateUtil.FORMART3);
			form.setEndtime(end);
		}
		if(xqid!=null){
			form.setNo(xqid);
		}
		
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		form.setMemberId(memberId);
		
		try {
			if(form!=null && form.getMemberId()!=null){
				PageResults<Map<String,Object>> page = discountrecordSpService.getPcPageList(pageIndex, pageSize, form);
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				if(page!=null && page.getResults()!=null){
					
					List<Map<String,Object>> s = new ArrayList<Map<String,Object>>();

					for (Map<String, Object>  map : page.getResults()) {
						Map<String,Object> temp = null;
						temp = map;
						DecimalFormat df = new DecimalFormat(".00");
						
						Float lon_a = (Float) map.get("longitude");
						Float lat_a = (Float) map.get("latitude");
						Float lon_b = (Float) map.get("d_lon");
						Float lat_b =(Float) map.get("d_lat");
						if(lon_a != null && lat_a != null && lon_b!= null && lat_b != null){
							Double distance = DistanceUtil.getDistance(lat_a, lon_a, lat_b, lon_b);
							temp.put("distances", df.format(distance));
						}else{
							temp.put("distances", null);
						}
						
						Date sj1 =(Date)map.get("begintime");
						Date sj2 =(Date)map.get("endtime");
						Integer type1 =(Integer) map.get("type1");
						int shengDay = com.ry.util.DateUtil.daysBetween(sj1, sj2);//天数（对应几个月） 
						int tzts = discountrecordService.getTzts(type1, sj2);//调整天数（根据票据类型）
						int jxts = shengDay + tzts;//计息天数
						temp.put("jxts", jxts);
						
						Object dcrdId = map.get("id");
						s = getDtbo(map, Integer.valueOf(dcrdId.toString()));
					
						if(s.size()>0){
							
							if(s.get(0).get("_speed")!=null && s.get(0).get("_speed")!="" && s.get(0).get("_speed")!="--"){
								Double pjspeed =(Double) s.get(0).get("_speed")*2;
								temp.put("pjspeed", df.format(pjspeed));
							}else{
								temp.put("pjspeed","--");
							}
							if(s.get(0).get("_price")!=null && s.get(0).get("_price")!="" && s.get(0).get("_price")!="--"){
								Double pjprice = (Double)s.get(0).get("_price")*2;
								temp.put("pjprice", df.format(pjprice));
							}else{
								temp.put("pjprice","--");
							}
							if(s.get(0).get("_service")!=null && s.get(0).get("_service")!="" && s.get(0).get("_service")!="--"){
								Double pjservice = (Double)s.get(0).get("_service")*2;
								temp.put("pjservice", df.format(pjservice));
							}else{
								temp.put("pjservice","--");
							}
					
							if(s.get(0).get("_door")!=null && s.get(0).get("_door")!="" && s.get(0).get("_door")!="--"){
								Double pjdoor = (Double)s.get(0).get("_door")*100;
								temp.put("pjdoor", df.format(pjdoor)+"%");
							}else{
								temp.put("pjdoor","--");
							}
						}
						list.add(temp);
					}
				}
				
				page.setResults(list);
				result.put("data",page);
				result.put("response", "success");
				result.put("msg", "获取成功");
			}else{
				result.put("response", "failed");
				result.put("msg", "获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 *  根据条件跳转到详情页面
	 * @param no  订单号
	 * @return
	 * @author MH
	 * @throws Exception
	 */
	@RequestMapping("discountordersp/optpage")
	public String optPage(Integer no,Integer orderflag,HttpServletRequest request) throws Exception{
		request.setAttribute("id", no);
		request.setAttribute("orderflag", orderflag);
		return "discountorder/discountordersp";
	}
	
	/**
	 * 接单机构订单列表
	 * @param id 下单主键
	 * @param model
	 * @author MH
	 */
	@RequestMapping("discountordersp/list/org")
	public String listOrg(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> data = getDtbo(result,id);
			List<Map<String,Object>>  list  = new ArrayList<Map<String,Object>>();
			//评价只要小数点后2位
			for (Map<String, Object> map : data) {
				DecimalFormat df = new DecimalFormat(".00");
				
				if(map.get("_speed")!=null && map.get("_speed")!="" && map.get("_speed")!="--"){
					Double pjspeed = (Double)map.get("_speed")*2;
					map.put("pjspeed",  df.format(pjspeed));
				}else{
					map.put("pjspeed",  "--");
				}
				if(map.get("_service")!=null && map.get("_service")!="" && map.get("_service")!="--"){
					Double pjservice = (Double)map.get("_service")*2;
					map.put("pjservice",  df.format(pjservice));
				}else{
					map.put("pjservice",  "--");
				}
				if(map.get("_price")!=null && map.get("_price")!="" && map.get("_price")!="--"){
					Double pjprice = (Double)map.get("_price")*2;
					map.put("pjprice",  df.format(pjprice));
				}else{
					map.put("pjprice",  "--");
				}
				if(map.get("_door")!=null && map.get("_door")!="" && map.get("_door")!="--"){
					Double pjdoor = (Double)map.get("_door")*100;
					map.put("pjdoor",  df.format(pjdoor)+"%");
				}else{
					map.put("pjdoor",  "--");
				}
				
				list.add(map);
			}
			data= list;
			result.put("data", data);
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
	 * 根据下单主键-获取接单列表（含特点标签）
	 * @author MH
	 * @param result
	 * @param dcrdId 下单主键
	 * @throws Exception
	 * @since 2016年8月24日 下午1:51:49
	 */
	private List<Map<String,Object>> getDtbo(Map<String,Object> result,Integer dcrdId) throws Exception{
		if(result==null)result = new HashMap<String, Object>();
		List<Object> listjl  = new ArrayList<Object>();
		List<Map<String,Object>> list = distributeOrderSpService.getPcAllByDcrdSpId(dcrdId);
		DiscountrecordSp disc = discountrecordSpService.getById(dcrdId);
		
		int shengDay = com.ry.util.DateUtil.daysBetween(disc.getBegintime(), disc.getEndtime());//天数（对应几个月） 
		int tzts = discountrecordService.getTzts(disc.getType1(),disc.getEndtime());//调整天数（根据票据类型）
		int jxts = shengDay + tzts;//计息天数
		result.put("jxtss",jxts);
		if(list!=null){
			if(disc==null)throw new Exception("数据异常");
			
			Float lon_a = disc.getLongitude();
			Float lat_a = disc.getLatitude();
			Map<String,Object> jl = new HashMap<String, Object>();//距离
			Map<String,Object> pf = new HashMap<String, Object>();//评分
			Map<String,Object> jg = new HashMap<String, Object>();//价格
			Map<String,Object> zls = new HashMap<String, Object>();//资料少
			Boolean noFrist = true;
			Object needStuff = null;
			for(Map<String,Object> dtbo:list){
				if(noFrist){//标识最低价格（获取列表是已经按价格排序）
					noFrist = false;
					jg.put("id", dtbo.get("id"));
				}
				dtbo.put("allmoney", disc.getAllmoney());
				if(dtbo.get("org_id")!=null){//评分
					dtbo = orgService.getComment(dtbo, Integer.valueOf(dtbo.get("org_id").toString()));
					if(list.size()>1 && pf.get("id")==null){//此为：获取评分最高
						pf.put("id", dtbo.get("id"));
						pf.put("value", dtbo.get("_score"));
					}else if(pf.get("value")!=null){
						if(dtbo.get("_score")!=null && pf.get("value").toString()!=null && Double.valueOf(pf.get("value").toString())<Double.valueOf(dtbo.get("_score").toString())){
							pf.put("id", dtbo.get("id"));
							pf.put("value", dtbo.get("_score"));
						}
					}
				}
				if(lon_a!=null && lat_a!=null && dtbo.get("longitude")!=null && dtbo.get("latitude")!=null){//距离
					Double distance = DistanceUtil.getDistance(lat_a, lon_a,Float.valueOf(dtbo.get("latitude").toString()),Float.valueOf(dtbo.get("longitude").toString()));
					dtbo.put("distance",distance);
					listjl.add(distance);
					if(list.size()>1 && jl.get("id")==null){//此为：获取最近的距离
						jl.put("id", dtbo.get("id"));
						jl.put("value", distance);
					}else if(jl.get("value")!=null){
						if(Double.valueOf(jl.get("value").toString())>distance){
							jl.put("id", dtbo.get("id"));
							jl.put("value", distance);
						}
					}
				}
				needStuff = dtbo.get("need_stuff");
				if(needStuff!=null){
					Integer size = (needStuff.toString()).split(",").length;
					if(list.size()>1 && zls.get("id")==null){//此为：获取需要手续最少
						zls.put("id", dtbo.get("id"));
						zls.put("value", size);
					}else if(zls.get("value")!=null){
						if(Double.valueOf(zls.get("value").toString())>size){
							zls.put("id", dtbo.get("id"));
							zls.put("value", size);
						}
					}
				}
			}
			//汇总推荐的报价机构
			List<String> org_list = new ArrayList<String>();
			if(jg.get("id")!=null)org_list.add(jg.get("id").toString());
			if(jl.get("id")!=null)org_list.add(jl.get("id").toString());
			if(pf.get("id")!=null)org_list.add(pf.get("id").toString());
			if(zls.get("id")!=null)org_list.add(zls.get("id").toString());
			String orgId = getRecommend(org_list);
			
			Object state = null;
			Object isSelect = null;
			Boolean noHas = true;
			for(Map<String,Object> dtbo:list){//组装数据
				if(jg.get("id")!=null && dtbo.get("id")!=null && (jg.get("id").toString()).equals(dtbo.get("id").toString())){
					dtbo.put("jg", true);
				}
				if(jl.get("id")!=null && dtbo.get("id")!=null && (jl.get("id").toString()).equals(dtbo.get("id").toString())){
					dtbo.put("jl", true);
				}
				if(pf.get("id")!=null && dtbo.get("id")!=null && (pf.get("id").toString()).equals(dtbo.get("id").toString())){
					dtbo.put("pf", true);
				}
				if(zls.get("id")!=null && dtbo.get("id")!=null && (zls.get("id").toString()).equals(dtbo.get("id").toString())){
					dtbo.put("zls", true);
				}
				if(StringUtils.isNotBlank(orgId) && dtbo.get("org_id")!=null && orgId.equals(dtbo.get("id").toString())){//找到推荐的接单机构
					result.put("recommend", dtbo);
				}
				state = dtbo.get("state");
				isSelect = dtbo.get("is_select");
				//找出当前选择（选择但是不是无效订单的）
				if(noHas && state!=null && !"-2".equals(state.toString()) && !"0".equals(state.toString()) && isSelect!=null && "1".equals(isSelect.toString())){
					noHas = false;
					result.put("current", dtbo);
				}
			}
			//获取最早派单的机构（从开始派单计算时间）
			List<DistributeOrderSp> list_ = distributeOrderSpService.getOrderByCreateTime(dcrdId);
			if(list_!=null && list_.size()>0){
				DistributeOrderSp temp = list_.get(0);
				int timer = com.ry.util.DateUtil.calSeconds(temp.getCreateTime(), new Date());
				result.put("timer", 1800-timer);
			}
			
			result.put("other", list.size());//接单机构数量
		}
		result.put("distance", listjl);
		result.put("disc", disc);//下单
		return list;
	}
	
	/**
	 * 在这些机构里找出最优的（出现次数最多的）
	 * @author MH
	 * @param list
	 * @since
	 */
	private static String getRecommend(List<String> list){
		String orgId = null;
		Map<String,Object> map = new HashMap<String, Object>();
		if(list!=null){
			for(String t:list){
				int c = Collections.frequency(list, t);
				if(map!=null && map.get("id")==null){
					map.put("id", t);
					map.put("value",c);
				}else{
					if(map.get("value")!=null && Integer.valueOf(map.get("value").toString())<c){
						map.put("id", t);
						map.put("value",c);
					}
				}
			}
		}
		if(map!=null && map.get("id")!=null)orgId = map.get("id").toString();
		return orgId;
	}
	
	/**
	 * 查看接单机构详情
	 * @author MH
	 * @param dtboId 接单主键
	 * @param model
	 * @since 2016年8月22日 下午1:20:56
	 */
	@RequestMapping("discountordersp/list/org/get")
	public String getOrg(Integer dtboId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> dtbo = distributeOrderSpService.getInfoById(dtboId);
			if(dtbo==null)throw new Exception("数据异常");
			
			Object dcrdSpId_ = dtbo.get("dcrd_sp_id");
			if(dtbo!=null && dcrdSpId_!=null){
				Integer dcrdSpId = Integer.valueOf(dcrdSpId_.toString());
				List<Map<String,Object>> list = distributeOrderSpService.getAllByDcrdSpId(dcrdSpId);
				if(list!=null)dtbo.put("other", list.size());//还有其他多少商家
				
				DiscountrecordSp disc = discountrecordSpService.getById(dcrdSpId);
				Float lon_a = disc.getLongitude();
				Float lat_a = disc.getLatitude();
				dtbo.put("allmoney", disc.getAllmoney());
				dtbo.put("need_todoor", disc.getNeedTodoor());
				if(dtbo.get("org_id")!=null){//评分
					dtbo = orgService.getComment(dtbo, Integer.valueOf(dtbo.get("org_id").toString()));
				}
				if(lon_a!=null && lat_a!=null && dtbo.get("longitude")!=null && dtbo.get("latitude")!=null){//距离
					Double distance = DistanceUtil.getDistance(lat_a, lon_a,Float.valueOf(dtbo.get("latitude").toString()),Float.valueOf(dtbo.get("longitude").toString()));
					dtbo.put("distance",distance);
				}
				//获取选择的机构数量（在选择其他时给出提醒）
				DistributeOrderSpForm form = new DistributeOrderSpForm();
				form.setDcrdSpId(dcrdSpId);
				form.setIsSelect(1);//企业端选择的机构（1选择、其它未选择过）
				List<DistributeOrderSp> list_sp = distributeOrderSpService.getList(form);
				if(list_sp!=null && list_sp.size()>0){
					dtbo.put("selectCount",list_sp.size());
				}
			}
			result.put("data", dtbo);
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
	 * 查看订单详情
	 * @author MH
	 * @param dcrdId 下单主键
	 * @param model
	 * @since 2016年8月24日 上午10:45:56
	 */
	@RequestMapping("discountordersp/get")
	public String get(Integer dcrdId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> data = getDtbo(result,dcrdId);//改方法既含有下单信息（也含有接单数据）
			result.put("data", data);
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
	 * 选择机构
	 * @author MH
	 * @param id 接单主键
	 * @param model
	 * @since 2016年9月7日 上午9:54:56
	 */
	@RequestMapping("discountordersp/select/org")
	public String selectOrg(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderSp dist = distributeOrderSpService.getById(id);
			if(dist==null)throw new Exception("数据异常");
			
			dist.setIsSelect(1);//选择
			distributeOrderSpService.updateToSelect(dist);//更新状态
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 取消订单
	 * @author MH
	 * @param id 贴现主键
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param model
	 * @since 2016年9月7日 下午8:13:21
	 */
	@RequestMapping("discountordersp/cancel")
	public String cancel(Integer id,Integer cancel,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		try {
			DiscountrecordSp disc = discountrecordSpService.getById(id);
			if(disc!=null && disc.getOrderflag()!=currentState){
				msg = "数据已过期";
				throw new Exception(msg);
			}
			if(reason!=null) disc.setCancelCause(reason);
			disc.setCancel(cancel);
			disc.setOrderflag(0);//无效订单
			discountrecordSpService.updateToInvalid(disc);
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 确定收到款项 订单完成
	 * @param id 贴现主键
	 * @param model
	 * @author MH
	 */
	@RequestMapping("discountordersp/update/finish")
	public String finish(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DiscountrecordSp disc = discountrecordSpService.getById(id);
			if(disc==null)throw new Exception("数据异常");
			
			disc.setOrderflag(3);//已完成
			discountrecordSpService.updateToFinish(disc);
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
