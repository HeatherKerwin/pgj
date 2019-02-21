package com.ry.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OperateLogDao;
import com.ry.core.entity.OperateLog;

@Repository
public class OperateLogDaoImpl extends BaseDao<OperateLog,Integer> implements OperateLogDao{
   
	/* (non-Javadoc)
	 * @see com.ry.core.dao.LogDao#saveModel(com.ry.core.entity.Log)
	 */
	@Override
	public void saveModel(OperateLog log) {
		if(log!=null && log.getId()!=null){
			update(log);
		}else{
			save(log);
		}
	}
}