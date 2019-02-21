package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ThirdAuthDao;
import com.ry.core.entity.ThirdAuth;

@Repository
public class ThirdAuthDaoImpl extends BaseDao<ThirdAuth, Integer> implements ThirdAuthDao {

	@Override
	public List<ThirdAuth> getByAttr(Integer memberId, Integer type, String openId, String token) {
		StringBuffer hql = new StringBuffer("from third_auth t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (memberId != null) {
			hql.append(" and memberId=?");
			params.add(memberId);
		}
		if (type != null) {
			if (type == 1) {// 微信
				if (openId != null) {
					hql.append(" and wechatId=?");
					params.add(openId);
				}
				if (token != null) {
					hql.append(" and wechatToken=?");
					params.add(token);
				}
			} else if (type == 2) {// QQ
				if (openId != null) {
					hql.append(" and qqId=?");
					params.add(openId);
				}
				if (token != null) {
					hql.append(" and qqToken=?");
					params.add(token);
				}
			} else if (type == 3) {// 微博
				if (openId != null) {
					hql.append(" and sinaId=?");
					params.add(openId);
				}
				if (token != null) {
					hql.append(" and sinaToken=?");
					params.add(token);
				}
			} else if (type == 4){// 微信小程序
				if (openId != null) {
					hql.append(" and wxappletId=?");
					params.add(openId);
				}
			}
		}
		return getListByHQL(hql.toString(), params.toArray());
	}

	@Override
	public void saveOrUpdate(ThirdAuth m) {
		if (m != null && m.getId() != null)
			update(m);
		else
			save(m);
	}
}
