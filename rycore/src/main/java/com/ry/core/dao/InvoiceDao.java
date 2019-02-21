/**
 * 
 */
package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Invoice;
import com.ry.core.form.company.InquiryReplyRequest;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface InvoiceDao {

	/**
	 * 主键获取发票详情
	 * @param req
	 * @return
	 */
	List<Invoice> getByType(InquiryReplyRequest req);

	/**
	 * 更新票据实体
	 * @param req
	 */
	void updateInvoice(Invoice req);
	
	/**
	 * 保存开具发票
	 * @author WKX
	 * @param invoice
	 * @since 2016年3月9日 下午5:09:27
	 */
	public void saveInvoice(Invoice invoice);
	
	/**
	 * 根据外键和外键类型获取对象
	 * @author WKX
	 * @param fkId
	 * @param fkType
	 * @since 2016年4月12日 下午10:58:31
	 */
	public List<Invoice> getByFkIdAndFkType(Integer fkId,String fkType);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年4月12日 下午10:59:23
	 */
	public Invoice getById(Integer id);
}