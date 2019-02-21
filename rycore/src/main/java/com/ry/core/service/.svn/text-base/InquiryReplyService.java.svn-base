package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.dto.InquiryReplyDto;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Invoice;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface InquiryReplyService {
	/**
	 * 分页查询查询查复信息列表
	 * @param list
	 */
	public PageResults<Map<String,Object>> listByInquiryReplyRequest(Integer currentPage,Integer pageSize,InquiryReplyRequest req);
	
	/**
	 * 分页查询发票信息列表
	 * @param list
	 * @return
	 */
	PageResults<InquiryReplyDto> getPageList(InquiryReplyRequest req);
	
	/**
	 * 主键获取发票详情
	 * @param req
	 * @return
	 */
	 List<InquiryReply> getByObj(InquiryReplyRequest req);
	 
	 /**
	  * 更新查询查复实体
	  * @param reply
	  */
	 void updateInquiryReply(InquiryReply reply);
	
	/**
	 * 根据编号获取查询查复（编号必传）
	 * @author WKX
	 * @param no
	 * @since 2016年3月9日 下午4:11:56
	 */
	public List<InquiryReply> getByNo(String no);
	
	/**
	 * 存储对象
	 * @author WKX
	 * @param reply
	 * @since 2016年3月9日 下午4:40:57
	 */
	public void saveInquiryReplyAndInvoice(InquiryReply reply,Invoice invoice);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年3月10日 上午9:30:30
	 */
	public InquiryReply getById(Integer id);
	
	/**
	 * 保存支付流水（并更新订单）
	 * @author WKX
	 * @param reply
	 * @param state
	 * @param des
	 * @since 2016年3月10日 上午9:49:21
	 */
	public void saveInquiryReplyAndPayRecord(InquiryReply reply,Integer state,String des);
	
	/**
	 * 根据主键获取查询查复信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> getInfoById(Integer id);
	
	/**
	 * 根据用户主键获取消息分页
	 * @author xiaoc
	 */
	public PageResults<InquiryReply> getPageList(InquiryReply inq,Integer currentPage,Integer pageSize);
	
	/**
	 * 根据商户订单号获取交易号（前提已完成支付的）
	 * 定时任务：根据交易状态、时间和交易号为空 查询订单号，得到一个订单号集合
	 * @author BKY
	 */
	public List<InquiryReply> updateTransaction(Integer payState,Date createTime, Date createTime2);
	
	/**
	 * 根据上面得到的集合 修改交易号(jyh)
	 */
	public void updateJyh(List<InquiryReply> inquiryReplyList);
	
	/**
	 * 根据认证主键获取查询查复的记录
	 * @author WKX
	 * @param orgId
	 * @since 2016年4月9日 上午10:26:21
	 */
	public List<InquiryReply> getByOrgId(Integer orgId);
	
	/**
	 * 查询多个用户在某段时间内的查询查复的记录
	 * @param paramList	用户的id列表
	 * @param payState	订单状态
	 */
	public List<Map<String, Object>> getCountByArray(List<Object> paramList, Integer payState, Date startTime, Date endTime);
	
	/**
	 * 根据（用户主键）获取查询查复的记录[APP2.2后企业可能没有OrgId]
	 * @author WKX
	 * @param memberId
	 * @since 2016年6月13日 下午1:58:04
	 */
	public List<InquiryReply> getByMemberId(Integer memberId);
	
	/**
	 * 动态分页获取查询查复信息
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @since 2016年11月9日 下午3:53:50
	 */
	public PageResults<Map<String, Object>> getPageList(InquiryReplyForm form,Integer pageIndex,Integer pageSize) throws Exception;

	/**
	 * 根据查询查复订单号获取详情
	 * @author KHC
	 * @param no
	 * @since 2016年11月10日 下午2:27:35
	 */
	public Map<String, Object> getInfoByNo(String no);
	
	/**
	 * 消息弹框推送
	 * @author ZY
	 * @param importState
	 * @param time
	 * @since 2017年1月12日 下午8:08:49
	 */
	public Long countByImportAndTime(Integer importState,Integer time);
	
	public ViewExcel getExcelData(InquiryReplyRequest req);
	
	/**
	 * 获取3条备注
	 * @param list
	 * @return
	 */
	public String getRemarks(Map<String, Object> list);
	
	/**
	 * 分页查询查询查复信息报表
	 * @author MH
	 * @param currentPage 
	 * @param pageSize
	 * @param req 参数集合
	 * @return
	 */
	public PageResults<Map<String,Object>> listReportByInquiryReplyRequest(Integer currentPage,Integer pageSize,InquiryReplyRequest req);
	
}