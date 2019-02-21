package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.HongbaoSendDao;
import com.ry.core.entity.HongbaoSend;

@Repository
public class HongbaoSendDaoImpl extends BaseDao<HongbaoSend, Integer> implements HongbaoSendDao {

	@Override
	public HongbaoSend getHongbaoSend(Integer id) {

		return get(id);
	}

	@Override
	public List<HongbaoSend> getList(Integer memberid) {
		String hql = "from hongbaoSend where memberid=? order by id desc ";
		
		return getListByHQL(hql, new Object[]{memberid});
	}

	@Override
	public List<HongbaoSend> getList(Integer memberid, Integer hid) {
		String hql = "from hongbaoSend where memberid=? and hid=? order by id desc ";
		return getListByHQL(hql, new Object[]{memberid,hid});	
	}

	@Override
	public void addHongbaoSend(HongbaoSend hongbaoSend) {
		
		save(hongbaoSend);
	}	
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.HongbaoSendDao#getCountByStartAndEnd(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Integer hid,Date start,Date end) {
		StringBuffer sql = new StringBuffer("SELECT *,COUNT(id)AS amount FROM (SELECT * FROM (SELECT *, CONCAT(memberid,DATE_FORMAT(createtime,'%Y-%m-%d')) AS _f FROM hongbaoSend WHERE 1=1");
		List<Object> params = new ArrayList<Object>();
		if(hid!=null){
			sql.append(" and hid=?");
			params.add(hid);
		}
		if(start!=null && end!=null){
			sql.append(" and createtime BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		sql.append(") _t GROUP BY _f)result");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
}