/**
 * 
 */
package com.ry.core.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.InvoiceDao;
import com.ry.core.entity.Invoice;
import com.ry.core.form.company.InquiryReplyRequest;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Repository
public class InvoiceDaoImpl extends BaseDao<Invoice, Integer> implements InvoiceDao{

	public List<Invoice> getByType(InquiryReplyRequest req){
		StringBuffer hql = new StringBuffer(" from invoice where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(req.getId() != null){
			hql.append(" and fk_id = ?");
			paramList.add(req.getId());
		}
		
		if(req.getId() != null){
			hql.append(" and fk_id = ?");
			paramList.add(req.getId());
		}
		
		if(req.getIid() != null){
			hql.append(" and id = ?");
			paramList.add(req.getIid());
		}
		
		if(req.getType() != null){
			hql.append(" and fk_type = ?");
			paramList.add(req.getType());
		}
		List<Invoice> list = getListByHQL(hql.toString(), paramList.toArray());
		return list;
	}
	
	public void updateInvoice(Invoice invoice){
		update(invoice);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InvoiceDao#saveInvoice(com.ry.core.entity.Invoice)
	 */
	public void saveInvoice(Invoice invoice){
		save(invoice);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InvoiceDao#getByFkIdAndFkType(java.lang.Integer, java.lang.String)
	 */
	public List<Invoice> getByFkIdAndFkType(Integer fkId,String fkType) {
		StringBuffer hql = new StringBuffer("from invoice where fkId=? AND fkType=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(fkId);
		paramList.add(fkType);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InvoiceDao#getById(java.lang.Integer)
	 */
	public Invoice getById(Integer id){
		return get(id);
	}
}