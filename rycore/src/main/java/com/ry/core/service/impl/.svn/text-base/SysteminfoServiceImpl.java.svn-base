package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.PreferentialInfoDao;
import com.ry.core.dao.SysteminfoDao;
import com.ry.core.entity.PreferentialInfo;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.core.service.SysteminfoService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Service
public class SysteminfoServiceImpl implements SysteminfoService {

	@Resource
	SysteminfoDao systeminfoDao;
	
	@Resource
	PreferentialInfoDao preferentialInfoDao;
	
	@Override
	public void updateSysteminfo(Systeminfo systeminfo) {
		systeminfoDao.updateSysteminfo(systeminfo);
	}

	@Override
	public void addSysteminfo(Systeminfo systeminfo) {
		systeminfoDao.addSysteminfo(systeminfo);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.SysteminfoService#getPageList(com.ry.core.form.SysteminfoForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Systeminfo> getPageList(SysteminfoForm nf,Integer currentPage,Integer pageSize) {
		return systeminfoDao.getPageList(nf, currentPage, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.SysteminfoService#getByMemberIdAndReadState(java.lang.Integer, com.ry.core.entity.Systeminfo.ReadState)
	 */
	public List<Systeminfo> getByMemberIdAndReadState(Integer memberId,ReadState readState){
		return systeminfoDao.getByMemberIdAndReadState(memberId, readState);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.SysteminfoService#getById(java.lang.Integer)
	 */
	public Systeminfo getById(Integer id) throws Exception{
		return systeminfoDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.SysteminfoService#updateSysteminfoToReadById(java.lang.Integer)
	 */
	public Systeminfo updateSysteminfoToReadById(Integer id) throws Exception{
		Systeminfo info = systeminfoDao.getById(id);
		if(ReadState.READ != info.getReadState()){
			info.setReadState(ReadState.READ);
			systeminfoDao.updateSysteminfo(info);
		}
		return info;
	}
	
	@Override
	public void updateToReadByMemberId(Integer memberId,List<Type> types){
		systeminfoDao.updateToReadByMemberId(memberId, types);
	}
	
	@Override
	public Integer getMessage(Integer memberId){
		int count = 0;
		List<Systeminfo> infos = this.getByMemberIdAndReadState(memberId, ReadState.UNREAD);
		if(infos!=null && infos.size()>0)count = infos.size();
		List<PreferentialInfo> preferentialInfos = preferentialInfoDao.getBetweenCreateTime(DateUtil.addDays(-3), new Date());
		if(preferentialInfos!=null && preferentialInfos.size()>0)count += preferentialInfos.size();
		return count;
	}
}