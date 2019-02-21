package com.ry.core.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.Enum.ImportStateEnum;
import com.ry.core.Enum.InquiryReplyHandleStateEnum;
import com.ry.core.Enum.OrgTpyeEnum;
import com.ry.core.Enum.PayStateEnum;
import com.ry.core.dao.InquiryReplyDao;
import com.ry.core.dao.InquiryReplyDtoDao;
import com.ry.core.dao.InvoiceDao;
import com.ry.core.dao.PayRecordDao;
import com.ry.core.dao.RemarksDao;
import com.ry.core.dto.InquiryReplyDto;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Invoice;
import com.ry.core.entity.PayRecord;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.core.service.InquiryReplyService;
import com.ry.util.StringUtil;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Service
public class OrderInvoiceServiceImpl implements InquiryReplyService{
	
	@Resource
	InquiryReplyDtoDao inquiryReplyDtoDao;

	@Resource
	RemarksDao remarksDao;
	
	@Resource
	InquiryReplyDao inquiryReplyDao;
	
	@Resource
	InvoiceDao invoiceDao;
	
	@Resource
	PayRecordDao payRecordDao;
	
	@Override
	public PageResults<InquiryReplyDto> getPageList(InquiryReplyRequest req) {
		PageResults<InquiryReplyDto> result = new PageResults<InquiryReplyDto>();
		result = inquiryReplyDtoDao.getPageList(req);
		return result;
	}

	
	public List<InquiryReply> getByObj(InquiryReplyRequest req){
		return inquiryReplyDao.getByObj(req);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#updateInquiryReply(com.ry.core.entity.InquiryReply)
	 */
	@Override
	public void updateInquiryReply(InquiryReply reply) {
		inquiryReplyDao.updateInquiryReply(reply);
		
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getByNo(java.lang.String)
	 */
	public List<InquiryReply> getByNo(String no){
		return inquiryReplyDao.getByNo(no);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#saveInquiryReplyAndInvoice(com.ry.core.entity.InquiryReply, com.ry.core.entity.Invoice)
	 */
	public void saveInquiryReplyAndInvoice(InquiryReply reply,Invoice invoice){
		inquiryReplyDao.saveModel(reply);
		if(invoice!=null && 0==reply.getNeedInvoice()){//需要发票
			invoice.setFkId(reply.getId());
			invoice.setFkType("2");//查询查复
			invoiceDao.saveInvoice(invoice);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getById(java.lang.Integer)
	 */
	public InquiryReply getById(Integer id){
		return inquiryReplyDao.getById(id);
	}
	
	public void saveInquiryReplyAndPayRecord(InquiryReply reply,Integer state,String des){
		if(reply!=null){
			PayRecord payRecord = new PayRecord();
			payRecord.setPkId(reply.getId());
			payRecord.setPkType("1");//外键类型
			payRecord.setPayMoney(reply.getPayMoney());//支付金额
			payRecord.setPayWay(reply.getPayWay());//支付方式
			payRecord.setState(state);//支付状态
			payRecord.setDescription(des);//描述
			payRecordDao.savePayRecord(payRecord);//保存流水
			inquiryReplyDao.saveModel(reply);//根据订单（查询查复）
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getInfoById(java.lang.Integer)
	 */
	@Override
	public Map<String,Object> getInfoById(Integer id) {
		List<Map<String,Object>> list = inquiryReplyDao.getInfoById(id);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getPageList(com.ry.core.entity.InquiryReply, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<InquiryReply> getPageList(InquiryReply inq, Integer currentPage, Integer pageSize) {
		return inquiryReplyDao.getPageList(inq, currentPage, pageSize);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#updateTransaction(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<InquiryReply> updateTransaction(Integer payState, Date createTime, Date createTime2) {
		return inquiryReplyDao.updateTransaction(payState, createTime, createTime2);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#updateJyh(java.util.List)
	 */
	public void updateJyh(List<InquiryReply> inquiryReplyList) {
		if (inquiryReplyList != null && inquiryReplyList.size() > 0) {
			for(InquiryReply inquiryReply : inquiryReplyList) {				
				inquiryReplyDao.updateJyh(inquiryReply);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getByOrgId(java.lang.Integer)
	 */
	public List<InquiryReply> getByOrgId(Integer orgId){
		return inquiryReplyDao.getByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getCountByArray(java.util.List, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getCountByArray(List<Object> paramList, Integer payState, Date startTime, Date endTime){
		return inquiryReplyDao.getCountByArray(paramList, payState, startTime, endTime);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getByMemberId(java.lang.Integer)
	 */
	public List<InquiryReply> getByMemberId(Integer memberId){
		return inquiryReplyDao.getByMemberId(memberId);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getPageList(com.ry.core.form.InquiryReplyForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(InquiryReplyForm form, Integer pageIndex, Integer pageSize)throws Exception {
		return inquiryReplyDao.getPageList(form, pageIndex, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#getInfoByNo(java.lang.String)
	 */
	public Map<String, Object> getInfoByNo(String no) {
		return inquiryReplyDao.getInfoByNo(no);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#listByInquiryReplyRequest(java.lang.Integer, java.lang.Integer, com.ry.core.form.company.InquiryReplyRequest)
	 */
	@Override
	public PageResults<Map<String,Object>> listByInquiryReplyRequest(Integer currentPage,Integer pageSize,InquiryReplyRequest req){
		return inquiryReplyDao.listByInquiryReplyRequest(currentPage, pageSize,req);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#countByImportAndTime(java.lang.Integer, java.lang.Integer)
	 */
	public Long countByImportAndTime(Integer importState,Integer time){
		return inquiryReplyDao.countByImportAndTime(importState,time);
	}


	@Override
	public ViewExcel getExcelData(InquiryReplyRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		String[] headData = new String[] {
				"订单号", 
				"票号", 
				"手机号", 
				"姓名", 
				"身份角色", 
				"查询日期", 
				"查询时间", 
				"票面金额", 
				"支付状态", 
				"订单状态", 
				"导入导出状态", 
				"备注", 
				"回访状态"};
		try{
			List<Map<String, Object>> reply = inquiryReplyDao.getExcelExport(req);
			if(reply.size() > 0){
				for(Map<String, Object> rep : reply){
					String createTime = rep.get("create_time").toString();
					String phone = rep.get("mobile")==null?"":rep.get("mobile").toString();
					String name = rep.get("name")==null?"":rep.get("name").toString();
					String visitState = rep.get("visit_state") == null?"":rep.get("visit_state").toString();
					String[] data = new String[headData.length];
					data[0] = rep.get("no").toString();
					data[1] = rep.get("draft_no").toString();
					data[2] = StringUtil.hideMobile(phone);
					data[3] = name;
					data[4] = OrgTpyeEnum.getOrgType(Integer.parseInt(rep.get("org_type").toString()));//身份角色
					data[5] = createTime.substring(0, 11);
					data[6] = createTime.substring(11,19);
					data[7] = rep.get("money").toString();
					Integer payState = Integer.parseInt(rep.get("pay_state").toString());
					data[8] = PayStateEnum.GetPayState(payState);//支付状态
					String state= rep.get("state")==null?"":rep.get("state").toString();
					data[9] = InquiryReplyHandleStateEnum.getInquiryReplyHandleState(Integer.parseInt(state));//订单状态
					Integer importState = Integer.parseInt(rep.get("import_state").toString());
					data[10] = ImportStateEnum.GetiImportState(importState);//导入导出状态
					data[11] = getRemarks(rep);//备注
					data[12] = visitState;//回访状态
					//data[13] = rep.get("").toString();操作人
					InquiryReply rep1 = inquiryReplyDao.getById(Integer.parseInt(rep.get("id").toString()));
					if(rep!=null && (0==importState || importState==null)){//未导出（变成已导出）
						rep1.setImportState(1);
						rep1.setState(1);//导出后处理状态变成 1处理中
						inquiryReplyDao.updateInquiryReply(rep1);
					}
					if(rep!=null && 2!=importState){//只把未导入过的（导出）
						dataList.add(data);
					}
				}
			}else{
				String[] data = new String[1];
				data[0] = "暂无数据";
				dataList.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ViewExcel("查询查复订单列表"+sdf.format(new Date()), headData, dataList, endData);
	}


	@Override
	public String getRemarks(Map<String, Object> result) {
		StringBuffer remarks=null;
		String time=null;
		PageResults<Map<String,Object>> listss=remarksDao.listByTypeAndFkid(0,3,new Integer(result.get("id").toString()),0);
		int i=0;
		remarks = new StringBuffer();
		List<Map<String,Object>> lists=listss.getResults();
		if(lists != null && lists.size() > 0 && listss!=null){
			for(Map<String, Object> list:lists){
				if(i>=3) break;
				try {
					time= list.get("create_time").toString().substring(0,11);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String s=list.get("content").toString().length()>40?list.get("content").toString().substring(0,40)+"...":list.get("content").toString();
				if(time!=null) remarks.append(time+"："+s+"</br>");
				i++;
			}
			return remarks.toString();
		}else {
			remarks.append("暂无备注");
		}
		return remarks.toString();
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.InquiryReplyService#listReportByInquiryReplyRequest(java.lang.Integer, java.lang.Integer, com.ry.core.form.company.InquiryReplyRequest)
	 */
	@Override
	public PageResults<Map<String, Object>> listReportByInquiryReplyRequest(Integer currentPage, Integer pageSize,
			InquiryReplyRequest req) {
		return inquiryReplyDao.listReportByInquiryReplyRequest(currentPage, pageSize,req);
	}
}