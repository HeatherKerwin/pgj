package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.NoticerecordDao;
import com.ry.core.entity.Noticerecord;

@Repository
public class NoticerecordDaoImpl extends BaseDao<Noticerecord, Integer> implements NoticerecordDao {

	@Override
	public List<Noticerecord> getList(Integer memberid, Date noticetime) {
		String hql = "from noticerecord where memberid like ? and noticetime > ?";
		List<Noticerecord> noticerecords =  getListByHQL(hql, new Object[]{memberid, noticetime});
		return noticerecords;
	}

	@Override
	public void addNoticerecord(Noticerecord noticerecord) {
		if(noticerecord!=null && noticerecord.getId()!=null){
			update(noticerecord);
		}else{
			save(noticerecord);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.NoticerecordDao#getByFkId(java.lang.Integer)
	 */
	public List<Noticerecord> getByFkId(Integer fkId){
		StringBuffer hql = new StringBuffer("from noticerecord where fkId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(fkId);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.NoticerecordDao#getById(java.lang.Integer)
	 */
	public Noticerecord getById(Integer id){
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.NoticerecordDao#delById(java.lang.Integer)
	 */
	public void delById(Integer id){
		delete(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.NoticerecordDao#getForRemindExpire(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getForRemindExpire(Date noticetime1, Date noticetime2) {
		StringBuffer sql = new StringBuffer("select nr.id, nr.memberid, nr.allprice, nr.expiredate, nr.noticetime, nr.noticedesc,nr.fkId from noticerecord nr where nr.noticetime between ? and ?");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(noticetime1);
		paramsList.add(noticetime2);
		return getListMapBySQL(sql.toString(), paramsList.toArray());
	}
	
	public void updateNoticerecord(Noticerecord n){
		update(n);
	}
}