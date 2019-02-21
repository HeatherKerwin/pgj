/**
* @Title: ActivecountServiceImpl.java
* @Package com.ry.core.service.impl
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ActivecountDao;
import com.ry.core.entity.Activecount;
import com.ry.core.form.ActivecountForm;
import com.ry.core.service.ActivecountService;

/**
 * @ClassName: ActivecountServiceImpl
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
@Service
public class ActivecountServiceImpl implements ActivecountService{
	@Resource
	ActivecountDao activecountDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActivecountService#getAllCount()
	 */
	@Override
	public Long getAllCount() {
		return activecountDao.getAllCount();
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ActivecountService#getCountByRpAndTime(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public Long getCountByRpAndTime(String rp, Long beginTime, Long endTime) {
		return activecountDao.getCountByRpAndTime(rp, beginTime, endTime);
	}

	@Override
	public void addActivecount(Activecount activecount) {
		activecountDao.addActivecount(activecount);
	}

	@Override
	public Long countActive(Long begintimed, Long endtimed, Integer memberid) {		
		return activecountDao.countActive(begintimed, endtimed, memberid);
	}

	@Override
	public Long getActivecount(String begintimed, String endtimed, Integer memberid) {
		
		return activecountDao.getCountActive(begintimed, endtimed, memberid);
	}

	@Override
	public List<Activecount> getList(ActivecountForm activecountForm) {
		
		return activecountDao.getList(activecountForm);
	}

}
