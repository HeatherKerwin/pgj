/**
 * 
 */
package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Invoice;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.core.form.invoice.UpdateInvoiceRequest;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface InvoiceService {
	/**
	 * 根据类型获取发票信息
	 * @param req
	 * @return
	 */
	List<Invoice> getByType(InquiryReplyRequest req);
	
	/**
	 * 更新发票实体
	 * @param req
	 */
	void updateInvoice(UpdateInvoiceRequest req);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年4月12日 下午11:00:10
	 */
	public Invoice getById(Integer id);
	
	/**
	 * 根据外键和外键类型获取对象
	 * @author WKX
	 * @param fkId
	 * @param fkType
	 * @since 2016年4月12日 下午10:58:31
	 */
	public Invoice getByFkIdAndFkType(Integer fkId,String fkType);
}