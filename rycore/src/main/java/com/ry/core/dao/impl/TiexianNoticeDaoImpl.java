package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.TiexianNoticeDao;
import com.ry.core.entity.Notice;
import com.ry.util.page.PageResults;
@Repository
public class TiexianNoticeDaoImpl extends BaseDao<Notice, Integer> implements TiexianNoticeDao{

	@Override
	public void add(Notice notice) {
		if(notice!=null && notice.getId()!=null && notice.getId()>0){
			update(notice);
		}else{
			save(notice);
		}
	}

	@Override
	public Notice findNoticeById(Integer id) {
		return get(id);
	}

	@Override
	public void updateNotice(Notice notice) {
		saveOrUpdate(notice);
	}

	@Override
	public List<Notice> findAll() {
		String hql = "from notice";
		return getListByHQL(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#findByYearToken(java.lang.String)
	 */
	public List<Notice> findByYearToken(String yearToken) {
		String hql = "from notice where (yearToken IS NULL OR yearToken=?)";
		List<Object> param = new ArrayList<Object>();
		param.add(yearToken);
		return getListByHQL(hql, param.toArray());
	}

	@Override
	public void deleteById(Integer id) {
		delete(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#findByCode(java.lang.String)
	 */
	public Notice findByCode(String code) {
		List<Object> param = new ArrayList<Object>();
		param.add(code);
		List<Notice> notices = getListByHQL("from notice where code=?",param.toArray());
		if(notices != null && notices.size() > 0){
			return notices.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#findByNowTime(java.util.Date, java.lang.String)
	 */
	public List<Notice> findByNowTime(Date nowTime,String yearToken) {
		StringBuffer hql = new StringBuffer("from notice where startDate<=? and endDate>=? and (yearToken IS NULL OR yearToken=?)");
		List<Object> param = new ArrayList<Object>();
		param.add(nowTime);
		param.add(nowTime);
		param.add(yearToken);
		return getListByHQL(hql.toString(),param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#findByConcent(java.lang.String)
	 */
	public List<Notice> findByContent(String yearToken) {
		StringBuffer hql = new StringBuffer("from notice where  (yearToken IS NULL OR yearToken=?) ORDER BY id DESC");
		List<Object> param = new ArrayList<Object>();
		param.add(yearToken);
		return getListByHQL(hql.toString(),param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.entity.Notice)
	 */
	public PageResults<Notice> getPageList(Integer index,Integer size,Notice notice) {
		StringBuffer hql = new StringBuffer("from notice where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(notice!=null && StringUtils.hasText(notice.getCode())){
			hql.append(" and code = ?");
			params.add(notice.getCode());
		}
		if(notice!=null && StringUtils.hasText(notice.getName())){
			hql.append(" and name like ?");
			params.add("%"+ notice.getName() +"%");
		}
		hql.append(" order by startDate desc");
		return findPageByFetchedHql(hql.toString(), "select count(*)" + hql, index, size, params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TiexianNoticeDao#getFestivalByNowTime(java.util.Date)
	 */
	public List<Notice> getFestivalByNowTime(Date nowTime) {
		StringBuffer hql = new StringBuffer("FROM notice WHERE startDate<=? AND endDate>=? AND code='FESTIVAL'");
		List<Object> param = new ArrayList<Object>();
		param.add(nowTime);
		param.add(nowTime);
		return getListByHQL(hql.toString(),param.toArray());
	}
}