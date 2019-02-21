package com.ry.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PriceLogDao;
import com.ry.core.entity.PriceLog;

@Repository
public class PriceLogDaoImpl extends BaseDao<PriceLog,Integer> implements PriceLogDao{
   
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceLogDao#saveModel(com.ry.core.entity.PriceLog)
	 */
	@Override
	public void saveModel(PriceLog log) {
		if(log!=null && log.getId()!=null){
			update(log);
		}else{
			save(log);
		}
	}
}