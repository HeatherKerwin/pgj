package com.ry.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.HongbaoActionDao;
import com.ry.core.entity.HongbaoAction;
import com.ry.core.util.Constant;

@Repository
public class HongbaoActionDaoImpl extends BaseDao<HongbaoAction, Integer> implements HongbaoActionDao {

	@Override
	public void addHongbaoAction(HongbaoAction hongbaoAction) {
		save(hongbaoAction);
	}

	@Override
	public List<Object []> countHongbaoPrice(Integer hid) {
		String sql = "select sum(price),count(id) from hongbaoAction where  hid=? and price>0 ";
		List<Object []> list = getListBySQL(sql, new Object[]{hid});
        return list;
	}
	
	@Override
	public List<HongbaoAction> getHongbaoActionList(Integer hid, String phone, Integer sid) {
		String hql = " from hongbaoAction where hid=? and phone=? and sid=? and price>0 order by createtime desc ";
		List paramList = new ArrayList();
		paramList.add(hid);
		paramList.add(phone);
		paramList.add(sid);
		return queryByHql(hql, paramList.toArray());
	}

	@Override
	public void updateHongbaoAction(HongbaoAction hongbaoAction) {
		
		update(hongbaoAction);
	}

	@Override
	public BigInteger countHongbaoAction(Integer hid) {
		String hql = "select count(*) from hongbaoAction where hid = ? and price>0 ";		
		Object obj = (Object)getBySQL(hql, new Object[]{hid});
		BigInteger bint = (BigInteger) obj;
		return bint;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.HongbaoActionDao#getCountByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Date start,Date end){
		StringBuffer sql = new StringBuffer("SELECT *,FLOOR(COUNT(id)/2)AS amount FROM hongbaoAction WHERE code=?");
		List<Object> params = new ArrayList<Object>();
		params.add(Constant.SENDHONGBAO);
		if(start!=null && end!=null){
			sql.append(" and createtime BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
}