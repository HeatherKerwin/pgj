package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PhonedetailDao;
import com.ry.core.entity.Phonedetail;

@Repository
public class PhonedetailDaoImpl extends BaseDao<Phonedetail, Integer> implements PhonedetailDao {

	@Override
	public void addPhonedetail(Phonedetail phonedetail) {

		save(phonedetail);
	}

	@Override
	public void updatePhonedetail(Phonedetail phonedetail) {
		
		update(phonedetail);
	}

	@Override
	public List<Phonedetail> getPhonedetail(Phonedetail phonedetail) {
		String hql = "from phonedetail where 1=1 ";
		List params = new ArrayList();
		if (phonedetail != null) {
			if (phonedetail.getMemberid() != null) {
				hql += " and memberid=? ";
				params.add(phonedetail.getMemberid());
			}
		}
		return getListByHQL(hql, params.toArray());
	}
	
	@Override
	public Long countByQudaoandDay(String qdp,String createDate){
		StringBuffer hql = new StringBuffer(" select count(*) from phonedetail where qudao = ?  and DATE_FORMAT(createDate,'%Y-%m-%d') = ? "); 
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(qdp);
		paramsList.add(createDate);
		return countByHql(hql.toString(), paramsList.toArray());
	}
	
	@Override
	public Long countByQudaoandMonth(String qdp,String createDate){
		StringBuffer hql = new StringBuffer(" select count(*) from phonedetail where qudao = ?  and DATE_FORMAT(createDate,'%Y-%m') = ? "); 
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(qdp);
		paramsList.add(createDate);
		return countByHql(hql.toString(), paramsList.toArray());
	}

}
