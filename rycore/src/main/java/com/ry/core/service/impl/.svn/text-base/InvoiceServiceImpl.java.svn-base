/**
 * 
 */
package com.ry.core.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.Enum.ExpressWay;
import com.ry.core.Enum.InvoiceState;
import com.ry.core.dao.InvoiceDao;
import com.ry.core.entity.Invoice;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.core.form.invoice.UpdateInvoiceRequest;
import com.ry.core.service.InvoiceService;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Resource
	InvoiceDao invoiceDao;

	
	public List<Invoice> getByType(InquiryReplyRequest req){
		return invoiceDao.getByType(req);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.InvoiceService#updateInvoice(com.ry.core.form.invoice.UpdateInvoiceRequest)
	 */
	@Override
	public void updateInvoice(UpdateInvoiceRequest req) {
		InquiryReplyRequest request = new InquiryReplyRequest();
		request.setIid(req.getId());
		List<Invoice> invList = invoiceDao.getByType(request);
		if(invList.size() > 0){
			Invoice invoice = invList.get(0);
			invoice.setExpressNo(req.getExpressNo());
			invoice.setState(InvoiceState.SENT.getCode());
			invoice.setState(0);
			if(req.getExpressWay() == ExpressWay.MAIL.getCode()){
				invoice.setExpressWay(ExpressWay.MAIL.getCode());
				invoice.setExpressCompany(req.getExpressWay()+"");
			}else if(req.getExpressWay() == ExpressWay.TOPAY.getCode()){
				invoice.setExpressWay(ExpressWay.TOPAY.getCode());
				invoice.setExpressCompany(req.getExpressWay()+"");
			}
			invoiceDao.updateInvoice(invoice);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InvoiceService#getById(java.lang.Integer)
	 */
	public Invoice getById(Integer id){
		return invoiceDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.InvoiceService#getByFkIdAndFkType(java.lang.Integer, java.lang.String)
	 */
	public Invoice getByFkIdAndFkType(Integer fkId,String fkType) {
		List<Invoice> list = invoiceDao.getByFkIdAndFkType(fkId,fkType);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
}