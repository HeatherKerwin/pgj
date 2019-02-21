package com.ry.ryapp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.BillQuota;
import com.ry.core.entity.PriceType;
import com.ry.core.entity.Variables;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.core.service.BillQuotaService;
import com.ry.core.service.PriceService;
import com.ry.core.service.VariablesService;
import com.ry.ryapp.util.DateUtil;
import com.ry.util.JacksonUtil;

/**
 * @author GXW
 * @date 2016年5月13日
 */
@Controller
@RequestMapping("/app/billquota")
public class BillQuotaController {
	
	@Resource
	private BillQuotaService billQuotaService;

	@Resource
	private PriceService priceService;

	@Resource
	private VariablesService variablesService;
	
	private static final Double SIGN = 10D;//票据报价标准值范围（小于该值才进行比较）

	/**
	 * 获取纸票近期票据指数 纸票
	 * 
	 * @param request
	 * @param req
	 * @param type (1:首页10个工作日;2：1个月;3:3个月)
	 * @return
	 * @author GXW
	 * @date 2016年5月13日
	 */
	@RequestMapping("/getdata")
	public String getdata(HttpServletRequest request, UpdateMemRequest req, Model model ,Integer type) {
		//if(type!=null)type = type + 1;//@EDIT WKX APP2.2 暂时线上查询传值有问题（一个月、三个月）
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> zhi=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> dianTemp=new ArrayList<Map<String,Object>>();
		if(type==1){
			Date cur = new Date();
			Calendar  g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.DATE,-14);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART4);
			zhi=billQuotaService.getList(10,date, 0);
			dianTemp=billQuotaService.getList(10,date, 1);
		}else if(type==2){
			Date cur = new Date();
			Calendar  g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH,-1);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			zhi=billQuotaService.getList(null,date, 0);
			dianTemp=billQuotaService.getList(null,date, 1);
		}else if(type==3){
			Date cur = new Date();
			Calendar  g = Calendar.getInstance();
			g.setTime(cur);
			g.add(Calendar.MONTH,-3);
			Date minusOne = g.getTime();
			String date = DateUtil.formart(minusOne, DateUtil.FORMART3);
			zhi=billQuotaService.getList(null,date, 0);
			dianTemp=billQuotaService.getList(null,date, 1);
		}
		List<Map<String, Object>> dian=new ArrayList<Map<String,Object>>();
		Collections.reverse(zhi);
		Collections.reverse(dianTemp);
		List<String> xData=new ArrayList<String>();
		Iterator<Map<String, Object>> iz=zhi.iterator();
		while (iz.hasNext()) {
			String x = iz.next().get("xValue").toString();
			if (!StringUtils.isBlank(x))
				xData.add(x);
		}
		Iterator<Map<String, Object>> id=dianTemp.iterator();
		while(id.hasNext()){//删除多余电票日期
			Map<String, Object> cur=id.next();
			String x = cur.get("xValue").toString();
			if(xData.contains(x))
				dian.add(cur);
		}
		result.put("xData",xData);
		result.put("zhi",zhi);
		result.put("dian",dian);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 模拟定时任务存入指数（测试用）
	 * @author GXW
	 * @throws Exception 
	 * @date 2016年6月6日
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(HttpServletRequest request, UpdateMemRequest req, Model model ,Integer type) throws Exception {
		try {
			Date date = DateUtil.addDays(-1);//获取昨天
			if (DateUtil.getWeekOfDate(date) == 0 | DateUtil.getWeekOfDate(date) == 6) return "weekend..";// 周末不计算
			//纸票部分
			List<Map<String, Object>> result = billQuotaService.getByDay(DateUtil.formart(date, DateUtil.FORMART3),0);
			if (result != null && result.size() > 0) return DateUtil.formart(date, DateUtil.FORMART3)+":record exists...(zhi)";//这天已存在记录
			String b = variablesService.getByCode("BASEZHI", null);//先从系统参数中获取基期数字
			Double baseZhi = 0.0;
			if (StringUtils.isBlank(b)) {
				String baseDateStr = variablesService.getByCode("BASEDATEZHI", "2015-09-16");
				Date baseDate = DateUtil.parser(baseDateStr, DateUtil.FORMART3);
				baseZhi = getZhiQuota1(baseDate);
				Variables base = new Variables();
				base.setCode("BASEZHI");
				base.setName("BASEZHI");
				base.setRemarks("纸票基期数据（分母）");
				base.setValue(baseZhi.toString());
				if(baseZhi != null && baseZhi !=0.0) variablesService.saveInfo(base);
			} else {
				baseZhi = Double.valueOf(b);
			}
			Double curZhi = getZhiQuota1(date);
			if (curZhi == null || curZhi == 0)return DateUtil.formart(date, DateUtil.FORMART3)+":no result...(zhipiao)";// 这天无指数
			BillQuota billQuota = new BillQuota();
			if(baseZhi==0)throw new Exception("No data on base day ... (zhipiao)");
			billQuota.setValue(curZhi / baseZhi * 1000);
			billQuota.setCreateTime(date);
			billQuota.setDay(DateUtil.formart(date, DateUtil.FORMART3));
			billQuota.setType(0);// 纸票
			billQuotaService.add(billQuota);
			
			//电票部分
			List<Map<String, Object>> res = billQuotaService.getByDay(DateUtil.formart(date, DateUtil.FORMART3),1);
			if (res != null && res.size() > 0) return DateUtil.formart(date, DateUtil.FORMART3)+":record exists...(dianpiao)";//这天已存在记录
			b = variablesService.getByCode("BASEDIAN", null);//先从系统参数中获取基期数字
			Double baseDian = 0.0;
			if (StringUtils.isBlank(b)) {
				String baseDateStr = variablesService.getByCode("BASEDATEDIAN", "2016-05-16");
				Date baseDate = DateUtil.parser(baseDateStr, DateUtil.FORMART3);
				baseDian = getDianQuota(baseDate);
				Variables base = new Variables();
				base.setCode("BASEDIAN");
				base.setName("BASEDIAN");
				base.setRemarks("电票基期数据（分母）");
				base.setValue(baseDian.toString());
				if(baseDian != null && baseDian !=0.0)variablesService.saveInfo(base);
			} else {
				baseDian = Double.valueOf(b);
			}
			Double curDian = getDianQuota(date);
			if (curDian == null || curDian == 0)return DateUtil.formart(date, DateUtil.FORMART3)+":no result...(dian)";// 这天无指数
			BillQuota billQuotaD = new BillQuota();
			if(baseZhi==0)throw new Exception("No data on base day ... (dianpiao)");
			billQuotaD.setValue(curDian / baseDian * 1000);
			billQuotaD.setCreateTime(date);
			billQuotaD.setDay(DateUtil.formart(date, DateUtil.FORMART3));
			billQuotaD.setType(1);// 电票
			billQuotaService.add(billQuotaD);
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR:"+e.getMessage();
		}
		return "SUCCESS";
	}
	
	/**
	 * 计算纸票、电票指数
	 * 
	 * @param request
	 * @param req
	 * @author GXW
	 * @date 2016年5月17日
	 */
	@RequestMapping("/initialize")
	public @ResponseBody String initialize(HttpServletRequest request, UpdateMemRequest req) {

		initializeZhi();
		initializeDian();
		return "SUCCESS";
	}

	/**
	 * 初始化纸票票据指数，截至到今天
	 * 
	 * @author GXW
	 * @date 2016年5月17日
	 */
	public void initializeZhi() {
		try {
			Date today = new Date();
			String baseDateStr = variablesService.getByCode("BASEDATEZHI", "2015-09-16");
			Date baseDate = DateUtil.parser(baseDateStr, DateUtil.FORMART3);
			Date date = DateUtil.addDays(DateUtil.parser(baseDateStr, DateUtil.FORMART3), 1);
			String b = variablesService.getByCode("BASEZHI", null);
			Double baseZhi = 0.0;
			if (StringUtils.isBlank(b)) {
				baseZhi = getZhiQuota1(baseDate);
				Variables base = new Variables();
				base.setCode("BASEZHI");
				base.setName("BASEZHI");
				base.setRemarks("纸票基期数据（分母）");
				base.setValue(baseZhi.toString());
				if(baseZhi != null && baseZhi !=0.0) variablesService.saveInfo(base);
			} else {
				baseZhi = Double.valueOf(b);
			}
			List<Map<String, Object>> result = null;
			while (date.before(today)) {
				if (DateUtil.getWeekOfDate(date) == 0 | DateUtil.getWeekOfDate(date) == 6) {// 周末不计算
					date = DateUtil.addDays(date, 1);
					continue;
				}
				result = billQuotaService.getByDay(DateUtil.formart(date, DateUtil.FORMART3),0);
				if (result != null && result.size() > 0) {// 这天已有记录
					date = DateUtil.addDays(date, 1);
					continue;
				} else {
					Double curZhi = getZhiQuota1(date);
					if (curZhi == null || curZhi == 0) {// 这天无指数
						date = DateUtil.addDays(date, 1);
						continue;
					} else {// 存入指数
						BillQuota billQuota = new BillQuota();
						billQuota.setValue(curZhi / baseZhi * 1000);
						billQuota.setCreateTime(date);
						billQuota.setDay(DateUtil.formart(date, DateUtil.FORMART3));
						billQuota.setType(0);// 纸票
						billQuotaService.add(billQuota);
						date = DateUtil.addDays(date, 1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("初始化票据指数出错：");
			e.printStackTrace();
		}
	}

	/**
	 * 初始化电票票据指数，截至到今天
	 * 
	 * @author GXW
	 * @date 2016年5月17日
	 */
	public void initializeDian() {
		try {
			Date today = new Date();
			String baseDateStr = variablesService.getByCode("BASEDATEDIAN", "2016-05-16");
			Date baseDate = DateUtil.parser(baseDateStr, DateUtil.FORMART3);
			Date date = DateUtil.addDays(DateUtil.parser(baseDateStr, DateUtil.FORMART3), 1);
			String b = variablesService.getByCode("BASEDIAN", null);
			Double baseDian = 0.0;
			if (StringUtils.isBlank(b)) {
				baseDian = getDianQuota(baseDate);
				Variables base = new Variables();
				base.setCode("BASEDIAN");
				base.setName("BASEDIAN");
				base.setRemarks("电票基期数据（分母）");
				base.setValue(baseDian.toString());
				if(baseDian != null && baseDian !=0.0)variablesService.saveInfo(base);
			} else {
				baseDian = Double.valueOf(b);
			}
			List<Map<String, Object>> result = null;
			while (date.before(today)) {
				if (DateUtil.getWeekOfDate(date) == 0 | DateUtil.getWeekOfDate(date) == 6) {// 周末不计算
					date = DateUtil.addDays(date, 1);
					continue;
				}
				result = billQuotaService.getByDay(DateUtil.formart(date, DateUtil.FORMART3),1);
				if (result != null && result.size() > 0) {// 这天已有记录
					date = DateUtil.addDays(date, 1);
					continue;
				} else {
					Double curDian = getDianQuota(date);
					if (curDian == null || curDian == 0) {// 这天无指数
						date = DateUtil.addDays(date, 1);
						continue;
					} else {// 存入指数
						BillQuota billQuota = new BillQuota();
						billQuota.setValue(curDian / baseDian * 1000);
						billQuota.setCreateTime(date);
						billQuota.setDay(DateUtil.formart(date, DateUtil.FORMART3));
						;
						billQuota.setType(1);// 电票
						billQuotaService.add(billQuota);
						date = DateUtil.addDays(date, 1);
						continue;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("初始化票据指数出错：");
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定某一天计算纸票分子部分
	 * 
	 * @param day
	 *            日期
	 * @return Double 当天纸票计算结果
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getZhiQuota1(Date day) {
		String date = DateUtil.formart(day, DateUtil.FORMART3);
		PriceType pt = new PriceType();
		pt.setType1(1);// 小票纸票50万
		pt.setType2(0);
		pt.setType3(0);// 小票纸票
		List<Map<String, Object>> lastDaySml = priceService.getAllPrice(date, pt, null);// 那一天所有机构的小票价格
		pt.setType1(1);// 小票纸票50-100万
		pt.setType2(0);
		pt.setType3(1);// 小票纸票
		List<Map<String, Object>> lastDaySml1 = priceService.getAllPrice(date, pt, null);// 那一天所有机构的小票价格
		pt.setType1(1);// 小票纸票100万以上
		pt.setType2(0);
		pt.setType3(2);// 小票纸票
		List<Map<String, Object>> lastDaySml2 = priceService.getAllPrice(date, pt, null);// 那一天所有机构的小票价格
		
		if (lastDaySml.size() == 0 && lastDaySml1.size() == 0 && lastDaySml2.size() == 0)
			return 0.0;// 大票或者小票没有报价 返回0
		List<List<Map<String, Object>>> smlGroup = getGroup(lastDaySml);// 将小票按机构分组
		List<List<Map<String, Object>>> smlGroup1 = getGroup(lastDaySml1);// 将小票按机构分组
		List<List<Map<String, Object>>> smlGroup2 = getGroup(lastDaySml2);// 将小票按机构分组
		Map<String, Double> smlMap = new HashMap<String, Double>();// org_id,小票平均值
		Map<String, Double> smlMap1 = new HashMap<String, Double>();// org_id,小票平均值
		Map<String, Double> smlMap2 = new HashMap<String, Double>();// org_id,小票平均值
		if(smlGroup != null){
			Iterator<List<Map<String, Object>>> si = smlGroup.iterator();
			while (si.hasNext()) {
				List<Map<String, Object>> l = si.next();
				Double result = getSmlAverage(l);
				if (result != null) {// 有报价机构放入
					smlMap.put(l.get(0).get("org_id").toString(), result);
				}
			}
		}
		if(smlGroup1 != null){
			Iterator<List<Map<String, Object>>> si = smlGroup1.iterator();
			while (si.hasNext()) {
				List<Map<String, Object>> l = si.next();
				Double result = getSmlAverage(l);
				if (result != null) {// 有报价机构放入
					smlMap1.put(l.get(0).get("org_id").toString(), result);
				}
			}
		}
		if(smlGroup2 != null){
			Iterator<List<Map<String, Object>>> si = smlGroup2.iterator();
			while (si.hasNext()) {
				List<Map<String, Object>> l = si.next();
				Double result = getSmlAverage(l);
				if (result != null) {// 有报价机构放入
					smlMap2.put(l.get(0).get("org_id").toString(), result);
				}
			}
		}
		return getBillQuota1(smlMap,smlMap1,smlMap2);

	}
	
	/**
	 * 指定某一天计算纸票分子部分
	 * 
	 * @param day
	 *            日期
	 * @return Double 当天纸票计算结果
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getZhiQuota(Date day) {
		String date = DateUtil.formart(day, DateUtil.FORMART3);
		PriceType pt = new PriceType();
		pt.setType1(0);// 大票纸票
		pt.setType2(0);
		pt.setType4(-1);// 大票纸票
		List<Map<String, Object>> lastDayBig = priceService.getAllPrice(date, pt, null);// 那一天所有机构的大票价格
		pt.setType1(1);// 小票纸票
		pt.setType2(0);
		pt.setType4(-1);// 小票纸票
		List<Map<String, Object>> lastDaySml = priceService.getAllPrice(date, pt, null);// 那一天所有机构的小票价格
		if (lastDayBig.size() == 0 && lastDaySml.size() == 0)
			return 0.0;// 大票或者小票没有报价 返回0
		List<List<Map<String, Object>>> smlGroup = getGroup(lastDaySml);// 将小票按机构分组
		Map<String, Double> bigMap = new HashMap<String, Double>();// org_id,大票平均值
		Map<String, Double> smlMap = new HashMap<String, Double>();// org_id,小票平均值
		Iterator<Map<String, Object>> i = lastDayBig.iterator();
		while (i.hasNext()) {
			Map<String, Object> m = i.next();
			Double result = getBigAverage(m);
			if (result != null) {// 有报价机构放入
				bigMap.put(m.get("org_id").toString(), result);
			}
		}
		if(smlGroup != null){
			Iterator<List<Map<String, Object>>> si = smlGroup.iterator();
			while (si.hasNext()) {
				List<Map<String, Object>> l = si.next();
				Double result = getSmlAverage(l);
				if (result != null) {// 有报价机构放入
					smlMap.put(l.get(0).get("org_id").toString(), result);
				}
			}
		}
		return getBillQuota(bigMap, smlMap);

	}

	/**
	 * 指定某一天计算电票分子部分
	 * 
	 * @param day
	 *            日期
	 * @return Double 当天电票计算结果
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getDianQuota(Date day) {
		String date = DateUtil.formart(day, DateUtil.FORMART3);
		PriceType pt = new PriceType();
		pt.setType1(0);// 大票电票
		pt.setType2(1);
		pt.setType4(-1);// 大票电票
		List<Map<String, Object>> lastDayBig = priceService.getAllPrice(date, pt, null);// 那一天所有机构的大票价格
		pt.setType1(1);// 小票电票
		pt.setType2(1);
		pt.setType4(-1);// 小票电票
		List<Map<String, Object>> lastDaySml = priceService.getAllPrice(date, pt, null);// 那一天所有机构的小票价格
		if (lastDayBig.size() == 0 && lastDaySml.size() == 0)
			return 0.0;// 大票或者小票没有报价 返回0
		List<List<Map<String, Object>>> smlGroup = getGroup(lastDaySml);// 将小票按机构分组
		Map<String, Double> bigMap = new HashMap<String, Double>();// org_id,大票平均值
		Map<String, Double> smlMap = new HashMap<String, Double>();// org_id,小票平均值
		Iterator<Map<String, Object>> i = lastDayBig.iterator();
		while (i.hasNext()) {
			Map<String, Object> m = i.next();
			Double result = getBigAverage(m);
			if (result != null) {// 有报价机构放入
				bigMap.put(m.get("org_id").toString(), result);
			}
		}
		if(smlGroup != null){
			Iterator<List<Map<String, Object>>> si = smlGroup.iterator();
			while (si.hasNext()) {
				List<Map<String, Object>> l = si.next();
				Double result = getSmlSpAverage(l);
				if (result != null) {// 有报价机构放入
					smlMap.put(l.get(0).get("org_id").toString(), result);
				}
			}
		}
		return getBillQuota(bigMap, smlMap);

	}
	
	/**
	 * 获取某一机构小票票价平均值
	 * 
	 * @param list
	 *            某一机构所有小票报价
	 * @return Double此机构电票小票报价均值
	 * @author MH
	 * @date 2016年5月16日
	 */
	public Double getSmlSpAverage(List<Map<String, Object>> list) {
		if (list.size() == 0)
			return null;
		int count = 0;
		double sum = 0.0;
		for (Map<String, Object> map : list) {
				if (!"--".equals(map.get("guogu").toString())) {
					Double temp = Double.valueOf(map.get("guogu").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("chengshang").toString())) {
					Double temp = Double.valueOf(map.get("chengshang").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("waizi").toString())) {
					Double temp = Double.valueOf(map.get("waizi").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nongshang").toString())) {
					Double temp = Double.valueOf(map.get("nongshang").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nonghe").toString())) {
					Double temp = Double.valueOf(map.get("nonghe").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nongxin").toString())) {
					Double temp = Double.valueOf(map.get("nongxin").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("cunzhen").toString())) {
					Double temp = Double.valueOf(map.get("cunzhen").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
			}
		
		if (count == 0)
			return null;
		return sum / count;
	}
	
	/**
	 * 通过机构小票平均值[]获取分子部分(3种)
	 * 
	 * @param 
	 *           sml每个机构的小票平均值（50），sml每个机构的小票平均值（50-100），sml每个机构的小票平均值（100）
	 * @param sml
	 * @return Double 当天计算结果
	 * @author MH
	 */
	public Double getBillQuota1( Map<String, Double> sml, Map<String, Double> sml1, Map<String, Double> sml2) {
		Set<String> org_ids = intersectionSet(sml1.keySet(), sml.keySet());
		Set<String> org_ids1 = intersectionSet(org_ids, sml2.keySet());
		List<Double> billQuotas = new ArrayList<Double>();
		Iterator<String> i = org_ids1.iterator();
		while (i.hasNext()) {
			String org_id = i.next();
			Double smlVal = sml.get(org_id);
			sml.remove(org_id);
			Double smlVal1 = sml1.get(org_id);
			sml1.remove(org_id);
			Double smlVal2 = sml2.get(org_id);
			sml2.remove(org_id);
			Double value = 0.382 * smlVal + 0.236 * smlVal1 + 0.382 * smlVal2;
			billQuotas.add(value);
		}
		Iterator<String> a = sml.keySet().iterator();
		while(a.hasNext()){
			String org_id = a.next();
			Double bigVal = sml.get(org_id);
			Double value =  bigVal ;
			billQuotas.add(value);
		}
		Iterator<String> b = sml1.keySet().iterator();
		while(b.hasNext()){
			String org_id = b.next();
			Double sml1Val = sml1.get(org_id);
			Double value =  sml1Val ;
			billQuotas.add(value);
		}
		Iterator<String> c = sml2.keySet().iterator();
		while(c.hasNext()){
			String org_id = c.next();
			Double sml2Val = sml2.get(org_id);
			Double value =  sml2Val ;
			billQuotas.add(value);
		}
		if (billQuotas.size() == 0)// 假如今天无价格，则今天无指数
			return null;
		if (billQuotas.size() == 1)// 假如今天就一家机构的价格，则采用这家机构的
			return billQuotas.get(0);
		if (billQuotas.size() < 4){// 三家和两家都是直接相加除以3或2
			Double sum=0.0;
			for(Double val:billQuotas){
				sum+=val;
			}
			return sum/billQuotas.size();
		}
		Collections.sort(billQuotas);
		billQuotas.remove(0);// 去掉最小
		billQuotas.remove(billQuotas.size() - 1);// 去掉最大
		Double sum = 0.0;
		for (Double x : billQuotas) {
			sum += x;
		}
		return sum / billQuotas.size();// 返回算术平均值
	}

	/**
	 * 通过机构大票平均值[]，机构小票平均值[]获取分子部分
	 * 
	 * @param big
	 *            每个机构的大票平均值， sml每个机构的小票平均值
	 * @param sml
	 * @return Double 当天计算结果
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getBillQuota(Map<String, Double> big, Map<String, Double> sml) {
		Set<String> org_ids = intersectionSet(big.keySet(), sml.keySet());
		List<Double> billQuotas = new ArrayList<Double>();
		Iterator<String> i = org_ids.iterator();
		while (i.hasNext()) {
			String org_id = i.next();
			Double bigVal = big.get(org_id);
			big.remove(org_id);
			Double smlVal = sml.get(org_id);
			sml.remove(org_id);
			Double value = 0.618 * bigVal + 0.382 * smlVal;
			billQuotas.add(value);
		}
		Iterator<String> a = big.keySet().iterator();
		while(a.hasNext()){
			String org_id = a.next();
			Double bigVal = big.get(org_id);
			Double value =  bigVal ;
			billQuotas.add(value);
		}
		Iterator<String> b = sml.keySet().iterator();
		while(b.hasNext()){
			String org_id = b.next();
			Double smlVal = sml.get(org_id);
			Double value =  smlVal ;
			
			billQuotas.add(value);
		}
		if (billQuotas.size() == 0)// 假如今天无价格，则今天无指数
			return null;
		if (billQuotas.size() == 1)// 假如今天就一家机构的价格，则采用这家机构的
			return billQuotas.get(0);
		if (billQuotas.size() < 4){// 三家和两家都是直接相加除以3或2
			Double sum=0.0;
			for(Double val:billQuotas){
				sum+=val;
			}
			return sum/billQuotas.size();
		}
		Collections.sort(billQuotas);
		billQuotas.remove(0);// 去掉最小
		billQuotas.remove(billQuotas.size() - 1);// 去掉最大
		Double sum = 0.0;
		for (Double x : billQuotas) {
			sum += x;
		}
		return sum / billQuotas.size();// 返回算术平均值
	}

	/**
	 * 获取两个SET的交集
	 * 
	 * @param setA
	 * @param setB
	 * @return intersectionSet A，B交集
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Set<String> intersectionSet(Set<String> setA, Set<String> setB) {
		Set<String> intersectionSet = new HashSet<String>();
		Iterator<String> iterA = setA.iterator();
		while (iterA.hasNext()) {
			String tempInner = iterA.next();
			if (setB.contains(tempInner)) {
				intersectionSet.add(tempInner);
			}
		}
		return intersectionSet;
	}

	/**
	 * 获取某一机构大票票价平均值
	 * 
	 * @param map
	 *            某一机构包含大票的一条数据
	 * @return Double 此机构大票平均值
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getBigAverage(Map<String, Object> map) {
		if (map.size() == 0)
			return null;// 空，不参与计算
		int count = 0;
		Double guogu = 0.0, chengshang = 0.0, waizi = 0.0, nongshang = 0.0, nonghe = 0.0, nongxin = 0.0, cunzhen = 0.0,
				average = 0.0;
		if (map.get("guogu") != null && !(map.get("guogu").equals("--"))// 国股
				&& !(map.get("guogu").equals(""))) {
			Double temp = Double.valueOf(map.get("guogu").toString());
			if(temp<SIGN){
				guogu = temp;
				count++;
			}
		}
		if (map.get("chengshang") != null && !(map.get("chengshang").equals("--"))// 城商
				&& !(map.get("chengshang").equals(""))) {
			Double temp = Double.valueOf(map.get("chengshang").toString());
			if(temp<SIGN){
				chengshang = temp;
				count++;
			}
		}
		if (map.get("waizi") != null && !(map.get("waizi").equals("--"))// 外资
				&& !(map.get("waizi").equals(""))) {
			Double temp = Double.valueOf(map.get("waizi").toString());
			if(temp<SIGN){
				waizi = temp;
				count++;
			}
		}
		if (map.get("nongshang") != null && !(map.get("nongshang").equals("--"))// 农商
				&& !(map.get("nongshang").equals(""))) {
			Double temp = Double.valueOf(map.get("nongshang").toString());
			if(temp<SIGN){
				nongshang = temp;
				count++;
			}
		}
		if (map.get("nonghe") != null && !(map.get("nonghe").equals("--"))// 农合
				&& !(map.get("nonghe").equals(""))) {
			Double temp = Double.valueOf(map.get("nonghe").toString());
			if(temp<SIGN){
				nonghe = temp;
				count++;
			}
		}
		if (map.get("nongxin") != null && !(map.get("nongxin").equals("--"))// 农新
				&& !(map.get("nongxin").equals(""))) {
			Double temp = Double.valueOf(map.get("nongxin").toString());
			if(temp<SIGN){
				nongxin = temp;
				count++;
			}
		}
		if (map.get("cunzhen") != null && !(map.get("cunzhen").equals("--"))// 村镇
				&& !(map.get("cunzhen").equals(""))) {
			Double temp = Double.valueOf(map.get("cunzhen").toString());
			if(temp<SIGN){
				cunzhen = temp;
				count++;
			}
		}
		if (count == 0) {// 无报价不参与计算
			return null;
		} else {
			average = guogu + chengshang + waizi + nongshang + nonghe + nongxin + cunzhen;
			average = average / count;
			return average;
		}
	}

	/**
	 * 小票数据按机构分组 同一机构小票报价可能对应多条数据，将所有数据按org_id分组
	 * 
	 * @param list
	 *            所有机构小票报价
	 * @return List<List<Map<String, Object>>> 分组后的list
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public List<List<Map<String, Object>>> getGroup(List<Map<String, Object>> list) {
		if (list.size() == 0)
			return null;
		List<List<Map<String, Object>>> result = new ArrayList<List<Map<String, Object>>>();
		String orgId = list.get(0).get("org_id").toString();
		int start = 0;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (orgId.equals(map.get("org_id").toString()))
				;// 同一机构
			else {// 机构变了
				List<Map<String, Object>> currOrg = list.subList(start, i);
				result.add(currOrg);
				orgId = map.get("org_id").toString();
				start = i;
			}
		}
		result.add(list.subList(start, list.size()));// 最后一组
		return result;
	}

	/**
	 * 获取某一机构小票票价平均值
	 * 
	 * @param list
	 *            某一机构所有小票报价
	 * @return Double此机构小票报价均值
	 * @author GXW
	 * @date 2016年5月16日
	 */
	public Double getSmlAverage(List<Map<String, Object>> list) {
		if (list.size() == 0)
			return null;
		int count = 0;
		double sum = 0.0;
		for (Map<String, Object> map : list) {
			Integer day = Integer
					.valueOf(StringUtils.isNotBlank(map.get("day").toString()) ? map.get("day").toString() : "0");
			if (map.get("way") != null && Integer.valueOf(map.get("way").toString()) == 0) {// 小票A方式报价
				if ((!"--".equals(map.get("guogu2").toString())) && (!"0".equals(map.get("guogu2").toString()))
						&& (!"--".equals(map.get("guogu").toString())
								|| !"".equals(map.get("guogu").toString().trim()))) {
					Double temp = Double.valueOf(map.get("guogu2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("chengshang2").toString()))
						&& (!"0".equals(map.get("chengshang2").toString()))
						&& (!"--".equals(map.get("chengshang").toString())
								|| !"".equals(map.get("chengshang").toString().trim()))) {
					Double temp = Double.valueOf(map.get("chengshang2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("waizi2").toString())) && (!"0".equals(map.get("waizi2").toString()))
						&& (!"--".equals(map.get("waizi").toString())
								|| !"".equals(map.get("waizi").toString().trim()))) {
					Double temp = Double.valueOf(map.get("waizi2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nongshang2").toString())) && (!"0".equals(map.get("nongshang2").toString()))
						&& (!"--".equals(map.get("nongshang").toString())
								|| !"".equals(map.get("nongshang").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nongshang2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nonghe2").toString())) && (!"0".equals(map.get("nonghe2").toString()))
						&& (!"--".equals(map.get("nonghe").toString())
								|| !"".equals(map.get("nonghe").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nonghe2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nongxin2").toString())) && (!"0".equals(map.get("nongxin2").toString()))
						&& (!"--".equals(map.get("nongxin").toString())
								|| !"".equals(map.get("nongxin").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nongxin2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("cunzhen2").toString())) && (!"0".equals(map.get("cunzhen2").toString()))
						&& (!"--".equals(map.get("cunzhen").toString())
								|| !"".equals(map.get("cunzhen").toString().trim()))) {
					Double temp = Double.valueOf(map.get("cunzhen2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
			} else if (map.get("way") != null && Integer.valueOf(map.get("way").toString()) == 1) {// 小票B方式报价
				if ((!"--".equals(map.get("guogu2").toString())) && (!"0".equals(map.get("guogu2").toString()))
						&& (!"--".equals(map.get("guogu").toString())
								|| !"".equals(map.get("guogu").toString().trim()))) {
					Double temp = Double.valueOf(map.get("guogu2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("chengshang2").toString()))
						&& (!"0".equals(map.get("chengshang2").toString()))
						&& (!"--".equals(map.get("chengshang").toString())
								|| !"".equals(map.get("chengshang").toString().trim()))) {
					Double temp = Double.valueOf(map.get("chengshang2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("waizi2").toString())) && (!"0".equals(map.get("waizi2").toString()))
						&& (!"--".equals(map.get("waizi").toString())
								|| !"".equals(map.get("waizi").toString().trim()))) {
					Double temp = Double.valueOf(map.get("waizi2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nongshang2").toString())) && (!"0".equals(map.get("nongshang2").toString()))
						&& (!"--".equals(map.get("nongshang").toString())
								|| !"".equals(map.get("nongshang").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nongshang2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nonghe2").toString())) && (!"0".equals(map.get("nonghe2").toString()))
						&& (!"--".equals(map.get("nonghe").toString())
								|| !"".equals(map.get("nonghe").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nonghe2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("nongxin2").toString())) && (!"0".equals(map.get("nongxin2").toString()))
						&& (!"--".equals(map.get("nongxin").toString())
								|| !"".equals(map.get("nongxin").toString().trim()))) {
					Double temp = Double.valueOf(map.get("nongxin2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if ((!"--".equals(map.get("cunzhen2").toString())) && (!"0".equals(map.get("cunzhen2").toString()))
						&& (!"--".equals(map.get("cunzhen").toString())
								|| !"".equals(map.get("cunzhen").toString().trim()))) {
					Double temp = Double.valueOf(map.get("cunzhen2").toString()) * 30 * 1000 / 100000 / day;
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
			}else{
				if (!"--".equals(map.get("guogu").toString())) {
					Double temp = Double.valueOf(map.get("guogu").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("chengshang").toString())) {
					Double temp = Double.valueOf(map.get("chengshang").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("waizi").toString())) {
					Double temp = Double.valueOf(map.get("waizi").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nongshang").toString())) {
					Double temp = Double.valueOf(map.get("nongshang").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nonghe").toString())) {
					Double temp = Double.valueOf(map.get("nonghe").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("nongxin").toString())) {
					Double temp = Double.valueOf(map.get("nongxin").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
				if (!"--".equals(map.get("cunzhen").toString())) {
					Double temp = Double.valueOf(map.get("cunzhen").toString());
					if(temp<SIGN){
						sum += temp;
						count++;
					}
				}
			}
		}
		if (count == 0)
			return null;
		return sum / count;
	}
}
