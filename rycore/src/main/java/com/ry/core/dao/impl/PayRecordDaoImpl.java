/**
 * 
 */
package com.ry.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PayRecordDao;
import com.ry.core.entity.PayRecord;

@Repository
public class PayRecordDaoImpl extends BaseDao<PayRecord, Integer> implements PayRecordDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PayRecordDao#savePayRecord(com.ry.core.entity.PayRecord)
	 */
	public void savePayRecord(PayRecord payRecord){
		save(payRecord);
	}
}