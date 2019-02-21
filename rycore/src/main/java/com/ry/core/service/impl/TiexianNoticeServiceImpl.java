package com.ry.core.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.NoticeAddDao;
import com.ry.core.dao.TiexianNoticeDao;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.service.TiexianNoticeService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;
@Service("tiexianNoticeService")
public class TiexianNoticeServiceImpl implements TiexianNoticeService {

	@Resource
	private TiexianNoticeDao tiexianNoticeDao;
	
	@Resource
	private NoticeAddDao noticeAddDao;
	
	@Override
	public void add(Notice notice) {
		tiexianNoticeDao.add(notice);
	}
	
	@Override
	public Notice findOne(Integer id) {
		Notice notice = tiexianNoticeDao.findNoticeById(id);
		return notice;
	}

	@Override
	public void update(Notice notice) {
		tiexianNoticeDao.updateNotice(notice);
	}

	@Override
	public List<Notice> findAll() {
		return (List<Notice>)tiexianNoticeDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#findByYearToken(java.lang.String)
	 */
	public List<Notice> findByYearToken(String yearToken) {
		return tiexianNoticeDao.findByYearToken(yearToken);
	}

	@Override
	public void deleteById(Integer id) {
		tiexianNoticeDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#findByCode(java.lang.String)
	 */
	public Notice findByCode(String code){
		return tiexianNoticeDao.findByCode(code);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#findByNowTime(java.lang.String)
	 */
	public Notice findByNowTime(String yearToken){
		List<Notice> list = tiexianNoticeDao.findByNowTime(new Date(),yearToken);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			Calendar rightNow = Calendar.getInstance();  
	        int now = rightNow.get(Calendar.DAY_OF_WEEK);
	        if(now==Calendar.SUNDAY || now==Calendar.SATURDAY){//周六周日
	        	return tiexianNoticeDao.findByCode("OFFDAY");//查询非工作日
	        }
		}
		return null;
	}
	
	public Notice findByContent(String yearToken){
		List<Notice> list = tiexianNoticeDao.findByContent(yearToken);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#findFestivalByNowTime(java.lang.String)
	 */
	public Notice findFestivalByNowTime(Date date){
		List<Notice> list = tiexianNoticeDao.findByNowTime(date,DateUtil.formart(date, "yyyy"));
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
	        return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.entity.Notice)
	 */
	public PageResults<Notice> getPageList(Integer index,Integer size,Notice notice){
		return tiexianNoticeDao.getPageList(index, size, notice);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#deleteGroupById(java.lang.Integer)
	 */
	public void deleteGroupById(Integer id){
		noticeAddDao.deleteByNoticeId(id);//根据节假日主键删除补班
		tiexianNoticeDao.deleteById(id);//删除节假日
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#saveAndNoticeAdd(com.ry.core.entity.Notice, java.util.List)
	 */
	public void saveAndNoticeAdd(Notice notice,List<NoticeAdd> noticeAdds) {
		tiexianNoticeDao.add(notice);
		if(noticeAdds!=null && noticeAdds.size()>0){
			for(NoticeAdd add:noticeAdds){
				add.setNoticeId(notice.getId());
				if(add.getId()!=null){
					noticeAddDao.updateModel(add);
				}else{
					noticeAddDao.saveModel(add);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#getFestivalByNowTime(java.util.Date)
	 */
	public Notice getFestivalByNowTime(Date nowTime){
		List<Notice> list = tiexianNoticeDao.getFestivalByNowTime(nowTime);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TiexianNoticeService#getNoticeByNowTime()
	 */
	public Notice getNoticeByNowTime(){
		Date date = new Date();
		List<Notice> list = tiexianNoticeDao.getFestivalByNowTime(date);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			Calendar rightNow = Calendar.getInstance();  
			rightNow.setTime(date);
	        int now = rightNow.get(Calendar.DAY_OF_WEEK);
	        if(now==Calendar.SUNDAY || now==Calendar.SATURDAY){//周六周日
	        	List<NoticeAdd> adds = noticeAddDao.getByNowTime(date);
	        	if(adds==null || adds.size()==0){//当前是周末
					return tiexianNoticeDao.findByCode("OFFDAY");//查询非工作日
				}
	        }
		}
		return null;
	}
}