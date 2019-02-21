package com.ry.core.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.dao.DistributeOrderDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.dao.ImageDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.dao.PayRecordDao;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Image;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.PayRecord;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.MemberService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.JPushUtil;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.SendMessagesUtil;
import com.ry.util.baofoo.BaofooUtil;
import com.ry.util.page.PageResults;

@Service
public class DistributeOrderServiceImpl extends BaseDao<DistributeOrderForm, Integer> implements DistributeOrderService {

	private static Logger logger = Logger.getLogger(DistributeOrderServiceImpl.class);
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	@Resource
	DistributeOrderDao distributeOrderDao;
	
	@Resource
	DistributeOrderTaskDao distributeOrderTaskDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;
	
	@Resource
	OrgDao orgDao;
	
	@Resource
	ImageDao imageDao;
	
	@Resource
	PayRecordDao payRecordDao;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DistributeOrderForm form){
		return distributeOrderDao.getPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getUnReadByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnReadByOrgIdAndState(Integer orgId,Integer state){
		return distributeOrderDao.getUnReadByOrgId(orgId, state);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getById(java.lang.Integer)
	 */
	public DistributeOrder getById(Integer id){
		return distributeOrderDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getInfoById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoById(Integer id){
		return distributeOrderDao.getInfoById(id);
	}
	
	public void updateDistributeOrder (DistributeOrder dis){
		distributeOrderDao.updateDistributeOrder(dis);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#updateDisAndSaveTask(com.ry.core.entity.DistributeOrder, com.ry.core.entity.DistributeOrderTask, com.ry.core.entity.Discountrecord, com.ry.core.entity.DiscountrecordTask)
	 */
	public void updateDisAndSaveTask(DistributeOrder dis,DistributeOrderTask task,Discountrecord record,DiscountrecordTask recordTask) throws Exception{
		payLog.info("更新订单：进入方法updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
		//step.1退款1
		if(dis!=null && dis.getState()!=null && dis.getState()==-2){//拒绝订单
			if(dis.getDeposit()!=null && dis.getDepositState()!=null && dis.getDepositState()==1 && dis.getDeposit()>0.0){//机构已支付
				if(record!=null && record.getOrderflag()==0 && record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit()>0.0){
					if(record.getPayWay() == 3){ //企业快钱支付，企业返还快钱的保证金
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
					}else if(dis.getPayWay() != 3){//双方都不是快钱支付，全都转账给企业
						AlipayUtil.refundsNoPwdByTradeNo(record.getJyh(), record.getDeposit().toString(), "退还接单押金");
						payLog.info("更新订单：[退还贴现押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
					}else{//企业不是快钱支付，返还企业的保证金
						AlipayUtil.refundsNoPwdByTradeNo(record.getJyh(), record.getDeposit().toString(), "退还接单押金");
					}
					discountrecordService.refund(record, dis, 0, 1);//转账给企业
					dis.setDepositState(3);
					record.setDepositState(4);
				}else if(record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit()>0.0){
					discountrecordService.refund(record, dis, 0, 1);//转账给企业
					dis.setDepositState(3);
					pushAndSend(record.getMemberid(), record.getId(), Type.DISCOUNTRECORD, "待确认", "您的这笔订单由于收票方拒绝订单，我们会立刻将这笔订单推送给其他机构；另外收票方支付的保证金将转入您的支付账户，请耐心等待。", "SMS_10610819");
					Org org = orgDao.getById(dis.getOrgId());
					if(org!=null)pushAndSend(org.getMemberId(), dis.getId(), Type.DISTRIBUTEORDER, "无效订单", "亲，由于您的单方面违约我们将您的保证金退还给下订单企业。", "SMS_10630869");
				}else{
					if(dis.getPayWay() == 3){ //企业没支付，机构快钱支付，机构返还快钱的保证金
						Map<String, String> paras = new LinkedHashMap<String, String>();
						paras.put("orderid", dis.getBnsNo());//商户订单号（小写）
						paras.put("amount", ""+dis.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						Map<String, String> res = Bill99Util.receive(paras);
						if("Y".equals(res.get("RESULT"))){
							dis.setRefundState(1);
						}else{
							dis.setRefundState(0);
						}
					}else if(dis.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
						Map<String,String> param = new HashMap<String,String>();
						param.put("bnsNo",dis.getBnsNo());//原商户发起的支付订单号
						param.put("money",dis.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
						param.put("reason","退还接单押金");//退款原因
						if("0000".equals(BaofooUtil.RefundFrom(param))){
							dis.setRefundState(2);//退款中
						}else{
							dis.setRefundState(0);
						}
					}else{//不是快钱支付的返还
						AlipayUtil.refundsNoPwdByTradeNo(dis.getJyh(), dis.getDeposit().toString(), "退还接单押金");
					}
					payLog.info("更新订单：[退还接单押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
					dis.setDepositState(2);
					Org org = orgDao.getById(dis.getOrgId());
					if(org!=null)pushAndSend(org.getMemberId(), dis.getId(), Type.DISTRIBUTEORDER, "无效订单", "亲，您的保证金已退还给您，请注意查收哦！", "SMS_10680818");
				}
			}
		}else if(dis!=null && dis.getState()!=null && dis.getState()==0){//无效订单（验票失败审核过）
			if(record!=null && record.getOrderflag()==0){//贴现也变无效（审核通过）
				if(dis.getDeposit()!=null && dis.getDepositState()!=null && dis.getDepositState()==1 && dis.getDeposit()>0.0){//机构已支付
					if(record!=null && record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit() >0.0){//企业已支付
						if(dis.getPayWay() == 3){//机构快钱支付
							Map<String, String> paras = new LinkedHashMap<String, String>();
							paras.put("orderid", dis.getBnsNo());//商户订单号（小写）
							paras.put("amount", ""+dis.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							Map<String, String> res = Bill99Util.receive(paras);
							if("Y".equals(res.get("RESULT"))){
								dis.setRefundState(1);
							}else{
								dis.setRefundState(0);
							}
						}else if(dis.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
							Map<String,String> param = new HashMap<String,String>();
							param.put("bnsNo",dis.getBnsNo());//原商户发起的支付订单号
							param.put("money",dis.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
							param.put("reason","退还接单押金");//退款原因
							if("0000".equals(BaofooUtil.RefundFrom(param))){
								dis.setRefundState(2);//退款中
							}else{
								dis.setRefundState(0);
							}
						}else if(record.getPayWay() != 3) {//双方都不是快钱支付
							AlipayUtil.refundsNoPwdByTradeNo(dis.getJyh(), dis.getDeposit().toString(), "退还接单押金");
							payLog.info("更新订单：[退还接单押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
						}else{
							AlipayUtil.refundsNoPwdByTradeNo(dis.getJyh(), dis.getDeposit().toString(), "退还接单押金");
						}
						discountrecordService.refund(record, dis, 1, 1);//转账给机构
						dis.setDepositState(4);
						record.setDepositState(3);
						Org org = orgDao.getById(dis.getOrgId());
						if(org!=null)pushAndSend(org.getMemberId(), dis.getId(), Type.DISTRIBUTEORDER, "无效订单", "您出示的证明出票方的票据有问题的图片我们已审核通过。由于出票方的违约，出票方和您支付的押金均将退回您的支付账户，感谢您使用票据管家。", "SMS_10675925");
						pushAndSend(record.getMemberid(), record.getId(), Type.DISCOUNTRECORD, "无效订单", "您的这笔订单由于票面有问题，已被收票方取消此订单。由于您单方违约，您的保证金已被扣除，请上传合规的票据进行贴现。如有任何问题欢迎联系票据管家。", "SMS_10635905");
					}else{
						if(dis.getPayWay() == 3){//企业没支付，机构快钱支付
							Map<String, String> paras = new LinkedHashMap<String, String>();
							paras.put("orderid", dis.getBnsNo());//商户订单号（小写）
							paras.put("amount", ""+dis.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							Map<String, String> res = Bill99Util.receive(paras);
							if("Y".equals(res.get("RESULT"))){
								dis.setRefundState(1);
							}else{
								dis.setRefundState(0);
							}
						}else if(dis.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
							Map<String,String> param = new HashMap<String,String>();
							param.put("bnsNo",dis.getBnsNo());//原商户发起的支付订单号
							param.put("money",dis.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
							param.put("reason","退还接单押金");//退款原因
							if("0000".equals(BaofooUtil.RefundFrom(param))){
								dis.setRefundState(2);//退款中
							}else{
								dis.setRefundState(0);
							}
						}else {
							AlipayUtil.refundsNoPwdByTradeNo(dis.getJyh(), dis.getDeposit().toString(), "退还接单押金");
						}
						payLog.info("更新订单：[退还接单押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
						dis.setDepositState(2);
						Org org = orgDao.getById(dis.getOrgId());
						if(org!=null)pushAndSend(org.getMemberId(), dis.getId(), Type.DISTRIBUTEORDER, "无效订单", "您出示的证明出票方的票据有问题的图片我们已审核通过。由于出票方的违约，您支付的押金将退回您的支付账户，感谢您使用票据管家。", "SMS_10680818");
					}
				}else if(record!=null && record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit() >0.0 ){//企业已支付
					if(record.getPayWay() == 3){//机构没支付，企业快钱支付
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
					}else {
						AlipayUtil.refundsNoPwdByTradeNo(record.getJyh(), record.getDeposit().toString(), "退还贴现押金");
					}
					payLog.info("更新订单：[退还贴现押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
					record.setDepositState(2);
					pushAndSend(record.getMemberid(), record.getId(), Type.DISCOUNTRECORD, "无效订单", "亲，您的保证金已退还给您，请注意查收哦！", "SMS_10680818");
				}
			}else{//审核不通过
				if(dis.getDeposit()!=null && dis.getDepositState()!=null && dis.getDepositState()==1 && dis.getDeposit()>0.0){//机构已支付
					if(record!=null && record.getDepositState()!=null && record.getDepositState()==1 && record.getDeposit()>0.0){//企业已支付
						discountrecordService.refund(record, dis, 0, 1);//转账给企业
						dis.setDepositState(3);
						pushAndSend(record.getMemberid(), record.getId(), Type.DISCOUNTRECORD, "待确认", "您的这笔订单由于收票方拒绝订单，我们会立刻将这笔订单推送给其他机构；另外收票方支付的保证金将转入您的支付账户，请耐心等待。", "SMS_10610819");
					}else{
						if(dis.getPayWay() == 3){//企业没支付，机构快钱支付
							Map<String, String> paras = new LinkedHashMap<String, String>();
							paras.put("orderid", dis.getBnsNo());//商户订单号（小写）
							paras.put("amount", ""+dis.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							Map<String, String> res = Bill99Util.receive(paras);
							if("Y".equals(res.get("RESULT"))){
								dis.setRefundState(1);
							}else{
								dis.setRefundState(0);
							}
						}else if(dis.getPayWay() == 4){//@WKX EDIT 返还机构的钱（宝付）
							Map<String,String> param = new HashMap<String,String>();
							param.put("bnsNo",dis.getBnsNo());//原商户发起的支付订单号
							param.put("money",dis.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
							param.put("returnUrl",Constant.properties.getProperty("BAOFOO_RETURNURL") + "/baofoo/refund");//退款结果回调链接
							param.put("reason","退还接单押金");//退款原因
							if("0000".equals(BaofooUtil.RefundFrom(param))){
								dis.setRefundState(2);//退款中
							}else{
								dis.setRefundState(0);
							}
						}else {
							AlipayUtil.refundsNoPwdByTradeNo(dis.getJyh(), dis.getDeposit().toString(), "退还接单押金");
						}
						payLog.info("更新订单：[退还接单押金]updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
						dis.setDepositState(2);
						Org org = orgDao.getById(dis.getOrgId());
						if(org!=null)pushAndSend(org.getMemberId(), dis.getId(), Type.DISTRIBUTEORDER, "无效订单", "亲，您的保证金已退还给您，请注意查收哦！", "SMS_10680818");
					}
				}
			}
		}
		//step.2保存
		if(dis!=null)distributeOrderDao.updateDistributeOrder(dis);
		if(task!=null)distributeOrderTaskDao.saveModel(task);
		if(record!=null)discountrecordDao.mergeDiscountrecord(record);
		if(recordTask!=null)discountrecordTaskDao.saveModel(recordTask);
		payLog.info("更新订单：执行完方法updateAndTaskToInvalid...用户主键："+record.getMemberid()+"订单主键："+record.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getTaskAndInfoById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id){
		return distributeOrderDao.getTaskAndInfoById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#updateReadTaskAndGetInfoById(java.lang.Integer)
	 */
	public Map<String,Object> updateReadTaskAndGetInfoById(Integer id) throws Exception{
		List<Map<String,Object>> tasks = distributeOrderDao.getTaskAndInfoById(id);
		if(tasks!=null && tasks.size()>0){
			Map<String,Object> task = tasks.iterator().next();
			if(task!=null && task.get("id")!=null){
				Object i = task.get("id");
				DistributeOrderTask t = distributeOrderTaskDao.getById(Integer.valueOf(i.toString()));
				if(t.getReadState()==ReadState.UNREAD){//最新记录未读
					t.setReadState(ReadState.READ);
					distributeOrderTaskDao.saveModel(t);
				}
			}
		}
		return distributeOrderDao.getInfoById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getByDcrdId(java.lang.Integer)
	 */
	public DistributeOrder getByDcrdId(Integer dcrdId){
		List<DistributeOrder> list = distributeOrderDao.getByDcrdId(dcrdId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getLimitAndMoneyForOrg(java.util.Date)
	 */
	public List<Map<String,Object>> getLimitAndMoneyForOrg(Date day) throws ParseException{
		return distributeOrderDao.getLimitAndMoneyForOrg(day);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getListIdAndTotal(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getListIdAndTotal(Integer state, Date startDate, Date endDate) {
		return distributeOrderDao.getListIdAndTotal(state, startDate, endDate);
	}

	@Override
	public Long countbyOrdertime(Integer org_id, Long ordertime1, Long ordertime2, Integer orderflag) {
		return distributeOrderDao.countbyOrdertime(org_id, ordertime1, ordertime2, orderflag);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getLimitAndMoneyByOrgId(java.lang.Integer, java.util.Date)
	 */
	public Map<String,Object> getLimitAndMoneyByOrgId(Integer orgId,Date day) throws ParseException{
		List<Map<String,Object>> list = distributeOrderDao.getLimitAndMoneyByOrgId(orgId, day);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getLimitAndMoneyForOrg(java.util.Date)
	 */
	public List<Map<String,Object>> getSuccessList(String date1,String date2){
		return distributeOrderDao.getSuccessList(date1, date2);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getLimitAndMoneyForOrg(java.util.Date)
	 */
	public List<Map<String,Object>> getRefuseList(String date1,String date2){
		return distributeOrderDao.getRefuseList(date1, date2);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getLimitAndMoneyForOrg(java.util.Date)
	 */
	public List<Map<String,Object>> getSuccessTimeList(String date1,String date2){
		return distributeOrderDao.getSuccessTimeList(date1, date2);
	}
	
	public List<Map<String,Object>> getDeposit(Integer org_id){
		return distributeOrderDao.getDeposit(org_id);
	}
	
	public PageResults<Map<String,Object>> getPcOrgDeposit(Integer pageIndex,Integer pageSize,Integer org_id){
		return distributeOrderDao.getPcOrgDeposit(pageIndex,pageSize,org_id);
	}
	
	public List<Map<String,Object>> getordthism(String date1,String date2,Integer orgid){
		return distributeOrderDao.getordthism(date1, date2, orgid);
	}

	@Override
	public List<Map<String, Object>> getAssignedByOrgId(Integer org_id,Float version) {
		return distributeOrderDao.getAssignedByOrgId(org_id,version);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getByDcrdIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrder> getByDcrdIdAndOrgId(Integer dcrdId,Integer orgId){
		return distributeOrderDao.getByDcrdIdAndOrgId(dcrdId, orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#updateDisAndImage(com.ry.core.entity.DistributeOrder, com.ry.core.entity.DistributeOrderTask, java.util.List, com.ry.core.entity.Discountrecord)
	 */
	public void updateDisAndImage(DistributeOrder dis,DistributeOrderTask task,List<Image> images,Discountrecord disc) throws Exception{
		if(dis!=null)distributeOrderDao.updateDistributeOrder(dis);
		if(task!=null)distributeOrderTaskDao.saveModel(task);
		if(images!=null && images.size()>0){
			for(Image img:images){
				img.setFkId(dis.getId());
				img.setFkType("2");//（1：OrgImage机构认证承诺函其他、2：验票失败DistributeOrder、3：其他）
				img.setCreateTime(new Date());
				imageDao.saveModel(img);
			}
		}
		if(disc!=null){
			disc.setOrderflag(0);//无效订单
			discountrecordDao.updateDiscountrecord(disc);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#updateAndPayRecord(com.ry.core.entity.DistributeOrder, java.lang.Integer, java.lang.String, java.lang.Float)
	 */
	@Override
	public void updateAndPayRecord(DistributeOrder dist, Integer state, String des,Float version) {
		if(dist!=null){
			PayRecord payRecord = new PayRecord();
			payRecord.setPkId(dist.getId());
			payRecord.setPkType("3");//外键类型：1查询查复inquiry_reply、2下单dis、3派单distribute_order
			payRecord.setPayMoney(new BigDecimal(dist.getDeposit()));//支付金额
			payRecord.setPayWay(dist.getPayWay());//支付方式
			payRecord.setState(state);//支付状态
			payRecord.setDescription(des);//描述
			payRecordDao.savePayRecord(payRecord);//保存流水
			if(state==0 || state==10) {//支付宝支付成功是0、快钱支付成功是10
				this.updateAndNoPayRecord(dist);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#updateAndNoPayRecord(com.ry.core.entity.DistributeOrder)
	 */
	public void updateAndNoPayRecord(DistributeOrder dist) {
		if(dist!=null){
			dist.setState(5);//交易中
			Discountrecord disc = discountrecordDao.getModelById(dist.getDcrdId());//只更新对象用此方法（不转码）
			if(disc!=null && disc.getOrderflag()==1){//贴现订单处于待确认
				disc.setOrderflag(4);
				discountrecordDao.updateDiscountrecord(disc);
			}
			distributeOrderDao.updateDistributeOrder(dist);//更新订单
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#saveModel(com.ry.core.entity.DistributeOrder)
	 */
	public void saveModel(DistributeOrder distributeOrder) {
		if(distributeOrder!=null && distributeOrder.getId()!=null){
			distributeOrderDao.updateDistributeOrder(distributeOrder);
		}else{
			distributeOrderDao.saveDistributeOrder(distributeOrder);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getByOrgIdAndTime(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByOrgIdAndTime(Integer orgId, String startDate, String endDate, Integer state) {
		return distributeOrderDao.getByOrgIdAndTime(orgId, startDate, endDate, state);
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
	 * @see com.ry.core.service.DistributeOrderService#getListByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId) {
		return distributeOrderDao.getListByOrgId(orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<DistributeOrder> getOrderPageList(ReviewOrderRequest req) {
		return distributeOrderDao.getOrderPageList(req);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getOrderMemberPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String, Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		return distributeOrderDao.getOrderMemberPageList(req);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<DistributeOrder> getPageList(Integer discId, Integer pageIndex, Integer pageSize) {
		return distributeOrderDao.getPageList(discId, pageIndex, pageSize);
	}
	
	public DistributeOrder getByBnsno(String bnsNo){
		return distributeOrderDao.getByBnsno(bnsNo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize, DistributeOrderForm form)throws Exception {
		return distributeOrderDao.getPageListPC(pageIndex, pageSize, form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getByNo(java.lang.String)
	 */
	public DistributeOrder getByNo(String no) {
		return distributeOrderDao.getByNo(no);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderService#getByRefundState(java.lang.Integer)
	 */
	public List<DistributeOrder> getByRefundState(Integer refundState){
		return distributeOrderDao.getByRefundState(refundState);
	}
}