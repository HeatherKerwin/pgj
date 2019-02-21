package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.Enum.OrderState;
import com.ry.core.dao.DiscountrecordPlDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.dao.DistributeOrderPlDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.dao.PriceDao;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Member;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.MemberService;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

@Service
public class DiscountrecordPlServiceImpl implements DiscountrecordPlService {

	@Resource
	DiscountrecordPlDao discountrecordPlDao;
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;
	
	@Resource
	DistributeOrderTaskDao distributeOrderTaskDao;
	
	@Resource
	DistributeOrderPlDao distributeOrderPlDao;
	
	@Resource
	PriceDao priceDao;
	
	@Resource
	MemberService memberService;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getById(java.lang.Integer)
	 */
	public DiscountrecordPl getById(Integer id) {
		return discountrecordPlDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getByOrdernumber(java.lang.String)
	 */
	public DiscountrecordPl getByOrdernumber(String ordernumber){
		return discountrecordPlDao.getByOrdernumber(ordernumber);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#saveModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public void saveModel(DiscountrecordPl discountrecordPl) {
		discountrecordPlDao.saveModel(discountrecordPl);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public void updateModel(DiscountrecordPl discountrecordPl) {
		discountrecordPlDao.updateModel(discountrecordPl);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getPageDisRecordPl(com.ry.core.form.MemOrder.MemOrderPageRequest)
	 */
	public PageResults<Map<String,Object>> getPageDisRecordPl(MemOrderPageRequest mem) {
		return discountrecordPlDao.getPageDisRecordPl(mem);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id) {
		return discountrecordPlDao.getInfoById(id);
	}

	public void updateMemById(UpdateMemRequest req) {
		DiscountrecordPl record= discountrecordPlDao.getById(req.getId());
		record.setOrderflag(-2);//删除（待复核）
		discountrecordPlDao.updateModel(record);
	}

	@Override
	public void updateOrderById(UpdateMemRequest req) throws Exception {
		DiscountrecordPl record = discountrecordPlDao.getById(req.getMemId());
		if(req.getType() == 0){//复核订单（企业）通过[变为：无效订单]0
			record.setOrderflag(OrderState.INVALID.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（通过）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.INVALID, des);//记录task（企业）
		}else if(req.getType() == 1){//复核订单（企业）拒绝[变为：待交易]1
			record.setOrderflag(OrderState.BECONFIRM.getCode());
			String des = req.getDes();
			if(!StringUtils.hasText(des))des = "客服取消订单（拒绝）";
			saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),record.getId(), Operator.UNCONFIRM, des);//记录task（企业）
		}
		discountrecordPlDao.updateModel(record);
	}
	/**
	 * 获取机构订单
	 * @author KHC
	 */
	public String createDistributeOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		return "JG"+sdf.format(new Date());
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
	 * @since 2016年8月14日 上午11:52:59
	 */
	
	private void saveDistributeOrderTask(Integer operatorId,OperatorType operatorType,Integer dtboId,Integer state,String remarks) throws Exception{
		DistributeOrderTask task = new DistributeOrderTask();
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setDtboId(dtboId);
		task.setState(state);
		task.setRemarks(remarks);
		task.setCreateTime(new Date());
		task.setType(2);
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
	 * @since 2016年8月14日 上午10:52:59
	 */
	private void saveDiscountrecordTask(Integer operatorId,OperatorType operatorType,Integer discountrecordPlId,Operator operator,String des) throws Exception{
		DiscountrecordTask task = new DiscountrecordTask();
		task.setDiscountrecordId(discountrecordPlId);
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setOperator(operator);
		task.setOperatorDesc(des);
		task.setCreateTime(new Date());
		task.setType(2);
		discountrecordTaskDao.saveModel(task);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getUnReadByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getUnReadByMemberId(Integer memberId, Integer orderflag) {
		return discountrecordPlDao.getUnReadByMemberId(memberId, orderflag);
	}
	
	@Override
	public List<Map<String, Object>> getOrgList(DiscountrecordPl discountrecordPl) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list1 = discountrecordPlDao.getOrgListByPrice(discountrecordPl);//获取额度匹配的机构
		List<Map<String, Object>> list2 = discountrecordPlDao.getOrgListByType(discountrecordPl);//获取类型匹配的机构
		for(Map<String, Object> org1:list1){
			for(Map<String, Object> org2:list2){
				if(org2.get("org_id").toString().equals(org1.get("orgId").toString())){
					list.add(org1);
				}
			}
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form){
		return discountrecordPlDao.getPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getPcPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordPlForm)
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form){
		return discountrecordPlDao.getPcPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, Integer id){
		return discountrecordPlDao.getPageList(pageIndex, pageSize, id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public Map<String,Object> updateReadTaskAndGetInfoAndOrderById(Integer id) throws Exception{
		List<DiscountrecordTask> tasks = discountrecordTaskDao.getCurrentByDcrdId(id,2);//由于是批量订单，所以type直接可以设置为2
		if(tasks!=null && tasks.size()>0){
			DiscountrecordTask task = tasks.get(0);
			if(task.getReadState()==ReadState.UNREAD){//最新记录未读
				task.setReadState(ReadState.READ);
				discountrecordTaskDao.saveModel(task);
			}
		}
		List<Map<String,Object>> result = discountrecordPlDao.getInfoAndOrderById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	@Override
	public Map<String,Object> getInfoAndOrderById(Integer id){
		List<Map<String,Object>> result = discountrecordPlDao.getInfoAndOrderById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateToInvalid(com.ry.core.entity.DiscountrecordPl)
	 */
	public void updateToInvalid(DiscountrecordPl discountrecordPl){
		discountrecordPlDao.updateModel(discountrecordPl);
		List<DistributeOrderPl> list = distributeOrderPlDao.getByDcrdId(discountrecordPl.getId());
		if(list!=null && list.size()>0){
			for(DistributeOrderPl dist:list){
				dist.setState(0);
				distributeOrderPlDao.updateModel(dist);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateToFinish(com.ry.core.entity.DiscountrecordPl)
	 */
	public void updateToFinish(DiscountrecordPl discountrecordPl){
		discountrecordPlDao.updateModel(discountrecordPl);
		
		DistributeOrderPlForm form = new DistributeOrderPlForm();
		form.setDcrdPlId(discountrecordPl.getId());
		form.setStates(new Integer[]{1,2});//交易中
		List<DistributeOrderPl> list = distributeOrderPlDao.getList(form);
		if(list!=null && list.size()>0){
			DistributeOrderPl dist = list.get(0);
			dist.setState(3);
			distributeOrderPlDao.updateModel(dist);
			sendMessage(discountrecordPl,dist);
		}
	}
	
	public void sendMessage(DiscountrecordPl disc,DistributeOrderPl dist){//完成订单后给双方发送短信
		Map<String,String> param = new HashMap<String, String>();
		param.put("allmoney", disc.getAllmoney()!=null?disc.getAllmoney().toString():"");
		param.put("endtime",DateUtil.formart(disc.getEndtime(), DateUtil.FORMART3));
		Member member = memberService.getById(disc.getMemberId());
		if(member!=null && member.getMobile()!=null){
			SendMessagesUtil.sendMessage(member.getMobile(), "SMS_14500102", param);
		}
		Map<String,Object> member1 = memberService.getInfoByOrgId(dist.getOrgId());
		if(dist!=null && member1!=null && member1.get("mobile")!=null){
			SendMessagesUtil.sendMessage(member1.get("mobile").toString(), "SMS_14540088", param);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updateToInvalidByAdmin(com.ry.core.form.MemOrder.UpdateMemRequest)
	 */
	public void updateToInvalidByAdmin(UpdateMemRequest req) {
		DiscountrecordPl disc = discountrecordPlDao.getById(req.getMemId());
		if(req.getType()==-2){
			try {
				disc.setOrderflag(req.getType());
				String des = req.getDes();
				if(!StringUtils.hasText(des))des = "客服取消订单（待复核）";
				discountrecordPlDao.updateModel(disc);
				saveDiscountrecordTask(req.getOperatorId(),req.getOperatorType(),disc.getId(), Operator.NOAUDIT, des);//记录task（企业）
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#updatePaidan(com.ry.core.form.MemOrder.UpdateMemRequest)
	 */
	public boolean updatePaidan(UpdateMemRequest req) {
		List<DistributeOrderPl> list = null;
		boolean flag = false;
		for(Integer orgId:req.getOrgIds()){
			list = distributeOrderPlDao.getListByDiscIdAndOrgId(req.getMemId(), orgId);
			if((list==null||list.size()<=0)&&orgId!=null){
				try {
					DistributeOrderPl orderPl = new DistributeOrderPl();
					orderPl.setDcrdPlId(req.getMemId());
					orderPl.setOrgId(orgId);
					orderPl.setState(OrderState.BECONFIRM.getCode());
					orderPl.setNo(createDistributeOrderNo());
					orderPl.setCreateTime(new Date());
					distributeOrderPlDao.saveModel(orderPl);
					if(req.getOperatorId()!=null){
						String des = "客服已派单";
						saveDistributeOrderTask(req.getOperatorId(),req.getOperatorType(),orderPl.getId(), OrderState.BECONFIRM.getCode(), des);//记录task（机构）
					}
					flag = true;
				} catch (Exception e) {
					e.printStackTrace();
					flag = false;
				}
			}
		}
		if(flag){//短信提醒
			DiscountrecordPl dis=discountrecordPlDao.getById(req.getMemId());
			Member member = memberService.getById(dis.getMemberId());
			Map<String,String> param = new HashMap<String, String>();
			param.put("allmoney", dis.getAllmoney()!=null?dis.getAllmoney().toString():"");
			if(member!=null && member.getMobile()!=null){
				SendMessagesUtil.sendMessage(member.getMobile(), "SMS_14580037", param);
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getNeedPaidan()
	 */
	public List<Map<String, Object>> getNeedPaidan() {
		return discountrecordPlDao.getNeedPaidan();
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getByEndTime(java.util.Date)
	 */
	public List<DiscountrecordPl> getByEndTime(Date date) {
		return discountrecordPlDao.getByEndTime(date);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordPlService#getList(java.lang.String)
	 */
	public List<DiscountrecordPl> getList(String id){
		List<DiscountrecordPl> list = discountrecordPlDao.getList(id);
		return list;
	}
	
	public List<DiscountrecordPl> getByDiscountrecordPl(DiscountrecordPl dis) {
		return discountrecordPlDao.getByDiscountrecordPl(dis);
	}

	@Override
	public List<DiscountrecordPl> getByObj(MemOrderPageRequest mem) {
		return discountrecordPlDao.getByObj(mem);
	}
}