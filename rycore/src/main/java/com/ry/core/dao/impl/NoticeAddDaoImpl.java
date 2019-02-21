package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.NoticeAddDao;
import com.ry.core.entity.NoticeAdd;
import com.ry.util.DateUtil;

@Repository
public class NoticeAddDaoImpl extends BaseDao<NoticeAdd, Integer> implements NoticeAddDao{

	public NoticeAdd getById(Integer id) {
		return get(id);
	}
	
	public void saveModel(NoticeAdd noticeAdd) {
		save(noticeAdd);
	}
	
	public void updateModel(NoticeAdd noticeAdd) {
		update(noticeAdd);
	}
	
	public void deleteById(Integer id) {
		delete(id);
	}
	
	public List<NoticeAdd> getByNoticeId(Integer noticeId) {
		StringBuffer hql = new StringBuffer("from notice_add add where add.noticeId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(noticeId);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	public void deleteByNoticeId(Integer noticeId) {
		StringBuffer hql = new StringBuffer("DELETE FROM notice_add add where add.noticeId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(noticeId);
		executeHql(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.NoticeAddDao#getByNowTime(java.util.Date)
	 */
	public List<NoticeAdd> getByNowTime(Date nowTime){
		StringBuffer hql = new StringBuffer("FROM notice_add add WHERE add.day=?");
		List<Object> params = new ArrayList<Object>();
		params.add(DateUtil.formart(nowTime, DateUtil.FORMART3));
		return getListByHQL(hql.toString(), params.toArray());
	}
}