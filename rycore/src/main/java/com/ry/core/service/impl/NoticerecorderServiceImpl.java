package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.NoticerecordDao;
import com.ry.core.entity.Noticerecord;
import com.ry.core.service.NoticerecordService;

@Service
public class NoticerecorderServiceImpl implements NoticerecordService {

	@Resource
	NoticerecordDao noticerecordDao;
	
	@Override
	public List<Noticerecord> getList(Integer memberid, Date noticetime) {
		
		return noticerecordDao.getList(memberid, noticetime);
	}

	@Override
	public void addNoticerecord(Noticerecord noticerecord) {
		noticerecordDao.addNoticerecord(noticerecord);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.NoticerecordService#getByFkId(java.lang.Integer)
	 */
	public Noticerecord getByFkId(Integer fkId){
		List<Noticerecord> list = noticerecordDao.getByFkId(fkId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.NoticerecordService#getById(java.lang.Integer)
	 */
	public Noticerecord getById(Integer id){
		return noticerecordDao.getById(id);
	}
	
	public void delById(Integer id){
		noticerecordDao.delById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.NoticerecordService#getForRemindExpire(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getForRemindExpire(Date noticetime1, Date noticetime2) {
		return noticerecordDao.getForRemindExpire(noticetime1, noticetime2);
	}
	
	public void updateNoticerecord(Noticerecord n){
		noticerecordDao.updateNoticerecord(n);
	}
}