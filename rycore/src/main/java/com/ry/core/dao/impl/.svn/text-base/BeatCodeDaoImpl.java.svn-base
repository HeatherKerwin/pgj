package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.BeatCodeDao;
import com.ry.core.entity.BeatCode;

@Repository
public class BeatCodeDaoImpl extends BaseDao<BeatCode, Integer> implements BeatCodeDao{
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#getEnableByNo(java.lang.String)
	 */
	@Override
	public List<BeatCode> getEnableByNo(String no) {
		StringBuffer hql = new StringBuffer("from beat_code where no=? AND state=1"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#updateModel(com.ry.core.entity.BeatCode)
	 */
	@Override
	public void updateModel(BeatCode beatCode) {
		 update(beatCode);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#saveModel(com.ry.core.entity.BeatCode)
	 */
	public void saveModel(BeatCode beatCode) {
		 save(beatCode);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#getByNo(java.lang.String)
	 */
	@Override
	public List<BeatCode> getByNo(String no) {
		StringBuffer hql = new StringBuffer("from beat_code where no=?"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#getByMemberId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("SELECT bc.* FROM beat_code bc LEFT JOIN member m ON bc.phone=m.mobile WHERE m.id=?"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.BeatCodeDao#getByNoAndPhone(java.lang.String, java.lang.String)
	 */
	public List<BeatCode> getByNoAndPhone(String no,String phone) {
		StringBuffer hql = new StringBuffer("from beat_code where no=? AND phone=?"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		paramList.add(phone);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
}