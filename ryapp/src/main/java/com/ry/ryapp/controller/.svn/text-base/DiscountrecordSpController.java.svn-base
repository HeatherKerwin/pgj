package com.ry.ryapp.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.Notice;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.OrgService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.ryapp.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/app/discountrecordsp/")
public class DiscountrecordSpController {
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	/**
	 * 贴现下单
	 * @author WKX
	 * @param discountrecordSp
	 * @param start 出票日期
	 * @param end 到期日期
	 * @param model
	 * @throws IOException
	 * @since 2016年8月15日 下午2:39:22
	 */
	@RequestMapping("tiexian")
	public String tieXian(DiscountrecordSp discountrecordSp,String print,String start,String end,Float version,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String msg = "下单成功";
		String eMsg = "操作失败,请稍后再试!";
		try {
			Notice notice = tiexianNoticeService.findByNowTime(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
			//如果在节假日定会额度受限,所以提醒非工作日的文字
			if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
				result.put("response", "failed");
				result.put("msg", notice.getAlert());
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}else{
				notice = tiexianNoticeService.findByCode("ERROR");
			}
			
			//基本信息验证（APP遗漏暂时在后台验证）2016-11-04
			if(StringUtils.isBlank(discountrecordSp.getBank())){
				eMsg = "请输入承兑企业";
				throw new Exception("请输入承兑企业");
			}
			if(discountrecordSp==null || discountrecordSp.getAllmoney()==null){
				eMsg = "请输入票据金额";
				throw new Exception("请输入票据金额");
			}
			
			//将临时目录文件转移到目录文件中
			List<String> tempstrs = new ArrayList<String>();
			String picpath = discountrecordSp.getPicpath();
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
						if (StringUtils.isNotBlank(picpaths[i])) {
							tString += picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
							FileUtil.moveFile(tString, pString);
						}
						String path = Constant.UPLOAD_SRC + extpath + picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
						tempstrs.add(path);
					}
				}
				picpath = "";//修改数据库保存地址
				if (tempstrs.size() > 0) {
					for (String tempstr : tempstrs) {
						picpath += tempstr + ",";
					}
				}
				discountrecordSp.setPicpath(picpath);
			}
			
			Date printtime = DateUtil.parser(print, DateUtil.FORMART3);	
			Date begintime = DateUtil.parser(start, DateUtil.FORMART3);	
			Date endtime = DateUtil.parser(end, DateUtil.FORMART3);
			
			discountrecordSp.setPrinttime(printtime);
			discountrecordSp.setBegintime(begintime);
			discountrecordSp.setEndtime(endtime);
			discountrecordSp.setNo(getNo());
			discountrecordSp.setOrderflag(1);//待确认
			discountrecordSp.setCreateTime(new Date());
			discountrecordSp.setSource("APP");//商票添加来源
			discountrecordSp.setHandleState(0);//默认待处理
			discountrecordSpService.saveModel(discountrecordSp);
			
			result.put("data", discountrecordSp);
			result.put("response", "success");
			result.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", eMsg);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 生成订单号
	 * @author WKX
	 * @since 2016年8月15日 下午3:11:15
	 */
	private String getNo(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
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
		String orderNumber = simpleDateFormat.format(new Date())+randomStr;
		return orderNumber;
	}
	
	/**
	 * 订单列表
	 * @author WKX
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年8月19日 下午3:32:47
	 */
	@RequestMapping("list")
	public String list(DiscountrecordSpForm form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			if(form!=null && form.getMemberId()!=null){
				PageResults<Map<String,Object>> page = discountrecordSpService.getPageList(pageIndex, pageSize, form);
				if(page!=null && page.getResults()!=null){
					List<Map<String,Object>> list = page.getResults();
					if(list!=null && list.size()>0){
						for(Map<String,Object> map:list){
							Object dcrdId = map.get("id");
							getDtbo(map, Integer.valueOf(dcrdId.toString()));
						}
					}
				}
				result.put("data",page.getResults());
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
	 * 接单机构订单列表
	 * @param id 下单主键
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("list/org")
	public String listOrg(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> data = getDtbo(result,id);
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
	 * @author WKX
	 * @param result
	 * @param dcrdId 下单主键
	 * @throws Exception
	 * @since 2016年8月24日 下午1:51:49
	 */
	private List<Map<String,Object>> getDtbo(Map<String,Object> result,Integer dcrdId) throws Exception{
		if(result==null)result = new HashMap<String, Object>();
		List<Map<String,Object>> list = distributeOrderSpService.getAllByDcrdSpId(dcrdId);
		DiscountrecordSp disc = discountrecordSpService.getById(dcrdId);
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
				if(StringUtils.isNotBlank(orgId) && dtbo.get("org_id")!=null && orgId.equals(dtbo.get("org_id").toString())){//找到推荐的接单机构
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
		result.put("disc", disc);//下单
		return list;
	}
	
	/**
	 * 在这些机构里找出最优的（出现次数最多的）
	 * @author WKX
	 * @param list
	 * @since 2016年8月24日 下午4:38:36
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
	 * @author WKX
	 * @param dtboId 接单主键
	 * @param model
	 * @since 2016年8月22日 下午1:20:56
	 */
	@RequestMapping("list/org/get")
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
					if("--".equals(dtbo.get("_price"))){
						dtbo.put("_price",0.00);
					}
					if("--".equals(dtbo.get("_service"))){
						dtbo.put("_service",0.00);
					}
					if("--".equals(dtbo.get("_speed"))){
						dtbo.put("_speed",0.00);
					}
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
	 * @author WKX
	 * @param dcrdId 下单主键
	 * @param model
	 * @since 2016年8月24日 上午10:45:56
	 */
	@RequestMapping("get")
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
	 * @author WKX
	 * @param id 接单主键
	 * @param model
	 * @since 2016年9月7日 上午9:54:56
	 */
	@RequestMapping("select/org")
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
	 * @author WKX
	 * @param id 贴现主键
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param version 版本
	 * @param model
	 * @since 2016年9月7日 下午8:13:21
	 */
	@RequestMapping("cancel")
	public String cancel(Integer id,Integer cancel,String reason,Integer currentState,Float version,Model model){
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
	 * @author WKX
	 */
	@RequestMapping("update/finish")
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