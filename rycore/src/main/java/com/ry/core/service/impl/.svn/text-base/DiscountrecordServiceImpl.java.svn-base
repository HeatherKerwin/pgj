package com.ry.core.service.impl;

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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.Enum.BankTypeEnum;
import com.ry.core.Enum.BillTypeEnum;
import com.ry.core.Enum.HandleStateEnum;
import com.ry.core.Enum.NeedTodoorEnum;
import com.ry.core.Enum.OrderState;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.DiscountrecordPlDao;
import com.ry.core.dao.DiscountrecordSpDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.dao.DistributeOrderDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.dao.NoticeAddDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.dao.OrgDtoDao;
import com.ry.core.dao.PriceDao;
import com.ry.core.dao.TiexianNoticeDao;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.PriceType;
import com.ry.core.entity.RefundRecord;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.MemOrder.DispatchOrderInofRequest;
import com.ry.core.form.MemOrder.DispatchOrderInofResponse;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.core.service.DiscountRebateService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.RefundRecordService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.JPushUtil;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.baofoo.BaofooUtil;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

@Service
public class DiscountrecordServiceImpl extends BaseDao<Discountrecord, Integer> implements DiscountrecordService {

	private static Logger logger = Logger.getLogger(DiscountrecordServiceImpl.class);
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	@Resource
	NoticeAddDao noticeAddDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;
	
	@Resource
	MemberDao memberDao;
	
	@Resource
	DistributeOrderDao distributeOrderDao;

	@Resource
	DiscountrecordSpDao discountrecordSpDao;
	
	@Resource
	DiscountrecordPlDao discountrecordPlDao;

	@Resource
	DistributeOrderTaskDao distributeOrderTaskDao;
	
	@Resource
	OrgDtoDao orgDtoDao;
	
	@Resource
	OrgDao orgDao;
	
	@Resource
	PriceDao priceDao;
	
	@Resource
	TiexianNoticeDao tiexianNoticeDao;
	
	@Resource
	MemberService memberService;
	
	@Resource
	RefundRecordService refundRecordService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	DiscountRebateService discountRebateService;
	
	@Override
	public void deleteDis(Discountrecord discountrecord) {
		discountrecordDao.delete(discountrecord);
	}

	@Override
	public List<Discountrecord> getList(String id) {
		List<Discountrecord> list = discountrecordDao.getList(id);
		return list;
	}
	
	@Override
	public void updateDis(Discountrecord discountrecord) throws Exception {
		discountrecordDao.updateDiscountrecord(discountrecord);
		
		//保存一条操作记录
		DiscountrecordTask task = new DiscountrecordTask();
		task.setCreateTime(new Date());
		task.setDiscountrecordId(discountrecord.getId());
		Operator operator = Operator.valueOf(discountrecord.getOrderflag());
		task.setOperator(operator);
		task.setOperatorDesc(operator.getName());
		task.setOperatorId(discountrecord.getMemberid());
		task.setOperatorType(OperatorType.MEMBER);
		discountrecordTaskDao.saveModel(task);
	}

	@Override
	public Long countbyOrderflag(Integer orderflag1, Integer orderflag2) {
		Long count = discountrecordDao.countbyOrderflag(orderflag1, orderflag2);
		return count;
	}

	@Override
	public Long countbyOrdertime(Date ordertime1, Date ordertime2, Integer orderflag) {
		Long count = discountrecordDao.countbyOrdertime(ordertime1, ordertime2, orderflag);
		return count;
	}

	@Override
	public List<Discountrecord> getList(Date begintimed, Date endtimed) {
		List<Discountrecord> list = discountrecordDao.getList(begintimed, endtimed);
		return list;		
	}

	@Override
	public PageResults<Discountrecord> findPageModel(int pageNo, int pageSize, Object[] values) {
		String hql = "from discountrecord where ordertime >= ? and ordertime <= ? and ordertime >= ? order by ordertime desc ";
		String hqlcount = "select count(*) from discountrecord where ordertime >= ? and ordertime <= ? and ordertime >= ? ";
		if (values.length == 4) {
			 hql = "from discountrecord where ordertime >= ? and ordertime <= ? and orderflag=? and ordertime >= ? order by ordertime desc ";
			 hqlcount = "select count(*) from discountrecord where ordertime >= ? and ordertime <= ? and orderflag=? and ordertime >= ? ";
		}
		PageResults<Discountrecord> pageResults = discountrecordDao.findPageModel(hql, hqlcount, pageNo, pageSize, values);
		return pageResults;
	}

	@Override
	public List<Discountrecord> getList(Integer orderflag1, Integer orderflag2,Integer memberid) {
		List<Discountrecord> baseEntityList = discountrecordDao.getList(orderflag1,orderflag2,memberid);
		
		List<Discountrecord> discountrecordList = null;
		if(!(baseEntityList == null || baseEntityList.size() == 0)){
			discountrecordList = new ArrayList<Discountrecord>();
			for(Discountrecord baseEntity : baseEntityList){
				Discountrecord discountrecord = (Discountrecord)baseEntity;							
				discountrecord.setOrdertimeshow(new SimpleDateFormat("yyyy-MM-dd").format(discountrecord.getOrdertime()));
				discountrecordList.add(discountrecord);
			}
		}
		
		return discountrecordList;
	}

	@Override
	public List<Discountrecord> getList(Integer orderflag, Integer memberid) {
		List<Discountrecord> discountrecords = discountrecordDao.getList(orderflag,memberid);
		return discountrecords;
	}

	@Override
	public Double allmoney(Date begintimed, Date endtimed) {
		Double allmoney = discountrecordDao.allmoney(begintimed, endtimed);
		return  allmoney == null? 0.0 : allmoney ;
	}

	@Override
	public void addDiscountrecord(Discountrecord discountrecord) throws Exception {
		discountrecordDao.addDiscountrecord(discountrecord);
		//保存一条操作记录
		DiscountrecordTask task = new DiscountrecordTask();
		task.setCreateTime(new Date());
		task.setDiscountrecordId(discountrecord.getId());
		task.setOperator(Operator.UNCONFIRM);
		task.setOperatorDesc("订单提交成功，请耐心等待");
		task.setOperatorId(discountrecord.getMemberid());
		task.setType(0);//银票
		discountrecordTaskDao.saveModel(task);
	}

	@Override
	public Double allmoney(Integer salepriceId) {
		return discountrecordDao.allmoney(salepriceId);
	}

	@Override
	public List<Discountrecord> getListbyMemberid(String id, String memberid) {
		return discountrecordDao.getList(id, memberid);
	}

	@Override
	public List<Discountrecord> getListFlag(String id, String memberid,
			Integer orderflag1) {
				
		return discountrecordDao.getListFlag(id, memberid, orderflag1);
	}

	@Override
	public List<Discountrecord> getTopDiscountrecode(int i) {
		return discountrecordDao.getTopDiscountrecode(i);
	}

	@Override
	public Double countPriceByWeek(Integer memberid) {
		return discountrecordDao.countByWeek(memberid);
	}
	
	@Override
	public Double countPriceByWeek1(Integer memberid) {
		return discountrecordDao.countByWeek1(memberid);
	}

	@Override
	public Double countAll(Integer memberid) {
		return discountrecordDao.countAll(memberid);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getById(java.lang.Integer)
	 */
	public Discountrecord getById(Integer id){
		return discountrecordDao.getById(id);
	}
	
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getByOrdernumber(java.lang.String)
	 */
	public Discountrecord getByOrdernumber(String ordernumber){
		return discountrecordDao.getByOrdernumber(ordernumber);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getByBnsNoDiscount(java.lang.String)
	 */
	public Discountrecord getByBnsNoDiscount(String bnsno){
		return discountrecordDao.getByBnsNoDiscount(bnsno);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getModelByBnsNoDiscount(java.lang.String)
	 */
	public Discountrecord getModelByBnsNoDiscount(String bnsno){
		return discountrecordDao.getModelByBnsNoDiscount(bnsno);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<Discountrecord> getPageList(DiscountrecordForm nf,Integer pageIndex,Integer pageSize){
		return discountrecordDao.getPageList(nf, pageIndex, pageSize);
	}

	@Override
	public Double countFinish(Integer memberid) {
		return discountrecordDao.countFinish(memberid);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getMoneyLastMonth()
	 */
	public Double getMoneyLastMonth(){
		return discountrecordDao.getMoneyLastMonth();
	}
	
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getMoneyAllFinish()
	 */
	public Double getMoneyAllFinish(){
		return discountrecordDao.getMoneyAllFinish();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getList(com.ry.core.form.DiscountrecordForm)
	 */
	public List<Discountrecord> getList(DiscountrecordForm df){
		return discountrecordDao.getList(df);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getFinishByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getFinishByStartAndEnd(Date start,Date end){
		return discountrecordDao.getFinishByStartAndEnd(start, end);
	}
	
	
	public PageResults<Discountrecord> getPageDisRecord(MemOrderPageRequest mem){
		return discountrecordDao.getPageDisRecord(mem);
	}
	
	
	public void updateMemById(UpdateMemRequest req){
		Discountrecord record= discountrecordDao.querybyId(req.getId());
		record.setOrderflag(-2);//删除（待复核）
		discountrecordDao.updateDiscountrecord(record);
	}
	
	
	public DispatchOrderInofResponse dispatchOrderInfo(DispatchOrderInofRequest req)throws Exception{
		DispatchOrderInofResponse  respoonse = new DispatchOrderInofResponse();
		Discountrecord  orderInfo = discountrecordDao.querybyId(req.getId());
		Member member = memberDao.getById(orderInfo.getMemberid());
		respoonse.setMember(member);
		respoonse.setRecord(orderInfo);
		return respoonse;
	}
	
	
	public List<Map<String,Object>> getInfoById(Integer id){
		return discountrecordDao.getInfoById(id);
	}
	
	
	public void updateOrderById(UpdateMemRequest req) throws Exception{
		Discountrecord record = discountrecordDao.getModelById(req.getMemId());
		//取消订单（待复核）-2
		if(req.getType() == OrderState.NOAUDIT.getCode()){
			record.setOrderflag(OrderState.NOAUDIT.getCode());
			
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（待复核）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.NOAUDIT, des);//记录task（企业）
		}else if(req.getType() == OrderState.BETICKET.getCode()){//派单2
			DistributeOrder order = new DistributeOrder();
			order.setDcrdId(record.getId());
			order.setOrgId(req.getOrgId());
			order.setState(OrderState.BECONFIRM.getCode());
			order.setNo(createDistributeOrderNo());
			order.setCreateTime(new Date());
			
			Date start = record.getBegintime();
			Date end = record.getEndtime();
			Integer type1 = record.getType1();
			Float allmoney = record.getAllmoney();
			
			Integer shengDay = DateUtil.daysBetween(start, end);//天数（对应几个月）
			Integer tzts = getTzts(type1,end);//调整天数（根据票据类型）
			Integer jxts = shengDay + tzts;//计息天数
			
			//保存当前报价数据
			Map<String,Object> price = getPrice(record, req.getOrgId());
			if(price==null)throw new Exception("该机构无匹配报价");
			
			if(price!=null){
				Object rate_ = price.get("rate");
				Object rate1_ = price.get("rate1");
				Object rate2_ = price.get("rate2");
				
				String rate = "--";
				String rate1 = "--";
				String rate2 = "--";
				
				if(rate_!=null)rate = rate_.toString();
				if(rate1_!=null)rate1 = rate1_.toString();
				if(rate2_!=null)rate2 = rate2_.toString();
				
				order.setPrice(rate);
				order.setPrice1(rate1);
				order.setPrice2(rate2);
				
				Float txlx = 0F;
				Float txje = 0F;
				if(1==type1){//纸票
					if(500<=allmoney){//大票
						Float r = 0F;
						if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						}
						txlx = r;
					}else{//小票
						Float r = 0F;
						if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
							if(StringUtils.hasText(rate1) && !"--".equals(rate1.trim())){
								r += (allmoney/10)*Float.valueOf(rate1);
							}
						}else if(StringUtils.hasText(rate2) && !"--".equals(rate2.trim())){
							r = (allmoney/10)*Float.valueOf(rate2);
						}
						txlx = r;
					}
				}else{//电票
					Float r = 0F;
					if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){//年利率
						r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
					}
					txlx = r;
				}
				if (allmoney != null && txlx != null) {//计算贴现金额保留2位小数
					txje = (allmoney * 10000 - txlx);
					txje = (float) (Math.round(txje * 100)) / 100;
				}
				String tzts_ = "--";
				String jxts_ = "--";
				String txlx_ = "--";
				String txje_ = "--";
				if(tzts!=null)tzts_ = tzts.toString();
				if(jxts!=null)jxts_ = jxts.toString();
				if(txlx!=null)txlx_ = txlx.toString();
				if(txje!=null)txje_ = txje.toString();
				order.setTzts(tzts_);//调整天数
				order.setJxts(jxts_);//计息天数
				order.setTxlx(txlx_);//贴现利息
				order.setTxje(txje_);//贴现金额
				if(price.get("way")!=null){
					order.setWay(Integer.valueOf(price.get("way").toString()));
				}else{
					order.setWay(0);//[大票不分方式，默认利率，结构接单报价用]报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
				}
			}
			
			distributeOrderDao.saveDistributeOrder(order);
			
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服已派单";
			saveDistributeOrderTask(req.getOperatorId(),req.getOperatorType(),order.getId(), OrderState.BECONFIRM.getCode(), des);//记录task（机构）
			
			Map<String,Object> member = memberService.getInfoByOrgId(req.getOrgId());//机构端发短信
			Map<String,String> param = new HashMap<String, String>();
			param.put("type1",record.getType1()==1?"纸票":"电票");
			param.put("type2",DataMatchUtil.getCDHByTypeFromNew(record.getType2()));
			param.put("allmoney", record.getAllmoney()!=null?record.getAllmoney().toString():"");
			param.put("endtime",DateUtil.formart(record.getEndtime(), "yyyy-MM-dd"));
			if(member!=null && member.get("mobile")!=null){
				SendMessagesUtil.sendMessage(member.get("mobile").toString(), "SMS_7765032", param);
			}
			
		}else if(req.getType() == OrderState.INVALID.getCode()){//复核订单（企业）通过[变为：无效订单]0
			record.setOrderflag(OrderState.INVALID.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（通过）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.INVALID, des);//记录task（企业）
		}else if(req.getType() == OrderState.BECONFIRM.getCode()){//复核订单（企业）拒绝[变为：待确认]1
			record.setOrderflag(OrderState.BECONFIRM.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（拒绝）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.UNCONFIRM, des);//记录task（企业）
		}
		discountrecordDao.updateDiscountrecord(record);
	}
	
	/**
	 * 获取当前机构报价
	 * @author WKX
	 * @param record
	 * @param orgId
	 * @throws ParseException
	 * @since 2016年4月7日 下午2:57:03
	 */
	public Map<String,Object> getPrice(Discountrecord record,Integer orgId) throws ParseException{
		if(record==null || orgId==null)return null;
		PriceType priceType = new PriceType();
		Integer type1 = record.getType1();//纸票、电票
		Float allmoney = record.getAllmoney();//总金额
		if(type1==1){//纸票
			priceType.setType2(0);
		}else if(type1==2){//电票
			priceType.setType2(1);
			if(allmoney >= 500){
				priceType.setType5(record.getAcceptTime());//承兑期限（0半年期、1一年期）
			}else{
				int shengDay = DateUtil.daysBetween(record.getBegintime(), record.getEndtime());//天数（对应几个月）
				int tzts = getTzts(type1,record.getEndtime());//调整天数（根据票据类型）
				int jxts = shengDay + tzts;//计息天数
				//小于等于90天0、91-178天1、大于等于179天2
				if(allmoney<500){//大票没有票据属性
					if(179<=jxts){
						priceType.setType4(2);
					}else if(91<=jxts && jxts<179){
						priceType.setType4(1);
					}else if(0<=jxts && jxts<91){
						priceType.setType4(0);
					}
	            }
			}
		}
		//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
		if(allmoney >= 500){
			priceType.setType1(0);
		}else if(100 <= allmoney && allmoney < 500){
			priceType.setType3(2);
			priceType.setType1(1);
		}else if(50 <= allmoney && allmoney < 100){
			priceType.setType3(1);
			priceType.setType1(1);
		}else{
			priceType.setType3(0);
			priceType.setType1(1);
		}
		
		List<Map<String,Object>> list = priceDao.getByPriceTypeAndOrgId(priceType, orgId);
		if(list!=null && list.size()>0){
			Map<String,Object> temp = list.get(0);
			String key = DataMatchUtil.getNameByStateCDH(record.getType2());
			temp.put("rate", temp.get(key));//月（年）利率
			temp.put("rate1", temp.get(key+"1"));//参数
			temp.put("rate2", temp.get(key+"2"));//10万贴现多少钱
			temp.put("way", temp.get("way"));//报价方式
			return temp;
		}else{
			return null;
		}
	}
	
	/**
	 * 保存贴现订单task
	 * @author WKX
	 * @param operatorId 操作员
	 * @param operatorType 操作员类型（ADMIN、MEMBER）
	 * @param discountrecordId 贴现主表主键
	 * @param operator 操作（状态）
	 * @param des 备注
	 * @throws Exception
	 * @since 2016年3月14日 上午10:52:59
	 */
	private void saveDiscountrecordTask(Integer operatorId,OperatorType operatorType,Integer discountrecordId,Operator operator,String des) throws Exception{
		DiscountrecordTask task = new DiscountrecordTask();
		task.setDiscountrecordId(discountrecordId);
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setOperator(operator);
		task.setOperatorDesc(des);
		task.setCreateTime(new Date());
		task.setType(0);//银票
		discountrecordTaskDao.saveModel(task);
	}
	
	/**
	 * 保存派单（机构订单）task
	 * @param operatorId 操作员
	 * @param operatorType 操作员类型（ADMIN、MEMBER）
	 * @param dtboId 主表主键
	 * @param state 状态
	 * @param remarks 备注
	 * @throws Exception
	 * @author WKX
	 */
	private void saveDistributeOrderTask(Integer operatorId,OperatorType operatorType,Integer dtboId,Integer state,String remarks) throws Exception{
		DistributeOrderTask task = new DistributeOrderTask();
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setDtboId(dtboId);
		task.setState(state);
		task.setRemarks(remarks);
		task.setCreateTime(new Date());
		task.setType(0);//银票
		distributeOrderTaskDao.saveModel(task);
	}
	
	/**
	 * 获取机构订单
	 * @author LXF
	 */
	public String createDistributeOrderNo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		return "JG"+sdf.format(new Date());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordForm)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordForm form){
		return discountrecordDao.getPageList(pageIndex, pageSize, form);
	}
	
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPcPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordForm)
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordForm form){
		return discountrecordDao.getPcPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getInfoAndOrderById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoAndOrderById(Integer id){
		List<Map<String,Object>> result = discountrecordDao.getInfoAndOrderById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#updateReadTaskAndGetInfoAndOrderById(java.lang.Integer)
	 */
	public Map<String,Object> updateReadTaskAndGetInfoAndOrderById(Integer id) throws Exception{
		List<DiscountrecordTask> tasks = discountrecordTaskDao.getCurrentByDcrdId(id,0);//银票的type为1
		if(tasks!=null && tasks.size()>0){
			DiscountrecordTask task = tasks.get(0);
			if(task.getReadState()==ReadState.UNREAD){//最新记录未读
				task.setReadState(ReadState.READ);
				discountrecordTaskDao.saveModel(task);
			}
		}
		List<Map<String,Object>> result = discountrecordDao.getInfoAndOrderById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#updateAndTaskById(com.ry.core.entity.Discountrecord, com.ry.core.entity.DistributeOrder, java.lang.Integer, java.lang.String)
	 */
	public void updateAndTaskToInvalid(Discountrecord record,DistributeOrder order,Integer operatorId,String des) throws Exception{
		payLog.info("更新订单：进入方法updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
		if(record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit()>0.0){//已支付（APP2.2添加）
			if(order!=null && order.getDepositState()!=null && order.getDepositState()==1 && order.getDeposit()>0.0){
				//（给机构-转）（并退还机构）
				if(order.getPayWay() == 3){//返还机构的钱
					Map<String, String> paras = new LinkedHashMap<String, String>();
					paras.put("orderid", order.getBnsNo());//商户订单号（小写）
					paras.put("amount", ""+order.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					Map<String, String> res = Bill99Util.receive(paras);
					if("Y".equals(res.get("RESULT"))){
						order.setRefundState(1);
					}else{
						order.setRefundState(0);
					}
				}else if(order.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
					Map<String,String> param = new HashMap<String,String>();
					param.put("bnsNo",order.getBnsNo());//原商户发起的支付订单号
					param.put("money",order.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
					param.put("reason","退还接单押金");//退款原因
					if("0000".equals(BaofooUtil.RefundFrom(param))){
						order.setRefundState(2);//退款中
					}else{
						order.setRefundState(0);
					}
				}else{
					if(order.getDeposit()!=null){
						AlipayUtil.refundsNoPwdByTradeNo(order.getJyh(), order.getDeposit().toString(), "退还接单押金");
						payLog.info("更新订单：[退还接单押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
					}	
				}
				refund(record, order, 1, 1);//转账给机构
				order.setDepositState(4);
				record.setDepositState(3);
				Org org = orgDao.getById(order.getOrgId());
				if(org!=null)pushAndSend(org.getMemberId(), order.getId(), Type.DISTRIBUTEORDER, "无效订单", "您的这笔订单由于出票方取消订单，出票方和您支付的押金均将退回您的支付账户，感谢您使用票据管家。", "SMS_10675925");
			}else{
				//（给企业-退）
				if(record.getPayWay() == 3){
					Map<String, String> paras = new LinkedHashMap<String, String>();
					paras.put("orderid", record.getBnsNo());//商户订单号（小写）
					paras.put("amount", ""+record.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					Map<String, String> res = Bill99Util.receive(paras);
					if("Y".equals(res.get("RESULT"))){
						record.setRefundState(1);
					}else{
						record.setRefundState(0);
					}
				}else if(record.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
					Map<String,String> param = new HashMap<String,String>();
					param.put("bnsNo",record.getBnsNo());//原商户发起的支付订单号
					param.put("money",record.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
					param.put("reason","退还贴现押金");//退款原因
					if("0000".equals(BaofooUtil.RefundFrom(param))){
						record.setRefundState(2);//退款中
					}else{
						record.setRefundState(0);
					}
				}else{
					if(record.getDeposit()!=null){
						AlipayUtil.refundsNoPwdByTradeNo(record.getJyh(), record.getDeposit().toString(), "退还贴现押金");
						payLog.info("更新订单：[退还贴现押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
					}
				}
				record.setDepositState(2);
				pushAndSend(record.getMemberid(), record.getId(), Type.DISCOUNTRECORD, "无效订单", "亲，您的保证金已退还给您，请注意查收哦！", "SMS_10680818");
			}
		}	
		discountrecordDao.updateDiscountrecord(record);
		saveDiscountrecordTask(operatorId, OperatorType.MEMBER, record.getId(), Operator.INVALID, des);
		if(order!=null){//企业未付，机构已经接单
			if(order.getDepositState()!=null && order.getDepositState()==1 && order.getDeposit()>0.0){//已支付,退还保证金（APP2.2添加）
				//（给机构-退）
				if(order.getPayWay() == 3){//返还机构的钱
					Map<String, String> paras = new LinkedHashMap<String, String>();
					paras.put("orderid", order.getBnsNo());//商户订单号（小写）
					paras.put("amount", ""+order.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					Map<String, String> res = Bill99Util.receive(paras);
					if("Y".equals(res.get("RESULT"))){
						order.setRefundState(1);
					}else{
						order.setRefundState(0);
					}
				}else if(order.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
					Map<String,String> param = new HashMap<String,String>();
					param.put("bnsNo",order.getBnsNo());//原商户发起的支付订单号
					param.put("money",order.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
					param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
					param.put("reason","退还贴现押金");//退款原因
					if("0000".equals(BaofooUtil.RefundFrom(param))){
						order.setRefundState(2);//退款中
					}else{
						order.setRefundState(0);
					}
				}else{//其他返还
					if(order.getDeposit()!=null)AlipayUtil.refundsNoPwdByTradeNo(order.getJyh(), order.getDeposit().toString(), "退还接单押金");
				}
				order.setDepositState(2);
				Org org = orgDao.getById(order.getOrgId());
				if(org!=null)pushAndSend(org.getMemberId(), order.getId(), Type.DISTRIBUTEORDER, "无效订单", "您的这笔订单由于出票方取消订单，出票方和您支付的押金均将退回您的支付账户，感谢您使用票据管家。", "SMS_10680818");
			}
			distributeOrderDao.updateDistributeOrder(order);
			saveDistributeOrderTask(operatorId, OperatorType.MEMBER, order.getId(), OrderState.INVALID.getCode(), des);
		}
			payLog.info("更新订单：执行完方法updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getTaskAndInfoById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id){
		return discountrecordDao.getTaskAndInfoById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getInfoAndImgById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoAndImgById(Integer id){
		List<Map<String,Object>> list = discountrecordDao.getInfoAndImgById(id);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#updateAndTaskToFinish(com.ry.core.entity.Discountrecord, com.ry.core.entity.DistributeOrder, java.lang.Integer, java.lang.String)
	 */
	public void updateAndTaskToFinish(Discountrecord record,DistributeOrder order,Integer operatorId,String des) throws Exception{
		if(record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit()>0.0){//未派单，但是之前已支付，取消把钱退给自己（APP2.2添加）,record.getDeposit()大于0  ，确实支付，退款
			//（给企业-退）
			//判断是否是快钱支付
			if(record.getPayWay() == 3){//快钱支付的
				Map<String, String> paras = new LinkedHashMap<String, String>();
				paras.put("orderid", record.getBnsNo());//商户订单号（小写）
				paras.put("amount", ""+record.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
				Map<String, String> res = Bill99Util.receive(paras);
				if("Y".equals(res.get("RESULT"))){
					record.setRefundState(1);
				}else{
					record.setRefundState(0);
				}
			}else if(record.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
				Map<String,String> param = new HashMap<String,String>();
				param.put("bnsNo",record.getBnsNo());//原商户发起的支付订单号
				param.put("money",record.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
				param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
				param.put("reason","退还贴现押金");//退款原因
				if("0000".equals(BaofooUtil.RefundFrom(param))){
					record.setRefundState(2);//退款中
				}else{
					record.setRefundState(0);
				}
			}else{//其他支付
				if(record.getDeposit()!=null)AlipayUtil.refundsNoPwdByTradeNo(record.getJyh(), record.getDeposit().toString(), "退还贴现押金");
			}
			record.setDepositState(2);
			pushAndSend(record.getMemberid(), order.getId(), Type.DISCOUNTRECORD, "已完成", "您的这笔订单已完成，您支付的保证金已退还给您，欢迎再次使用票据管家贴现。", "SMS_10680818");
		}
		discountrecordDao.updateDiscountrecord(record);
		saveDiscountrecordTask(operatorId, OperatorType.MEMBER, record.getId(), Operator.FINISH, des);
		if(order!=null && order.getDeposit()>0.0){
			//判断是否是快钱支付
			if(order.getPayWay() == 3){//快钱支付
				Map<String, String> paras = new LinkedHashMap<String, String>();
				paras.put("orderid", order.getBnsNo());//商户订单号（小写）
				paras.put("amount", ""+order.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
				Map<String, String> res = Bill99Util.receive(paras);
				if("Y".equals(res.get("RESULT"))){
					order.setRefundState(1);
				}else{
					order.setRefundState(0);
				}
			}else if(order.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
				Map<String,String> param = new HashMap<String,String>();
				param.put("bnsNo",order.getBnsNo());//原商户发起的支付订单号
				param.put("money",order.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
				param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
				param.put("reason","退还贴现押金");//退款原因
				if("0000".equals(BaofooUtil.RefundFrom(param))){
					order.setRefundState(2);//退款中
				}else{
					order.setRefundState(0);
				}
			}else{//其他支付
				if(order.getDepositState()!=null && order.getDepositState()==1 &&  order.getDeposit()>0.0){//已支付,退还保证金（APP2.2添加） order.getDeposit()大于0，确实支付，退款
					//（给机构-退）
					if(order.getDeposit()!=null)AlipayUtil.refundsNoPwdByTradeNo(order.getJyh(), order.getDeposit().toString(), "退还接单押金");
				}
			}
			order.setDepositState(2);
			Org org = orgDao.getById(order.getOrgId());
			if(org!=null)pushAndSend(org.getMemberId(), order.getId(), Type.DISTRIBUTEORDER, "已完成", "您的这笔订单已完成，您支付的保证金已退还给您，欢迎再次使用票据管家贴现。", "SMS_10680818");
			distributeOrderDao.updateDistributeOrder(order);
			saveDistributeOrderTask(operatorId, OperatorType.MEMBER, order.getId(), OrderState.COMPLETE.getCode(), des);
			
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(order.getOrgId(), DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(orgLimit!=null){//订单完成更新已用金额
				Float usedPrice = 0F;
				if(orgLimit.getUsedPrice()!=null)usedPrice = orgLimit.getUsedPrice();
				usedPrice += record.getAllmoney();
				orgLimit.setUsedPrice(usedPrice);
				orgLimitService.saveModel(orgLimit);
			}
		}
		discountRebateService.saveFromFinish(record);//贴现返利（2016-10-01~2017-12-31）
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#disImg(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Object> disImg(String orderflag,Date start,Date end) throws Exception {
		return discountrecordDao.disImg(orderflag, start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getMoneyByIdAndTime(java.lang.Integer, java.lang.Long, java.lang.Long)
	 */
	public Double getMoneyByIdAndTime(Integer memberId, Date start, Date end) {
		return discountrecordDao.getMoneyByIdAndTime(memberId, start, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getUnReadByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnReadByMemberId(Integer memberId,Integer orderflag){
		return discountrecordDao.getUnReadByMemberId(memberId, orderflag);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getByMemberId(java.lang.Integer)
	 */
	public List<Discountrecord> getByMemberId(Integer memberId){
		return discountrecordDao.getByMemberId(memberId);
	}
	
	/**
	 * 获取调整天数
	 * @author WKX
	 * @param type1
	 * @param end 到期日期
	 * @throws ParseException
	 * @since 2016年4月11日 下午8:31:58
	 */
	public int getTzts(Integer type1,Date end) throws ParseException{
		int init = 0;
		Notice notice = null;
		
		if(end==null)return init;
		List<Notice> list = tiexianNoticeDao.findByNowTime(end,DateUtil.formart(end, "yyyy"));
		if(list!=null && list.size()>0){
			notice = list.iterator().next();
		}
		
		if(notice!=null && notice.getEndDate()!=null){
			init = DateUtil.daysBetween(end, notice.getEndDate());//天数（对应几个月）
		}else{
			List<NoticeAdd> adds = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(6==w){
				Date temp = DateUtil.addDays(end, 1);
				adds = noticeAddDao.getByNowTime(temp);
				if(adds!=null && adds.size()>0){//当前是周六，但是周日也补班
				}else{
					adds = noticeAddDao.getByNowTime(end);
					if(adds!=null && adds.size()>0){//当前是周六，但是周六补班，周日放假
						init += 1;
					}else{
						init += 2;
					}
				}
			}else if(7==w){
				adds = noticeAddDao.getByNowTime(end);
				if(adds!=null && adds.size()>0){//当前是周日，周日补班
				}else{
					init += 1;
				}
			}
		}
		if(1==type1)init += 3;//纸票加3天
		return init;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getCountRecord(java.util.List, java.lang.Integer, java.lang.Long, java.lang.Long)
	 */
	public List<Map<String, Object>> getCountRecord(List<Object> paramList,Integer orderflag, Date startTime, Date endTime) {
		return discountrecordDao.getCountRecord(paramList, orderflag, startTime, endTime);
	}
	
	public List<Map<String, Object>> getCountAmount(Long time, Integer amount, List<Object> paramList){
		return discountrecordDao.getCountAmount(time, amount, paramList);
	}
	
	public List<Discountrecord> getByBnsNo(String no){
		return discountrecordDao.getByBnsNo(no);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#randomNo(java.lang.String)
	 */
	public String randomBnsNo(String prefix){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(new Date());
		for(int i = 0; i < 6; i++){
			str += (int)(Math.random()*10);
		}
		String code = prefix+str;
		List<Discountrecord> list = getByBnsNo(code);
		if(list!=null && list.size()>0)return randomBnsNo(prefix);
		else return code;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getUnCommentsByMemberId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnCommentsByMemberId(Integer memberId){
		return discountrecordDao.getUnCommentsByMemberId(memberId);
	}
	
	public List<Map<String, Object>> getAllList(){
		return discountrecordDao.getAllList();
	}
	
	public List<Map<String,Object>> getDeposit(String memberId){
		return discountrecordDao.getDeposit(memberId);
	}
	
	public PageResults<Map<String,Object>> getPcBnsDeposit(Integer pageIndex,Integer pageSize,String memberId){
		return discountrecordDao.getPcBnsDeposit(pageIndex,pageSize,memberId);
	}
	
	public List<Map<String,Object>> baoJinListJG(Integer orgId){
		return discountrecordDao.baoJinListJG(orgId);
	}
	
	public List<Map<String,Object>> ifComplete(Date datetime1,Date datetime2,Integer memberid){
		return discountrecordDao.ifComplete(datetime1, datetime2,memberid);
	}
	
	public List<Map<String,Object>> ifRefused(Date datetime1,Date datetime2,Integer memberid){
		return discountrecordDao.ifRefused(datetime1, datetime2, memberid);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#refund(com.ry.core.entity.Discountrecord, com.ry.core.entity.DistributeOrder, java.lang.Integer, java.lang.Integer)
	 */
	public void refund(Discountrecord record,DistributeOrder order,Integer toBelong,Integer type) throws Exception{
		RefundRecord re = new RefundRecord();
		if(type==null)type = 1;//类型（1转账、2退款（系统退款失败需要人工转账））
		if(record!=null){
			re.setDcrdId(record.getId());
		}	
		if(order!=null){
			re.setDtboId(order.getId());
		}
		re.setBelong(toBelong);
		if(toBelong!=null && toBelong==0){
			re.setMemberId(record.getMemberid());
			re.setPayWay(record.getPayWay());
			re.setCard(record.getCard());
			re.setBnsNo(record.getBnsNo());
			re.setJyh(record.getJyh());
			re.setMoney(record.getDeposit());
			re.setType(type);//类型（1转账、2退款（系统退款失败需要人工转账））
			re.setState(1);//操作类型（1待转账（退款）、2已完成）
			re.setCreateTime(new Date());
		}else if(toBelong!=null && toBelong==1){
			Org org = orgDao.getById(order.getOrgId());
			if(org!=null)re.setMemberId(org.getMemberId());
			re.setPayWay(order.getPayWay());
			re.setCard(order.getCard());
			re.setBnsNo(order.getBnsNo());
			re.setJyh(order.getJyh());
			re.setMoney(order.getDeposit());
			re.setType(type);//类型（1转账、2退款（系统退款失败需要人工转账））
			re.setState(1);//操作类型（1待转账（退款）、2已完成）
			re.setCreateTime(new Date());
		}
		refundRecordService.saveModel(re);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#updateModel(com.ry.core.entity.Discountrecord)
	 */
	public void updateModel(Discountrecord discountrecord) throws Exception{
		discountrecordDao.updateDiscountrecord(discountrecord);
	}
	
	/**
	 * 推送消息、保存消息、发送短信
	 * @author WKX
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

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#updateAndRefund(com.ry.core.entity.Discountrecord, com.ry.core.entity.DistributeOrder, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateAndRefund(Discountrecord record, DistributeOrder order, Integer toBelong, Integer type)throws Exception {
		if(record!=null)discountrecordDao.updateDiscountrecord(record);
		if(order!=null)distributeOrderDao.updateDistributeOrder(order);
		refund(record, order, toBelong, type);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageMapList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		return discountrecordDao.getPageMapList(mem, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		return discountrecordDao.getPageList(mem, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageMapListVisit(com.ry.core.form.MemOrder.MemOrderPageRequest, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageMapListVisit(MemOrderPageRequest mem, Integer pageNo,Integer pageSize) {
		return discountrecordDao.getPageMapListVisit(mem, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getListByHandleState(com.ry.core.form.DiscountrecordForm)
	 */
	public List<Discountrecord> getListByHandleState(DiscountrecordForm form) {
		return discountrecordDao.getListByHandleState(form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#mergeModel(com.ry.core.entity.Discountrecord)
	 */
	public void mergeModel(Discountrecord discountrecord) {
		discountrecordDao.mergeModel(discountrecord);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getByRefundState(java.lang.Integer)
	 */
	public List<Discountrecord> getByRefundState(Integer refundState){
		return discountrecordDao.getByRefundState(refundState);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getModelById(java.lang.Integer)
	 */
	public Discountrecord getModelById(Integer id){
		return discountrecordDao.getModelById(id);
	}

	@Override
	public ViewExcel getByExcelData(MemOrderPageRequest mem,String orderType) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String orderTypeStr = "";
		String week = "";
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		String[] headData = new String[] {
				"订单号", 
				"用户订单注册手机号", 
				"下单日期", 
				"下单时间", 
				"订单金额", 
				"票据类型", 
				"承兑行类型", 
				"贴现日期", 
				"到期日期", 
				"交易地区", 
				"是否上门", 
				"订单状态", 
				"业务处理状态", 
				"派单方式", 
				"订单备注"};
		try{
			List<Discountrecord> reply = new ArrayList<Discountrecord>();
			List<DiscountrecordSp> replySP = new ArrayList<DiscountrecordSp>();
			List<DiscountrecordPl> replyPL = new ArrayList<DiscountrecordPl>();
			if(mem.getList()!=null && mem.getList()==1){
				week = "（两周）";
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				if(!StringUtils.hasText(mem.getEndDate())){
					mem.setEndDate(DateUtil.formart(c.getTime(),DateUtil.FORMART3));
				}
				if(!StringUtils.hasText(mem.getStartDate())){
					c.add(Calendar.DATE,-14);
					mem.setStartDate(DateUtil.formart(c.getTime(),DateUtil.FORMART3));
				}
			}
			if(orderType.equals("1")){//銀票
				orderTypeStr = "银票";
				reply = discountrecordDao.getByObj(mem);
				if(reply.size() > 0){
					for (Discountrecord rep : reply) {
						String[] data = new String[headData.length];
						data[0] = rep.getOrdernumber();//订单号
						data[1] = rep.getMembermobile();//用户订单注册手机号（用户ID）
						data[2] = DateUtil.formart(rep.getOrdertime(), DateUtil.FORMART3);//下单日期。
						data[3] = DateUtil.formart(rep.getOrdertime(), DateUtil.FORMART).substring(11, 19);//下单时间节点。
						data[4] = rep.getAllmoney().toString();//订单金额。
						data[5] = BillTypeEnum.getBillType(rep.getType1());//票据类型
						data[6] = BankTypeEnum.GetBankType(rep.getType2());//承兑行
						data[7] = DateUtil.formart(rep.getBegintime(), DateUtil.FORMART3);;//贴现日期（预出票日期）
						data[8] = DateUtil.formart(rep.getEndtime(), DateUtil.FORMART3);;//到期日期
						data[9] = rep.getPlace();//交易地区
						data[10] = NeedTodoorEnum.getBillType(rep.getNeedTodoor());//是否上门
						data[11] = OrderState.getOrderState(rep.getOrderflag());//订单状态
						data[12] = HandleStateEnum.getHandleState(rep.getHandleState());//处理状态
						data[13] = "人工派单";//派单方式/状态（人工派单/自动派单）？（已派单/待派单）？
						data[14] = rep.getMemberother();//订单备注。
						dataList.add(data);
					}
				}else{
					String[] data = new String[1];
					data[0] = "暂无数据";
					dataList.add(data);
				}
			}
			if(orderType.equals("2")){//商票
				orderTypeStr = "商票";
				replySP = discountrecordSpDao.getByObj(mem);
				if(replySP.size() > 0){
					for (DiscountrecordSp rep : replySP) {
						String[] data = new String[headData.length];
						data[0] = rep.getNo();//订单号
						data[1] = rep.getMemberMobile();//用户订单注册手机号（用户ID）
						data[2] = DateUtil.formart(rep.getCreateTime(), DateUtil.FORMART3);//下单日期。
						data[3] = DateUtil.formart(rep.getCreateTime(), DateUtil.FORMART).substring(11, 19);//下单时间节点。
						data[4] = rep.getAllmoney().toString();//订单金额。
						data[5] = BillTypeEnum.getBillType(rep.getType1());//（纸票、电票）
						data[6] = rep.getBank();
						data[7] = DateUtil.formart(rep.getBegintime(), DateUtil.FORMART3);;//贴现日期（预出票日期）
						data[8] = DateUtil.formart(rep.getEndtime(), DateUtil.FORMART3);;//到期日期
						data[9] = rep.getPlace();//交易地区
						data[10] = NeedTodoorEnum.getBillType(rep.getNeedTodoor());//是否上门
						data[11] = OrderState.getOrderState(rep.getOrderflag());//订单状态
						data[12] = HandleStateEnum.getHandleState(rep.getHandleState());//处理状态
						data[13] = "人工派单";//派单方式/状态（人工派单/自动派单）？（已派单/待派单）？
						data[14] = rep.getRemarks();//订单备注。
						dataList.add(data);
					}
				}else{
					String[] data = new String[1];
					data[0] = "暂无数据";
					dataList.add(data);
				}
			}
			if(orderType.equals("3")){//批量
				orderTypeStr = "批量";
				replyPL = discountrecordPlDao.getByObj(mem);
				if(replyPL.size() > 0){
					for (DiscountrecordPl rep : replyPL) {
						String[] data = new String[headData.length];
						data[0] = rep.getNo();//订单号
						data[1] = rep.getMemberMobile();//用户订单注册手机号（用户ID）
						data[2] = DateUtil.formart(rep.getCreateTime(), DateUtil.FORMART3);//下单日期。
						data[3] = DateUtil.formart(rep.getCreateTime(), DateUtil.FORMART).substring(11, 19);//下单时间节点。
						data[4] = rep.getAllmoney().toString();//订单金额。
						data[5] = BillTypeEnum.getBillType(rep.getType1());//（纸票、电票）
						data[6] = "";
						if(rep.getType2() != null){
							String [] strArray = rep.getType2().split(",");
							for(int i = 0;i<strArray.length;i++){
								data[6] += BankTypeEnum.GetBankType(Integer.parseInt(strArray[i]));//承兑行
							}
						}
						data[7] = DateUtil.formart(rep.getCreateTime(), DateUtil.FORMART3);//贴现日期（预出票日期）
						data[8] = DateUtil.formart(rep.getEndtime(), DateUtil.FORMART3);;//到期日期
						data[9] = rep.getPlace();//交易地区
						data[10] = NeedTodoorEnum.getBillType(rep.getNeedTodoor());//是否上门
						data[11] = OrderState.getOrderState(rep.getOrderflag());//订单状态
						data[12] = HandleStateEnum.getHandleState(rep.getHandleState());//处理状态
						data[13] = "人工派单";//派单方式/状态（人工派单/自动派单）？（已派单/待派单）？
						data[14] = rep.getRemarks();//订单备注。
						dataList.add(data);
					}
				}else{
					String[] data = new String[1];
					data[0] = "暂无数据";
					dataList.add(data);
				}
			}
		}catch(Exception e){
			throw e;
		}
		return new ViewExcel("企业"+orderTypeStr+"贴现订单列表"+week+sdf.format(new Date()), headData, dataList, endData);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordService#getPageMapList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getReportPageMapList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		return discountrecordDao.getReportPageMapList(mem, pageNo, pageSize);
	}
}