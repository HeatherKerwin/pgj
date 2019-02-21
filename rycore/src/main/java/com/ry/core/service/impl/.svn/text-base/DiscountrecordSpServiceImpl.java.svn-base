package com.ry.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.Enum.OrderState;
import com.ry.core.dao.DiscountrecordSpDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.dao.DistributeOrderSpDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.dao.PriceDao;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Member;
import com.ry.core.entity.OrgLimit;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgLimitService;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

@Service
public class DiscountrecordSpServiceImpl implements DiscountrecordSpService {

	@Resource
	DiscountrecordSpDao discountrecordSpDao;
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;
	
	@Resource
	PriceDao priceDao;
	
	@Resource
	DistributeOrderSpDao distributeOrderSpDao;
	
	@Resource
	DistributeOrderTaskDao distributeOrderTaskDao;
	
	@Resource
	MemberDao memberDao;
	
	@Resource
	MemberService memberService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getById(java.lang.Integer)
	 */
	public DiscountrecordSp getById(Integer id) {
		return discountrecordSpDao.getById(id);
	}
	
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getByOrdernumber(java.lang.String)
	 */
	public DiscountrecordSp getByOrdernumber(String ordernumber){
		return discountrecordSpDao.getByOrdernumber(ordernumber);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#saveModel(com.ry.core.entity.DiscountrecordSp)
	 */
	public void saveModel(DiscountrecordSp discountrecordSp) {
		discountrecordSpDao.saveModel(discountrecordSp);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#updateModel(com.ry.core.entity.DiscountrecordSp)
	 */
	public void updateModel(DiscountrecordSp discountrecordSp) {
		discountrecordSpDao.updateModel(discountrecordSp);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordSpForm)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordSpForm form){
		return discountrecordSpDao.getPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getPcPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordSpForm)
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordSpForm form){
		return discountrecordSpDao.getPcPageList(pageIndex, pageSize, form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getPageDisRecordSp(com.ry.core.form.MemOrder.MemOrderPageRequest)
	 */
	public PageResults<Map<String,Object>> getPageDisRecordSp(MemOrderPageRequest mem) {
		return discountrecordSpDao.getPageDisRecordSp(mem);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id) {
		return discountrecordSpDao.getInfoById(id);
	}

	public void updateMemById(UpdateMemRequest req) {
		DiscountrecordSp record= discountrecordSpDao.getById(req.getId());
		record.setOrderflag(-2);//删除（待复核）
		discountrecordSpDao.updateModel(record);
	}

	public void updateOrderById(UpdateMemRequest req) throws Exception {
		DiscountrecordSp record = discountrecordSpDao.getById(req.getMemId());
		if(req.getType() == 0){//复核订单（企业）通过[变为：无效订单]0
			record.setOrderflag(OrderState.INVALID.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（通过）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.INVALID, des);//记录task（企业）
		}else if(req.getType() == 1){//复核订单（企业）拒绝[还是原来状态]1
			record.setOrderflag(OrderState.BECONFIRM.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（拒绝）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.UNCONFIRM, des);//记录task（企业）
		}
		discountrecordSpDao.updateModel(record);
	}
	
	/**
	 * 保存派单（机构订单）task
	 * @author KHC
	 * @param operatorId 操作员
	 * @param operatorType 操作员类型（ADMIN、MEMBER）
	 * @param dtboId 主表主键
	 * @param state 状态
	 * @param remarks 备注
	 * @throws Exception
	 * @since 2016年8月13日 上午11:52:59
	 */
	private void saveDistributeOrderTask(Integer operatorId,OperatorType operatorType,Integer dtboId,Integer state,String remarks) throws Exception{
		DistributeOrderTask task = new DistributeOrderTask();
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setDtboId(dtboId);
		task.setState(state);
		task.setRemarks(remarks);
		task.setCreateTime(new Date());
		task.setType(1);
		distributeOrderTaskDao.saveModel(task);
	}

	/**
	 * 保存贴现订单task
	 * @author KHC
	 * @param operatorId 操作员
	 * @param operatorType 操作员类型（ADMIN、MEMBER）
	 * @param discountrecordId 贴现主表主键
	 * @param operator 操作（状态）
	 * @param des 备注
	 * @throws Exception
	 * @since 2016年8月13日 上午10:52:59
	 */
	private void saveDiscountrecordTask(Integer operatorId,OperatorType operatorType,Integer discountrecordSpId,Operator operator,String des) throws Exception{
		DiscountrecordTask task = new DiscountrecordTask();
		task.setDiscountrecordId(discountrecordSpId);
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setOperator(operator);
		task.setType(1);
		task.setOperatorDesc(des);
		task.setCreateTime(new Date());
		discountrecordTaskDao.saveModel(task);
	}

	/**
	 * 获取机构订单
	 * @author KHC
	 */
	public String createDistributeOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		return "JG"+sdf.format(new Date());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getOrg(com.ry.core.form.orgOrder.OrderRequest)
	 */
	public List<Map<String, Object>> getOrgList(DiscountrecordSp discountrecordSp) {
		return discountrecordSpDao.getOrgList(discountrecordSp);
	}
	
	@Override
	public Map<String,Object> getInfoAndOrderById(Integer id){
		List<Map<String,Object>> result = discountrecordSpDao.getInfoAndOrderById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#updateToInvalid(com.ry.core.entity.DiscountrecordSp)
	 */
	public void updateToInvalid(DiscountrecordSp discountrecordSp){
		discountrecordSpDao.updateModel(discountrecordSp);
		List<DistributeOrderSp> list = distributeOrderSpDao.getByDcrdId(discountrecordSp.getId());
		if(list!=null && list.size()>0){
			for(DistributeOrderSp dist:list){
				dist.setState(0);
				distributeOrderSpDao.updateModel(dist);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#updateToFinish(com.ry.core.entity.DiscountrecordSp)
	 */
	public void updateToFinish(DiscountrecordSp discountrecordSp){
		discountrecordSpDao.updateModel(discountrecordSp);
		
		DistributeOrderSpForm form = new DistributeOrderSpForm();
		form.setDcrdSpId(discountrecordSp.getId());
		form.setIsSelect(1);//被选择过
		form.setStates(new Integer[]{1,2});//交易中
		List<DistributeOrderSp> list = distributeOrderSpDao.getList(form);
		if(list!=null && list.size()>0){
			DistributeOrderSp dist = list.get(0);
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(dist.getOrgId(), DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(orgLimit!=null){//订单完成更新已用金额
				Float usedPriceSp = 0f;
				if(orgLimit.getUsedPriceSp()!=null)usedPriceSp = orgLimit.getUsedPriceSp();
				usedPriceSp =(float)(usedPriceSp + discountrecordSp.getAllmoney());
				orgLimit.setUsedPriceSp(usedPriceSp);
				orgLimitService.saveModel(orgLimit);
			}
			dist.setState(3);
			distributeOrderSpDao.updateModel(dist);
			sendMessage(discountrecordSp,dist);
		}
	}

	public void sendMessage(DiscountrecordSp disc,DistributeOrderSp dist){//完成订单后给双方发送短信
		Map<String,String> param = new HashMap<String, String>();
		param.put("allmoney", disc.getAllmoney()!=null?disc.getAllmoney().toString():"");
		param.put("endtime",DateUtil.formart(disc.getEndtime(), DateUtil.FORMART3));
		Member member = memberDao.getById(disc.getMemberId());
		if(member!=null && member.getMobile()!=null){
			SendMessagesUtil.sendMessage(member.getMobile(), "SMS_14550068", param);
		}
		Map<String,Object> member1 = memberService.getInfoByOrgId(dist.getOrgId());
		if(dist!=null && member1!=null && member1.get("mobile")!=null){
			SendMessagesUtil.sendMessage(member1.get("mobile").toString(), "SMS_14725095", param);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#updateToInvalidByAdmin(com.ry.core.form.MemOrder.UpdateMemRequest)
	 */
	public void updateToInvalidByAdmin(UpdateMemRequest req) {
		DiscountrecordSp disc = discountrecordSpDao.getById(req.getMemId());
		if(req.getType()==-2 && disc.getOrderflag()==1){//待交易状态才能取消订单
			try {
				disc.setOrderflag(req.getType());
				String des = req.getDes();
				if(!StringUtils.hasText(des))des = "客服取消订单（待复核）";
				discountrecordSpDao.updateModel(disc);
				saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),disc.getId(), Operator.NOAUDIT, des);//记录task（企业）
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#updatePaidan(com.ry.core.form.MemOrder.UpdateMemRequest)
	 */
	public boolean updatePaidan(UpdateMemRequest req) {
		List<DistributeOrderSp> list = null;
		DiscountrecordSp disc = discountrecordSpDao.getById(req.getMemId());
		boolean flag = false;
		try {
			int shengDay = com.ry.util.DateUtil.daysBetween(disc.getBegintime(), disc.getEndtime());//天数（对应几个月） 
			int tzts = discountrecordService.getTzts(disc.getType1(), disc.getEndtime());//调整天数（根据票据类型）
			int jxts = shengDay + tzts;//计息天数
			DistributeOrderSp orderSp = null;
			for(Integer orgId:req.getOrgIds()){
				list = distributeOrderSpDao.getListByDiscIdAndOrgId(req.getMemId(), orgId);
				orderSp = new DistributeOrderSp();
				orderSp.setDcrdSpId(req.getMemId());
				orderSp.setOrgId(orgId);
				orderSp.setState(OrderState.BECONFIRM.getCode());
				orderSp.setNo(createDistributeOrderNo());
				orderSp.setCreateTime(new Date());
				orderSp.setJxts(String.valueOf(jxts));
				if(list!=null && list.size()>0 && orgId!=null){
					for (int i = 0; i < list.size(); i++) {
						if(list.get(i).getState()==0){//机构接单超时后，还可以继续派单
							try {
								distributeOrderSpDao.saveModel(orderSp);
								
								String des = "客服已派单";
								saveDistributeOrderTask(req.getOperatorId(),req.getOperatorType(),orderSp.getId(), OrderState.BECONFIRM.getCode(), des);//记录task（机构）
								flag = true;
							} catch (Exception e) {
								e.printStackTrace();
								flag = false;
							}
						}
					}
				}
				if(((list==null||list.size()<=0) && orgId!=null)){
					try {
						distributeOrderSpDao.saveModel(orderSp);
						
						String des = "客服已派单";
						saveDistributeOrderTask(req.getOperatorId(),req.getOperatorType(),orderSp.getId(), OrderState.BECONFIRM.getCode(), des);//记录task（机构）
						flag = true;
					} catch (Exception e) {
						e.printStackTrace();
						flag = false;
					}
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(flag){//短信提醒
			DiscountrecordSp dis=discountrecordSpDao.getById(req.getMemId());
			Member member = memberDao.getById(dis.getMemberId());
			Map<String,String> param = new HashMap<String, String>();
			param.put("allmoney", dis.getAllmoney()!=null?dis.getAllmoney().toString():"");
			param.put("endtime",DateUtil.formart(dis.getEndtime(), DateUtil.FORMART3));
			if(member!=null && member.getMobile()!=null){
				SendMessagesUtil.sendMessage(member.getMobile(), "SMS_14545077", param);
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getNeedPaidan()
	 */
	public List<Map<String, Object>> getNeedPaidan() {
		return discountrecordSpDao.getNeedPaidan();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSpService#getList(java.lang.String)
	 */
	public List<DiscountrecordSp> getList(String id){
		List<DiscountrecordSp> list = discountrecordSpDao.getList(id);
		return list;
	}
	
	public List<DiscountrecordSp> getByMemberId(Integer memberId){
		return  discountrecordSpDao.getByMemberId(memberId);
	}
	
	public DiscountrecordSp getModelById(Integer id){
		return discountrecordSpDao.getModelById(id);	
	}
	
	public List<DiscountrecordSp> getListByHandleState(DiscountrecordSpForm form){
		return discountrecordSpDao.getListByHandleState(form);
	}

	@Override
	public List<DiscountrecordSp> getByObj(MemOrderPageRequest mem) {
		return discountrecordSpDao.getByObj(mem);
	}

	@Override
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem) {
		return discountrecordSpDao.getPageMapList(mem);
	}
}